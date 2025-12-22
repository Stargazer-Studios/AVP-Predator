package com.predator.common.config;

import com.blib.common.config.BLibConfigs;
import com.predator.Predator;
import com.predator.common.gameplay.entity.living.yautja.Yautja;
import mod.azure.azurelib.common.config.Config;
import mod.azure.azurelib.common.config.Configurable;
import mod.azure.azurelib.common.config.format.ConfigFormats;

@Config(id = Predator.MOD_ID)
public class PredatorConfig {

    public static PredatorConfig INSTANCE;

    public static void initialize() {
        INSTANCE = BLibConfigs.register(PredatorConfig.class, ConfigFormats.json()).getConfigInstance();
    }

    @Configurable
    @Configurable.Synchronized
    @Configurable.Comment("Controls the settings of the various blocks")
    public BlockConfigs blockConfigs = new BlockConfigs();

    public static class BlockConfigs {

        @Configurable
        @Configurable.Synchronized
        @Configurable.Comment("Block radius that a trip mine looks for a living entity")
        public double TRIP_MINE_SEARCH_RADIUS = 2;
    }

    @Configurable
    @Configurable.Synchronized
    @Configurable.Comment("Controls the Mobs Spawn Settings")
    public SpawnConfigs spawnConfigs = new SpawnConfigs();

    public static class SpawnConfigs {

        @Configurable
        @Configurable.Synchronized
        @Configurable.Comment("Yautja spawn settings. Modifying these will require restarting the game.")
        public SpawnSettings YAUTJA_SPAWN = new SpawnSettings(true, 1, 1, 10);

        public static class SpawnSettings {

            @Configurable
            @Configurable.Synchronized
            @Configurable.Comment("If true, spawning is enabled.")
            public boolean enabled;

            @Configurable
            @Configurable.Synchronized
            @Configurable.Comment("The minimum group size for this entity's spawn.")
            public int minGroupSize;

            @Configurable
            @Configurable.Synchronized
            @Configurable.Comment("The maximum group size for this entity's spawn.")
            public int maxGroupSize;

            @Configurable
            @Configurable.Synchronized
            @Configurable.Comment("The spawn weight for this entity.")
            public int weight;

            public SpawnSettings(
                boolean enabled,
                int minGroupSize,
                int maxGroupSize,
                int weight
            ) {
                this.enabled = enabled;
                this.minGroupSize = minGroupSize;
                this.maxGroupSize = maxGroupSize;
                this.weight = weight;
            }
        }
    }

    @Configurable
    @Configurable.Synchronized
    @Configurable.Comment("Controls the Mobs Stats")
    public StatsConfigs statsConfigs = new StatsConfigs();

    public static class StatsConfigs {

        @Configurable
        @Configurable.Synchronized
        @Configurable.Comment("Modifying any of these will require restarting the game.")
        public AdvancedStats YAUTJA_STATS = new AdvancedStats(
            Yautja.HEALTH,
            Yautja.ATTACK_DAMAGE,
            0.0f,
            Yautja.KNOCKBACK_RESISTANCE,
            Yautja.SPEED,
            Yautja.ARMOR,
            Yautja.ARMOR_TOUGHNESS,
            Yautja.FOLLOW_RANGE
        );

        public static class AdvancedStats {

            @Configurable
            @Configurable.Synchronized
            @Configurable.Comment("The entity's health.")
            public float health;

            @Configurable
            @Configurable.Synchronized
            @Configurable.Comment("The entity's attack damage.")
            public float attackDamage;

            @Configurable
            @Configurable.Synchronized
            @Configurable.Comment("The amount of health the entity regenerates per second.")
            public float healthRegenPerSecond;

            @Configurable
            @Configurable.Synchronized
            @Configurable.Comment("The entity's knockback resistance.")
            public float knockbackResistance;

            @Configurable
            @Configurable.Synchronized
            @Configurable.Comment("The entity's movement speed.")
            public float moveSpeed;

            @Configurable
            @Configurable.Synchronized
            @Configurable.Comment("The entity's armor value.")
            public float armor;

            @Configurable
            @Configurable.Synchronized
            @Configurable.Comment("The entity's armor toughness.")
            public float armorToughness;

            @Configurable
            @Configurable.Synchronized
            @Configurable.Comment("The entity's max follow range.")
            public float followRange;

            public AdvancedStats(
                float health,
                float attackDamage,
                float healthRegenPerSecond,
                float knockbackResistance,
                float moveSpeed,
                float armor,
                float armorToughness,
                float followRange
            ) {
                this.health = health;
                this.attackDamage = attackDamage;
                this.healthRegenPerSecond = healthRegenPerSecond;
                this.knockbackResistance = knockbackResistance;
                this.moveSpeed = moveSpeed;
                this.armor = armor;
                this.armorToughness = armorToughness;
                this.followRange = followRange;
            }
        }
    }
}
