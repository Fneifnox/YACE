package net.fneifnox.yace.controller.selectionController.stringSelectionDropdown;

import dev.isxander.yacl3.api.Option;
import dev.isxander.yacl3.api.utils.Dimension;
import dev.isxander.yacl3.gui.AbstractWidget;
import dev.isxander.yacl3.gui.YACLScreen;
import dev.isxander.yacl3.gui.controllers.dropdown.DropdownStringController;
import net.fneifnox.yace.controller.WidgetIconRenderer;
import net.fneifnox.yace.controller.YaceControllerData;

import java.util.List;

public class YaceStringSelectionDropdownController extends DropdownStringController implements WidgetIconRenderer {
    public final YaceControllerData data;
    public final List<String> allowedValues;

    @Override
    public YaceControllerData data() {
        return this.data;
    }

    public YaceStringSelectionDropdownController(Option<String> option, YaceControllerData yaceControllerData, List<String> values, boolean allowEmptyValue, boolean allowAnyValue) {
        super(option, values, allowEmptyValue, allowAnyValue);
        this.data = yaceControllerData;
        this.allowedValues = values;
    }

    public List<String> getAllowedValues() {
        return this.allowedValues;
    }

    @Override
    public AbstractWidget provideWidget(YACLScreen screen, Dimension<Integer> widgetDimension) {
        return new YaceStringSelectionDropdownControllerElement(this, screen, widgetDimension);
    }
}
