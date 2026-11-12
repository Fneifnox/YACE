package net.fneifnox.yace.mixin.accessor;

import dev.isxander.yacl3.api.utils.Dimension;
import dev.isxander.yacl3.gui.controllers.string.StringControllerElement;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(StringControllerElement.class)
public interface StringControllerElementAccessor {
    @Accessor("inputFieldBounds")
    Dimension<Integer> getInputFieldBounds();
}
