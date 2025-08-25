package flatteringanvils;

import net.fabricmc.api.ModInitializer;
import net.minecraft.entity.attribute.ClampedEntityAttribute;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;

public class Main implements ModInitializer {
    public static final RegistryEntry.Reference<EntityAttribute> HEIGHT = Registry.registerReference(Registries.ATTRIBUTE,
            Identifier.of("flatteringanvils", "height"),
            new ClampedEntityAttribute(
                    "attribute.flatteringanvils.height",
                    1,
                    0.04,
                    64
            ).setTracked(true));

    @Override
    public void onInitialize() {}
}
