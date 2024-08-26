package flatteringanvils.mixin;

import flatteringanvils.Sizes;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.entity.Entity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageTypes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(ClientPlayNetworkHandler.class)
public class ClientPlayNetworkHandlerMixin {
    @Redirect(method = "onEntityDamage", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/Entity;onDamaged(Lnet/minecraft/entity/damage/DamageSource;)V"))
    private void onDamaged(Entity instance, DamageSource damageSource){
        if(damageSource.isOf(DamageTypes.FALLING_ANVIL)) {
            Sizes.map.put(instance, Math.max(.05F, Sizes.map.getOrDefault(instance, 1f) - .2f));
            instance.calculateDimensions();
        }
        instance.onDamaged(damageSource);
    }
}
