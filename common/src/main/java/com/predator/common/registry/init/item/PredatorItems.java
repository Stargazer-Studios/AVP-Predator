package com.predator.common.registry.init.item;

import com.avp.common.registry.AVPDeferredHolder;
import com.predator.PredatorResources;
import com.predator.common.registry.init.PredatorTiers;
import com.predator.common.registry.key.PredatorJukeboxSongKeys;
import com.avp.service.Services;
import com.predator.common.gameplay.item.ShurikenItem;
import com.predator.common.gameplay.item.SmartDiscItem;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.DiscFragmentItem;
import net.minecraft.world.item.HoeItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.ShovelItem;
import net.minecraft.world.item.SwordItem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Supplier;

public class PredatorItems {

    private static final List<AVPDeferredHolder<? extends Item>> HOLDERS = new ArrayList<>();

    public static List<AVPDeferredHolder<? extends Item>> getAll() {
        return Collections.unmodifiableList(HOLDERS);
    }

    public static final AVPDeferredHolder<Item> PREDATOR_MUSIC_DISC_1 = register(
        "predator_music_disc_1",
        new Item.Properties().stacksTo(1).rarity(Rarity.RARE).jukeboxPlayable(PredatorJukeboxSongKeys.PREDATOR_MUSIC_1)
    );

    public static final AVPDeferredHolder<Item> PREDATOR_MUSIC_DISC_1_FRAGMENT = register(
        "predator_music_disc_1_fragment",
        () -> new DiscFragmentItem(new Item.Properties())
    );

    public static final AVPDeferredHolder<Item> SHURIKEN = register("shuriken", ShurikenItem::new);

    public static final AVPDeferredHolder<Item> SMART_DISC = register("smart_disc", SmartDiscItem::new);

    public static final AVPDeferredHolder<Item> VERITANIUM_AXE = register(
        "veritanium_axe",
        () -> new AxeItem(
            PredatorTiers.VERITANIUM,
            new Item.Properties().fireResistant().attributes(AxeItem.createAttributes(PredatorTiers.VERITANIUM, 6.0F, -3.1F))
        )
    );

    public static final AVPDeferredHolder<Item> VERITANIUM_HOE = register(
        "veritanium_hoe",
        () -> new HoeItem(
            PredatorTiers.VERITANIUM,
            new Item.Properties().fireResistant().attributes(HoeItem.createAttributes(PredatorTiers.VERITANIUM, -2.0F, -1.0F))
        )
    );

    public static final AVPDeferredHolder<Item> VERITANIUM_PICKAXE = register(
        "veritanium_pickaxe",
        () -> new PickaxeItem(
            PredatorTiers.VERITANIUM,
            new Item.Properties().fireResistant().attributes(PickaxeItem.createAttributes(PredatorTiers.VERITANIUM, 1.0F, -2.8F))
        )
    );

    public static final AVPDeferredHolder<Item> VERITANIUM_SHARD = register("veritanium_shard", new Item.Properties().fireResistant());

    public static final AVPDeferredHolder<Item> VERITANIUM_SHOVEL = register(
        "veritanium_shovel",
        () -> new ShovelItem(
            PredatorTiers.VERITANIUM,
            new Item.Properties().fireResistant().attributes(ShovelItem.createAttributes(PredatorTiers.VERITANIUM, 1.5F, -3.0F))
        )
    );

    public static final AVPDeferredHolder<Item> VERITANIUM_SWORD = register(
        "veritanium_sword",
        () -> new SwordItem(
            PredatorTiers.VERITANIUM,
            new Item.Properties().fireResistant().attributes(SwordItem.createAttributes(PredatorTiers.VERITANIUM, 3, -2.4F))
        )
    );

    public static AVPDeferredHolder<Item> register(String name) {
        return register(name, new Item.Properties());
    }

    public static AVPDeferredHolder<Item> register(String name, Item.Properties properties) {
        return register(name, () -> new Item(properties));
    }

    public static <T extends Item> AVPDeferredHolder<T> register(String name, Supplier<T> itemSupplier) {
        var holder = Services.REGISTRY.register(BuiltInRegistries.ITEM, PredatorResources.location(name), itemSupplier);
        HOLDERS.add(holder);
        return holder;
    }

    public static void initialize() {}
}
