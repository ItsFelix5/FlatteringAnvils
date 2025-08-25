package flatteringanvils.mixin;

import net.minecraft.entity.attribute.AttributeContainer;
import net.minecraft.entity.attribute.EntityAttributeInstance;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import flatteringanvils.Main;

@Mixin(AttributeContainer.class)
public class AttributeContainerMixin {
    @Inject(method = "method_60613", at = @At("HEAD"), cancellable = true)
    private void method_60613(EntityAttributeInstance attributeInstance, CallbackInfo ci) {
        if (attributeInstance.getAttribute() == Main.HEIGHT) ci.cancel();
    }
}
