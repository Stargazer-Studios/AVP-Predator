package com.predator.fabric.data.lang.en_us.provider;

import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;

import java.util.function.Consumer;

import com.predator.common.registry.key.PredatorCreativeModeTabKeys;

public class EnUsCreativeModeTabProvider {

    public static final Consumer<FabricLanguageProvider.TranslationBuilder> CONSUMER = builder -> {
        builder.add(PredatorCreativeModeTabKeys.COMBAT_KEY, "Predator Combat");
        builder.add(PredatorCreativeModeTabKeys.INGREDIENTS_KEY, "Predator Ingredients");
        builder.add(PredatorCreativeModeTabKeys.SPAWN_EGGS_KEY, "Predator Spawn Eggs");
        builder.add(PredatorCreativeModeTabKeys.TOOLS_AND_UTILITIES_KEY, "Predator Tools & Utilities");
    };
}
