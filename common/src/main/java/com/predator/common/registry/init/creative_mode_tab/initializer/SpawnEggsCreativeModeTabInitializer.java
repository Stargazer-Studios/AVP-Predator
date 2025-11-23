package com.predator.common.registry.init.creative_mode_tab.initializer;

import com.predator.common.registry.init.item.PredatorSpawnEggItems;
import net.minecraft.world.item.CreativeModeTab;

import java.util.function.Consumer;

public class SpawnEggsCreativeModeTabInitializer {

    public static final Consumer<CreativeModeTab.Output> OUTPUT_CONSUMER = output -> {
        CreativeModeTabUtil.accept(output, PredatorSpawnEggItems.YAUTJA_SPAWN_EGG);
    };
}
