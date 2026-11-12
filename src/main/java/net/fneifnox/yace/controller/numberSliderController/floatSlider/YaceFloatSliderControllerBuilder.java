package net.fneifnox.yace.controller.numberSliderController.floatSlider;

import dev.isxander.yacl3.api.Controller;
import dev.isxander.yacl3.api.Option;
import dev.isxander.yacl3.api.controller.ValueFormatter;
import dev.isxander.yacl3.gui.controllers.slider.FloatSliderController;
import net.fneifnox.yace.abstractControllerBuilder.slider.AbstractYaceNumberSliderControllerBuilder;

public class YaceFloatSliderControllerBuilder extends AbstractYaceNumberSliderControllerBuilder<Float, YaceFloatSliderControllerBuilder> {
    private float min, max;
    private float step;

    private ValueFormatter<Float> formatter = FloatSliderController.DEFAULT_FORMATTER::apply;

    public YaceFloatSliderControllerBuilder(Option<Float> option) {
        super(option, 0.1);
    }

    public static YaceFloatSliderControllerBuilder create(Option<Float> option) {
        return new YaceFloatSliderControllerBuilder(option);
    }

    public YaceFloatSliderControllerBuilder range(float min, float max) {
        this.min = min;
        this.max = max;
        return this;
    }

    public YaceFloatSliderControllerBuilder step(float step) {
        this.step = step;
        return this;
    }

    public YaceFloatSliderControllerBuilder formatValue(ValueFormatter<Float> formatter) {
        this.formatter = formatter;
        return this;
    }

    @Override
    public Controller<Float> build() {
        return new YaceFloatSliderController(option, createCustomControllerData(), min, max, step, formatter);
    }
}
