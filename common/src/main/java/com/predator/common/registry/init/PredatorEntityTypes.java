package com.predator.common.registry.init;

import com.blib.BLibHolder;
import com.blib.common.registry.SilencedEntityTypeBuilder;
import com.blib.common.registry.impl.BLibEntityTypeRegistry;
import com.predator.Predator;
import com.predator.common.gameplay.entity.living.yautja.Yautja;
import com.predator.common.gameplay.entity.projectile.ShurikenProjectile;
import com.predator.common.gameplay.entity.projectile.SmartDiscProjectile;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;

public class PredatorEntityTypes {

    public static final BLibEntityTypeRegistry REGISTRY = Predator.MOD.createEntityTypeRegistry();

    public static final BLibHolder<EntityType<ShurikenProjectile>> SHURIKEN = create(
        "shuriken",
        EntityType.Builder.<ShurikenProjectile>of(ShurikenProjectile::new, MobCategory.MISC)
            .sized(0.25F, 0.25F)
    );

    public static final BLibHolder<EntityType<SmartDiscProjectile>> SMART_DISC = create(
        "smart_disc",
        EntityType.Builder.<SmartDiscProjectile>of(SmartDiscProjectile::new, MobCategory.MISC)
            .sized(0.25F, 0.25F)
    );

    public static final BLibHolder<EntityType<Yautja>> YAUTJA = create(
        "yautja",
        EntityType.Builder.of(Yautja::new, MobCategory.MONSTER)
            .sized(0.7f, 2.48f)
    );

    public static <T extends Entity> BLibHolder<EntityType<T>> create(String id, EntityType.Builder<T> builder) {
        return REGISTRY.createHolder(id, () -> ((SilencedEntityTypeBuilder) builder).blib$buildWithoutDataFixerCheck());
    }

    public static void initialize() {
        REGISTRY.registerAll();
        REGISTRY.registerEntityAttributes(YAUTJA, Yautja::createYautjaAttributes);
    }
}
