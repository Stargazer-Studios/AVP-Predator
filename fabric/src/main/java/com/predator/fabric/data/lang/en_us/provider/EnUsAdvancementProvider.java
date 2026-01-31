package com.predator.fabric.data.lang.en_us.provider;

import com.blib.api.common.advancement.v1.BLibAdvancement;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;

import java.util.function.Consumer;

public class EnUsAdvancementProvider {

    public static final Consumer<FabricLanguageProvider.TranslationBuilder> CONSUMER = builder -> {};

    private static void addAdvancement(
        FabricLanguageProvider.TranslationBuilder builder,
        BLibAdvancement advancementAccess,
        String title,
        String description
    ) {
        builder.add(advancementAccess.titleTranslationKey(), title);
        builder.add(advancementAccess.descriptionTranslationKey(), description);
    }
}
