package flatteringanvils.mixin;

import net.minecraft.client.render.entity.state.LivingEntityRenderState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import flatteringanvils.LivingEntityRenderStateExtension;

@Mixin(LivingEntityRenderState.class)
public class LivingEntityRenderStateMixin implements LivingEntityRenderStateExtension {
    @Unique
    private float height;

    @Override
    public void flatteringanvils$setHeight(float height) {
        this.height = height;
    }

    @Override
    public float flatteringanvils$getHeight() {
        return height;
    }
}
