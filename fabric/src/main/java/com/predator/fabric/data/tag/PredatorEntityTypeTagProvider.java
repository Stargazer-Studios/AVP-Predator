package com.predator.fabric.data.tag;

import com.avp.common.registry.tag.AVPEntityTypeTags;
import com.predator.common.registry.init.PredatorEntityTypes;
import com.predator.common.registry.tag.PredatorEntityTypeTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.core.HolderLookup;

import java.util.concurrent.CompletableFuture;

public class PredatorEntityTypeTagProvider extends FabricTagProvider.EntityTypeTagProvider {

    public PredatorEntityTypeTagProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> completableFuture) {
        super(output, completableFuture);
    }

    @Override
    protected void addTags(HolderLookup.Provider wrapperLookup) {
        addPredators();
        addRadiationResistant();
    }

    private void addPredators() {
        getOrCreateTagBuilder(PredatorEntityTypeTags.PREDATORS)
            .add(PredatorEntityTypes.YAUTJA.get());
    }

    private void addRadiationResistant() {
        getOrCreateTagBuilder(AVPEntityTypeTags.RADIATION_RESISTANT)
            .addTag(PredatorEntityTypeTags.PREDATORS);
    }
}
