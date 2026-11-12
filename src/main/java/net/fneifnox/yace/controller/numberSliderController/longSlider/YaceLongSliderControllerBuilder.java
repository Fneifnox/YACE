package net.fneifnox.yace.controller.numberSliderController.longSlider;

import dev.isxander.yacl3.api.Controller;
import dev.isxander.yacl3.api.Option;
import dev.isxander.yacl3.api.controller.ValueFormatter;
import dev.isxander.yacl3.gui.controllers.slider.LongSliderController;
import net.fneifnox.yace.abstractControllerBuilder.slider.AbstractYaceNumberSliderControllerBuilder;

public class YaceLongSliderControllerBuilder extends AbstractYaceNumberSliderControllerBuilder<Long, YaceLongSliderControllerBuilder> {
    private long min, max;
    private long step;

    private ValueFormatter<Long> formatter = LongSliderController.DEFAULT_FORMATTER::apply;

    public YaceLongSliderControllerBuilder(Option<Long> option) {
        super(option, 1L);
    }

    public static YaceLongSliderControllerBuilder create(Option<Long> option) {
        return new YaceLongSliderControllerBuilder(option);
    }

    public YaceLongSliderControllerBuilder range(long min, long max) {
        this.min = min;
        this.max = max;
        return this;
    }

    public YaceLongSliderControllerBuilder step(long step) {
        this.step = step;
        return this;
    }

    public YaceLongSliderControllerBuilder formatValue(ValueFormatter<Long> formatter) {
        this.formatter = formatter;
        return this;
    }

    @Override
    public Controller<Long> build() {
        return new YaceLongSliderController(option, createCustomControllerData(), min, max, step, formatter);
    }
}
