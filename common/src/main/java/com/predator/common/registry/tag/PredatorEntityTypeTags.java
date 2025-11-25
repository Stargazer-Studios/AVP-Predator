package com.predator.common.registry.tag;

import com.predator.PredatorResources;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;

public class PredatorEntityTypeTags {

    public static final TagKey<EntityType<?>> PREDATORS = create("predators");

    private static TagKey<EntityType<?>> create(String name) {
        return TagKey.create(Registries.ENTITY_TYPE, PredatorResources.location(name));
    }
}
