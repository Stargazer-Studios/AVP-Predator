package com.predator.common.property;

import com.blib.api.common.property.v1.BLibPropertySchema;

public class PredatorPropertySchema {

    static final BLibPropertySchema SCHEMA = BLibPropertySchema.builder()
        .withPropertyValueAlignment(true)
        .addComment("Block radius that a trip mine looks for a living entity.")
        .addProperty(PredatorProperties.Blocks.TripMine.RANGE, 2)
        .addBlankLine()
        .apply(PredatorPropertySchema::addYautjaSpawnProperties)
        .addBlankLine()
        .apply(PredatorPropertySchema::addYautjaStatsProperties)
        .build();

    private static BLibPropertySchema.Builder addYautjaSpawnProperties(BLibPropertySchema.Builder builder) {
        var spawnProperties = PredatorProperties.Entities.Yautja.SPAWNING;

        return builder
            .addComment("If true, spawning is enabled.")
            .addProperty(spawnProperties.enabled(), true)
            .addComment("The maximum group size for this entity's spawn.")
            .addProperty(spawnProperties.maximumGroupSize(), 1)
            .addComment("The minimum group size for this entity's spawn.")
            .addProperty(spawnProperties.minimumGroupSize(), 1)
            .addComment("The spawn weight for this entity.")
            .addProperty(spawnProperties.weight(), 10);
    }

    private static BLibPropertySchema.Builder addYautjaStatsProperties(BLibPropertySchema.Builder builder) {
        var spawnProperties = PredatorProperties.Entities.Yautja.STATS;

        return builder
            .addComment("The entity's armor value.")
            .addProperty(spawnProperties.armor(), com.predator.common.gameplay.entity.living.yautja.Yautja.ARMOR)
            .addComment("The entity's armor toughness.")
            .addProperty(spawnProperties.armorToughness(), com.predator.common.gameplay.entity.living.yautja.Yautja.ARMOR_TOUGHNESS)
            .addComment("The entity's attack damage.")
            .addProperty(spawnProperties.attackDamage(), com.predator.common.gameplay.entity.living.yautja.Yautja.ATTACK_DAMAGE)
            .addComment("The entity's max follow range.")
            .addProperty(spawnProperties.followRange(), com.predator.common.gameplay.entity.living.yautja.Yautja.FOLLOW_RANGE)
            .addComment("The entity's health.")
            .addProperty(spawnProperties.health(), com.predator.common.gameplay.entity.living.yautja.Yautja.HEALTH)
            .addComment("The entity's knockback resistance.")
            .addProperty(
                spawnProperties.knockbackResistance(),
                com.predator.common.gameplay.entity.living.yautja.Yautja.KNOCKBACK_RESISTANCE
            )
            .addComment("The entity's movement speed.")
            .addProperty(spawnProperties.movementSpeed(), com.predator.common.gameplay.entity.living.yautja.Yautja.SPEED);
    }

    private PredatorPropertySchema() {
        throw new UnsupportedOperationException();
    }
}
