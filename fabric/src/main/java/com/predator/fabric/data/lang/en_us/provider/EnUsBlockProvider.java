package com.predator.fabric.data.lang.en_us.provider;

import com.avp.common.registry.AVPRegistryValidation;
import com.predator.common.registry.init.PredatorBlocks;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.world.level.block.Block;

import java.util.HashSet;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class EnUsBlockProvider {

    private static final HashSet<Block> TOUCHED_ENTRIES = new HashSet<>();

    public static final Consumer<FabricLanguageProvider.TranslationBuilder> CONSUMER = builder -> {
        addBlock(builder, PredatorBlocks.TRIP_MINE_BLOCK, "Trip Mine");
        AVPRegistryValidation.throwIfMissingEntries(
            PredatorBlocks.getAll(),
            TOUCHED_ENTRIES::contains,
            Block::getDescriptionId,
            "Block translation did not complete successfully - there are unhandled blocks that need to be handled."
        );
    };

    private static void addBlock(
        FabricLanguageProvider.TranslationBuilder translationBuilder,
        Supplier<? extends Block> blockSupplier,
        String value
    ) {
        addBlock(translationBuilder, blockSupplier.get(), value);
    }

    private static void addBlock(FabricLanguageProvider.TranslationBuilder translationBuilder, Block block, String value) {
        TOUCHED_ENTRIES.add(block);
        translationBuilder.add(block, value);
    }
}
