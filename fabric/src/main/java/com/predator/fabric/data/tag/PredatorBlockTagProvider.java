package com.predator.fabric.data.tag;

import com.predator.common.registry.init.PredatorBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.tags.BlockTags;

import java.util.concurrent.CompletableFuture;

public class PredatorBlockTagProvider extends FabricTagProvider.BlockTagProvider {

    public PredatorBlockTagProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> completableFuture) {
        super(output, completableFuture);
    }

    @Override
    protected void addTags(HolderLookup.Provider wrapperLookup) {
        getOrCreateTagBuilder(BlockTags.MINEABLE_WITH_PICKAXE)
            .add(
                PredatorBlocks.TRIP_MINE_BLOCK.get()
            );

        getOrCreateTagBuilder(BlockTags.NEEDS_IRON_TOOL)
            .add(
                PredatorBlocks.TRIP_MINE_BLOCK.get()
            );
    }
}
