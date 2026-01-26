package com.predator.common.registry.init.creative_mode_tab;

import com.blib.api.common.registry.v1.BLibHolder;
import com.blib.api.common.registry.v1.BLibRegistry;
import com.predator.Predator;
import com.predator.common.registry.init.PredatorBlocks;
import com.predator.common.registry.init.creative_mode_tab.initializer.BlocksCreativeModeTabInitializer;
import com.predator.common.registry.init.creative_mode_tab.initializer.CombatCreativeModeTabInitializer;
import com.predator.common.registry.init.creative_mode_tab.initializer.IngredientsCreativeModeTabInitializer;
import com.predator.common.registry.init.creative_mode_tab.initializer.SpawnEggsCreativeModeTabInitializer;
import com.predator.common.registry.init.creative_mode_tab.initializer.ToolsAndUtilitiesCreativeModeTabInitializer;
import com.predator.common.registry.init.item.PredatorArmorItems;
import com.predator.common.registry.init.item.PredatorItems;
import com.predator.common.registry.init.item.block.PredatorSpawnEggItems;
import com.predator.common.registry.key.PredatorCreativeModeTabKeys;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class PredatorCreativeModeTabs {

    private static final BLibRegistry<CreativeModeTab> REGISTRY = Predator.MOD.registries().create(BuiltInRegistries.CREATIVE_MODE_TAB);

    private static final String BASE_PATH = "creativeModeTab";

    public static final BLibHolder<CreativeModeTab> BLOCKS = create(
        PredatorCreativeModeTabKeys.BLOCKS_KEY,
        () -> new ItemStack(PredatorBlocks.TRIP_MINE_BLOCK.get()),
        BlocksCreativeModeTabInitializer.OUTPUT_CONSUMER
    );

    public static final BLibHolder<CreativeModeTab> COMBAT = create(
        PredatorCreativeModeTabKeys.COMBAT_KEY,
        () -> new ItemStack(PredatorArmorItems.JUNGLE_PREDATOR_HELMET.get()),
        CombatCreativeModeTabInitializer.OUTPUT_CONSUMER
    );

    public static final BLibHolder<CreativeModeTab> INGREDIENTS = create(
        PredatorCreativeModeTabKeys.INGREDIENTS_KEY,
        () -> new ItemStack(PredatorItems.VERITANIUM_SHARD.get()),
        IngredientsCreativeModeTabInitializer.OUTPUT_CONSUMER
    );

    public static final BLibHolder<CreativeModeTab> SPAWN_EGGS = create(
        PredatorCreativeModeTabKeys.SPAWN_EGGS_KEY,
        () -> new ItemStack(PredatorSpawnEggItems.YAUTJA_SPAWN_EGG.get()),
        SpawnEggsCreativeModeTabInitializer.OUTPUT_CONSUMER
    );

    public static final BLibHolder<CreativeModeTab> TOOLS_AND_UTILITIES = create(
        PredatorCreativeModeTabKeys.TOOLS_AND_UTILITIES_KEY,
        () -> new ItemStack(PredatorItems.VERITANIUM_SWORD.get()),
        ToolsAndUtilitiesCreativeModeTabInitializer.OUTPUT_CONSUMER
    );

    public static BLibHolder<CreativeModeTab> create(
        ResourceKey<CreativeModeTab> resourceKey,
        Supplier<ItemStack> iconSupplier,
        Consumer<CreativeModeTab.Output> outputConsumer
    ) {
        var path = resourceKey.location().getPath();

        return REGISTRY.createHolder(
            path,
            () -> CreativeModeTab.builder(CreativeModeTab.Row.TOP, 0)
                .icon(iconSupplier)
                .title(Component.translatable(BASE_PATH + "." + Predator.MOD_ID + "." + path))
                .displayItems((itemDisplayParameters, output) -> outputConsumer.accept(output))
                .build()
        );
    }

    public static void initialize() {
        REGISTRY.registerAll();
    }
}
