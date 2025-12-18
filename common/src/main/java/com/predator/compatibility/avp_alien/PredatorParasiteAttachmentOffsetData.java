package com.predator.compatibility.avp_alien;

import com.alien.client.render.entity.head.EntityHeadData;
import com.alien.client.render.entity.parasite.attachment.ParasiteHeadAttachmentOffsetData;
import net.minecraft.world.entity.Entity;

public class PredatorParasiteAttachmentOffsetData {

    public static final ParasiteHeadAttachmentOffsetData YAUTJA = new ParasiteHeadAttachmentOffsetData(
        PredatorParasiteAttachmentOffsetData::yautjaVerticalOffset,
        PredatorParasiteAttachmentOffsetData::yautjaFaceOffset
    );

    private static double yautjaVerticalOffset(EntityHeadData data, Entity parasite) {
        return -data.size().y - (data.size().y * 2);
    }

    private static double yautjaFaceOffset(EntityHeadData data, Entity parasite) {
        return data.size().z - (data.size().z / 24);
    }

}
