package com.predator.client.vision;

import com.predator.common.gameplay.component.PredatorVisionMode;

/**
 * Client-side state for the sweeping erosion wipe that plays when the predator helmet's vision mode changes. Started
 * locally by the keybind handler (so the visual is responsive without waiting for a server round-trip); the actual
 * mode-persistence packet is sent in parallel.
 * <p>
 * The wipe is purely cosmetic: {@link #progress()} drives the shader's {@code wipeLineX} uniform, and the recorded
 * old/new modes drive the {@code oldMode}/{@code newMode} int uniforms (passed as enum ordinals) so the shader knows
 * which post-effect coloring to display on each side of the sweep band.
 */
public final class PredatorVisionTransition {

    public static final long DEFAULT_DURATION_MS = 200L;

    private static volatile long durationMs = DEFAULT_DURATION_MS;

    private static long startTimeMs;

    private static PredatorVisionMode oldMode = PredatorVisionMode.REGULAR;

    private static PredatorVisionMode newMode = PredatorVisionMode.REGULAR;

    private PredatorVisionTransition() {
        throw new UnsupportedOperationException();
    }

    public static void begin(PredatorVisionMode from, PredatorVisionMode to) {
        startTimeMs = System.currentTimeMillis();
        oldMode = from;
        newMode = to;
    }

    /** @return progress in [0, 1]; 1 means transition complete (or never started). */
    public static float progress() {
        if (startTimeMs == 0) {
            return 1.0F;
        }

        var elapsed = System.currentTimeMillis() - startTimeMs;

        if (elapsed >= durationMs) {
            return 1.0F;
        }

        return elapsed / (float) durationMs;
    }

    public static boolean isActive() {
        return startTimeMs != 0 && progress() < 1.0F;
    }

    public static PredatorVisionMode oldMode() {
        return oldMode;
    }

    public static PredatorVisionMode newMode() {
        return newMode;
    }

    /**
     * @return the active transition duration in milliseconds. Defaults to {@link #DEFAULT_DURATION_MS} but can be
     *         overridden at runtime via the {@code /avp_predator vision_transition_duration <ms>} debug command.
     */
    public static long durationMs() {
        return durationMs;
    }

    /**
     * Override the transition duration. Volatile because the debug command may run on the integrated-server thread
     * while {@link #progress()} reads it on the render thread.
     */
    public static void setDurationMs(long newDurationMs) {
        durationMs = newDurationMs;
    }
}
