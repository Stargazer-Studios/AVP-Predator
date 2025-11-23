package com.predator.neoforge.client;

import com.predator.Predator;
import com.predator.client.PredatorClient;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;

@Mod(value = Predator.MOD_ID, dist = Dist.CLIENT)
public class PredatorNeoForgeClient {

    static {
        // We want this to run before any of the other events, as this sets up queues of data pairs (for example, pairs
        // of item suppliers to item renderers) prior the registration events firing.
        PredatorClient.initialize();
    }

    public PredatorNeoForgeClient(IEventBus modBus) {
    }
}
