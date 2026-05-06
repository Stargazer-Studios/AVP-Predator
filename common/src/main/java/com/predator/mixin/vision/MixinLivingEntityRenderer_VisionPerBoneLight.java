package com.predator.mixin.vision;

import com.blib.api.client.posteffect.v1.BLibPerBoneLightContext;
import com.blib.api.client.posteffect.v1.BLibPostEffectFramework;
import com.mojang.blaze3d.vertex.PoseStack;
import com.predator.client.vision.PredatorVisionAccessor;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * Two-part hook into {@code LivingEntityRenderer.render}, gated on any non-regular vision mode being active. The
 * current vision mode (read from the equipped predator helmet) supplies the visible/hot tags that drive how each entity
 * is processed.
 * <p>
 * <b>Visible entities</b> (those in the current mode's
 * {@link com.predator.common.gameplay.component.PredatorVisionMode#visibleTag()}): a {@link BLibPerBoneLightContext}
 * frame is pushed so BLib's {@code MixinModelPart_PerBoneLight} samples world block-light at each bone's
 * pose-stack-derived position, optionally floored at 14 for {@code HOT}-tag entities.
 * <p>
 * <b>Background entities</b> (rendered, but not in the visible tag):
 * {@link BLibPostEffectFramework#pushBackgroundEntity()} is called so the entity-shader patcher writes a flag into
 * {@code entityMask.g}; the predator vision shader reads that flag and routes those pixels through its world-coloring
 * branch (dark blue thermal / dark green EM, with texture detail intact). The entity still draws in full so it blends
 * with the world rather than disappearing.
 * <p>
 * <b>Why this mixin force-flushes the buffer source around background entities:</b> {@code LivingEntityRenderer.render}
 * only queues vertices into the level's {@code MultiBufferSource} — the actual GL draw and {@code ShaderInstance.apply}
 * (which is when {@code BlibBackgroundEntity} gets pushed to the GPU) doesn't happen until the level renderer flushes
 * the whole entity batch later. Without forcing flushes at the visible↔background boundary, every entity in the same
 * batch ends up sampling whatever flag state happened to be active at flush time — usually whichever entity rendered
 * last — so the color of any given entity drifts based on render order, which depends on camera angle and distance.
 * Calling {@code BufferSource.endBatch()} at the boundary ensures each entity's vertices flush under the uniform value
 * that was set when its render was called.
 */
@Mixin(LivingEntityRenderer.class)
public abstract class MixinLivingEntityRenderer_VisionPerBoneLight {

    @Inject(method = "render", at = @At("HEAD"))
    private void predator$visionPushContext(
        LivingEntity entity,
        float entityYaw,
        float partialTicks,
        PoseStack poseStack,
        MultiBufferSource buffer,
        int packedLight,
        CallbackInfo ci
    ) {
        if (BLibPostEffectFramework.isShaderModActive()) {
            return;
        }

        var mode = PredatorVisionAccessor.currentVisionMode();
        var visibleTag = mode.visibleTag();

        if (visibleTag == null) {
            return;
        }

        if (entity.getType().is(visibleTag)) {
            // Visible entity: per-bone block-light floor so the body reads bright against a dim backdrop.
            var mc = Minecraft.getInstance();
            var camera = mc.gameRenderer.getMainCamera();

            if (mc.level == null || !camera.isInitialized()) {
                return;
            }

            var hotTag = mode.hotTag();
            var blockLightFloor = (hotTag != null && entity.getType().is(hotTag)) ? 14 : 7;

            BLibPerBoneLightContext.push(mc.level, camera.getPosition(), blockLightFloor);
        } else {
            // Background entity: flush whatever's been queued under the current state (= flag 0) so those vertices
            // draw correctly, then flip the flag and let this entity queue under flag=1.
            if (buffer instanceof MultiBufferSource.BufferSource bufferSource) {
                bufferSource.endBatch();
            }

            BLibPostEffectFramework.pushBackgroundEntity();
        }
    }

    @Inject(method = "render", at = @At("RETURN"))
    private void predator$visionPopContext(
        LivingEntity entity,
        float entityYaw,
        float partialTicks,
        PoseStack poseStack,
        MultiBufferSource buffer,
        int packedLight,
        CallbackInfo ci
    ) {
        if (BLibPostEffectFramework.isShaderModActive()) {
            return;
        }

        var mode = PredatorVisionAccessor.currentVisionMode();
        var visibleTag = mode.visibleTag();

        if (visibleTag == null) {
            return;
        }

        if (entity.getType().is(visibleTag)) {
            BLibPerBoneLightContext.pop();
        } else {
            // Flush this background entity's queued vertices NOW so they draw with flag=1 (the current state),
            // then pop the flag back to 0 for any subsequent entities.
            if (buffer instanceof MultiBufferSource.BufferSource bufferSource) {
                bufferSource.endBatch();
            }

            BLibPostEffectFramework.popBackgroundEntity();
        }
    }
}
