package com.predator.common.command;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.arguments.LongArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.predator.client.vision.PredatorVisionTransition;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;

/**
 * Debug subcommand for tuning the vision-mode transition (erosion-wipe) duration at runtime.
 * <p>
 * Usage:
 * <ul>
 * <li>{@code /avp_predator debug vision_transition_duration} — print the current value.</li>
 * <li>{@code /avp_predator debug vision_transition_duration <ms>} — set the duration in milliseconds.</li>
 * <li>{@code /avp_predator debug vision_transition_duration reset} — restore the default
 * ({@link PredatorVisionTransition#DEFAULT_DURATION_MS}).</li>
 * </ul>
 * <p>
 * Lives under {@code /avp_predator debug …} so the parent {@code debug} subtree carries the gamemaster permission gate
 * and the command is grouped with any other dev-only tools we add later. Note that the underlying {@code durationMs} is
 * a client-side static — running this command in singleplayer is straightforward (the integrated server and client
 * share the JVM), but in multiplayer the server-side execution wouldn't actually change the client's wipe; that's
 * acceptable since this is a dev tool.
 */
public final class PredatorVisionTransitionDurationCommand {

    private static final long MIN_DURATION_MS = 1L;

    private static final long MAX_DURATION_MS = 10_000L;

    private PredatorVisionTransitionDurationCommand() {
        throw new UnsupportedOperationException();
    }

    public static LiteralArgumentBuilder<CommandSourceStack> create() {
        return Commands.literal("vision_transition_duration")
            .executes(PredatorVisionTransitionDurationCommand::print)
            .then(Commands.literal("reset").executes(PredatorVisionTransitionDurationCommand::reset))
            .then(
                Commands.argument("ms", LongArgumentType.longArg(MIN_DURATION_MS, MAX_DURATION_MS))
                    .executes(ctx -> set(ctx, LongArgumentType.getLong(ctx, "ms")))
            );
    }

    private static int print(CommandContext<CommandSourceStack> context) {
        var current = PredatorVisionTransition.durationMs();
        context.getSource()
            .sendSuccess(() -> Component.literal("Vision transition duration: " + current + " ms"), false);
        return Command.SINGLE_SUCCESS;
    }

    private static int set(CommandContext<CommandSourceStack> context, long ms) {
        PredatorVisionTransition.setDurationMs(ms);
        context.getSource()
            .sendSuccess(() -> Component.literal("Vision transition duration set to " + ms + " ms"), false);
        return Command.SINGLE_SUCCESS;
    }

    private static int reset(CommandContext<CommandSourceStack> context) {
        PredatorVisionTransition.setDurationMs(PredatorVisionTransition.DEFAULT_DURATION_MS);
        context.getSource()
            .sendSuccess(
                () -> Component.literal(
                    "Vision transition duration reset to " + PredatorVisionTransition.DEFAULT_DURATION_MS + " ms"
                ),
                false
            );
        return Command.SINGLE_SUCCESS;
    }
}
