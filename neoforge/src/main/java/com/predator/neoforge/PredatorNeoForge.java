package com.predator.neoforge;

import com.blib.neoforge.BLibNeoForge;
import com.predator.Predator;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;

@Mod(Predator.MOD_ID)
public class PredatorNeoForge {

    public PredatorNeoForge(IEventBus modBus) {
        Predator.initialize();
        // TODO: Automate this somehow.
        BLibNeoForge.finalizeMod(Predator.MOD, modBus);
    }
}
