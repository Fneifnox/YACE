package net.fneifnox.yace.controller.selectionController.color;

import dev.isxander.yacl3.api.Option;
import dev.isxander.yacl3.gui.controllers.ColorController;
import net.fneifnox.yace.controller.YaceControllerData;
import net.fneifnox.yace.controller.WidgetIconRenderer;

import java.awt.*;

public class YaceColorController extends ColorController implements WidgetIconRenderer {
    public final YaceControllerData data;

    @Override
    public YaceControllerData data() {
        return this.data;
    }

    public YaceColorController(Option<Color> option, YaceControllerData yaceControllerData, boolean allowAlpha) {
        super(option, allowAlpha);
        data = yaceControllerData;
    }
}

