package com.predator.fabric.data.lang.en_us.provider;

import com.predator.common.registry.tag.PredatorEntityTypeTags;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;

import java.util.function.Consumer;

public class EnUsEntityTypeTagProvider {

    public static final Consumer<FabricLanguageProvider.TranslationBuilder> CONSUMER = builder -> {
        builder.add(PredatorEntityTypeTags.PREDATORS, "Predators");
    };
}
