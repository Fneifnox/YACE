package net.fneifnox.yace.controller.selectionController.dropdownString;

import dev.isxander.yacl3.api.Controller;
import dev.isxander.yacl3.api.Option;
import net.fneifnox.yace.abstractControllerBuilder.dropdown.AbstractYaceDropdownControllerBuilder;

import java.util.Arrays;
import java.util.List;

public class YaceDropdownStringControllerBuilder extends AbstractYaceDropdownControllerBuilder<String, YaceDropdownStringControllerBuilder> {
    private List<String> values;
    private boolean allowEmptyValue = false;
    private boolean allowAnyValue = false;

    public YaceDropdownStringControllerBuilder(Option<String> option) {
        super(option);
    }

    public static YaceDropdownStringControllerBuilder create(Option<String> option) {
        return new YaceDropdownStringControllerBuilder(option);
    }

    public YaceDropdownStringControllerBuilder values(List<String> values) {
        this.values = values;
        return this;
    }

    public YaceDropdownStringControllerBuilder values(String... values) {
        this.values = Arrays.asList(values);
        return this;
    }

    public YaceDropdownStringControllerBuilder allowEmptyValue(boolean allowEmptyValue) {
        this.allowEmptyValue = allowEmptyValue;
        return this;
    }

    public YaceDropdownStringControllerBuilder allowAnyValue(boolean allowAnyValue) {
        this.allowAnyValue = allowAnyValue;
        return this;
    }



    @Override
    public Controller<String> build() {
        return new YaceDropdownStringController(option, createCustomControllerData(), values, allowEmptyValue, allowAnyValue);
    }
}
