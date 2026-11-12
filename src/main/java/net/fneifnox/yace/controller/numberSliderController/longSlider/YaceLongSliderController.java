package net.fneifnox.yace.controller.numberSliderController.longSlider;

import dev.isxander.yacl3.api.Option;
import dev.isxander.yacl3.api.controller.ValueFormatter;
import dev.isxander.yacl3.api.utils.Dimension;
import dev.isxander.yacl3.gui.AbstractWidget;
import dev.isxander.yacl3.gui.YACLScreen;
import dev.isxander.yacl3.gui.controllers.slider.LongSliderController;
import dev.isxander.yacl3.gui.controllers.slider.SliderControllerElement;
import net.fneifnox.yace.controller.YaceControllerData;
import net.fneifnox.yace.controller.WidgetIconRenderer;
import net.fneifnox.yace.controller.WidgetValueScroller;

public class YaceLongSliderController extends LongSliderController implements WidgetIconRenderer, WidgetValueScroller {
    public final YaceControllerData data;

    @Override
    public YaceControllerData data() {
        return this.data;
    }

    public YaceLongSliderController(Option<Long> option, YaceControllerData yaceControllerData, long min, long max, long interval, ValueFormatter<Long> formatter) {
        super(option, min, max, interval, formatter::format);
        this.data = yaceControllerData;
    }

    @Override
    public AbstractWidget provideWidget(YACLScreen screen, Dimension<Integer> widgetDimension) {
        return new SliderControllerElement(this, screen, widgetDimension, this.min(), this.max(), this.interval()) {
            @Override
            public boolean mouseScrolled(double mouseX, double mouseY, double horizontalAmount, double verticalAmount) {
                if ((data.hoveredOverCorrectly().get() || focused) && data.adjustableByScrolling()) {
                    return adjustIntegerValueByScrolling(verticalAmount, data.stepSize(), option(), min(), max(), null);
                }
                return super.mouseScrolled(mouseX, mouseY, horizontalAmount, verticalAmount);
            }
        };
    }
}
