package com.predator.client.render.block;

import com.blib.azurelib.common.render.block.AzBlockEntityRenderer;
import com.blib.azurelib.common.render.block.AzBlockEntityRendererConfig;
import com.blib.azurelib.common.render.layer.AzAutoGlowingLayer;
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
