package com.predator.common.registry.init.creative_mode_tab.initializer;

import com.predator.common.registry.init.PredatorBlocks;
import net.minecraft.world.item.CreativeModeTab;

import java.util.function.Consumer;

public class BlocksCreativeModeTabInitializer {

    public static final Consumer<CreativeModeTab.Output> OUTPUT_CONSUMER = output -> {
        CreativeModeTabUtil.accept(output, PredatorBlocks.TRIP_MINE_BLOCK);
    };
}
