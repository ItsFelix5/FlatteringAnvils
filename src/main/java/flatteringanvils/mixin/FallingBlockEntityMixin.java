package flatteringanvils.mixin;

import net.minecraft.entity.Entity;
import net.minecraft.entity.FallingBlockEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageTypes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import flatteringanvils.Main;

@Mixin(FallingBlockEntity.class)
public class FallingBlockEntityMixin {
    @Inject(method = "method_32879", at = @At("TAIL"))
    private static void shrinkEntity(DamageSource damageSource, float amount, Entity entity, CallbackInfo ci) {
        if (damageSource.getTypeRegistryEntry().matchesKey(DamageTypes.FALLING_ANVIL) && entity instanceof LivingEntity living)
            living.getAttributeInstance(Main.HEIGHT).setBaseValue(living.getAttributeInstance(Main.HEIGHT).getBaseValue() - amount / 20);
    }
}
