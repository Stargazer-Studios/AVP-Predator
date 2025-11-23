package com.predator.fabric.data.lang.en_us.provider;

import com.predator.common.registry.tag.PredatorItemTags;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;

import java.util.function.Consumer;

public class EnUsItemTagProvider {

    public static final Consumer<FabricLanguageProvider.TranslationBuilder> CONSUMER = builder -> {
        builder.add(PredatorItemTags.HOSTILE_WEAPONS, "Hostile Weapons");
        builder.add(PredatorItemTags.JUNGLE_PREDATOR_ARMOR, "Jungle Predator Armor");
        builder.add(PredatorItemTags.PREDATOR_ARMORS, "Predator Armors");
    };
}
