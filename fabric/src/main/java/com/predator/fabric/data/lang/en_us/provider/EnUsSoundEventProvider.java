package com.predator.fabric.data.lang.en_us.provider;

import com.predator.common.registry.init.PredatorSoundEvents;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.sounds.SoundEvent;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class EnUsSoundEventProvider {

    public static final Consumer<FabricLanguageProvider.TranslationBuilder> CONSUMER = builder -> {
        addSound(builder, PredatorSoundEvents.ITEM_ARMOR_EQUIP_VERITANIUM, "Veritanium armor clanks");

        addSound(builder, PredatorSoundEvents.JUKEBOX_SOUNDS_PREDATOR_MUSIC_1, "Hunter plays");
    };

    private static void addSound(
        FabricLanguageProvider.TranslationBuilder translationBuilder,
        Supplier<SoundEvent> soundEventSupplier,
        String value
    ) {
        addSound(translationBuilder, soundEventSupplier.get(), value);
    }

    private static void addSound(FabricLanguageProvider.TranslationBuilder translationBuilder, SoundEvent soundEvent, String value) {
        translationBuilder.add("subtitles." + soundEvent.getLocation().getPath(), value);
    }
}
