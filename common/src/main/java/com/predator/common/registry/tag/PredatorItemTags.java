package com.predator.common.registry.tag;

import com.predator.PredatorResources;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public class PredatorItemTags {

    public static final TagKey<Item> HOSTILE_WEAPONS = create("hostile_weapons");

    public static final TagKey<Item> JUNGLE_PREDATOR_ARMOR = create("jungle_predator_armor");

    public static final TagKey<Item> PREDATOR_ARMORS = create("predator_armors");

    private static TagKey<Item> create(String name) {
        return TagKey.create(Registries.ITEM, PredatorResources.location(name));
    }
}
