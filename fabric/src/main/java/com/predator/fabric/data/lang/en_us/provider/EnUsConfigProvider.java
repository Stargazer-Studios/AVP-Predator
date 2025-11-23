package com.predator.fabric.data.lang.en_us.provider;

import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;

import java.util.function.Consumer;

public class EnUsConfigProvider {

    public static final Consumer<FabricLanguageProvider.TranslationBuilder> CONSUMER = builder -> {
        builder.add("config.screen.avp_predator", "Predator Config");

        builder.add("config.avp_predator.option.blockConfigs", "Block Setting Configs");

        builder.add("config.avp_predator.option.spawnConfigs", "Mob Spawn Configs");
        builder.add(
            "config.avp_predator.option.PREDATOR_CUSTOM_MOB_CATEGORY_LIMIT",
            "Maximum spawn count for predators in the custom mob category."
        );
        builder.add("config.avp_predator.option.YAUTJA_SPAWN", "Yautja spawn settings");
        builder.add("config.avp_predator.option.enabled", "Enable spawning");
        builder.add("config.avp_predator.option.maxY", "Maximum Y-level at for spawn");
        builder.add("config.avp_predator.option.minY", "Minimum Y-level at for spawn");
        builder.add("config.avp_predator.option.minGroupSize", "Minimum group size for spawns");
        builder.add("config.avp_predator.option.maxGroupSize", "Maximum group size for spawns");
        builder.add("config.avp_predator.option.weight", "Spawn weight");

        builder.add("config.avp_predator.option.statsConfigs", "Mob Stat Configs");
        builder.add("config.avp_predator.option.health", "Health value");
        builder.add("config.avp_predator.option.attackDamage", "Attack damage");
        builder.add("config.avp_predator.option.healthRegenPerSecond", "Health regeneration per second");
        builder.add("config.avp_predator.option.knockbackResistance", "Knockback resistance");
        builder.add("config.avp_predator.option.moveSpeed", "Movement speed");
        builder.add("config.avp_predator.option.armorToughness", "Armor toughness.");
        builder.add("config.avp_predator.option.armor", "Armor value");
        builder.add("config.avp_predator.option.nestTickrate", "Nest tickrate");
        builder.add("config.avp_predator.option.followRange", "Follow range");
        builder.add("config.avp_predator.option.YAUTJA_STATS", "Yautja stats");
    };
}
