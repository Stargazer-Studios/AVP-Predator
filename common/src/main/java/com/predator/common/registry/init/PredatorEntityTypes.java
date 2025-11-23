package com.predator.common.registry.init;

import com.avp.common.registry.init.entity_type.SilencedEntityTypeBuilder;
import com.predator.PredatorResources;
import com.predator.common.gameplay.entity.living.yautja.Yautja;
import com.predator.common.gameplay.entity.projectile.ShurikenProjectile;
import com.predator.common.gameplay.entity.projectile.SmartDiscProjectile;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;

import com.avp.common.registry.AVPDeferredHolder;
import com.avp.service.Services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PredatorEntityTypes {

    public static final MobCategory PREDATOR_CATEGORY = Services.BRIDGE.getPredatorMobCategory();

    private static final List<AVPDeferredHolder<? extends EntityType<?>>> ENTITY_TYPE_HOLDERS = new ArrayList<>();

    public static List<AVPDeferredHolder<? extends EntityType<?>>> getAll() {
        return Collections.unmodifiableList(ENTITY_TYPE_HOLDERS);
    }

    public static final AVPDeferredHolder<EntityType<ShurikenProjectile>> SHURIKEN = register(
        "shuriken",
        EntityType.Builder.<ShurikenProjectile>of(ShurikenProjectile::new, MobCategory.MISC)
            .sized(0.25F, 0.25F)
    );

    public static final AVPDeferredHolder<EntityType<SmartDiscProjectile>> SMART_DISC = register(
        "smart_disc",
        EntityType.Builder.<SmartDiscProjectile>of(SmartDiscProjectile::new, MobCategory.MISC)
            .sized(0.25F, 0.25F)
    );

    public static final AVPDeferredHolder<EntityType<Yautja>> YAUTJA = register(
        "yautja",
        EntityType.Builder.of(Yautja::new, PREDATOR_CATEGORY)
            .sized(0.7f, 2.48f)
    );

    public static <T extends Entity> AVPDeferredHolder<EntityType<T>> register(String id, EntityType.Builder<T> builder) {
        var holder = Services.REGISTRY.register(
            BuiltInRegistries.ENTITY_TYPE,
            PredatorResources.location(id),
            () -> ((SilencedEntityTypeBuilder) builder).<T>avp$buildWithoutDataFixerCheck()
        );

        ENTITY_TYPE_HOLDERS.add(holder);

        return holder;
    }

    public static void initialize() {
        Services.REGISTRY.registerEntityAttributes(YAUTJA, Yautja::createYautjaAttributes);
    }
}
