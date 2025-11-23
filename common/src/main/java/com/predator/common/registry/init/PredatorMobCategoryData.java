package com.predator.common.registry.init;

public class PredatorMobCategoryData {

    public static final Data PREDATOR = new Data("avp:predator", 75, false, false, 128);

    public record Data(
        String name,
        int max,
        boolean isFriendly,
        boolean isPersistent,
        int despawnDistance
    ) {}
}
