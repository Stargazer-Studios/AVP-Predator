package com.predator.common.gameplay.block.property;

import com.blib.common.gameplay.block.property.BlockPropertyBuilder;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;

import java.util.function.Supplier;

public class PredatorBlockProperties {

    public static final Supplier<BlockPropertyBuilder> TRIP_MINE_SUPPLIER = () -> BlockPropertyBuilder.of()
        .instrument(NoteBlockInstrument.IRON_XYLOPHONE)
        .mapColor(MapColor.SAND)
        .requiresCorrectToolForDrops()
        .sound(SoundType.COPPER)
        .strength(7, 8);

    public static final BlockPropertyBuilder TRIP_MINE = TRIP_MINE_SUPPLIER.get();
}
