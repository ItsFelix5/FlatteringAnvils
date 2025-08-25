package flatteringanvils.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.registry.entry.RegistryEntry;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import flatteringanvils.Main;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin {
    @Inject(method = "createLivingAttributes", at = @At("TAIL"))
    private static void createLivingAttributes(CallbackInfoReturnable<DefaultAttributeContainer.Builder> cir) {
        cir.getReturnValue().add(Main.HEIGHT);
    }

    @Shadow
    public abstract double getAttributeValue(RegistryEntry<EntityAttribute> attribute);

    @ModifyReturnValue(method = "getDimensions", at = @At("RETURN"))
    private EntityDimensions getDimensions(EntityDimensions original) {
        float height = (float) getAttributeValue(Main.HEIGHT);
        return new EntityDimensions(original.width(), original.height() * height, original.eyeHeight() * height, original.attachments().scale(1, height, 1), false);
    }

    @WrapOperation(method = "updateAttribute", at = @At(value = "INVOKE", target = "Lnet/minecraft/registry/entry/RegistryEntry;matches(Lnet/minecraft/registry/entry/RegistryEntry;)Z", ordinal = 2))
    private boolean matches(RegistryEntry<Object> instance, RegistryEntry<Object> tRegistryEntry, Operation<Boolean> original) {
        return original.call(instance, tRegistryEntry) || original.call(instance, Main.HEIGHT);
    }
}
