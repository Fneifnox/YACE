package net.fneifnox.yace.controller.inputFieldController.longField;

import dev.isxander.yacl3.api.Controller;
import dev.isxander.yacl3.api.Option;
import dev.isxander.yacl3.api.controller.ValueFormatter;
import dev.isxander.yacl3.gui.controllers.slider.LongSliderController;
import net.fneifnox.yace.abstractControllerBuilder.field.number.AbstractYaceNumberFieldControllerBuilder;

public class YaceLongFieldControllerBuilder extends AbstractYaceNumberFieldControllerBuilder<Long, YaceLongFieldControllerBuilder> {
    private long min = -Long.MAX_VALUE;
    private long max = Long.MAX_VALUE;

    private ValueFormatter<Long> formatter = LongSliderController.DEFAULT_FORMATTER::apply;

    public YaceLongFieldControllerBuilder(Option<Long> option) {
        super(option, 1L);
    }

    public static YaceLongFieldControllerBuilder create(Option<Long> option) {
        return new YaceLongFieldControllerBuilder(option);
    }

    public YaceLongFieldControllerBuilder range(long min, long max) {
        this.min = min;
        this.max = max;
        return this;
    }

    public YaceLongFieldControllerBuilder min(long min) {
        this.min = min;
        return this;
    }

    public YaceLongFieldControllerBuilder max(long max) {
        this.max = max;
        return this;
    }

    public YaceLongFieldControllerBuilder formatValue(ValueFormatter<Long> formatter) {
        this.formatter = formatter;
        return this;
    }

    @Override
    public Controller<Long> build() {
        return new YaceLongFieldController(option, createCustomControllerData(), min, max, formatter);
    }
}
