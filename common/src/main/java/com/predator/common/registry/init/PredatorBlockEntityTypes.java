package com.predator.common.registry.init;

import com.blib.common.registry.BLibHolder;
import com.blib.common.registry.BLibRegistry;
import com.predator.Predator;
import com.predator.common.gameplay.block.entity.TripMineBlockEntity;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;

import java.util.function.Supplier;

public class PredatorBlockEntityTypes {

    private static final BLibRegistry<BlockEntityType<?>> REGISTRY = Predator.MOD.registries().create(BuiltInRegistries.BLOCK_ENTITY_TYPE);

    public static final BLibHolder<BlockEntityType<TripMineBlockEntity>> TRIP_MINE = create(
        "trip_mine",
        () -> BlockEntityType.Builder.of(TripMineBlockEntity::new, PredatorBlocks.TRIP_MINE_BLOCK.get())
    );

    private static <T extends BlockEntity> BLibHolder<BlockEntityType<T>> create(
        String path,
        Supplier<BlockEntityType.Builder<T>> builder
    ) {
        return REGISTRY.createHolder(path, () -> builder.get().build(null));
    }

    public static void initialize() {
        REGISTRY.registerAll();
    }
}
