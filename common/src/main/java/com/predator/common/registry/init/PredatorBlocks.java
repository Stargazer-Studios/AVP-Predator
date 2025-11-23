package com.predator.common.registry.init;

import com.predator.PredatorResources;
import com.predator.common.gameplay.block.TripMineBlock;
import com.avp.common.gameplay.block.property.BlockPropertyBuilder;
import com.avp.service.Services;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.Block;

import com.avp.common.gameplay.block.property.BlockProperties;
import com.avp.common.registry.AVPDeferredHolder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Supplier;

public class PredatorBlocks {
    private static final List<AVPDeferredHolder<? extends Block>> HOLDERS = new ArrayList<>();

    public static List<AVPDeferredHolder<? extends Block>> getAll() {
        return Collections.unmodifiableList(HOLDERS);
    }

    public static final AVPDeferredHolder<Block> TRIP_MINE_BLOCK = register(
        "trip_mine",
        () -> new TripMineBlock(BlockProperties.TITANIUM.build().noOcclusion())
    );

    public static AVPDeferredHolder<Block> register(String id, BlockPropertyBuilder blockPropertyBuilder) {
        return register(id, () -> new Block(blockPropertyBuilder.build()));
    }

    public static <T extends Block> AVPDeferredHolder<T> register(String id, Supplier<T> blockSupplier) {
        var holder = Services.REGISTRY.register(BuiltInRegistries.BLOCK, PredatorResources.location(id), blockSupplier);
        HOLDERS.add(holder);
        return holder;
    }

    public static void initialize() {}
}
