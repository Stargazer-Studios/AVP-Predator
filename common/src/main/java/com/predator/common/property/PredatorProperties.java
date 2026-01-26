package com.predator.common.property;

import com.blib.api.common.property.v1.BLibPropertyKey;
import com.blib.api.common.property.v1.serializer.BLibPropertySerializers;

public class PredatorProperties {

    public static class Blocks {

        private static final BLibPropertyKey.Parent BLOCKS = BLibPropertyKey.parent("blocks");

        public static class TripMine {

            private static final BLibPropertyKey.Parent TRIP_MINE = BLOCKS.child("trip_mine");

            public static final BLibPropertyKey.Leaf<Integer> RANGE = TRIP_MINE.leaf("range", BLibPropertySerializers.INT);
        }
    }

    public static class Entities {

        private static final BLibPropertyKey.Parent ENTITIES = BLibPropertyKey.parent("entities");

        public static class Yautja {

            private static final BLibPropertyKey.Parent YAUTJA = ENTITIES.child("yautja");

            public static final SpawnProperties SPAWNING = SpawnProperties.createFor(YAUTJA.child("spawning"));

            public static final StatProperties STATS = StatProperties.createFor(YAUTJA.child("stats"));
        }
    }

    public record SpawnProperties(
        BLibPropertyKey.Leaf<Boolean> enabled,
        BLibPropertyKey.Leaf<Integer> maximumGroupSize,
        BLibPropertyKey.Leaf<Integer> minimumGroupSize,
        BLibPropertyKey.Leaf<Integer> weight
    ) {

        public static SpawnProperties createFor(BLibPropertyKey.Parent parent) {
            return new SpawnProperties(
                parent.leaf("enabled", BLibPropertySerializers.BOOLEAN),
                parent.leaf("maximum_group_size", BLibPropertySerializers.INT),
                parent.leaf("mininum_group_size", BLibPropertySerializers.INT),
                parent.leaf("weight", BLibPropertySerializers.INT)
            );
        }
    }

    public record StatProperties(
        BLibPropertyKey.Leaf<Float> armor,
        BLibPropertyKey.Leaf<Float> armorToughness,
        BLibPropertyKey.Leaf<Float> attackDamage,
        BLibPropertyKey.Leaf<Float> followRange,
        BLibPropertyKey.Leaf<Float> health,
        BLibPropertyKey.Leaf<Float> knockbackResistance,
        BLibPropertyKey.Leaf<Float> movementSpeed
    ) {

        public static StatProperties createFor(BLibPropertyKey.Parent parent) {
            return new StatProperties(
                parent.leaf("armor", BLibPropertySerializers.FLOAT),
                parent.leaf("armor_toughness", BLibPropertySerializers.FLOAT),
                parent.leaf("attack_damage", BLibPropertySerializers.FLOAT),
                parent.leaf("follow_range", BLibPropertySerializers.FLOAT),
                parent.leaf("health", BLibPropertySerializers.FLOAT),
                parent.leaf("knockback_resistance", BLibPropertySerializers.FLOAT),
                parent.leaf("movement_speed", BLibPropertySerializers.FLOAT)
            );
        }
    }
}
