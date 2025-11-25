package com.predator.common.registry.init.item;

import com.blib.BLibHolder;
import com.blib.BLibRegistry;
import com.predator.Predator;
import com.predator.common.gameplay.item.VeritaniumArmorItem;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.ArmorItem;

import java.util.function.Supplier;

public class PredatorArmorItems {

    private static final BLibRegistry<ArmorItem> REGISTRY = Predator.MOD.createRegistry(BuiltInRegistries.ITEM);

    public static final BLibHolder<ArmorItem> JUNGLE_PREDATOR_LEGGINGS = create(
        "jungle_predator_leggings",
        () -> new VeritaniumArmorItem(ArmorItem.Type.LEGGINGS)
    );

    public static final BLibHolder<ArmorItem> JUNGLE_PREDATOR_HELMET = create(
        "jungle_predator_helmet",
        () -> new VeritaniumArmorItem(ArmorItem.Type.HELMET)
    );

    public static final BLibHolder<ArmorItem> JUNGLE_PREDATOR_CHESTPLATE = create(
        "jungle_predator_chestplate",
        () -> new VeritaniumArmorItem(ArmorItem.Type.CHESTPLATE)
    );

    public static final BLibHolder<ArmorItem> JUNGLE_PREDATOR_BOOTS = create(
        "jungle_predator_boots",
        () -> new VeritaniumArmorItem(ArmorItem.Type.BOOTS)
    );

    private static <T extends ArmorItem> BLibHolder<T> create(String name, Supplier<T> itemSupplier) {
        return REGISTRY.createHolder(name, itemSupplier);
    }

    public static void initialize() {
        REGISTRY.registerAll();
    }
}
