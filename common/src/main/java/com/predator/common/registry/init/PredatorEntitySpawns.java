package com.predator.common.registry.init;

import com.avp.service.Services;
import com.blib.common.gameplay.model.spawning.BLibEntitySpawnData;
import com.blib.common.gameplay.model.spawning.SpawnSettings;
import com.predator.common.gameplay.entity.living.yautja.YautjaSpawning;
import net.minecraft.tags.BiomeTags;

public class PredatorEntitySpawns {

    public static void initialize() {
        // TODO: Move this back to config.
        var yautjaSpawn = new SpawnSettings(true, 1, 1, 10);

        Services.REGISTRY.registerEntitySpawnData(
            BLibEntitySpawnData.builder(PredatorEntityTypes.YAUTJA)
                .withBiomeTagKey(BiomeTags.IS_JUNGLE)
                .withSpawnPredicate(YautjaSpawning.PREDICATE)
                .withSpawnSettings(yautjaSpawn)
                .build()
        );
    }
}
