package net.fneifnox.yace.controller.selectionController.cyclingList;

import com.google.common.collect.ImmutableList;
import dev.isxander.yacl3.api.Controller;
import dev.isxander.yacl3.api.Option;
import dev.isxander.yacl3.api.controller.ValueFormatter;
import net.fneifnox.yace.abstractControllerBuilder.cycling.AbstractYaceCyclingControllerBuilder;

public class YaceCyclingListControllerBuilder<T> extends AbstractYaceCyclingControllerBuilder<T, YaceCyclingListControllerBuilder<T>> {
    private Iterable<? extends T> values;
    private ValueFormatter<T> formatter = null;

    public YaceCyclingListControllerBuilder(Option<T> option) {
        super(option);
    }

    public static <T> YaceCyclingListControllerBuilder<T> create(Option<T> option) {
        return new YaceCyclingListControllerBuilder<>(option);
    }

    public YaceCyclingListControllerBuilder<T> values(Iterable<? extends T> values) {
        this.values = values;
        return this;
    }

    @SafeVarargs
    public final YaceCyclingListControllerBuilder<T> values(T... values) {
        this.values = ImmutableList.copyOf(values);
        return this;
    }

    public YaceCyclingListControllerBuilder<T> formatValue(ValueFormatter<T> formatter) {
        this.formatter = formatter;
        return this;
    }

    @Override
    public Controller<T> build() {
        return new YaceCyclingListController<>(option, createCustomControllerData(), values, formatter);
    }
}
