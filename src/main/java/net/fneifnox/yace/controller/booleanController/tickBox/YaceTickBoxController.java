package net.fneifnox.yace.controller.booleanController.tickBox;

import dev.isxander.yacl3.api.Option;
import dev.isxander.yacl3.gui.controllers.TickBoxController;
import net.fneifnox.yace.controller.YaceControllerData;
import net.fneifnox.yace.controller.WidgetIconRenderer;

public class YaceTickBoxController extends TickBoxController implements WidgetIconRenderer {
    public final YaceControllerData data;

    @Override
    public YaceControllerData data() {
        return this.data;
    }

    public YaceTickBoxController(Option<Boolean> option, YaceControllerData yaceControllerData) {
        super(option);
        data = yaceControllerData;
    }
}

