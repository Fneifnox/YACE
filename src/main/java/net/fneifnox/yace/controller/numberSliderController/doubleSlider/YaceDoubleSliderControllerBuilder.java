package net.fneifnox.yace.controller.numberSliderController.doubleSlider;

import dev.isxander.yacl3.api.Controller;
import dev.isxander.yacl3.api.Option;
import dev.isxander.yacl3.api.controller.ValueFormatter;
import dev.isxander.yacl3.gui.controllers.slider.DoubleSliderController;
import net.fneifnox.yace.abstractControllerBuilder.slider.AbstractYaceNumberSliderControllerBuilder;

public class YaceDoubleSliderControllerBuilder extends AbstractYaceNumberSliderControllerBuilder<Double, YaceDoubleSliderControllerBuilder> {
    private double min, max;
    private double step;

    private ValueFormatter<Double> formatter = DoubleSliderController.DEFAULT_FORMATTER::apply;

    public YaceDoubleSliderControllerBuilder(Option<Double> option) {
        super(option, 0.1);
    }

    public static YaceDoubleSliderControllerBuilder create(Option<Double> option) {
        return new YaceDoubleSliderControllerBuilder(option);
    }

    public YaceDoubleSliderControllerBuilder range(double min, double max) {
        this.min = min;
        this.max = max;
        return this;
    }

    public YaceDoubleSliderControllerBuilder step(double step) {
        this.step = step;
        return this;
    }

    public YaceDoubleSliderControllerBuilder formatValue(ValueFormatter<Double> formatter) {
        this.formatter = formatter;
        return this;
    }

    @Override
    public Controller<Double> build() {
        return new YaceDoubleSliderController(option, createCustomControllerData(), min, max, step, formatter);
    }
}
