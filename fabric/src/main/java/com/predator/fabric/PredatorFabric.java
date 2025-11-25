package com.predator.fabric;

import com.avp.fabric.data.loot.LootTableModifier;
import com.predator.Predator;
import net.fabricmc.api.ModInitializer;

public class PredatorFabric implements ModInitializer {

    @Override
    public void onInitialize() {
        Predator.initialize();

        // Functionality
        LootTableModifier.initialize();
    }
}
