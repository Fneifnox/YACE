package net.fneifnox.yace.controller.selectionController.enumerator;

import dev.isxander.yacl3.api.Option;
import dev.isxander.yacl3.api.controller.ValueFormatter;
import dev.isxander.yacl3.gui.controllers.cycling.EnumController;
import net.fneifnox.yace.controller.WidgetIconRenderer;
import net.fneifnox.yace.controller.YaceControllerData;

public class YaceEnumController<T extends Enum<T>> extends EnumController<T> implements WidgetIconRenderer {
    public final YaceControllerData data;

    @Override
    public YaceControllerData data() {
        return this.data;
    }

    public YaceEnumController(Option<T> option, YaceControllerData yaceControllerData, ValueFormatter<T> formatter, T[] availableValues) {
        super(option, formatter::format, availableValues);
        this.data = yaceControllerData;
    }
}
