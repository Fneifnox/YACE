package net.fneifnox.yace.mixin.accessor;

import dev.isxander.yacl3.gui.AbstractWidget;
import net.minecraft.client.font.TextRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(AbstractWidget.class)
public interface AbstractWidgetAccessor {
    @Accessor("textRenderer")
    TextRenderer getTextRenderer();
}
