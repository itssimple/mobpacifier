package se.itssimple.mobpacifier.fabric.mixin;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.RangedBowAttackGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.RangedAttackMob;
import net.minecraft.world.item.Items;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(RangedBowAttackGoal.class)
abstract class RangedBowAttackGoalMixin<T extends Monster & RangedAttackMob> {
    @Shadow
    private @Final T mob;

    @Inject(method = "canContinueToUse", at = @At("HEAD"), cancellable = true)
    public void preventTargeting(CallbackInfoReturnable<Boolean> cir) {
        RangedBowAttackGoal goal = (RangedBowAttackGoal) (Object) this;
        LivingEntity target = mob.getTarget();

        if (target != null && target.getOffhandItem().getItem() == Items.ROTTEN_FLESH) {
            mob.setTarget(null);
            mob.setAggressive(false);
            goal.stop();
            cir.setReturnValue(false);
        }
    }

    @Inject(method = "tick", at = @At("HEAD"), cancellable = true)
    public void preventTargetingTick(CallbackInfo ci) {
        LivingEntity target = mob.getTarget();

        if (target != null && target.getOffhandItem().getItem() == Items.ROTTEN_FLESH) {
            mob.setTarget(null);
            mob.setAggressive(false);
        }
    }
}
