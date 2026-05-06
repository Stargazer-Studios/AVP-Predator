package com.predator.common.network;

import com.blib.api.common.network.v1.NetworkHandler;
import com.blib.api.common.registry.v1.impl.BLibNetworkRegistry;
import com.predator.Predator;
import com.predator.common.network.packet.C2SCyclePredatorVisionPayload;

public class PredatorServerPacketHandlerRegistry {

    private static final BLibNetworkRegistry REGISTRY = Predator.MOD.registries().createNetworkRegistry();

    public static void initialize() {
        REGISTRY.registerPacketHandler(
            new NetworkHandler.FromClient<>(
                C2SCyclePredatorVisionPayload.TYPE,
                C2SCyclePredatorVisionPayload.CODEC,
                PredatorServerListener::handleCyclePredatorVisionPayload
            )
        );
    }
}
