package com.predator.fabric.client;

import com.predator.client.PredatorClient;
import net.fabricmc.api.ClientModInitializer;

public class PredatorFabricClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        PredatorClient.initialize();
    }
}
