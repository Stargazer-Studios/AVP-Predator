package com.predator.compatibility.avp_alien;

import com.alien.client.render.entity.head.EntityHeadData;
import net.minecraft.world.phys.Vec3;

public class PredatorEntityHeadData {

    private static final double MULTIPLIER = 1 / 16.0;

    public static final EntityHeadData YAUTJA = adjust(vec3(1, 4, 3), vec3(-4.0699, 31.8255, -4.43554), vec3(-0.3347, 32, 1.8756));

    private static EntityHeadData adjust(Vec3 size, Vec3 position, Vec3 pivot) {
        var adjustedSize = size.multiply(MULTIPLIER, MULTIPLIER, MULTIPLIER);
        var adjustedPosition = position.multiply(MULTIPLIER, MULTIPLIER, MULTIPLIER);
        var adjustedPivot = pivot.multiply(MULTIPLIER, MULTIPLIER, MULTIPLIER);

        return new EntityHeadData(adjustedSize, adjustedPosition, adjustedPivot);
    }

    private static Vec3 vec3(double x, double y, double z) {
        return new Vec3(x, y, z);
    }
}
