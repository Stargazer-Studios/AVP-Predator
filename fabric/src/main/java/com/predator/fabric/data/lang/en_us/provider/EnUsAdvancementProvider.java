package com.predator.fabric.data.lang.en_us.provider;

import com.blib.api.common.advancement.v1.BLibAdvancementAccess;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;

import java.util.function.Consumer;

public class EnUsAdvancementProvider {

    public static final Consumer<FabricLanguageProvider.TranslationBuilder> CONSUMER = builder -> {};

    private static void addAdvancement(
        FabricLanguageProvider.TranslationBuilder builder,
        BLibAdvancementAccess advancementAccess,
        String title,
        String description
    ) {
        builder.add(advancementAccess.getTitleTranslationKey(), title);
        builder.add(advancementAccess.getDescriptionTranslationKey(), description);
    }
}
