package com.predator.common.registry.init;

import com.avp.common.gameplay.block.property.BlockProperties;
import com.blib.BLibHolder;
import com.blib.BLibRegistry;
import com.predator.Predator;
import com.predator.common.gameplay.block.TripMineBlock;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.Block;

import java.util.function.Supplier;

public class PredatorBlocks {

    public static final BLibRegistry<Block> REGISTRY = Predator.MOD.createRegistry(BuiltInRegistries.BLOCK);

    public static final BLibHolder<Block> TRIP_MINE_BLOCK = create(
        "trip_mine",
        () -> new TripMineBlock(BlockProperties.TITANIUM.build().noOcclusion())
    );

    private static <T extends Block> BLibHolder<T> create(String path, Supplier<T> blockSupplier) {
        return REGISTRY.createHolder(path, blockSupplier);
    }

    public static void initialize() {
        REGISTRY.registerAll();
    }
}
