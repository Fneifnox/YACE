package net.fneifnox.yace.controller.inputFieldController.doubleField;

import dev.isxander.yacl3.api.Controller;
import dev.isxander.yacl3.api.Option;
import dev.isxander.yacl3.api.controller.ValueFormatter;
import dev.isxander.yacl3.gui.controllers.slider.DoubleSliderController;
import net.fneifnox.yace.abstractControllerBuilder.field.number.AbstractYaceNumberFieldControllerBuilder;

public class YaceDoubleFieldControllerBuilder extends AbstractYaceNumberFieldControllerBuilder<Double, YaceDoubleFieldControllerBuilder> {
    private double min = -Double.MAX_VALUE;
    private double max = Double.MAX_VALUE;

    private ValueFormatter<Double> formatter = DoubleSliderController.DEFAULT_FORMATTER::apply;

    public YaceDoubleFieldControllerBuilder(Option<Double> option) {
        super(option, 0.1);
    }

    public static YaceDoubleFieldControllerBuilder create(Option<Double> option) {
        return new YaceDoubleFieldControllerBuilder(option);
    }

    public YaceDoubleFieldControllerBuilder range(double min, double max) {
        this.min = min;
        this.max = max;
        return this;
    }

    public YaceDoubleFieldControllerBuilder min(double min) {
        this.min = min;
        return this;
    }

    public YaceDoubleFieldControllerBuilder max(double max) {
        this.max = max;
        return this;
    }

    public YaceDoubleFieldControllerBuilder formatValue(ValueFormatter<Double> formatter) {
        this.formatter = formatter;
        return this;
    }

    @Override
    public Controller<Double> build() {
        return new YaceDoubleFieldController(option, createCustomControllerData(), min, max, formatter);
    }
}
