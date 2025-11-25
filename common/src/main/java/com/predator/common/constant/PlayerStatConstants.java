package com.predator.common.constant;

public class PlayerStatConstants {

    // Basic measurements (in terms of half-hearts).
    public static final float PLAYER_HEALTH = 20F;

    // Basic measurements.
    public static final float PLAYER_SPEED_MULTIPLIER = 10 * 0.315000001192092896F;

    public static final float PLAYER_WALK_SPEED = 0.1F * PLAYER_SPEED_MULTIPLIER;

    public static final float PLAYER_SPRINT_SPEED = PLAYER_WALK_SPEED * 1.15F;

    public static final float PLAYER_SPRINT_JUMP_SPEED = PLAYER_SPRINT_SPEED * 1.15F;
}
