package com.predator;

import com.avp.AVP;
import com.avp.common.config.AVPConfig;
import com.avp.service.Services;
import com.predator.common.registry.init.PredatorArmorMaterials;
import com.predator.common.registry.init.PredatorBlockEntityTypes;
import com.predator.common.registry.init.PredatorBlockItems;
import com.predator.common.registry.init.PredatorBlocks;
import com.predator.common.registry.init.PredatorEntitySpawns;
import com.predator.common.registry.init.PredatorEntityTypes;
import com.predator.common.registry.init.PredatorSoundEvents;
import com.predator.common.registry.init.creative_mode_tab.PredatorCreativeModeTabs;
import com.predator.common.registry.init.item.PredatorArmorItems;
import com.predator.common.registry.init.item.PredatorItems;
import com.predator.common.registry.init.item.PredatorSpawnEggItems;
import com.predator.common.registry.key.PredatorJukeboxSongKeys;
import mod.azure.azurelib.common.config.format.ConfigFormats;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Predator {

    public static final String MOD_ID = "avp_predator";

    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    public static void initialize() {
        // FIXME:
        AVP.config = AVP.registerConfig(AVPConfig.class, ConfigFormats.json()).getConfigInstance();

        LOGGER.info("Initializing AVP (Predator) for platform '{}'", Services.PLATFORM.getPlatformName());

        PredatorBlocks.initialize();
        PredatorBlockItems.initialize();
        PredatorItems.initialize();
        PredatorArmorMaterials.initialize();
        PredatorArmorItems.initialize();
        PredatorSpawnEggItems.initialize();
        PredatorEntityTypes.initialize();
        PredatorBlockEntityTypes.initialize();
        PredatorCreativeModeTabs.initialize();
        PredatorSoundEvents.initialize();
        PredatorJukeboxSongKeys.initialize();

        // Functionality
        PredatorEntitySpawns.initialize();
    }
}
