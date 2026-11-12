package net.fneifnox.yace.controller.numberSliderController.integerSlider;

import dev.isxander.yacl3.api.Controller;
import dev.isxander.yacl3.api.Option;
import dev.isxander.yacl3.api.controller.ValueFormatter;
import dev.isxander.yacl3.gui.controllers.slider.IntegerSliderController;
import net.fneifnox.yace.abstractControllerBuilder.slider.AbstractYaceNumberSliderControllerBuilder;

public class YaceIntegerSliderControllerBuilder extends AbstractYaceNumberSliderControllerBuilder<Integer, YaceIntegerSliderControllerBuilder> {
    private int min, max;
    private int step;

    private ValueFormatter<Integer> formatter = IntegerSliderController.DEFAULT_FORMATTER::apply;

    public YaceIntegerSliderControllerBuilder(Option<Integer> option) {
        super(option, 1);
    }

    public static YaceIntegerSliderControllerBuilder create(Option<Integer> option) {
        return new YaceIntegerSliderControllerBuilder(option);
    }

    public YaceIntegerSliderControllerBuilder range(int min, int max) {
        this.min = min;
        this.max = max;
        return this;
    }

    public YaceIntegerSliderControllerBuilder step(int step) {
        this.step = step;
        return this;
    }

    public YaceIntegerSliderControllerBuilder formatValue(ValueFormatter<Integer> formatter) {
        this.formatter = formatter;
        return this;
    }

    @Override
    public Controller<Integer> build() {
        return new YaceIntegerSliderController(option, createCustomControllerData(), min, max, step, formatter);
    }
}
