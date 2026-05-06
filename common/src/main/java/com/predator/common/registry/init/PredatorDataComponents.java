package com.predator.common.registry.init;

import com.blib.api.common.codec.v1.stream.adapter.J2MStreamCodecAdapter;
import com.blib.api.common.registry.v1.BLibHolder;
import com.blib.api.common.registry.v1.BLibRegistry;
import com.predator.Predator;
import com.predator.common.gameplay.component.PredatorVisionMode;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.BuiltInRegistries;

import java.util.function.UnaryOperator;

public class PredatorDataComponents {

    private static final BLibRegistry<DataComponentType<?>> REGISTRY =
        Predator.MOD.registries().create(BuiltInRegistries.DATA_COMPONENT_TYPE);

    public static final BLibHolder<DataComponentType<PredatorVisionMode>> VISION_MODE = create(
        "vision_mode",
        builder -> builder.persistent(PredatorVisionMode.CODEC)
            .networkSynchronized(new J2MStreamCodecAdapter<>(PredatorVisionMode.STREAM_CODEC))
            .cacheEncoding()
    );

    private static <T> BLibHolder<DataComponentType<T>> create(
        String id,
        UnaryOperator<DataComponentType.Builder<T>> unaryOperator
    ) {
        return REGISTRY.createHolder(id, () -> unaryOperator.apply(DataComponentType.builder()).build());
    }

    public static void initialize() {
        REGISTRY.registerAll();
    }
}
