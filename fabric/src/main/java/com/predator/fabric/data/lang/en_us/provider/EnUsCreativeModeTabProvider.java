package com.predator.fabric.data.lang.en_us.provider;

import com.predator.common.registry.key.PredatorCreativeModeTabKeys;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;

import java.util.function.Consumer;

public class EnUsCreativeModeTabProvider {

    public static final Consumer<FabricLanguageProvider.TranslationBuilder> CONSUMER = builder -> {
        builder.add(PredatorCreativeModeTabKeys.BLOCKS_KEY, "Blocks (AVP: Predator)");
        builder.add(PredatorCreativeModeTabKeys.COMBAT_KEY, "Combat (AVP: Predator)");
        builder.add(PredatorCreativeModeTabKeys.INGREDIENTS_KEY, "Ingredients (AVP: Predator)");
        builder.add(PredatorCreativeModeTabKeys.SPAWN_EGGS_KEY, "Spawn Eggs (AVP: Predator)");
        builder.add(PredatorCreativeModeTabKeys.TOOLS_AND_UTILITIES_KEY, "Tools & Utilities (AVP: Predator)");
    };
}
