package com.predator.common.registry.init;

import com.blib.common.gameplay.model.spawning.BLibEntitySpawnData;
import com.blib.common.gameplay.model.spawning.SpawnSettings;
import com.blib.common.registry.impl.BLibEntityTypeRegistry;
import com.predator.Predator;
import com.predator.common.gameplay.entity.living.yautja.YautjaSpawning;
import net.minecraft.tags.BiomeTags;

public class PredatorEntitySpawns {

    private static final BLibEntityTypeRegistry REGISTRY = Predator.MOD.createEntityTypeRegistry();

    public static void initialize() {
        // TODO: Move this back to config.
        var yautjaSpawn = new SpawnSettings(true, 1, 1, 10);

        REGISTRY.registerSpawnData(
            BLibEntitySpawnData.builder(PredatorEntityTypes.YAUTJA)
                .withBiomeTagKey(BiomeTags.IS_JUNGLE)
                .withSpawnPredicate(YautjaSpawning.PREDICATE)
                .withSpawnSettings(yautjaSpawn)
                .build()
        );
    }
}
