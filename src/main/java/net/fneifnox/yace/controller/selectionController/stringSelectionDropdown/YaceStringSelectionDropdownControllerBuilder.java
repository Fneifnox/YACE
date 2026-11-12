package net.fneifnox.yace.controller.selectionController.stringSelectionDropdown;

import dev.isxander.yacl3.api.Controller;
import dev.isxander.yacl3.api.Option;
import net.fneifnox.yace.abstractControllerBuilder.dropdown.AbstractYaceDropdownControllerBuilder;

import java.util.Arrays;
import java.util.List;

public class YaceStringSelectionDropdownControllerBuilder extends AbstractYaceDropdownControllerBuilder<String, YaceStringSelectionDropdownControllerBuilder> {
    private List<String> values;
    private boolean allowEmptyValue = false;
    private boolean allowAnyValue = false;

    public YaceStringSelectionDropdownControllerBuilder(Option<String> option) {
        super(option);
    }

    public static YaceStringSelectionDropdownControllerBuilder create(Option<String> option) {
        return new YaceStringSelectionDropdownControllerBuilder(option);
    }

    public YaceStringSelectionDropdownControllerBuilder values(List<String> values) {
        this.values = values;
        return this;
    }

    public YaceStringSelectionDropdownControllerBuilder values(String... values) {
        this.values = Arrays.asList(values);
        return this;
    }

    public YaceStringSelectionDropdownControllerBuilder allowEmptyValue(boolean allowEmptyValue) {
        this.allowEmptyValue = allowEmptyValue;
        return this;
    }

    public YaceStringSelectionDropdownControllerBuilder allowAnyValue(boolean allowAnyValue) {
        this.allowAnyValue = allowAnyValue;
        return this;
    }

    @Override
    public Controller<String> build() {
        return new YaceStringSelectionDropdownController(option, createCustomControllerData(), values, allowEmptyValue, allowAnyValue);
    }
}
