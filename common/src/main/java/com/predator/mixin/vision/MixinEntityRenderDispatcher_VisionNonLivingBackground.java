package com.predator.mixin.vision;

import com.blib.api.client.posteffect.v1.BLibPostEffectFramework;
import com.mojang.blaze3d.vertex.PoseStack;
import com.predator.client.vision.PredatorVisionAccessor;
import com.predator.common.gameplay.component.PredatorVisionMode;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * Background-flag push for non-LivingEntity entities (item entities, projectiles, minecarts, boats, paintings, item
 * frames, experience orbs, lightning bolts, etc.). They render via {@code EntityRenderer} subclasses that aren't
 * {@code LivingEntityRenderer}, so {@code MixinLivingEntityRenderer_VisionPerBoneLight} doesn't fire for them. Without
 * this mixin their pixels arrive in the gbuffer with {@code mask.g = 0} and the vision shader routes them through the
 * foreground-entity branch — making dropped items glow warm in thermal, etc.
 * <p>
 * LivingEntities are skipped here so the existing per-bone-light/visibility-tag logic in
 * {@code MixinLivingEntityRenderer_VisionPerBoneLight} can run with the right semantics.
 * <p>
 * Same forced-flush dance as the LivingEntity mixin: vanilla queues vertices into the level's buffer source and doesn't
 * draw until much later, so we have to flush at the visible↔background boundaries to keep each entity drawing under the
 * right uniform value.
 */
@Mixin(EntityRenderDispatcher.class)
public abstract class MixinEntityRenderDispatcher_VisionNonLivingBackground {

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
            return;
        }

        if (BLibPostEffectFramework.isShaderModActive()) {
            return;
        }

        if (PredatorVisionAccessor.currentVisionMode() == PredatorVisionMode.REGULAR) {
            return;
        }

        if (buffer instanceof MultiBufferSource.BufferSource bufferSource) {
            bufferSource.endBatch();
        }

        BLibPostEffectFramework.pushBackgroundEntity();
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

        if (BLibPostEffectFramework.isShaderModActive()) {
            return;
        }

        if (PredatorVisionAccessor.currentVisionMode() == PredatorVisionMode.REGULAR) {
            return;
        }

        if (buffer instanceof MultiBufferSource.BufferSource bufferSource) {
            bufferSource.endBatch();
        }

        BLibPostEffectFramework.popBackgroundEntity();
    }
}
