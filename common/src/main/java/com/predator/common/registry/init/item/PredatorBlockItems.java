package com.predator.common.registry.init.item;

import com.blib.common.registry.BLibHolder;
import com.blib.common.registry.BLibRegistry;
import com.predator.Predator;
import com.predator.common.registry.init.PredatorBlocks;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

import java.util.function.Supplier;

public class PredatorBlockItems {

    private static final BLibRegistry<Item> REGISTRY = Predator.MOD.registries().create(BuiltInRegistries.ITEM);

    public static final BLibHolder<BlockItem> TRIP_MINE_BLOCK = create("trip_mine", PredatorBlocks.TRIP_MINE_BLOCK);

    private static BLibHolder<BlockItem> create(String path, Supplier<? extends Block> blockSupplier) {
        return create(path, blockSupplier, new Item.Properties());
    }

    private static BLibHolder<BlockItem> create(String path, Supplier<? extends Block> blockSupplier, Item.Properties properties) {
        return createBlockItem(path, () -> new BlockItem(blockSupplier.get(), properties));
    }

    private static BLibHolder<BlockItem> createBlockItem(String path, Supplier<BlockItem> blockItemSupplier) {
        return REGISTRY.createHolder(path, blockItemSupplier);
    }

    public static void initialize() {
        REGISTRY.registerAll();
    }
}
