package com.predator.common.registry.init.creative_mode_tab;

import com.predator.Predator;
import com.avp.common.registry.AVPDeferredHolder;
import com.predator.PredatorResources;
import com.predator.common.registry.init.PredatorBlocks;
import com.predator.common.registry.init.creative_mode_tab.initializer.BlocksCreativeModeTabInitializer;
import com.predator.common.registry.init.creative_mode_tab.initializer.CombatCreativeModeTabInitializer;
import com.predator.common.registry.init.creative_mode_tab.initializer.IngredientsCreativeModeTabInitializer;
import com.predator.common.registry.init.creative_mode_tab.initializer.SpawnEggsCreativeModeTabInitializer;
import com.predator.common.registry.init.creative_mode_tab.initializer.ToolsAndUtilitiesCreativeModeTabInitializer;
import com.predator.common.registry.init.item.PredatorItems;
import com.predator.common.registry.key.PredatorCreativeModeTabKeys;
import com.avp.service.Services;
import com.predator.common.registry.init.item.PredatorArmorItems;
import com.predator.common.registry.init.item.PredatorSpawnEggItems;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class PredatorCreativeModeTabs {

    private static final String BASE_PATH = "creativeModeTab";

    public static final AVPDeferredHolder<CreativeModeTab> BLOCKS = register(
        PredatorCreativeModeTabKeys.BLOCKS_KEY,
        () -> new ItemStack(PredatorBlocks.TRIP_MINE_BLOCK.get()),
        BlocksCreativeModeTabInitializer.OUTPUT_CONSUMER
    );

    public static final AVPDeferredHolder<CreativeModeTab> COMBAT = register(
        PredatorCreativeModeTabKeys.COMBAT_KEY,
        () -> new ItemStack(PredatorArmorItems.JUNGLE_PREDATOR_HELMET.get()),
        CombatCreativeModeTabInitializer.OUTPUT_CONSUMER
    );

    public static final AVPDeferredHolder<CreativeModeTab> INGREDIENTS = register(
        PredatorCreativeModeTabKeys.INGREDIENTS_KEY,
        () -> new ItemStack(PredatorItems.VERITANIUM_SHARD.get()),
        IngredientsCreativeModeTabInitializer.OUTPUT_CONSUMER
    );

    public static final AVPDeferredHolder<CreativeModeTab> SPAWN_EGGS = register(
        PredatorCreativeModeTabKeys.SPAWN_EGGS_KEY,
        () -> new ItemStack(PredatorSpawnEggItems.YAUTJA_SPAWN_EGG.get()),
        SpawnEggsCreativeModeTabInitializer.OUTPUT_CONSUMER
    );

    public static final AVPDeferredHolder<CreativeModeTab> TOOLS_AND_UTILITIES = register(
        PredatorCreativeModeTabKeys.TOOLS_AND_UTILITIES_KEY,
        () -> new ItemStack(PredatorItems.VERITANIUM_SWORD.get()),
        ToolsAndUtilitiesCreativeModeTabInitializer.OUTPUT_CONSUMER
    );

    public static AVPDeferredHolder<CreativeModeTab> register(
        ResourceKey<CreativeModeTab> resourceKey,
        Supplier<ItemStack> iconSupplier,
        Consumer<CreativeModeTab.Output> outputConsumer
    ) {
        var path = resourceKey.location().getPath();

        return Services.REGISTRY.register(
            BuiltInRegistries.CREATIVE_MODE_TAB,
            PredatorResources.location(path),
            () -> CreativeModeTab.builder(CreativeModeTab.Row.TOP, 0)
                .icon(iconSupplier)
                .title(Component.translatable(BASE_PATH + "." + Predator.MOD_ID + "." + path))
                .displayItems((itemDisplayParameters, output) -> outputConsumer.accept(output))
                .build()
        );
    }

    public static void initialize() {}
}
