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

import java.util.function.Supplier;

public class PredatorSpawnEggItems {

    public static final BLibRegistry<Item> REGISTRY = Predator.MOD.registries().create(BuiltInRegistries.ITEM);

    public static final BLibHolder<SpawnEggItem> YAUTJA_SPAWN_EGG = create(
        "yautja",
        PredatorEntityTypes.YAUTJA,
        0xB9A86C,
        0x5A4728
    );

    private static <E extends Mob> BLibHolder<SpawnEggItem> create(
        String baseId,
        Supplier<EntityType<E>> entityTypeSupplier,
        int primaryColor,
        int secondaryColor
    ) {
        var supplier = Predator.MOD.factories()
            .createSpawnEggSupplier(entityTypeSupplier, primaryColor, secondaryColor, new Item.Properties());
        return REGISTRY.createHolder(baseId + "_spawn_egg", supplier);
    }

    public static void initialize() {
        REGISTRY.registerAll();
    }
}
