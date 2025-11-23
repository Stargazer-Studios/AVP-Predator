package com.predator.client.render.block;

import com.predator.common.gameplay.block.entity.TripMineBlockEntity;
import mod.azure.azurelib.common.render.block.AzBlockEntityRenderer;
import mod.azure.azurelib.common.render.block.AzBlockEntityRendererConfig;
import mod.azure.azurelib.common.render.layer.AzAutoGlowingLayer;
import net.minecraft.resources.ResourceLocation;

import com.predator.PredatorResources;

public class TripMineRenderer extends AzBlockEntityRenderer<TripMineBlockEntity> {

    public static final String NAME = "trip_mine";

    private static final ResourceLocation GEO = PredatorResources.blockGeoModelLocation(NAME);

    private static final ResourceLocation TEX = PredatorResources.blockTextureLocation(NAME);

    public TripMineRenderer() {
        super(
            AzBlockEntityRendererConfig.<TripMineBlockEntity>builder(GEO, TEX)
                .addRenderLayer(new AzAutoGlowingLayer<>())
                .build()
        );
    }
}
