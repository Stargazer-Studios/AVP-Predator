package com.predator.mixin;

import com.avp.common.util.AVPPredicates;
import com.predator.common.registry.tag.PredatorItemTags;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public abstract class MixinLivingEntity_ApplyArmorEffects extends Entity {

    protected MixinLivingEntity_ApplyArmorEffects(EntityType<?> entityType, Level level) {
        super(entityType, level);
    }

    @Inject(at = @At("HEAD"), method = "tick")
    public void tick(CallbackInfo callbackInfo) {
        var self = LivingEntity.class.cast(this);

        if (isWearingFullPredatorArmor(self)) {
            self.addEffect(new MobEffectInstance(MobEffects.JUMP, 5, 0, true, false, true));
            self.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 5, 0, true, false, true));
        }
    }

    @Unique
    private boolean isWearingFullPredatorArmor(LivingEntity self) {
        return AVPPredicates.hasFullArmorSetMatching(self, (itemStack -> itemStack.is(PredatorItemTags.PREDATOR_ARMORS)));
    }
}
