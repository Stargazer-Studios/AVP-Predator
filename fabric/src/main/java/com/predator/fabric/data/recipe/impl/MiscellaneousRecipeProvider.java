package com.predator.fabric.data.recipe.impl;

import com.predator.common.registry.init.item.PredatorItems;
import com.avp.fabric.data.recipe.builder.RecipeBuilder;
import net.minecraft.data.recipes.RecipeCategory;

public class MiscellaneousRecipeProvider {

    public static void provide(RecipeBuilder builder) {
        builder.shapeless()
            .withCategory(RecipeCategory.MISC)
            .requires(9, PredatorItems.PREDATOR_MUSIC_DISC_1_FRAGMENT)
            .into(1, PredatorItems.PREDATOR_MUSIC_DISC_1);
    }
}
