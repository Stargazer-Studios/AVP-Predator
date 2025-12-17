package com.predator.common.registry.init.item.block;

import com.blib.common.registry.BLibHolder;
import com.blib.common.registry.BLibRegistry;
import com.predator.Predator;
import com.predator.common.registry.init.PredatorEntityTypes;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SpawnEggItem;

public class PredatorSpawnEggItems {

    public static final BLibRegistry<Item> REGISTRY = Predator.MOD.registries().create(BuiltInRegistries.ITEM);

    public static final BLibHolder<SpawnEggItem> YAUTJA_SPAWN_EGG = create("yautja", PredatorEntityTypes.YAUTJA);

    private static <E extends Mob> BLibHolder<SpawnEggItem> create(String path, BLibHolder<EntityType<E>> holder) {
        return REGISTRY.createHolder(
            path + "_spawn_egg",
            Predator.MOD.factories().createSpawnEggSupplier(holder, 0xFFFFFF, 0xFFFFFF, new Item.Properties())
        );
    }

    public static void initialize() {
        REGISTRY.registerAll();
    }
}
