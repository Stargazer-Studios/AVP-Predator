package com.predator.common.registry.init;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.sounds.SoundEvent;

import com.predator.PredatorResources;
import com.avp.common.registry.AVPDeferredHolder;
import com.avp.service.Services;

public class PredatorSoundEvents {

    public static final AVPDeferredHolder<SoundEvent> ITEM_ARMOR_EQUIP_VERITANIUM = register("item.armor.equip_veritanium");

    public static final AVPDeferredHolder<SoundEvent> JUKEBOX_SOUNDS_PREDATOR_MUSIC_1 = register("jukebox_sounds.predator_music_1");

    private static AVPDeferredHolder<SoundEvent> register(String id) {
        return Services.REGISTRY.register(
            BuiltInRegistries.SOUND_EVENT,
            PredatorResources.location(id),
            () -> SoundEvent.createVariableRangeEvent(PredatorResources.location(id))
        );
    }

    public static void initialize() {}
}
