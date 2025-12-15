package com.predator.common.registry.key;

import com.predator.Predator;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;

public class PredatorCreativeModeTabKeys {

    public static final ResourceKey<CreativeModeTab> BLOCKS_KEY = create("predator_blocks");

    public static final ResourceKey<CreativeModeTab> COMBAT_KEY = create("predator_combat");

    public static final ResourceKey<CreativeModeTab> INGREDIENTS_KEY = create("predator_ingredients");

    public static final ResourceKey<CreativeModeTab> SPAWN_EGGS_KEY = create("predator_spawn_eggs");

    public static final ResourceKey<CreativeModeTab> TOOLS_AND_UTILITIES_KEY = create("predator_tools_and_utilities");

    public static ResourceKey<CreativeModeTab> create(String name) {
        return Predator.MOD.resources().createKey(Registries.CREATIVE_MODE_TAB, name);
    }

}
