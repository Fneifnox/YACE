package net.fneifnox.yace.controller.selectionController.enumeratorDropdown;

import dev.isxander.yacl3.api.Option;
import dev.isxander.yacl3.api.controller.ValueFormatter;
import dev.isxander.yacl3.gui.controllers.dropdown.EnumDropdownController;
import net.fneifnox.yace.controller.WidgetIconRenderer;
import net.fneifnox.yace.controller.YaceControllerData;

public class YaceEnumDropdownController<T extends Enum<T>> extends EnumDropdownController<T> implements WidgetIconRenderer {
    public final YaceControllerData data;

    @Override
    public YaceControllerData data() {
        return this.data;
    }

    public YaceEnumDropdownController(Option<T> option, YaceControllerData yaceControllerData, ValueFormatter<T> formatter) {
        super(option, formatter);
        this.data = yaceControllerData;
    }
}
