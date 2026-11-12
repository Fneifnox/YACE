package net.fneifnox.yace.mixin;

import dev.isxander.yacl3.api.utils.Dimension;
import dev.isxander.yacl3.gui.controllers.string.StringControllerElement;
import net.fneifnox.yace.controller.WidgetIconRenderer;
import net.fneifnox.yace.controller.selectionController.item.YaceItemController;
import net.fneifnox.yace.mixin.accessor.AbstractWidgetAccessor;
import net.fneifnox.yace.mixin.accessor.ControllerWidgetAccessor;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(StringControllerElement.class)
public abstract class StringControllerElementMixin {

    @Shadow
    protected abstract Text getValueText();

    @Shadow
    protected Dimension<Integer> inputFieldBounds;

    @Shadow
    public abstract int getUnshiftedLength();

    // To move the inputFieldBounds into the center when .centeredWithoutName() is true
    @Inject(method = "setDimension", at = @At("TAIL"))
    private void adjustInputBoundsForCentering(Dimension<Integer> dim, CallbackInfo ci) {
        StringControllerElement stringControllerElement = (StringControllerElement)(Object) this;

        if (((ControllerWidgetAccessor) stringControllerElement).getControl() instanceof WidgetIconRenderer widgetIconRenderer) {
            if (widgetIconRenderer.data().centeredWithoutName()) {
                int additionalMoving = ((ControllerWidgetAccessor) stringControllerElement).getControl() instanceof YaceItemController ? 8 : 0;

                TextRenderer textRenderer = ((AbstractWidgetAccessor) stringControllerElement).getTextRenderer();
                int width = Math.max(6, Math.min(textRenderer.getWidth(getValueText()), getUnshiftedLength()));

                int centerX = dim.x() + (dim.width() / 2);
                int fieldX = centerX - (width / 2);

                this.inputFieldBounds = Dimension.ofInt(fieldX + additionalMoving, dim.centerY() - textRenderer.fontHeight / 2, width, textRenderer.fontHeight);
            }
        }
    }

    // To set the text, caret... into the center when .centeredWithoutName() is true
    @ModifyVariable(method = "drawValueText", at = @At("STORE"), name = "textX")
    private int replaceTextXVariable(int textX) {
        StringControllerElement stringControllerElement = (StringControllerElement)(Object) this;
        if (((ControllerWidgetAccessor) stringControllerElement).getControl() instanceof WidgetIconRenderer widgetIconRenderer) {
            if (widgetIconRenderer.data().centeredWithoutName()) {
                int additionalMoving = ((ControllerWidgetAccessor) stringControllerElement).getControl() instanceof YaceItemController ? 8 : 0;
                return stringControllerElement.getDimension().x() + (stringControllerElement.getDimension().width() - MinecraftClient.getInstance().textRenderer.getWidth(getValueText())) / 2 + additionalMoving;
            }
        }
        return textX;
    }
}
