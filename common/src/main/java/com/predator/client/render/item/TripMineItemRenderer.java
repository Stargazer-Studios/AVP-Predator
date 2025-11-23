package com.predator.client.render.item;

import mod.azure.azurelib.common.render.item.AzItemRenderer;
import mod.azure.azurelib.common.render.item.AzItemRendererConfig;
import net.minecraft.resources.ResourceLocation;

import com.predator.PredatorResources;

public class TripMineItemRenderer extends AzItemRenderer {

    public static final String NAME = "trip_mine";

    private static final ResourceLocation GEO = PredatorResources.blockGeoModelLocation(NAME);

    private static final ResourceLocation TEX = PredatorResources.blockTextureLocation(NAME);

    public TripMineItemRenderer() {
        super(AzItemRendererConfig.builder(GEO, TEX).useNewOffset(true).build());
    }
}
