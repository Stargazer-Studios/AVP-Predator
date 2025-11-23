package com.predator.common.registry.init;

import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

import com.predator.common.registry.init.item.PredatorItems;

public class PredatorTiers {

    public static final Tier VERITANIUM = create(
        2640,
        10.0F,
        5F,
        BlockTags.INCORRECT_FOR_NETHERITE_TOOL,
        11,
        () -> Ingredient.of(PredatorItems.VERITANIUM_SHARD.get())
    );

    private static Tier create(
        int uses,
        float speed,
        float attackDamageBonus,
        TagKey<Block> incorrectBlocksForDrops,
        int enchantmentValue,
        Supplier<Ingredient> repairIngredient
    ) {
        return new Tier() {

            @Override
            public int getUses() {
                return uses;
            }

            @Override
            public float getSpeed() {
                return speed;
            }

            @Override
            public float getAttackDamageBonus() {
                return attackDamageBonus;
            }

            @Override
            public @NotNull TagKey<Block> getIncorrectBlocksForDrops() {
                return incorrectBlocksForDrops;
            }

            @Override
            public int getEnchantmentValue() {
                return enchantmentValue;
            }

            @Override
            public @NotNull Ingredient getRepairIngredient() {
                return repairIngredient.get();
            }
        };
    }

}
