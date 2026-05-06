package com.predator.mixin.vision;

import com.blib.api.client.posteffect.v1.BLibPostEffectFramework;
import com.mojang.blaze3d.vertex.PoseStack;
import com.predator.client.vision.PredatorVisionAccessor;
import com.predator.common.gameplay.component.PredatorVisionMode;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderDispatcher;
import net.minecraft.world.level.block.entity.BlockEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * Background-flag push for block (tile) entities — chests, beacons, signs, banners, beds, item frames in 1.21
 * (technically a block entity since the framework rework, though item frames are still {@code Entity} in this version),
 * conduits, end portals, etc. They render via {@code BlockEntityRenderDispatcher} which is parallel to (not under)
 * {@code EntityRenderDispatcher}, so neither the entity nor the living-entity mixin fires for them. Many BERs use
 * {@code rendertype_entity_*} render types under the hood (chest sheet, sign sheet, banner sheet), which means BLib's
 * patcher classifies their fragments as entity (mask.r = 1.0) — and without a background-flag push, the vision shader
 * would render them as foreground entities (warm glow).
 * <p>
 * Same forced-flush dance as the other vision mixins so each block entity's vertices flush under the right uniform
 * value.
 */
@Mixin(BlockEntityRenderDispatcher.class)
public abstract class MixinBlockEntityRenderDispatcher_VisionBackground {

    @Inject(method = "render", at = @At("HEAD"))
    private void predator$pushBackgroundForBlockEntity(
        BlockEntity blockEntity,
        float partialTicks,
        PoseStack poseStack,
        MultiBufferSource buffer,
        CallbackInfo ci
    ) {
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
    private void predator$popBackgroundForBlockEntity(
        BlockEntity blockEntity,
        float partialTicks,
        PoseStack poseStack,
        MultiBufferSource buffer,
        CallbackInfo ci
    ) {
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
