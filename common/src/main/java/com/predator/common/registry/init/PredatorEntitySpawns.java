package com.predator.common.registry.init;

import com.blib.common.gameplay.model.spawning.SpawnSettings;

public class PredatorEntitySpawns {

    public static void initialize() {
        // TODO: Move this back to config.
        var yautjaSpawn = new SpawnSettings(true, 1, 1, 10);

        // FIXME:
        // Services.REGISTRY.registerEntitySpawnData(
        // BLibEntitySpawnData.builder(PredatorEntityTypes.YAUTJA)
        // .withBiomeTagKey(BiomeTags.IS_JUNGLE)
        // .withSpawnPredicate(YautjaSpawning.PREDICATE)
        // .withSpawnSettings(yautjaSpawn)
        // .build()
        // );
    }
}
