package com.predator.fabric;

import com.avp.common.AVPEvents;
import com.avp.fabric.data.loot.LootTableModifier;
import com.avp.fabric.service.FabricRegistryService;
import com.avp.service.Services;
import com.lib.common.network.DataContainer;
import com.lib.common.network.DataUser;
import com.predator.Predator;
import com.predator.common.registry.init.PredatorEntityTypes;
import com.predator.fabric.common.DispenserBlockBehaviors;
import com.predator.mixin.ParrotSoundMapAccessor;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.fabricmc.fabric.api.event.lifecycle.v1.CommonLifecycleEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.fabricmc.fabric.api.networking.v1.EntityTrackingEvents;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.LivingEntity;

public class PredatorFabric implements ModInitializer {

    private static final FabricRegistryService REGISTRY = (FabricRegistryService) Services.REGISTRY;

    @Override
    public void onInitialize() {
        Predator.initialize();

        // Functionality
        DispenserBlockBehaviors.initialize();
        LootTableModifier.initialize();
        ServerTickEvents.START_WORLD_TICK.register(this::onWorldTick);

        EntityTrackingEvents.START_TRACKING.register(
            (trackedEntity, player) -> {
                if (trackedEntity instanceof LivingEntity livingEntity) {
                    ((DataUser) livingEntity).getDataContainer().syncToClient(livingEntity, DataContainer.SyncType.ALL);
                }
            }
        );

        CommandRegistrationCallback.EVENT.register(
            (dispatcher, registryAccess, environment) -> REGISTRY.getLiteralArgumentBuilders()
                .forEach(dispatcher::register)
        );

        CommonLifecycleEvents.TAGS_LOADED.register((registries, client) -> AVPEvents.onTagsUpdated());
    }

    private void onWorldTick(ServerLevel serverLevel) {
        modifyParrotSounds();
    }

    public static void modifyParrotSounds() {
        var sounds = ParrotSoundMapAccessor.getSoundMap();

        /*
         * TODO: Use Yautja sound when added
         */
        sounds.put(PredatorEntityTypes.YAUTJA.get(), SoundEvents.ALLAY_AMBIENT_WITH_ITEM);
    }
}
