package xyz.przemyk.przemgravitymod.client;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraftforge.client.event.EntityViewRenderEvent;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class ClientEvents {
    @SubscribeEvent
    public static void updateCamera(EntityViewRenderEvent.CameraSetup event) {
        event.setRoll(180);
    }

    @SubscribeEvent
    public static void playerRenderPre(RenderPlayerEvent.Pre event) {
        MatrixStack matrixStack = event.getMatrixStack();
        PlayerEntity playerEntity = event.getPlayer();
        matrixStack.pushPose();
        matrixStack.mulPose(Vector3f.XP.rotationDegrees(180));
        matrixStack.mulPose(Vector3f.YP.rotationDegrees(180 + 2 * playerEntity.yHeadRot));
        matrixStack.translate(0, -playerEntity.getDimensions(playerEntity.getPose()).height, 0);
    }

    @SubscribeEvent
    public static void playerRenderPost(RenderPlayerEvent.Post event) {
        event.getMatrixStack().popPose();
    }
}
