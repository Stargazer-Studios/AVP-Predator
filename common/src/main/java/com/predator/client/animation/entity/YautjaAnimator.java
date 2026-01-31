package com.predator.client.animation.entity;

import com.blib.api.client.animation.v1.animator.AzAnimationContext;
import com.blib.api.client.animation.v1.animator.AzAnimatorConfig;
import com.blib.api.client.animation.v1.animator.AzEntityAnimator;
import com.blib.api.client.animation.v1.controller.AzAnimationController;
import com.blib.api.client.animation.v1.controller.AzAnimationControllerContainer;
import com.predator.PredatorResources;
import com.predator.client.animation.BasicAnimationUtils;
import com.predator.common.gameplay.entity.living.yautja.Yautja;
import com.predator.common.gameplay.entity.living.yautja.YautjaAnimationRefs;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public class YautjaAnimator extends AzEntityAnimator<Yautja> {

    private static final String NAME = "yautja";

    private static final ResourceLocation ANIMATION = PredatorResources.entityAnimationLocation(NAME);

    public YautjaAnimator() {
        super(AzAnimatorConfig.defaultConfig());
    }

    @Override
    public void registerControllers(AzAnimationControllerContainer<Yautja> animationControllerContainer) {
        animationControllerContainer.add(
            AzAnimationController.builder(this, YautjaAnimationRefs.FULL_BODY_CONTROLLER_NAME)
                .setTransitionLength(5)
                .build()
        );
    }

    @Override
    public @NotNull ResourceLocation getAnimationLocation(Yautja animatable) {
        return ANIMATION;
    }

    @Override
    public void setCustomAnimations(Yautja animatable, float partialTick) {
        showHelmet(animatable, context());
        showWristBlades(animatable, context());
        BasicAnimationUtils.applyHeadRotations(animatable, context(), partialTick, "gNeckUpper", 0F);
        BasicAnimationUtils.applyLimbRotations(
            animatable,
            context(),
            partialTick,
            "gLeftArm",
            "gRightArm",
            "gLeftLeg",
            "gRightLeg",
            0F,
            0F
        );
    }

    private static void showWristBlades(Yautja entity, AzAnimationContext<?> context) {
        var bakedModel = context.boneCache().getBakedModel();
        var blade = bakedModel.getBoneOrNull("gWristBlade");

        if (blade != null) {
            blade.setHidden(!entity.getMainHandItem().isEmpty() && !entity.isAggressive());
        }
    }

    private static void showHelmet(Yautja entity, AzAnimationContext<?> context) {
        var bakedModel = context.boneCache().getBakedModel();
        var helmet = bakedModel.getBoneOrNull("gArmorMask");

        if (helmet != null) {
            helmet.setHidden(!entity.hasMask());
        }
    }
}
