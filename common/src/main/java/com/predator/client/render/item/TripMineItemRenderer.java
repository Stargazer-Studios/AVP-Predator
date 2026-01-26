package com.predator.client.render.item;

import com.blib.azurelib.common.render.item.AzItemRenderer;
import com.blib.azurelib.common.render.item.AzItemRendererConfig;
import com.predator.PredatorResources;
import net.minecraft.resources.ResourceLocation;

public class TripMineItemRenderer extends AzItemRenderer {

    public static final String NAME = "trip_mine";

    private static final ResourceLocation MODEL = PredatorResources.blockGeoModelLocation(NAME);

    private static final ResourceLocation TEXTURE = PredatorResources.blockTextureLocation(NAME);

    public TripMineItemRenderer() {
        super(AzItemRendererConfig.builder(MODEL, TEXTURE).useNewOffset(true).build());
    }
}
