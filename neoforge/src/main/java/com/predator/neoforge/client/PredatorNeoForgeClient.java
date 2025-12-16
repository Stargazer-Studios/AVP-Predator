package com.predator.neoforge.client;

import com.predator.Predator;
import com.predator.client.PredatorClient;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.fml.common.Mod;

@Mod(value = Predator.MOD_ID, dist = Dist.CLIENT)
public class PredatorNeoForgeClient {

    public PredatorNeoForgeClient() {
        PredatorClient.initialize();
    }
}
