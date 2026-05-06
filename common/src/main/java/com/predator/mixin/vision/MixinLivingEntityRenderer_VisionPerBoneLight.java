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
 * Two-part hook into {@code LivingEntityRenderer.render}, both gated on any non-regular vision mode being active. The
 * current vision mode (read from the equipped predator helmet) supplies the visible/hot tags that drive both filtering
 * and per-bone lighting.
 * <p>
 * <b>Tag-based visibility filter.</b> If the entity's type isn't in the current mode's
 * {@link com.predator.common.gameplay.component.PredatorVisionMode#visibleTag()}, the render call is cancelled — the
 * entity isn't drawn into the gbuffer, so its pixels keep whatever the world geometry behind them wrote and the entity
 * reads as "invisible" in that mode. Each mode's visible tag is empty by default; modders/datapacks declare which mob
 * types should be detectable per mode.
 * <p>
 * <b>Per-bone lighting context.</b> For visible entities, pushes a {@link BLibPerBoneLightContext} frame so BLib's
 * {@code MixinModelPart_PerBoneLight} can sample world block-light at each bone's pose-stack-derived position. Popped
 * on return. When no vision is active, both hooks no-op and vanilla render is untouched.
 */
@Mixin(LivingEntityRenderer.class)
public abstract class MixinLivingEntityRenderer_VisionPerBoneLight {

    @Inject(method = "render", at = @At("HEAD"), cancellable = true)
    private void predator$visionGateAndContextPush(
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

        // Tag-gate: skip rendering entities not in this mode's VISIBLE set so their pixels show through to whatever
        // values the world geometry behind them wrote. Visually, the entity is invisible in this vision mode.
        if (!entity.getType().is(visibleTag)) {
            ci.cancel();
            return;
        }

        var mc = Minecraft.getInstance();
        var camera = mc.gameRenderer.getMainCamera();

        if (mc.level == null || !camera.isInitialized()) {
            return;
        }

        // Block-light floor for the per-bone packedLight mixin. Vanilla MC light coords are 0-15:
        // - HOT entities (THERMAL_HOT etc.): floor = 14 (fully lit in this mode).
        // - Regular visible entities: floor = 7 (mid-warm — visible against a dim/cold backdrop).
        var hotTag = mode.hotTag();
        var blockLightFloor = (hotTag != null && entity.getType().is(hotTag)) ? 14 : 7;

        BLibPerBoneLightContext.push(mc.level, camera.getPosition(), blockLightFloor);
    }

    @Inject(method = "render", at = @At("RETURN"))
    private void predator$popPerBoneLightContext(
        LivingEntity entity,
        float entityYaw,
        float partialTicks,
        PoseStack poseStack,
        MultiBufferSource buffer,
        int packedLight,
        CallbackInfo ci
    ) {
        BLibPerBoneLightContext.pop();
    }
}
