package com.predator.common.registry.init.creative_mode_tab.initializer;

import com.predator.common.registry.init.item.PredatorItems;
import com.predator.common.registry.init.item.PredatorArmorItems;
import net.minecraft.world.item.CreativeModeTab;

import java.util.function.Consumer;

public class CombatCreativeModeTabInitializer {

    public static final Consumer<CreativeModeTab.Output> OUTPUT_CONSUMER = output -> {
        CreativeModeTabUtil.accept(output, PredatorItems.SHURIKEN);
        CreativeModeTabUtil.accept(output, PredatorItems.SMART_DISC);

        CreativeModeTabUtil.accept(output, PredatorArmorItems.JUNGLE_PREDATOR_HELMET);
        CreativeModeTabUtil.accept(output, PredatorArmorItems.JUNGLE_PREDATOR_CHESTPLATE);
        CreativeModeTabUtil.accept(output, PredatorArmorItems.JUNGLE_PREDATOR_LEGGINGS);
        CreativeModeTabUtil.accept(output, PredatorArmorItems.JUNGLE_PREDATOR_BOOTS);
    };
}
