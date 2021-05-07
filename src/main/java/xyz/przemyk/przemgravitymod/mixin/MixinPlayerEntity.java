package xyz.przemyk.przemgravitymod.mixin;

import net.minecraft.entity.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntity.class)
public abstract class MixinPlayerEntity extends LivingEntity {

    protected MixinPlayerEntity(EntityType<? extends LivingEntity> p_i48577_1_, World p_i48577_2_) {
        super(p_i48577_1_, p_i48577_2_);
    }

    @Override
    public void move(MoverType moverType, Vector3d deltaMovement) {
        if (moverType == MoverType.SELF) {
            super.move(moverType, deltaMovement.reverse());
        } else {
            super.move(moverType, deltaMovement);
        }
    }

    @Override
    public void moveRelative(float p_213309_1_, Vector3d p_213309_2_) {
        Vector3d vector3d = getInputVector(p_213309_2_, p_213309_1_, this.yRot + 180);
        this.setDeltaMovement(this.getDeltaMovement().add(vector3d));
    }

    @Override
    public void turn(double a, double b) {
        super.turn(-a, -b);
    }

    @Override
    public float getStandingEyeHeight(Pose pose, EntitySize entitySize) {
        return entitySize.height * 0.15F;
    }

    @Inject(method = "jumpFromGround", at = @At("HEAD"))
    public void adjustYRotBeforeJump(CallbackInfo ci) {
        yRot += 180f;
    }

    @Inject(method = "jumpFromGround", at = @At("RETURN"))
    public void adjustYRotAfterJump(CallbackInfo ci) {
        yRot -= 180f;
    }

//    @Override ????
//    protected float tickHeadTurn(float p_110146_1_, float movedLastTickLength) {
//        float f = MathHelper.wrapDegrees(p_110146_1_ - this.yBodyRot);
//        this.yBodyRot += f * 0.3F;
//        float f1 = MathHelper.wrapDegrees(this.yRot - this.yBodyRot);
//        boolean flag = f1 < -90.0F || f1 >= 90.0F;
//        if (f1 < -75.0F) {
//            f1 = -75.0F;
//        }
//
//        if (f1 >= 75.0F) {
//            f1 = 75.0F;
//        }
//
//        this.yBodyRot = this.yRot - f1;
//        if (f1 * f1 > 2500.0F) {
//            this.yBodyRot += f1 * 0.2F;
//        }
//
//        if (flag) {
//            movedLastTickLength *= -1.0F;
//        }
//
//        return movedLastTickLength;
//    }
}


