package com.predator.common.registry.init;

import com.avp.common.model.spawning.AVPEntitySpawnData;
import com.avp.common.model.spawning.SpawnSettings;
import com.avp.service.Services;
import com.predator.common.gameplay.entity.living.yautja.YautjaSpawning;
import net.minecraft.tags.BiomeTags;

public class PredatorEntitySpawns {

    public static void initialize() {
        // TODO: Move this back to config.
        var yautjaSpawn = new SpawnSettings(true, 1, 1, 10);

        Services.REGISTRY.registerEntitySpawnData(
            AVPEntitySpawnData.builder(PredatorEntityTypes.YAUTJA)
                .withBiomeTagKey(BiomeTags.IS_JUNGLE)
                .withSpawnPredicate(YautjaSpawning.PREDICATE)
                .withSpawnSettings(yautjaSpawn)
                .build()
        );
    }
}
