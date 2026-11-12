package net.fneifnox.yace.controller.selectionController.dropdownString;

import dev.isxander.yacl3.api.Option;
import dev.isxander.yacl3.gui.controllers.dropdown.DropdownStringController;
import net.fneifnox.yace.controller.WidgetIconRenderer;
import net.fneifnox.yace.controller.YaceControllerData;

import java.util.List;

public class YaceDropdownStringController extends DropdownStringController implements WidgetIconRenderer {
    public final YaceControllerData data;

    @Override
    public YaceControllerData data() {
        return this.data;
    }

    public YaceDropdownStringController(Option<String> option, YaceControllerData yaceControllerData, List<String> values, boolean allowEmptyValue, boolean allowAnyValue) {
        super(option, values, allowEmptyValue, allowAnyValue);
        this.data = yaceControllerData;
    }
}
