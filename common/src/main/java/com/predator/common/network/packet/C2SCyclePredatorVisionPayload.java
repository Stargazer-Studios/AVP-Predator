package com.predator.common.network.packet;

import com.just.codec.stream.StreamCodec;
import com.predator.PredatorResources;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

/**
 * Sent by the client when the player presses the {@code toggle_vision} keybind while wearing a predator helmet. The
 * server-side handler rotates the helmet's stored vision mode through
 * {@link com.predator.common.gameplay.component.PredatorVisionMode#cycleNext()}.
 * <p>
 * Carries no payload — the action is self-explanatory from the packet type.
 */
public class C2SCyclePredatorVisionPayload implements CustomPacketPayload {

    public static final C2SCyclePredatorVisionPayload INSTANCE = new C2SCyclePredatorVisionPayload();

    public static final ResourceLocation PAYLOAD_ID = PredatorResources.location("cycle_predator_vision");

    public static final Type<C2SCyclePredatorVisionPayload> TYPE = new Type<>(PAYLOAD_ID);

    public static final StreamCodec<C2SCyclePredatorVisionPayload> CODEC = StreamCodec.unit(INSTANCE);

    private C2SCyclePredatorVisionPayload() {}

    @Override
    public @NotNull Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
