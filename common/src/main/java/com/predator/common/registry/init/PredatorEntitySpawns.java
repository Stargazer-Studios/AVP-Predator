package com.predator.common.registry.init;

import com.blib.api.common.entity.v1.spawning.BLibEntitySpawnData;
import com.blib.api.common.entity.v1.spawning.SpawnSettings;
import com.blib.api.common.registry.v1.impl.BLibEntitySpawnRegistry;
import com.predator.Predator;
import com.predator.common.gameplay.entity.living.yautja.YautjaSpawning;
import com.predator.common.property.PredatorProperties;
import com.predator.common.property.PredatorPropertyAccess;
import net.minecraft.tags.BiomeTags;

public class PredatorEntitySpawns {

    private static final BLibEntitySpawnRegistry REGISTRY = Predator.MOD.registries().createEntitySpawnRegistry();

    public static void initialize() {
        REGISTRY.register(
            BLibEntitySpawnData.builder(PredatorEntityTypes.YAUTJA)
                .withBiomeTagKey(BiomeTags.IS_JUNGLE)
                .withSpawnPredicate(YautjaSpawning.PREDICATE)
                .withSpawnSettings(convert(PredatorProperties.Entities.Yautja.SPAWNING))
                .build()
        );
    }

    private static SpawnSettings convert(PredatorProperties.SpawnProperties spawnProperties) {
        return new SpawnSettings(
            PredatorPropertyAccess.INSTANCE.getOrThrow(spawnProperties.enabled()),
            PredatorPropertyAccess.INSTANCE.getOrThrow(spawnProperties.minimumGroupSize()),
            PredatorPropertyAccess.INSTANCE.getOrThrow(spawnProperties.maximumGroupSize()),
            PredatorPropertyAccess.INSTANCE.getOrThrow(spawnProperties.weight())
        );
    }
}
