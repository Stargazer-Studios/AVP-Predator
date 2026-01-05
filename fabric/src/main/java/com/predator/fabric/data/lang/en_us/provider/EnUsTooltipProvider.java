package com.predator.fabric.data.lang.en_us.provider;

import com.predator.common.data.PredatorTooltipTranslationKeys;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import org.jetbrains.annotations.ApiStatus;

import java.util.function.Consumer;

// TODO: Move to alien module.
@ApiStatus.Internal
public final class EnUsTooltipProvider {

    public static final Consumer<FabricLanguageProvider.TranslationBuilder> CONSUMER = builder -> {
        builder.add(PredatorTooltipTranslationKeys.EFFECT_PREVENTS_FACEHUGGING, "Prevents Facehugging");
    };
}
