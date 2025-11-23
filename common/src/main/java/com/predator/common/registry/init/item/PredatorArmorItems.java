package com.predator.common.registry.init.item;

import com.predator.common.gameplay.item.VeritaniumArmorItem;
import net.minecraft.core.Holder;
import net.minecraft.world.item.ArmorItem;

import com.avp.common.registry.AVPDeferredHolder;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Item;

import java.util.function.Supplier;

public class PredatorArmorItems {

    public static final AVPDeferredHolder<ArmorItem> JUNGLE_PREDATOR_LEGGINGS = register(
        "jungle_predator_leggings",
        () -> new VeritaniumArmorItem(ArmorItem.Type.LEGGINGS)
    );

    public static final AVPDeferredHolder<ArmorItem> JUNGLE_PREDATOR_HELMET = register(
        "jungle_predator_helmet",
        () -> new VeritaniumArmorItem(ArmorItem.Type.HELMET)
    );

    public static final AVPDeferredHolder<ArmorItem> JUNGLE_PREDATOR_CHESTPLATE = register(
        "jungle_predator_chestplate",
        () -> new VeritaniumArmorItem(ArmorItem.Type.CHESTPLATE)
    );

    public static final AVPDeferredHolder<ArmorItem> JUNGLE_PREDATOR_BOOTS = register(
        "jungle_predator_boots",
        () -> new VeritaniumArmorItem(ArmorItem.Type.BOOTS)
    );

    public static AVPDeferredHolder<ArmorItem> register(
        String id,
        Supplier<Holder<ArmorMaterial>> holderSupplier,
        ArmorItem.Type type,
        int durabilityMultiplier
    ) {
        return register(id, holderSupplier, type, durabilityMultiplier, new Item.Properties());
    }

    public static AVPDeferredHolder<ArmorItem> register(
        String id,
        Supplier<Holder<ArmorMaterial>> holderSupplier,
        ArmorItem.Type type,
        int durabilityMultiplier,
        Item.Properties properties
    ) {
        return register(id, () -> createArmorItem(holderSupplier.get(), type, durabilityMultiplier, properties));
    }

    public static <T extends Item> AVPDeferredHolder<T> register(String id, Supplier<T> itemSupplier) {
        return PredatorItems.register(id, itemSupplier);
    }

    public static ArmorItem createArmorItem(
        Holder<ArmorMaterial> holder,
        ArmorItem.Type type,
        int durabilityMultiplier,
        Item.Properties properties
    ) {
        var durability = type.getDurability(durabilityMultiplier);
        properties = properties.durability(durability);
        return new ArmorItem(holder, type, properties);
    }

    public static void initialize() {}
}
