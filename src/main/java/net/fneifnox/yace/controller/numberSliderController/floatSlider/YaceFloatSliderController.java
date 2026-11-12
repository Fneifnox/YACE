package net.fneifnox.yace.controller.numberSliderController.floatSlider;

import dev.isxander.yacl3.api.Option;
import dev.isxander.yacl3.api.controller.ValueFormatter;
import dev.isxander.yacl3.api.utils.Dimension;
import dev.isxander.yacl3.gui.AbstractWidget;
import dev.isxander.yacl3.gui.YACLScreen;
import dev.isxander.yacl3.gui.controllers.slider.FloatSliderController;
import dev.isxander.yacl3.gui.controllers.slider.SliderControllerElement;
import net.fneifnox.yace.controller.YaceControllerData;
import net.fneifnox.yace.controller.WidgetIconRenderer;
import net.fneifnox.yace.controller.WidgetValueScroller;

public class YaceFloatSliderController extends FloatSliderController implements WidgetIconRenderer, WidgetValueScroller {
    public final YaceControllerData data;

    @Override
    public YaceControllerData data() {
        return this.data;
    }

    public YaceFloatSliderController(Option<Float> option, YaceControllerData yaceControllerData, float min, float max, float interval, ValueFormatter<Float> formatter) {
        super(option, min, max, interval, formatter::format);
        this.data = yaceControllerData;
    }

    @Override
    public AbstractWidget provideWidget(YACLScreen screen, Dimension<Integer> widgetDimension) {
        return new SliderControllerElement(this, screen, widgetDimension, this.min(), this.max(), this.interval()) {
            @Override
            public boolean mouseScrolled(double mouseX, double mouseY, double horizontalAmount, double verticalAmount) {
                if ((data.hoveredOverCorrectly().get() || focused) && data.adjustableByScrolling()) {
                    return adjustDecimalValueByScrolling(verticalAmount, data.stepSize(), option(), min(), max(), null);
                }
                return super.mouseScrolled(mouseX, mouseY, horizontalAmount, verticalAmount);
            }
        };
    }
}
