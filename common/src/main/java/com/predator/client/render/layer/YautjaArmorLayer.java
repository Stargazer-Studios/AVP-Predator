package com.predator.client.render.layer;

import com.blib.azurelib.common.model.AzBone;
import com.blib.azurelib.common.render.AzRendererPipelineContext;
import com.blib.azurelib.common.render.layer.AzRenderLayer;
import com.predator.PredatorResources;
import com.predator.common.gameplay.entity.living.yautja.Yautja;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;

import java.util.UUID;

public class YautjaArmorLayer<T extends Yautja> implements AzRenderLayer<UUID, T> {

    private static final ResourceLocation TEXTURE = PredatorResources.entityTextureLocation("yautja_armor");

    private static final int FALLBACK_COLOR = -1;

    @Override
    public void preRender(AzRendererPipelineContext<UUID, T> context) {}

    @Override
    public void render(AzRendererPipelineContext<UUID, T> context) {
        var localPlayer = Minecraft.getInstance().player;

        if (localPlayer == null) {
            return;
        }

        var animatable = context.animatable();
        var renderPipeline = context.rendererPipeline();

        var isInvisible = animatable.isInvisible();
        var renderType = isInvisible
            ? RenderType.entityTranslucentCull(TEXTURE)
            : RenderType.entityCutout(TEXTURE);
        var vertexConsumer = context.multiBufferSource().getBuffer(renderType);
        var alphaValue = animatable.isInvisibleTo(localPlayer) ? 0 : 0.38;
        int color;

        if (isInvisible) {
            var alpha = (int) (alphaValue * 0xFF) << 24;
            color = (context.renderColor() & 0xFFFFFF) | alpha;
        } else {
            color = FALLBACK_COLOR;
        }

        context.setRenderColor(color);
        context.setVertexConsumer(vertexConsumer);

        renderPipeline.reRender(context);
    }

    @Override
    public void renderForBone(AzRendererPipelineContext<UUID, T> context, AzBone bone) {}
}
