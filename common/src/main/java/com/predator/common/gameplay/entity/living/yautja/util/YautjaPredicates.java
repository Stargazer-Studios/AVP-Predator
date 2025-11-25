package com.predator.common.gameplay.entity.living.yautja.util;

import com.avp.common.util.AVPPredicates;
import com.predator.common.gameplay.entity.living.yautja.Yautja;
import com.predator.common.registry.tag.PredatorItemTags;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import org.jetbrains.annotations.NotNull;

public class YautjaPredicates {

    public static boolean isThreateningTarget(@NotNull Yautja yautja, @NotNull LivingEntity potentialTarget) {
        return isValidTarget(yautja, potentialTarget);
    }

    public static boolean isValidTarget(@NotNull Yautja yautja, @NotNull LivingEntity potentialTarget) {
        return switch (potentialTarget) {
            case Yautja yautja1 -> false;
            case Creeper creeper -> false;
            case Player player -> !AVPPredicates.IS_IMMORTAL.test(player)
                && (player.getMainHandItem().is(PredatorItemTags.HOSTILE_WEAPONS)
                || (yautja.getLastAttacker() != null && yautja.getLastAttacker().is(player)));
            default -> {
                if (potentialTarget instanceof Mob || potentialTarget instanceof Monster) {
                    yield potentialTarget.getMainHandItem().is(PredatorItemTags.HOSTILE_WEAPONS)
                        || (yautja.getLastAttacker() != null && yautja.getLastAttacker().is(potentialTarget));
                }

                yield yautja.getLastAttacker() != null && yautja.getLastAttacker().is(potentialTarget);
            }
        };
    }

    private YautjaPredicates() {
        throw new UnsupportedOperationException();
    }
}
