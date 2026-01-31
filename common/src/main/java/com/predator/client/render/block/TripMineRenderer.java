package com.predator.client.render.block;

import com.blib.api.client.render.v1.block.AzBlockEntityRenderer;
import com.blib.api.client.render.v1.block.AzBlockEntityRendererConfig;
import com.blib.api.client.render.v1.layer.AzAutoGlowingLayer;
import com.predator.PredatorResources;
import com.predator.common.gameplay.block.entity.TripMineBlockEntity;
import net.minecraft.resources.ResourceLocation;

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
