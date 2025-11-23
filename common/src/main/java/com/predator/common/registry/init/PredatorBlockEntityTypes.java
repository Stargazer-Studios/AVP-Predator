package com.predator.common.registry.init;

import com.avp.common.registry.AVPDeferredHolder;
import com.avp.service.Services;
import com.predator.PredatorResources;
import com.predator.common.gameplay.block.entity.TripMineBlockEntity;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;

import java.util.function.Supplier;

public class PredatorBlockEntityTypes {

    public static final AVPDeferredHolder<BlockEntityType<TripMineBlockEntity>> TRIP_MINE = register(
        "trip_mine",
        () -> BlockEntityType.Builder.of(TripMineBlockEntity::new, PredatorBlocks.TRIP_MINE_BLOCK.get())
    );

    private static <T extends BlockEntity> AVPDeferredHolder<BlockEntityType<T>> register(
        String id,
        Supplier<BlockEntityType.Builder<T>> builder
    ) {
        return Services.REGISTRY.register(BuiltInRegistries.BLOCK_ENTITY_TYPE, PredatorResources.location(id), () -> builder.get().build(null));
    }

    public static void initialize() {}
}
