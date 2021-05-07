package xyz.przemyk.przemgravitymod.mixin;

import net.minecraft.client.renderer.entity.model.AgeableModel;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.entity.model.IHasArm;
import net.minecraft.client.renderer.entity.model.IHasHead;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(BipedModel.class)
public abstract class MixinBipedModel<T extends LivingEntity> extends AgeableModel<T> implements IHasArm, IHasHead {

    @Shadow public ModelRenderer head;

    @Inject(method = "setupAnim", at = @At("RETURN"))
    public void changeRotation(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, CallbackInfo ci) {
//        float f = MathHelper.rotLerp(partialTicks, entityIn.yBodyRotO, entityIn.yBodyRot);
//        float f1 = MathHelper.rotLerp(partialTicks, entityIn.yHeadRotO, entityIn.yHeadRot);
//        float newNetHeadYaw = f1 - f;
//        head.yRot = netHeadYaw * ((float)Math.PI / 180F);
        head.xRot = -headPitch * ((float)Math.PI / 180F);
//        body.yRot *= -1f;
    }
}
