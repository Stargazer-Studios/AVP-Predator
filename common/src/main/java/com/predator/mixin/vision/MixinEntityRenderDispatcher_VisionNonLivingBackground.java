package com.predator.mixin.vision;

import com.blib.api.client.posteffect.v1.BLibPostEffectFramework;
import com.mojang.blaze3d.vertex.PoseStack;
import com.predator.client.vision.PredatorVisionClassification;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Background-flag push for non-LivingEntity entities (item entities, projectiles, minecarts, boats, paintings, item
 * frames, experience orbs, lightning bolts, etc.). They render via {@code EntityRenderer} subclasses that aren't
 * {@code LivingEntityRenderer}, so {@code MixinLivingEntityRenderer_VisionPerBoneLight} doesn't fire for them. Without
 * this mixin their pixels arrive in the gbuffer with {@code mask.g = 0} and the vision shader routes them through the
 * foreground-entity branch — making dropped items glow warm in thermal, etc.
 * <p>
 * LivingEntities are skipped here so the per-bone-light/visibility-tag logic in
 * {@code MixinLivingEntityRenderer_VisionPerBoneLight} can run with the right semantics. RETURN pops based on what HEAD
 * actually pushed (recorded on a per-render stack) rather than recomputing — recomputation is unsafe because the
 * transition state can change mid-render and would produce mismatched pop counts that leak BLib lane depth.
 */
@Mixin(EntityRenderDispatcher.class)
public abstract class MixinEntityRenderDispatcher_VisionNonLivingBackground {

    private static final ThreadLocal<Deque<int[]>> FRAME_STACK = ThreadLocal.withInitial(ArrayDeque::new);

    @Inject(method = "render", at = @At("HEAD"))
    private void predator$pushBackgroundForNonLiving(
        Entity entity,
        double x,
        double y,
        double z,
        float rotationYaw,
        float partialTicks,
        PoseStack poseStack,
        MultiBufferSource buffer,
        int packedLight,
        CallbackInfo ci
    ) {
        if (entity instanceof LivingEntity) {
            // LivingEntities go through the LivingEntityRenderer mixin instead. Don't push a frame here so RETURN
            // also bails for them — keeps stack push/pop balanced.
            return;
        }

        var frame = new int[2];
        FRAME_STACK.get().push(frame);

        if (BLibPostEffectFramework.isShaderModActive()) {
            return;
        }

        var classification = PredatorVisionClassification.nonLivingClassification();
        var pushLaneA = classification.isBackgroundUnderOld();
        var pushLaneB = classification.isBackgroundUnderNew();

        if (!pushLaneA && !pushLaneB) {
            return;
        }

        if (buffer instanceof MultiBufferSource.BufferSource bufferSource) {
            bufferSource.endBatch();
        }

        if (pushLaneA) {
            BLibPostEffectFramework.pushBackgroundEntity();
            frame[0] = 1;
        }

        if (pushLaneB) {
            BLibPostEffectFramework.pushBackgroundEntityB();
            frame[1] = 1;
        }
    }

    @Inject(method = "render", at = @At("RETURN"))
    private void predator$popBackgroundForNonLiving(
        Entity entity,
        double x,
        double y,
        double z,
        float rotationYaw,
        float partialTicks,
        PoseStack poseStack,
        MultiBufferSource buffer,
        int packedLight,
        CallbackInfo ci
    ) {
        if (entity instanceof LivingEntity) {
            return;
        }

        var stack = FRAME_STACK.get();

        if (stack.isEmpty()) {
            return;
        }

        var frame = stack.pop();
        var poppedLaneA = frame[0] == 1;
        var poppedLaneB = frame[1] == 1;

        if (!poppedLaneA && !poppedLaneB) {
            return;
        }

        if (buffer instanceof MultiBufferSource.BufferSource bufferSource) {
            bufferSource.endBatch();
        }

        if (poppedLaneB) {
            BLibPostEffectFramework.popBackgroundEntityB();
        }

        if (poppedLaneA) {
            BLibPostEffectFramework.popBackgroundEntity();
        }
    }
}
