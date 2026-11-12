package net.fneifnox.yace.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import dev.isxander.yacl3.api.Controller;
import dev.isxander.yacl3.gui.controllers.ControllerWidget;
import net.fneifnox.yace.controller.WidgetIconRenderer;
import net.fneifnox.yace.controller.selectionController.item.YaceItemController;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static net.fneifnox.yace.abstractControllerBuilder.slider.AbstractYaceNumberSliderControllerBuilder.isHoveredOverSlider;

@Mixin(ControllerWidget.class)
public abstract class ControllerWidgetMixin {

    @Final
    @Shadow
    protected Controller<?> control;

    @Shadow
    protected abstract Text getValueText();

    // Moves text at the controller widgets in order that they don't overlap with the icon
    @WrapOperation(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/DrawContext;drawText(Lnet/minecraft/client/font/TextRenderer;Lnet/minecraft/text/Text;IIIZ)I"))
    private int moveText(DrawContext graphics, TextRenderer textRenderer, Text text, int x, int y, int color, boolean shadow, Operation<Integer> original) {
        ControllerWidget<?> controllerWidget = (ControllerWidget<?>)(Object)this;
        if (this.control instanceof WidgetIconRenderer widgetIconRenderer) {
            if (!widgetIconRenderer.data().centeredWithoutName()) {
                widgetIconRenderer.renderWidgetIcon(graphics, widgetIconRenderer.data(), controllerWidget.getDimension());
            }
            else {
                widgetIconRenderer.renderWidgetIconCentered(graphics, widgetIconRenderer.data(), controllerWidget.getDimension(), control, controllerWidget);
            }

            // Disable optionNameText for centeredValueText
            if (widgetIconRenderer.data().centeredWithoutName()) {
                return 0;
            }
            else if (widgetIconRenderer.data().hasIcon()) {
                return original.call(graphics, textRenderer, text, x + widgetIconRenderer.data().pxWidth() + 6, y, color, shadow);
            }
        }

        return original.call(graphics, textRenderer, text, x, y, color, shadow);
    }

    @Inject(method = "render", at = @At("TAIL"))
    private void checkForSliderHovering(DrawContext graphics, int mouseX, int mouseY, float delta, CallbackInfo ci) {
        ControllerWidget<?> controllerWidget = (ControllerWidget<?>)(Object)this;
        if (this.control instanceof WidgetIconRenderer widgetIconRenderer) {
            widgetIconRenderer.data().hoveredOverCorrectly().set(
                    isHoveredOverSlider(mouseX, mouseY, controllerWidget.getDimension(), 0.35f, 20));
        }
    }

    // For .centeredWithoutName() (setting controller valueTexts without stringControllerElements in the center)
    @WrapOperation(method = "drawValueText", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/DrawContext;drawText(Lnet/minecraft/client/font/TextRenderer;Lnet/minecraft/text/Text;IIIZ)I"))
    private int centerValueText(DrawContext graphics, TextRenderer textRenderer, Text text, int x, int y, int color, boolean shadow, Operation<Integer> original) {
        ControllerWidget<?> controllerWidget = (ControllerWidget<?>)(Object)this;
        if (this.control instanceof WidgetIconRenderer widgetIconRenderer) {
            if (widgetIconRenderer.data().centeredWithoutName()) {
                int additionalMoving = this.control instanceof YaceItemController ? 8 : 0;

                int textX = controllerWidget.getDimension().x() + (controllerWidget.getDimension().width() - textRenderer.getWidth(getValueText())) / 2 + additionalMoving;
                int textY = controllerWidget.getDimension().y() + (controllerWidget.getDimension().height() - 8) / 2;
                return original.call(graphics, textRenderer, text, textX, textY, color, shadow);
            }
        }
        return original.call(graphics, textRenderer, text, x, y, color, shadow);
    }
}
