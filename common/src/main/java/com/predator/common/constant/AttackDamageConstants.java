package com.predator.common.constant;

public record AttackDamageConstants() {

    // Basic measurements (in terms of half-hearts).
    public static final float PLAYER_HEALTH = 20;

    public static final float YAUTJA_ATTACK_DAMAGE = PLAYER_HEALTH * 0.75F;
}
