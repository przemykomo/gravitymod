package xyz.przemyk.przemgravitymod.mixin;

import net.minecraft.entity.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;

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
}


