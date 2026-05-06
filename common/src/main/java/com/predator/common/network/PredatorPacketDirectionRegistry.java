package com.predator.common.network;

import com.blib.api.common.network.v1.PacketDirection;
import com.blib.api.common.registry.v1.impl.BLibNetworkRegistry;
import com.predator.Predator;
import com.predator.common.network.packet.C2SCyclePredatorVisionPayload;

public class PredatorPacketDirectionRegistry {

    private static final BLibNetworkRegistry REGISTRY = Predator.MOD.registries().createNetworkRegistry();

    public static void initialize() {
        REGISTRY.registerPacketDirection(
            new PacketDirection.C2S<>(C2SCyclePredatorVisionPayload.TYPE, C2SCyclePredatorVisionPayload.CODEC)
        );
    }
}
