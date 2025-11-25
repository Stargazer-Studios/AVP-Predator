package com.predator.common.registry.key;

import com.predator.PredatorResources;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;

public class PredatorCreativeModeTabKeys {

    public static final ResourceKey<CreativeModeTab> BLOCKS_KEY = createResourceKey("predator_blocks");

    public static final ResourceKey<CreativeModeTab> COMBAT_KEY = createResourceKey("predator_combat");

    public static final ResourceKey<CreativeModeTab> INGREDIENTS_KEY = createResourceKey("predator_ingredients");

    public static final ResourceKey<CreativeModeTab> SPAWN_EGGS_KEY = createResourceKey("predator_spawn_eggs");

    public static final ResourceKey<CreativeModeTab> TOOLS_AND_UTILITIES_KEY = createResourceKey("predator_tools_and_utilities");

    public static ResourceKey<CreativeModeTab> createResourceKey(String name) {
        return ResourceKey.create(
            BuiltInRegistries.CREATIVE_MODE_TAB.key(),
            PredatorResources.location(name)
        );
    }

    public static void initialize() {}
}
