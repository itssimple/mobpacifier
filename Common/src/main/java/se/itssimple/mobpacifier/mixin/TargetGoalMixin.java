package se.itssimple.mobpacifier.mixin;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.target.TargetGoal;
import net.minecraft.world.item.Items;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(TargetGoal.class)
abstract class TargetGoalMixin {
    @Shadow
    protected @Final Mob mob;

    @Inject(method = "canContinueToUse", at = @At("HEAD"), cancellable = true)
    public void preventTargeting(CallbackInfoReturnable<Boolean> cir) {
        TargetGoal goal = (TargetGoal) (Object) this;
        LivingEntity target = mob.getTarget();

        if (target != null && target.getOffhandItem().getItem() == Items.ROTTEN_FLESH) {
            mob.setTarget(null);
            mob.setAggressive(false);
            goal.stop();
            cir.setReturnValue(false);
        }
    }

}
