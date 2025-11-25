package com.predator.client.render.item;

import com.predator.PredatorResources;
import mod.azure.azurelib.common.render.item.AzItemRenderer;
import mod.azure.azurelib.common.render.item.AzItemRendererConfig;
import net.minecraft.resources.ResourceLocation;

public class TripMineItemRenderer extends AzItemRenderer {

    public static final String NAME = "trip_mine";

    private static final ResourceLocation GEO = PredatorResources.blockGeoModelLocation(NAME);

    private static final ResourceLocation TEX = PredatorResources.blockTextureLocation(NAME);

    public TripMineItemRenderer() {
        super(AzItemRendererConfig.builder(GEO, TEX).useNewOffset(true).build());
    }
}
