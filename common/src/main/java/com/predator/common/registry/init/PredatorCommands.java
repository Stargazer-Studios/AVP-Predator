package com.predator.common.registry.init;

import com.blib.api.common.registry.v1.impl.BLibCommandRegistry;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.predator.Predator;
import com.predator.common.command.PredatorVisionTransitionDurationCommand;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;

public class PredatorCommands {

    private static final BLibCommandRegistry REGISTRY = Predator.MOD.registries().createCommandRegistry();

    public static void initialize() {
        REGISTRY.register(
            LiteralArgumentBuilder.<CommandSourceStack>literal(Predator.MOD.id())
                .then(
                    Commands.literal("debug")
                        .requires(source -> source.hasPermission(Commands.LEVEL_GAMEMASTERS))
                        .then(PredatorVisionTransitionDurationCommand.create())
                )
        );
    }
}
