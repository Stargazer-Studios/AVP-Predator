package com.predator.client;

import com.blib.client.BLibClientMod;
import com.predator.Predator;
import com.predator.client.render.armor.JunglePredatorArmorRenderer;
import com.predator.client.render.block.TripMineRenderer;
import com.predator.client.render.entity.YautjaRenderer;
import com.predator.client.render.item.SpinningItemRenderer;
import com.predator.client.render.item.TripMineItemRenderer;
import com.predator.common.registry.init.PredatorBlockEntityTypes;
import com.predator.common.registry.init.PredatorEntityTypes;
import com.predator.common.registry.init.item.PredatorArmorItems;
import com.predator.common.registry.init.item.PredatorBlockItems;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;

import java.util.List;

public class PredatorClient {

    private static final BLibClientMod MOD = BLibClientMod.createFor(Predator.MOD);

    public static void initialize() {
        registerArmorRenderers();
        registerBlockEntityRenderers();
        registerEntityRenderers();
        registerItemRenderers();
        MOD.initialize();
    }

    private static void registerArmorRenderers() {
        MOD.registries()
            .registerArmorRenderer(
                JunglePredatorArmorRenderer::new,
                List.of(
                    PredatorArmorItems.JUNGLE_PREDATOR_HELMET,
                    PredatorArmorItems.JUNGLE_PREDATOR_CHESTPLATE,
                    PredatorArmorItems.JUNGLE_PREDATOR_LEGGINGS,
                    PredatorArmorItems.JUNGLE_PREDATOR_BOOTS
                )
            );
    }

    private static void registerBlockEntityRenderers() {
        MOD.registries()
            .registerBlockEntityRenderer(
                PredatorBlockEntityTypes.TRIP_MINE,
                (BlockEntityRendererProvider.Context rendererDispatcherIn) -> new TripMineRenderer()
            );
    }

    private static void registerEntityRenderers() {
        MOD.registries().registerEntityRenderer(PredatorEntityTypes.SHURIKEN, SpinningItemRenderer::new);
        MOD.registries().registerEntityRenderer(PredatorEntityTypes.SMART_DISC, SpinningItemRenderer::new);
        MOD.registries().registerEntityRenderer(PredatorEntityTypes.YAUTJA, YautjaRenderer::new);
    }

    private static void registerItemRenderers() {
        MOD.registries().registerItemRenderer(PredatorBlockItems.TRIP_MINE_BLOCK, name -> TripMineItemRenderer::new);
    }
}
