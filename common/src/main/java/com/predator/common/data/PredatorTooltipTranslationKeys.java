package com.predator.common.data;

import com.predator.Predator;
import org.jetbrains.annotations.NotNull;

public class PredatorTooltipTranslationKeys {

    public static final String EFFECT_PREVENTS_FACEHUGGING = create("prevents_facehugging");

    private static @NotNull String create(String name) {
        return "tooltip." + Predator.MOD.id() + "." + name;
    }

    private PredatorTooltipTranslationKeys() {
        throw new UnsupportedOperationException();
    }
}
