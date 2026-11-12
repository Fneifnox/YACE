package net.fneifnox.yace.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import dev.isxander.yacl3.api.Controller;
import dev.isxander.yacl3.gui.LegacySelectionList;
import dev.isxander.yacl3.gui.controllers.ControllerWidget;
import net.fneifnox.yace.controller.WidgetIconRenderer;
import net.fneifnox.yace.controller.WidgetValueScroller;
import net.fneifnox.yace.mixin.accessor.ControllerWidgetAccessor;
import net.minecraft.client.gui.Element;
import net.minecraft.client.gui.ParentElement;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;


@Mixin(LegacySelectionList.class)
public class LegacySelectionListMixin {

    @WrapOperation(method = "mouseScrolled", at = @At(value = "INVOKE", target = "Ldev/isxander/yacl3/gui/LegacySelectionList;setScrollAmount(D)V"))
    private void disableScrollingWhileEditing(LegacySelectionList<?> instance, double amount, Operation<Void> original) {
        for (Object child : instance.children()) {
            if (child instanceof Element entryElement) {
                if (isCustomControllerFocused(entryElement)) {
                    return;
                }
            }
        }

        original.call(instance, amount);
    }

    @Unique
    private boolean isCustomControllerFocused(Element element) {
        if (element instanceof ControllerWidget<?> widget) {
            Controller<?> control = ((ControllerWidgetAccessor) widget).getControl();
            if (control instanceof WidgetIconRenderer widgetIconRenderer) {
                if (control instanceof WidgetValueScroller) {
                    return widgetIconRenderer.data().hoveredOverCorrectly().get() || widget.isFocused();
                }
            }
        }

        if (element instanceof ParentElement parent) {
            for (Element child : parent.children()) {
                if (isCustomControllerFocused(child)) {
                    return true;
                }
            }
        }

        return false;
    }
}
