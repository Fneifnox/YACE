package net.fneifnox.yace.mixin.accessor;

import dev.isxander.yacl3.api.Controller;
import dev.isxander.yacl3.gui.controllers.ControllerWidget;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(ControllerWidget.class)
public interface ControllerWidgetAccessor {
    @Accessor("control")
    Controller<?> getControl();
}
