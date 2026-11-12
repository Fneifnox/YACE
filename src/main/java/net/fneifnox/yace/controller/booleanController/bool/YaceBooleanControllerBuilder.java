package net.fneifnox.yace.controller.booleanController.bool;

import dev.isxander.yacl3.api.Controller;
import dev.isxander.yacl3.api.Option;
import dev.isxander.yacl3.api.controller.ValueFormatter;
import dev.isxander.yacl3.gui.controllers.BooleanController;
import net.fneifnox.yace.abstractControllerBuilder.AbstractYaceControllerBuilder;

public class YaceBooleanControllerBuilder extends AbstractYaceControllerBuilder<Boolean, YaceBooleanControllerBuilder> {
    private ValueFormatter<Boolean> formatter;
    private boolean coloured = false;

    public YaceBooleanControllerBuilder(Option<Boolean> option) {
        super(option);
    }

    public static YaceBooleanControllerBuilder create(Option<Boolean> option) {
        return new YaceBooleanControllerBuilder(option);
    }

    public YaceBooleanControllerBuilder formatValue(ValueFormatter<Boolean> formatter) {
        this.formatter = formatter;
        return this;
    }

    public YaceBooleanControllerBuilder coloured(boolean coloured) {
        this.coloured = coloured;
        return this;
    }

    public YaceBooleanControllerBuilder onOffFormatter() {
        this.formatter = BooleanController.ON_OFF_FORMATTER::apply;
        return this;
    }

    public YaceBooleanControllerBuilder yesNoFormatter() {
        this.formatter = BooleanController.YES_NO_FORMATTER::apply;
        return this;
    }

    public YaceBooleanControllerBuilder trueFalseFormatter() {
        this.formatter = BooleanController.TRUE_FALSE_FORMATTER::apply;
        return this;
    }

    public YaceBooleanControllerBuilder tickCrossFormatter() {
        this.formatter = YaceBooleanController.TICK_CROSS_FORMATTER;
        return this;
    }

    @Override
    public Controller<Boolean> build() {
        return new YaceBooleanController(option, createCustomControllerData(), formatter, coloured);
    }
}
