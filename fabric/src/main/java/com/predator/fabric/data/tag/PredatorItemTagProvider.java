package com.predator.fabric.data.tag;

import com.compatibility.CommonItemTags;
import com.human.common.registry.tag.HumanItemTags;
import com.predator.Predator;
import com.predator.common.registry.init.item.PredatorArmorItems;
import com.predator.common.registry.init.item.PredatorItems;
import com.predator.common.registry.tag.PredatorItemTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.HoeItem;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.ShovelItem;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.level.block.ButtonBlock;
import net.minecraft.world.level.block.DoorBlock;
import net.minecraft.world.level.block.FenceBlock;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.TrapDoorBlock;
import net.minecraft.world.level.block.WallBlock;

import java.util.concurrent.CompletableFuture;

public class PredatorItemTagProvider extends FabricTagProvider.ItemTagProvider {

    public PredatorItemTagProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> completableFuture) {
        super(output, completableFuture);
    }

    @Override
    protected void addTags(HolderLookup.Provider wrapperLookup) {
        addArmors();
        addAutomatedTagItems();

        getOrCreateTagBuilder(PredatorItemTags.HOSTILE_WEAPONS)
            .addOptionalTag(HumanItemTags.GUNS)
            .addTag(ItemTags.AXES)
            .addTag(ItemTags.SWORDS)
            .add(
                Items.BOW,
                Items.CROSSBOW
            );

        getOrCreateTagBuilder(ItemTags.FREEZE_IMMUNE_WEARABLES)
            .addTag(PredatorItemTags.PREDATOR_ARMORS);

        addCompatibilityTags();
    }

    private void addAutomatedTagItems() {
        // Armor
        var headArmorTagProvider = getOrCreateTagBuilder(ItemTags.HEAD_ARMOR);
        var chestArmorTagProvider = getOrCreateTagBuilder(ItemTags.CHEST_ARMOR);
        var legArmorTagProvider = getOrCreateTagBuilder(ItemTags.LEG_ARMOR);
        var footArmorTagProvider = getOrCreateTagBuilder(ItemTags.FOOT_ARMOR);

        // Blocks
        var buttonTagProvider = getOrCreateTagBuilder(ItemTags.BUTTONS);
        var doorTagProvider = getOrCreateTagBuilder(ItemTags.DOORS);
        var fenceTagProvider = getOrCreateTagBuilder(ItemTags.FENCES);
        var slabTagProvider = getOrCreateTagBuilder(ItemTags.SLABS);
        var stairsTagProvider = getOrCreateTagBuilder(ItemTags.STAIRS);
        var trapdoorTagProvider = getOrCreateTagBuilder(ItemTags.TRAPDOORS);
        var wallTagBuilder = getOrCreateTagBuilder(ItemTags.WALLS);

        // Tools
        var axeTagProvider = getOrCreateTagBuilder(ItemTags.AXES);
        var hoeTagProvider = getOrCreateTagBuilder(ItemTags.HOES);
        var pickaxeTagProvider = getOrCreateTagBuilder(ItemTags.PICKAXES);
        var shovelTagProvider = getOrCreateTagBuilder(ItemTags.SHOVELS);

        // Weapons
        var swordTagProvider = getOrCreateTagBuilder(ItemTags.SWORDS);

        Predator.MOD.registries()
            .getAllHolders(BuiltInRegistries.ITEM)
            .forEach(holder -> {
                var item = holder.get();

                if (item instanceof ArmorItem armorItem) {
                    switch (armorItem.getType()) {
                        case HELMET -> headArmorTagProvider.add(item);
                        case CHESTPLATE -> chestArmorTagProvider.add(item);
                        case LEGGINGS -> legArmorTagProvider.add(item);
                        case BOOTS -> footArmorTagProvider.add(item);
                        case BODY -> { /* NO-OP */ }
                    }
                }

                if (item instanceof BlockItem blockItem) {
                    var block = blockItem.getBlock();

                    if (block instanceof ButtonBlock) {
                        buttonTagProvider.add(item);
                    }

                    if (block instanceof DoorBlock) {
                        doorTagProvider.add(item);
                    }

                    if (block instanceof FenceBlock) {
                        fenceTagProvider.add(item);
                    }

                    if (block instanceof SlabBlock) {
                        slabTagProvider.add(item);
                    }

                    if (block instanceof StairBlock) {
                        stairsTagProvider.add(item);
                    }

                    if (block instanceof TrapDoorBlock) {
                        trapdoorTagProvider.add(item);
                    }

                    if (block instanceof WallBlock) {
                        wallTagBuilder.add(item);
                    }
                }

                if (item instanceof AxeItem) {
                    axeTagProvider.add(item);
                }

                if (item instanceof HoeItem) {
                    hoeTagProvider.add(item);
                }

                if (item instanceof PickaxeItem) {
                    pickaxeTagProvider.add(item);
                }

                if (item instanceof ShovelItem) {
                    shovelTagProvider.add(item);
                }

                if (item instanceof SwordItem) {
                    swordTagProvider.add(item);
                }
            });
    }

    private void addArmors() {
        getOrCreateTagBuilder(PredatorItemTags.JUNGLE_PREDATOR_ARMOR)
            .add(
                PredatorArmorItems.JUNGLE_PREDATOR_BOOTS.get(),
                PredatorArmorItems.JUNGLE_PREDATOR_CHESTPLATE.get(),
                PredatorArmorItems.JUNGLE_PREDATOR_HELMET.get(),
                PredatorArmorItems.JUNGLE_PREDATOR_LEGGINGS.get()
            );

        getOrCreateTagBuilder(PredatorItemTags.PREDATOR_ARMORS)
            .addTag(PredatorItemTags.JUNGLE_PREDATOR_ARMOR);
    }

    private void addCompatibilityTags() {
        getOrCreateTagBuilder(CommonItemTags.MUSIC_DISCS)
            .add(PredatorItems.PREDATOR_MUSIC_DISC_1.get());
    }
}
