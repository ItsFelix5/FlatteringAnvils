package flatteringanvils.mixin;

import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.client.render.entity.state.LivingEntityRenderState;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import flatteringanvils.LivingEntityRenderStateExtension;
import flatteringanvils.Main;

@Mixin(LivingEntityRenderer.class)
public class LivingEntityRendererMixin {
    @Inject(method = "updateRenderState(Lnet/minecraft/entity/LivingEntity;Lnet/minecraft/client/render/entity/state/LivingEntityRenderState;F)V", at = @At("TAIL"))
    private void updateRenderState(LivingEntity entity, LivingEntityRenderState state, float f, CallbackInfo ci) {
        ((LivingEntityRenderStateExtension) state).flatteringanvils$setHeight((float) entity.getAttributeValue(Main.HEIGHT));
    }

    @Inject(method = "setupTransforms", at = @At("TAIL"))
    private void setupTransforms(LivingEntityRenderState state, MatrixStack matrices, float bodyYaw, float baseHeight, CallbackInfo ci) {
        matrices.scale(1F, ((LivingEntityRenderStateExtension) state).flatteringanvils$getHeight(), 1F);
    }
}
