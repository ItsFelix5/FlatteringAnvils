package flatteringanvils.mixin;

import flatteringanvils.Sizes;
import net.minecraft.entity.*;
import net.minecraft.entity.damage.DamageSource;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class) // This should be Entity but LivingEntity Overrides this and I am lazy
public abstract class LivingEntityMixin {
    @Inject(method = "getDimensions", at = @At("RETURN"), cancellable = true)
    private void getDimensions(EntityPose pose, CallbackInfoReturnable<EntityDimensions> cir) {
        if(Sizes.map.containsKey((LivingEntity) (Object) this)) cir.setReturnValue(cir.getReturnValue().scaled(1,
                Sizes.map.get((LivingEntity) (Object) this)));
    }

    @Inject(method = "onDeath", at = @At("HEAD"))
    private void onDeath(DamageSource damageSource, CallbackInfo ci) {
        Sizes.map.remove((LivingEntity) (Object) this);
    }

    @Inject(method = "remove", at = @At("HEAD"))
    private void remove(Entity.RemovalReason reason, CallbackInfo ci) {
        Sizes.map.remove((LivingEntity) (Object) this);
    }
}
