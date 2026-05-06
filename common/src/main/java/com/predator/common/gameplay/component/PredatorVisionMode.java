package com.predator.common.gameplay.component;

import com.just.codec.stream.StreamCodec;
import com.just.codec.stream.schema.StreamCodecSchema;
import com.mojang.serialization.Codec;
import com.predator.common.registry.tag.PredatorEntityTypeTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Vision mode persisted on a predator helmet stack. The helmet's wearer toggles between modes via the
 * {@code toggle_vision} keybind, which sends a C2S packet that rotates this value through {@link #cycleNext()}.
 * <p>
 * Each non-regular mode owns a {@link #visibleTag()} (entities the mode renders) and a {@link #hotTag()} (entities that
 * read as fully-lit in that mode). Adding a future vision is one new enum case + one tag pair.
 * <p>
 * The vision post-effect's {@code enabledWhen} supplier reads the equipped helmet's component each frame, so taking the
 * helmet off automatically disables the effect — the helmet's stored mode is preserved and resumes on re-equip.
 */
public enum PredatorVisionMode {

    /** No shader effect. Default for newly-crafted helmets. */
    REGULAR(null, null),

    /** Thermal-vision post-effect. Visible: warm-blooded biology. Hot: fire creatures, lava-dwellers. */
    THERMAL(PredatorEntityTypeTags.THERMAL_VISIBLE, PredatorEntityTypeTags.THERMAL_HOT),

    /**
     * Electromagnetic-vision post-effect. Visible: end-realm beings. No hot/cold concept — entities are either EM or
     * they aren't, with no per-mob brightness boost.
     */
    ELECTROMAGNETIC(PredatorEntityTypeTags.EM_VISIBLE, null);

    private final @Nullable TagKey<EntityType<?>> visibleTag;

    private final @Nullable TagKey<EntityType<?>> hotTag;

    PredatorVisionMode(@Nullable TagKey<EntityType<?>> visibleTag, @Nullable TagKey<EntityType<?>> hotTag) {
        this.visibleTag = visibleTag;
        this.hotTag = hotTag;
    }

    /** @return the entity-type tag whose members are rendered in this mode, or {@code null} for {@link #REGULAR}. */
    public @Nullable TagKey<EntityType<?>> visibleTag() {
        return visibleTag;
    }

    /** @return the entity-type tag whose members are floored at max block-light in this mode, or {@code null}. */
    public @Nullable TagKey<EntityType<?>> hotTag() {
        return hotTag;
    }

    public PredatorVisionMode cycleNext() {
        var values = values();
        return values[(ordinal() + 1) % values.length];
    }

    public static final Codec<PredatorVisionMode> CODEC = Codec.STRING.xmap(
        PredatorVisionMode::valueOf,
        PredatorVisionMode::name
    );

    public static final StreamCodec<PredatorVisionMode> STREAM_CODEC = new StreamCodec<>() {

        @Override
        public @NotNull <T> PredatorVisionMode decode(@NotNull StreamCodecSchema<T> streamCodecSchema, @NotNull T input) {
            var ordinal = streamCodecSchema.readVarInt(input);
            var values = PredatorVisionMode.values();

            if (ordinal < 0 || ordinal >= values.length) {
                throw new IllegalArgumentException("Invalid PredatorVisionMode ordinal: " + ordinal);
            }

            return values[ordinal];
        }

        @Override
        public <T> void encode(@NotNull StreamCodecSchema<T> streamCodecSchema, @NotNull T input, @NotNull PredatorVisionMode value) {
            streamCodecSchema.writeVarInt(input, value.ordinal());
        }
    };
}
