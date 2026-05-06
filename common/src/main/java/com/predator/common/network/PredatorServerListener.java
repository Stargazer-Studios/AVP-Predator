package com.predator.common.network;

import com.predator.common.gameplay.component.PredatorVisionMode;
import com.predator.common.network.packet.C2SCyclePredatorVisionPayload;
import com.predator.common.registry.init.PredatorDataComponents;
import com.predator.common.registry.init.item.PredatorArmorItems;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;

public class PredatorServerListener {

    private PredatorServerListener() {
        throw new UnsupportedOperationException();
    }

    public static void handleCyclePredatorVisionPayload(C2SCyclePredatorVisionPayload payload, Player serverPlayer) {
        var helmet = serverPlayer.getItemBySlot(EquipmentSlot.HEAD);

        if (!helmet.is(PredatorArmorItems.JUNGLE_PREDATOR_HELMET.get())) {
            return;
        }

        var component = PredatorDataComponents.VISION_MODE.get();
        var current = helmet.getOrDefault(component, PredatorVisionMode.REGULAR);
        helmet.set(component, current.cycleNext());
    }
}
