package com.predator.fabric.data.tag;

import com.avp.common.registry.tag.AVPEntityTypeTags;
import com.predator.common.registry.tag.PredatorEntityTypeTags;
import com.predator.common.registry.init.PredatorEntityTypes;
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
        addHatedByXenomorphs();
        addHosts();
        addPredators();
        addRadiationResistant();
    }

    private void addHatedByXenomorphs() {
        getOrCreateTagBuilder(AVPEntityTypeTags.HATED_BY_XENOMORPHS)
            .addTag(PredatorEntityTypeTags.PREDATORS);
    }

    private void addPredators() {
        getOrCreateTagBuilder(PredatorEntityTypeTags.PREDATORS)
            .add(PredatorEntityTypes.YAUTJA.get());
    }

    private void addRadiationResistant() {
        getOrCreateTagBuilder(AVPEntityTypeTags.RADIATION_RESISTANT)
            .addTag(PredatorEntityTypeTags.PREDATORS);
    }

    private void addHosts() {
        getOrCreateTagBuilder(AVPEntityTypeTags.HOSTS)
            .add(
                PredatorEntityTypes.YAUTJA.get()
            );
    }
}
