package net.fneifnox.yace.controller.inputFieldController.doubleField;

import dev.isxander.yacl3.api.Option;
import dev.isxander.yacl3.api.controller.ValueFormatter;
import dev.isxander.yacl3.api.utils.Dimension;
import dev.isxander.yacl3.gui.AbstractWidget;
import dev.isxander.yacl3.gui.YACLScreen;
import dev.isxander.yacl3.gui.controllers.string.StringControllerElement;
import dev.isxander.yacl3.gui.controllers.string.number.DoubleFieldController;
import net.fneifnox.yace.controller.YaceControllerData;
import net.fneifnox.yace.controller.WidgetIconRenderer;
import net.fneifnox.yace.controller.WidgetValueScroller;

public class YaceDoubleFieldController extends DoubleFieldController implements WidgetIconRenderer, WidgetValueScroller {
    public final YaceControllerData data;

    @Override
    public YaceControllerData data() {
        return this.data;
    }

    public YaceDoubleFieldController(Option<Double> option, YaceControllerData yaceControllerData, double min, double max, ValueFormatter<Double> formatter) {
        super(option, min, max, formatter::format);
        this.data = yaceControllerData;
    }

    @Override
    public AbstractWidget provideWidget(YACLScreen screen, Dimension<Integer> widgetDimension) {
        return new StringControllerElement(this, screen, widgetDimension, false) {
            @Override
            public boolean mouseScrolled(double mouseX, double mouseY, double horizontalAmount, double verticalAmount) {
                if (focused && data.adjustableByScrolling()) {
                    return adjustDecimalValueByScrolling(verticalAmount, data.stepSize(), option(), min(), max(), val -> {
                        this.caretPos = val.length();
                        this.inputField = val;
                    });
                }
                return super.mouseScrolled(mouseX, mouseY, horizontalAmount, verticalAmount);
            }
        };
    }
}
