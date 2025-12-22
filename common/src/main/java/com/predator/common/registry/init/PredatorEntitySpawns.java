package com.predator.common.registry.init;

import com.blib.common.gameplay.model.spawning.BLibEntitySpawnData;
import com.blib.common.gameplay.model.spawning.SpawnSettings;
import com.blib.common.registry.impl.BLibEntitySpawnRegistry;
import com.predator.Predator;
import com.predator.common.config.PredatorConfig;
import com.predator.common.gameplay.entity.living.yautja.YautjaSpawning;
import net.minecraft.tags.BiomeTags;

public class PredatorEntitySpawns {

    private static final BLibEntitySpawnRegistry REGISTRY = Predator.MOD.registries().createEntitySpawnRegistry();

    public static void initialize() {
        REGISTRY.register(
            BLibEntitySpawnData.builder(PredatorEntityTypes.YAUTJA)
                .withBiomeTagKey(BiomeTags.IS_JUNGLE)
                .withSpawnPredicate(YautjaSpawning.PREDICATE)
                .withSpawnSettings(convert(PredatorConfig.INSTANCE.spawnConfigs.YAUTJA_SPAWN))
                .build()
        );
    }

    private static SpawnSettings convert(PredatorConfig.SpawnConfigs.SpawnSettings spawnSettings) {
        return new SpawnSettings(
            spawnSettings.enabled,
            spawnSettings.minGroupSize,
            spawnSettings.maxGroupSize,
            spawnSettings.weight
        );
    }
}
