package com.predator.common.registry.init.item;

import com.predator.PredatorResources;
import com.predator.common.registry.init.PredatorEntityTypes;
import com.avp.service.Services;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.item.Item;

import com.avp.common.registry.AVPDeferredHolder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Supplier;

public class PredatorSpawnEggItems {

    private static final List<AVPDeferredHolder<Item>> HOLDERS = new ArrayList<>();

    public static List<AVPDeferredHolder<Item>> getAll() {
        return Collections.unmodifiableList(HOLDERS);
    }

    public static final AVPDeferredHolder<Item> YAUTJA_SPAWN_EGG = register(
        "yautja",
        PredatorEntityTypes.YAUTJA,
        0xB9A86C,
        0x5A4728
    );

    private static <E extends Mob> AVPDeferredHolder<Item> register(
        String baseId,
        Supplier<EntityType<E>> entityTypeSupplier,
        int primaryColor,
        int secondaryColor
    ) {
        AVPDeferredHolder<Item> spawnEggItemSupplier = Services.REGISTRY.register(
            BuiltInRegistries.ITEM,
            PredatorResources.location(baseId + "_spawn_egg"),
            Services.BRIDGE.createSpawnEggSupplier(entityTypeSupplier, primaryColor, secondaryColor, new Item.Properties())
        );

        HOLDERS.add(spawnEggItemSupplier);

        return spawnEggItemSupplier;
    }

    public static void initialize() {}
}
