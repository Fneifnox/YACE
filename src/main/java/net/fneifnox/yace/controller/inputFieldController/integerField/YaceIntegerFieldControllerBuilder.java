package net.fneifnox.yace.controller.inputFieldController.integerField;

import dev.isxander.yacl3.api.Controller;
import dev.isxander.yacl3.api.Option;
import dev.isxander.yacl3.api.controller.ValueFormatter;
import dev.isxander.yacl3.gui.controllers.slider.IntegerSliderController;
import net.fneifnox.yace.abstractControllerBuilder.field.number.AbstractYaceNumberFieldControllerBuilder;

public class YaceIntegerFieldControllerBuilder extends AbstractYaceNumberFieldControllerBuilder<Integer, YaceIntegerFieldControllerBuilder> {
    private int min = -Integer.MAX_VALUE;
    private int max = Integer.MAX_VALUE;

    private ValueFormatter<Integer> formatter = IntegerSliderController.DEFAULT_FORMATTER::apply;

    public YaceIntegerFieldControllerBuilder(Option<Integer> option) {
        super(option, 1);
    }

    public static YaceIntegerFieldControllerBuilder create(Option<Integer> option) {
        return new YaceIntegerFieldControllerBuilder(option);
    }

    public YaceIntegerFieldControllerBuilder range(int min, int max) {
        this.min = min;
        this.max = max;
        return this;
    }

    public YaceIntegerFieldControllerBuilder min(int min) {
        this.min = min;
        return this;
    }

    public YaceIntegerFieldControllerBuilder max(int max) {
        this.max = max;
        return this;
    }

    public YaceIntegerFieldControllerBuilder formatValue(ValueFormatter<Integer> formatter) {
        this.formatter = formatter;
        return this;
    }

    @Override
    public Controller<Integer> build() {
        return new YaceIntegerFieldController(option, createCustomControllerData(), min, max, formatter);
    }
}
