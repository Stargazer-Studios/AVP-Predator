package com.predator.common.registry.init;

import com.predator.common.registry.init.item.PredatorItems;
import net.minecraft.world.item.BlockItem;

import com.avp.common.registry.AVPDeferredHolder;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

import java.util.function.Supplier;

public class PredatorBlockItems {

    public static final AVPDeferredHolder<BlockItem> TRIP_MINE_BLOCK = register("trip_mine", PredatorBlocks.TRIP_MINE_BLOCK);

    public static AVPDeferredHolder<BlockItem> register(String id, Supplier<? extends Block> blockSupplier) {
        return register(id, blockSupplier, new Item.Properties());
    }

    public static AVPDeferredHolder<BlockItem> register(String id, Supplier<? extends Block> blockSupplier, Item.Properties properties) {
        return registerWithSupplier(id, () -> new BlockItem(blockSupplier.get(), properties));
    }

    public static AVPDeferredHolder<BlockItem> registerWithSupplier(String id, Supplier<BlockItem> blockItemSupplier) {
        return PredatorItems.register(id, blockItemSupplier);
    }

    public static void initialize() {}
}
