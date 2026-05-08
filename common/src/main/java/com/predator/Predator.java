package com.predator;

import com.blib.api.BLibAPI;
import com.blib.api.common.mod.v1.BLibMod;
import com.predator.common.data.fixer.migration.PredatorDataMigrations;
import com.predator.common.network.PredatorPacketDirectionRegistry;
import com.predator.common.network.PredatorServerPacketHandlerRegistry;
import com.predator.common.property.PredatorPropertyAccess;
import com.predator.common.registry.init.PredatorArmorMaterials;
import com.predator.common.registry.init.PredatorBlockEntityTypes;
import com.predator.common.registry.init.PredatorBlocks;
import com.predator.common.registry.init.PredatorCommands;
import com.predator.common.registry.init.PredatorDataComponents;
import com.predator.common.registry.init.PredatorEntitySpawns;
import com.predator.common.registry.init.PredatorEntityTypes;
import com.predator.common.registry.init.PredatorMobEffects;
import com.predator.common.registry.init.PredatorSoundEvents;
import com.predator.common.registry.init.creative_mode_tab.PredatorCreativeModeTabs;
import com.predator.common.registry.init.item.PredatorArmorItems;
import com.predator.common.registry.init.item.PredatorBlockItems;
import com.predator.common.registry.init.item.PredatorItems;
import com.predator.common.registry.init.item.block.PredatorSpawnEggItems;
import com.predator.mixin.ParrotSoundMapAccessor;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.level.Level;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Predator {

    public static final String MOD_ID = "avp_predator";

    public static final BLibMod MOD = BLibAPI.createMod(MOD_ID);

    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    public static void initialize() {
        LOGGER.info("Initializing AVP (Predator) for mod loader '{}'", BLibAPI.getModLoaderType());

        PredatorPropertyAccess.INSTANCE.save();

        MOD.initialize(Predator::runInitialization);
    }

    private static void runInitialization() {
        PredatorArmorItems.initialize();
        PredatorArmorMaterials.initialize();
        PredatorBlockEntityTypes.initialize();
        PredatorBlockItems.initialize();
        PredatorBlocks.initialize();
        PredatorCommands.initialize();
        PredatorCreativeModeTabs.initialize();
        PredatorDataComponents.initialize();
        PredatorEntitySpawns.initialize();
        PredatorEntityTypes.initialize();
        PredatorItems.initialize();
        PredatorMobEffects.initialize();
        PredatorSoundEvents.initialize();
        PredatorSpawnEggItems.initialize();

        PredatorPacketDirectionRegistry.initialize();
        PredatorServerPacketHandlerRegistry.initialize();

        PredatorDataMigrations.initialize();

        // TODO: Only run this once on server start.
        MOD.events().preLevelTick().register(Predator::injectCustomParrotSounds);
    }

    // Marine Spawns and Ash placement in nuked zones
    public static void injectCustomParrotSounds(Level level) {
        if (level.isClientSide) {
            return;
        }

        var sounds = ParrotSoundMapAccessor.getSoundMap();

        /*
         * TODO: Use Yautja sound when added
         */
        sounds.put(PredatorEntityTypes.YAUTJA.get(), SoundEvents.ALLAY_AMBIENT_WITH_ITEM);
    }
}
