package net.fneifnox.yace.controller.selectionController.cyclingList;

import dev.isxander.yacl3.api.Option;
import dev.isxander.yacl3.api.controller.ValueFormatter;
import dev.isxander.yacl3.gui.controllers.cycling.CyclingListController;
import net.fneifnox.yace.controller.WidgetIconRenderer;
import net.fneifnox.yace.controller.YaceControllerData;

public class YaceCyclingListController<T> extends CyclingListController<T> implements WidgetIconRenderer {
    public final YaceControllerData data;

    @Override
    public YaceControllerData data() {
        return this.data;
    }

    public YaceCyclingListController(Option<T> option, YaceControllerData yaceControllerData, Iterable<? extends T> values, ValueFormatter<T> formatter) {
        super(option, values, formatter::format);
        this.data = yaceControllerData;
    }
}
