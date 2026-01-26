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
}
