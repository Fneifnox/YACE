package net.fneifnox.yace.controller.selectionController.enumerator;

import dev.isxander.yacl3.api.Controller;
import dev.isxander.yacl3.api.Option;
import dev.isxander.yacl3.api.controller.ValueFormatter;
import dev.isxander.yacl3.gui.controllers.cycling.EnumController;
import net.fneifnox.yace.abstractControllerBuilder.cycling.AbstractYaceCyclingControllerBuilder;
import net.minecraft.text.Text;

import java.util.function.Function;

public class YaceEnumControllerBuilder<T extends Enum<T>> extends AbstractYaceCyclingControllerBuilder<T, YaceEnumControllerBuilder<T>> {
    private Class<T> enumClass;
    private ValueFormatter<T> formatter = null;

    public YaceEnumControllerBuilder(Option<T> option) {
        super(option);
    }

    public static <T extends Enum<T>> YaceEnumControllerBuilder<T> create(Option<T> option) {
        return new YaceEnumControllerBuilder<>(option);
    }

    public YaceEnumControllerBuilder<T> enumClass(Class<T> enumClass) {
        this.enumClass = enumClass;
        return this;
    }

    public YaceEnumControllerBuilder<T> formatValue(ValueFormatter<T> formatter) {
        this.formatter = formatter;
        return this;
    }

    @Override
    public Controller<T> build() {
        ValueFormatter<T> formatter = this.formatter;
        if (formatter == null) {
            Function<T, Text> formatFunction = EnumController.getDefaultFormatter();
            formatter = formatFunction::apply;
        }

        return new YaceEnumController<>(option, createCustomControllerData(), formatter, enumClass.getEnumConstants());
    }
}
