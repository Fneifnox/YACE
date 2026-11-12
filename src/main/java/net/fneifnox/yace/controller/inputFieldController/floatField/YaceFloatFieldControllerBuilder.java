package net.fneifnox.yace.controller.inputFieldController.floatField;

import dev.isxander.yacl3.api.Controller;
import dev.isxander.yacl3.api.Option;
import dev.isxander.yacl3.api.controller.ValueFormatter;
import dev.isxander.yacl3.gui.controllers.slider.FloatSliderController;
import net.fneifnox.yace.abstractControllerBuilder.field.number.AbstractYaceNumberFieldControllerBuilder;

public class YaceFloatFieldControllerBuilder extends AbstractYaceNumberFieldControllerBuilder<Float, YaceFloatFieldControllerBuilder> {
    private float min = -Float.MAX_VALUE;
    private float max = Float.MAX_VALUE;

    private ValueFormatter<Float> formatter = FloatSliderController.DEFAULT_FORMATTER::apply;

    public YaceFloatFieldControllerBuilder(Option<Float> option) {
        super(option, 0.1);
    }

    public static YaceFloatFieldControllerBuilder create(Option<Float> option) {
        return new YaceFloatFieldControllerBuilder(option);
    }

    public YaceFloatFieldControllerBuilder range(float min, float max) {
        this.min = min;
        this.max = max;
        return this;
    }

    public YaceFloatFieldControllerBuilder min(float min) {
        this.min = min;
        return this;
    }

    public YaceFloatFieldControllerBuilder max(float max) {
        this.max = max;
        return this;
    }

    public YaceFloatFieldControllerBuilder formatValue(ValueFormatter<Float> formatter) {
        this.formatter = formatter;
        return this;
    }

    @Override
    public Controller<Float> build() {
        return new YaceFloatFieldController(option, createCustomControllerData(), min, max, formatter);
    }
}
