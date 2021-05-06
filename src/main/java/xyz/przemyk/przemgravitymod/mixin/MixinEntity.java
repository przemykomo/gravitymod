package xyz.przemyk.przemgravitymod.mixin;

import net.minecraft.command.ICommandSource;
import net.minecraft.entity.Entity;
import net.minecraft.entity.MoverType;
import net.minecraft.util.INameable;
import net.minecraft.util.math.vector.Vector3d;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Entity.class)
public abstract class MixinEntity extends net.minecraftforge.common.capabilities.CapabilityProvider<Entity> implements INameable, ICommandSource, net.minecraftforge.common.extensions.IForgeEntity {

    protected MixinEntity(Class<Entity> baseClass) {
        super(baseClass);
    }

    @Shadow protected boolean onGround;
    @Shadow public boolean verticalCollision;

    @Inject(method = "move(Lnet/minecraft/entity/MoverType;Lnet/minecraft/util/math/vector/Vector3d;)V",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/Entity;getOnPos()Lnet/minecraft/util/math/BlockPos;"))
    public void updateOnGround(MoverType moverType, Vector3d deltaMovement, CallbackInfo callbackInfo) {
        onGround = verticalCollision && deltaMovement.y > 0.0D;
    }
}
