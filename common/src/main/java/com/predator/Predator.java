package com.predator;

import com.blib.BLib;
import com.blib.BLibMod;
import com.blib.service.BLibServices;
import com.predator.common.registry.init.PredatorArmorMaterials;
import com.predator.common.registry.init.PredatorBlockEntityTypes;
import com.predator.common.registry.init.PredatorBlocks;
import com.predator.common.registry.init.PredatorEntitySpawns;
import com.predator.common.registry.init.PredatorEntityTypes;
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

    public static final BLibMod MOD = BLib.createMod(MOD_ID);

    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    public static void initialize() {
        LOGGER.info("Initializing AVP (Predator) for mod loader '{}'", BLibServices.MOD_LOADER.getModLoaderName());

        MOD.initialize(() -> {
            // No dependencies.
            PredatorBlocks.initialize();
            PredatorItems.initialize();
            PredatorEntityTypes.initialize();
            PredatorSoundEvents.initialize();

            // Depends on blocks.
            PredatorBlockItems.initialize();
            // Depends on sound events.
            PredatorArmorMaterials.initialize();
            // Depends on armor materials.
            PredatorArmorItems.initialize();
            // Depends on entity types.
            PredatorSpawnEggItems.initialize();
            // Depends on blocks.
            PredatorBlockEntityTypes.initialize();
            // Depends on blocks, items, block items, etc.
            PredatorCreativeModeTabs.initialize();

            // Functionality
            PredatorEntitySpawns.initialize();
        });

        // TODO: Only run this once on server start.
        BLibServices.EVENT.beforeLevelTick().register(Predator::injectCustomParrotSounds);
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
