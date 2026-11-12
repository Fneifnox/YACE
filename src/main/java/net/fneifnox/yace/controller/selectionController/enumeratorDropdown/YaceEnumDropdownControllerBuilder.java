package net.fneifnox.yace.controller.selectionController.enumeratorDropdown;

import dev.isxander.yacl3.api.Controller;
import dev.isxander.yacl3.api.Option;
import dev.isxander.yacl3.api.controller.ValueFormatter;
import dev.isxander.yacl3.gui.controllers.cycling.EnumController;
import net.fneifnox.yace.abstractControllerBuilder.dropdown.AbstractYaceDropdownControllerBuilder;

public class YaceEnumDropdownControllerBuilder<T extends Enum<T>> extends AbstractYaceDropdownControllerBuilder<T, YaceEnumDropdownControllerBuilder<T>> {
    private ValueFormatter<T> formatter = EnumController.<T>getDefaultFormatter()::apply;

    public YaceEnumDropdownControllerBuilder(Option<T> option) {
        super(option);
    }

    public static <T extends Enum<T>> YaceEnumDropdownControllerBuilder<T> create(Option<T> option) {
        return new YaceEnumDropdownControllerBuilder<>(option);
    }

    public YaceEnumDropdownControllerBuilder<T> formatValue(ValueFormatter<T> formatter) {
        this.formatter = formatter;
        return this;
    }

    @Override
    public Controller<T> build() {
        return new YaceEnumDropdownController<>(option, createCustomControllerData(), formatter);
    }
}
