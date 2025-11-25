package com.predator.fabric.data;

import com.predator.fabric.data.gene_bonus_data.GeneBonusDataSubProvider;
import com.predator.fabric.data.jukebox_song.PredatorJukeboxSongsProvider;
import com.predator.fabric.data.lang.en_us.EnglishLanguageProvider;
import com.predator.fabric.data.loot.BlockLootTableProvider;
import com.predator.fabric.data.loot.EntityLootTableProvider;
import com.predator.fabric.data.model.BlockModelProvider;
import com.predator.fabric.data.model.ItemModelProvider;
import com.predator.fabric.data.recipe.RecipeProvider;
import com.predator.fabric.data.tag.PredatorBlockTagProvider;
import com.predator.fabric.data.tag.PredatorEntityTypeTagProvider;
import com.predator.fabric.data.tag.PredatorItemTagProvider;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.minecraft.core.RegistrySetBuilder;

public class PredatorDataGenerator implements DataGeneratorEntrypoint {

    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        var pack = fabricDataGenerator.createPack();
        // Language providers
        pack.addProvider(EnglishLanguageProvider::new);

        // Model providers
        pack.addProvider(BlockModelProvider::new);
        pack.addProvider(ItemModelProvider::new);

        // Recipe providers
        pack.addProvider(RecipeProvider::new);

        // Tag providers
        pack.addProvider(PredatorBlockTagProvider::new);
        pack.addProvider(PredatorEntityTypeTagProvider::new);
        pack.addProvider(PredatorItemTagProvider::new);

        // Loot providers
        pack.addProvider(BlockLootTableProvider::new);
        pack.addProvider(EntityLootTableProvider::new);

        // Jukebox Song Providers
        pack.addProvider(PredatorJukeboxSongsProvider::new);

        // Custom Providers
        pack.addProvider(GeneBonusDataSubProvider::new);
    }

    @Override
    public void buildRegistry(RegistrySetBuilder registryBuilder) {}
}
