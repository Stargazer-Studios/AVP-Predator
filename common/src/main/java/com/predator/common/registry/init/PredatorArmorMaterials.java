package com.predator.common.registry.init;

import com.predator.PredatorResources;
import com.avp.service.Services;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ArmorMaterials;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

import com.avp.common.registry.AVPDeferredHolder;
import com.predator.common.registry.init.item.PredatorItems;
import org.jetbrains.annotations.NotNull;

public class PredatorArmorMaterials {

    public static final AVPDeferredHolder<ArmorMaterial> VERITANIUM = register(
        "veritanium",
        relativeDefense(
            ArmorMaterials.NETHERITE,
            Map.ofEntries(
                Map.entry(ArmorItem.Type.HELMET, 1),
                Map.entry(ArmorItem.Type.CHESTPLATE, 1),
                Map.entry(ArmorItem.Type.LEGGINGS, 1),
                Map.entry(ArmorItem.Type.BOOTS, 1)
            )
        ),
        6,
        PredatorSoundEvents.ITEM_ARMOR_EQUIP_VERITANIUM::getHolder,
        () -> Ingredient.of(PredatorItems.VERITANIUM_SHARD.get()),
        4,
        0.15F,
        false
    );

    public static AVPDeferredHolder<ArmorMaterial> register(
        String id,
        Map<ArmorItem.Type, Integer> defensePoints,
        int enchantability,
        Supplier<Holder<SoundEvent>> equipSoundHolderSupplier,
        Supplier<Ingredient> repairIngredientSupplier,
        float toughness,
        float knockbackResistance,
        boolean dyeable
    ) {
        var resourceLocation = PredatorResources.location(id);

        List<ArmorMaterial.Layer> layers = List.of(
            new ArmorMaterial.Layer(resourceLocation, "", dyeable)
        );

        return Services.REGISTRY.register(
            BuiltInRegistries.ARMOR_MATERIAL,
            PredatorResources.location(id),
            () -> new ArmorMaterial(
                defensePoints,
                enchantability,
                equipSoundHolderSupplier.get(),
                repairIngredientSupplier,
                layers,
                toughness,
                knockbackResistance
            )
        );
    }

    public static Map<ArmorItem.Type, Integer> relativeDefense(
        Holder<ArmorMaterial> armorMaterialHolder,
        Map<ArmorItem.Type, Integer> additiveDefense
    ) {
        var armorMaterial = armorMaterialHolder.value();

        return Map.ofEntries(
            compute(ArmorItem.Type.HELMET, additiveDefense, armorMaterial),
            compute(ArmorItem.Type.CHESTPLATE, additiveDefense, armorMaterial),
            compute(ArmorItem.Type.LEGGINGS, additiveDefense, armorMaterial),
            compute(ArmorItem.Type.BOOTS, additiveDefense, armorMaterial)
        );
    }

    private static @NotNull Map.Entry<ArmorItem.Type, Integer> compute(
        ArmorItem.Type type,
        Map<ArmorItem.Type, Integer> additiveDefense,
        ArmorMaterial armorMaterial
    ) {
        return Map.entry(type, armorMaterial.getDefense(type) + additiveDefense.getOrDefault(type, 0));
    }

    public static void initialize() {}
}
