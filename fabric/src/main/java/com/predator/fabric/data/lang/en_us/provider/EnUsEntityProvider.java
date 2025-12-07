package com.predator.fabric.data.lang.en_us.provider;

import com.predator.Predator;
import com.predator.common.registry.init.PredatorEntityTypes;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.world.entity.EntityType;

import java.util.HashSet;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class EnUsEntityProvider {

    private static final HashSet<EntityType<?>> TOUCHED_ENTRIES = new HashSet<>();

    public static final Consumer<FabricLanguageProvider.TranslationBuilder> CONSUMER = builder -> {
        addEntity(builder, PredatorEntityTypes.SHURIKEN, "Shuriken");
        addEntity(builder, PredatorEntityTypes.SMART_DISC, "Smart Disc");
        addEntity(builder, PredatorEntityTypes.YAUTJA, "Yautja");

        var missingEntries = PredatorEntityTypes.REGISTRY.computeMissingEntries(TOUCHED_ENTRIES);

        if (!missingEntries.isEmpty()) {
            var unhandledBlocksStrings = String.join("\n", missingEntries.stream().map(EntityType::getDescriptionId).toList());

            Predator.LOGGER.error(
                "Detected {} unhandled entries. Entries:\n{}",
                missingEntries.size(),
                unhandledBlocksStrings
            );

            throw new IllegalStateException(
                "Entity type translation did not complete successfully - there are unhandled entity types that need to be handled."
            );
        }
    };

    private static void addEntity(
        FabricLanguageProvider.TranslationBuilder translationBuilder,
        Supplier<? extends EntityType<?>> entityTypeSupplier,
        String value
    ) {
        addEntity(translationBuilder, entityTypeSupplier.get(), value);
    }

    private static void addEntity(FabricLanguageProvider.TranslationBuilder translationBuilder, EntityType<?> entityType, String value) {
        TOUCHED_ENTRIES.add(entityType);
        translationBuilder.add(entityType, value);
    }
}
