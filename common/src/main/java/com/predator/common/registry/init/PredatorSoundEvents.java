package com.predator.common.registry.init;

import com.blib.BLibHolder;
import com.blib.BLibRegistry;
import com.predator.Predator;
import com.predator.PredatorResources;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.sounds.SoundEvent;

public class PredatorSoundEvents {

    private static final BLibRegistry<SoundEvent> REGISTRY = Predator.MOD.createRegistry(BuiltInRegistries.SOUND_EVENT);

    public static final BLibHolder<SoundEvent> ITEM_ARMOR_EQUIP_VERITANIUM = create("item.armor.equip_veritanium");

    public static final BLibHolder<SoundEvent> JUKEBOX_SOUNDS_PREDATOR_MUSIC_1 = create("jukebox_sounds.predator_music_1");

    private static BLibHolder<SoundEvent> create(String path) {
        return REGISTRY.createHolder(path, () -> SoundEvent.createVariableRangeEvent(PredatorResources.location(path)));
    }

    public static void initialize() {
        REGISTRY.registerAll();
    }
}
