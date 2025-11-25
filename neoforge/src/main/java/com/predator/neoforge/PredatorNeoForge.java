package com.predator.neoforge;

import com.blib.neoforge.service.impl.NeoForgeBLibRegistryServiceImpl;
import com.blib.service.BLibServices;
import com.predator.Predator;
import com.predator.common.registry.init.PredatorEntityTypes;
import com.predator.mixin.ParrotSoundMapAccessor;
import net.minecraft.sounds.SoundEvents;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.tick.LevelTickEvent;

@Mod(Predator.MOD_ID)
public class PredatorNeoForge {

    public PredatorNeoForge(IEventBus modBus) {
        Predator.initialize();

        // TODO: Automate this.
        ((NeoForgeBLibRegistryServiceImpl) BLibServices.REGISTRY).finalize(Predator.MOD, modBus);

        // Game bus events.
        NeoForge.EVENT_BUS.addListener(EventPriority.HIGH, PredatorNeoForge::onWorldEndTick);
    }

    // Marine Spawns and Ash placement in nuked zones
    public static void onWorldEndTick(LevelTickEvent.Post event) {
        if (event.getLevel().isClientSide) {
            return;
        }

        var sounds = ParrotSoundMapAccessor.getSoundMap();
        /*
         * TODO: Use Yautja sound when added
         */
        sounds.put(PredatorEntityTypes.YAUTJA.get(), SoundEvents.ALLAY_AMBIENT_WITH_ITEM);
    }
}
