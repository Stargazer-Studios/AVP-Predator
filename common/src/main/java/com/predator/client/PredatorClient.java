package com.predator.client;

import com.avp.service.Services;
import com.blib.service.BLibServices;
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

    public static void initialize() {
        registerArmorRenderers();
        registerBlockEntityRenderers();
        registerEntityRenderers();
        registerItemRenderers();
    }

    private static void registerArmorRenderers() {
        BLibServices.CLIENT_REGISTRY.registerArmorRenderer(
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
        BLibServices.CLIENT_REGISTRY.registerBlockEntityRenderer(
            PredatorBlockEntityTypes.TRIP_MINE,
            (BlockEntityRendererProvider.Context rendererDispatcherIn) -> new TripMineRenderer()
        );
    }

    private static void registerEntityRenderers() {
        BLibServices.CLIENT_REGISTRY.registerEntityRenderer(PredatorEntityTypes.SHURIKEN, SpinningItemRenderer::new);
        BLibServices.CLIENT_REGISTRY.registerEntityRenderer(PredatorEntityTypes.SMART_DISC, SpinningItemRenderer::new);
        BLibServices.CLIENT_REGISTRY.registerEntityRenderer(PredatorEntityTypes.YAUTJA, YautjaRenderer::new);
    }

    private static void registerItemRenderers() {
        BLibServices.CLIENT_REGISTRY.registerItemRenderer(PredatorBlockItems.TRIP_MINE_BLOCK, name -> TripMineItemRenderer::new);
    }
}
