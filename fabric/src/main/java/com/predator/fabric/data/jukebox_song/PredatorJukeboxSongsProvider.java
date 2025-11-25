package com.predator.fabric.data.jukebox_song;

import com.predator.common.registry.init.PredatorSoundEvents;
import com.predator.common.registry.key.PredatorJukeboxSongKeys;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.JukeboxSong;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.CompletableFuture;

public class PredatorJukeboxSongsProvider extends FabricDynamicRegistryProvider {

    public PredatorJukeboxSongsProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(HolderLookup.Provider registries, Entries entries) {
        entries.add(PredatorJukeboxSongKeys.PREDATOR_MUSIC_1, createPredatorMusic1Song());
    }

    private JukeboxSong createPredatorMusic1Song() {
        return new JukeboxSong(
            PredatorSoundEvents.JUKEBOX_SOUNDS_PREDATOR_MUSIC_1.getHolder(),
            Component.translatable("jukebox_song.avp_predator.predator_music_1"),
            184,
            12
        );
    }

    @Override
    public @NotNull String getName() {
        return "Predator Jukebox Songs";
    }
}
