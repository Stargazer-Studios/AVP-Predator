package com.predator.common.registry.key;

import com.predator.PredatorResources;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.JukeboxSong;

public class PredatorJukeboxSongKeys {

    public static final ResourceKey<JukeboxSong> PREDATOR_MUSIC_1 = create("predator_music_1");

    private static ResourceKey<JukeboxSong> create(String id) {
        var resourceLocation = PredatorResources.location(id);
        return ResourceKey.create(Registries.JUKEBOX_SONG, resourceLocation);
    }
}
