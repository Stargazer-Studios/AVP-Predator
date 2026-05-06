package com.predator.common.registry.tag;

import com.predator.PredatorResources;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;

public class PredatorEntityTypeTags {

    public static final TagKey<EntityType<?>> PREDATORS = create("predators");

    /**
     * Entity types that should be visible in the predator thermal-vision post-effect. Entities not in this tag are
     * skipped during the gbuffer pass while thermal is active — their pixels show the cold world heat behind them, so
     * they appear "invisible" in IR.
     * <p>
     * Empty by default — modders/datapacks add the entity types they want detectable. Common case: warm-blooded mobs
     * (cows, sheep, players, villagers, hostile mobs) but not e.g. silverfish, endermites, or constructed/non-
     * biological entities.
     */
    public static final TagKey<EntityType<?>> THERMAL_VISIBLE = create("thermal_visible");

    /**
     * Entity types that read as "naturally hot" in thermal vision — every bone's effective block-light coord is floored
     * at maximum, so the entity always reads as if lit by a full-intensity light source regardless of its actual world
     * lighting. Combined with the warm-color heuristic (lava/magma/blaze textures are red/orange), the result is the
     * entity displays as bright orange/red/white in IR.
     * <p>
     * Membership implies {@link #THERMAL_VISIBLE} effectively — but they should still be added to both tags explicitly.
     * An entity in THERMAL_HOT but not in THERMAL_VISIBLE will be skipped from rendering entirely.
     */
    public static final TagKey<EntityType<?>> THERMAL_HOT = create("thermal_hot");

    /**
     * Entity types that should be visible in the predator electromagnetic-vision post-effect. Entities not in this tag
     * are skipped during the gbuffer pass while EM is active. Default population (via the fabric tag provider) targets
     * end-realm beings (enderman, ender dragon, endermite).
     */
    public static final TagKey<EntityType<?>> EM_VISIBLE = create("em_visible");

    private static TagKey<EntityType<?>> create(String name) {
        return TagKey.create(Registries.ENTITY_TYPE, PredatorResources.location(name));
    }
}
