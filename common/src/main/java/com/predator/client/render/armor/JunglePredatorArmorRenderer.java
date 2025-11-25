package com.predator.client.render.armor;

import com.predator.PredatorResources;
import mod.azure.azurelib.common.render.armor.AzArmorRenderer;
import mod.azure.azurelib.common.render.armor.AzArmorRendererConfig;
import net.minecraft.resources.ResourceLocation;

public class JunglePredatorArmorRenderer extends AzArmorRenderer {

    private static final String NAME = "jungle_predator";

    private static final ResourceLocation MODEL = PredatorResources.armorGeoModelLocation(NAME);

    private static final ResourceLocation TEXTURE = PredatorResources.armorTextureLocation(NAME);

    public JunglePredatorArmorRenderer() {
        super(AzArmorRendererConfig.builder(MODEL, TEXTURE).build());
    }
}
