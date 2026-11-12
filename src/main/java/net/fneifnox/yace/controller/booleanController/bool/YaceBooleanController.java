package net.fneifnox.yace.controller.booleanController.bool;

import dev.isxander.yacl3.api.Option;
import dev.isxander.yacl3.api.controller.ValueFormatter;
import dev.isxander.yacl3.gui.controllers.BooleanController;
import net.fneifnox.yace.controller.YaceControllerData;
import net.fneifnox.yace.controller.WidgetIconRenderer;
import net.minecraft.text.Text;

public class YaceBooleanController extends BooleanController implements WidgetIconRenderer {
    public final YaceControllerData data;

    @Override
    public YaceControllerData data() {
        return this.data;
    }

    public static final ValueFormatter<Boolean> TICK_CROSS_FORMATTER = (state) ->
            state
                    ? Text.literal("✔")
                    : Text.literal("❌");

    public YaceBooleanController(Option<Boolean> option, YaceControllerData yaceControllerData, ValueFormatter<Boolean> formatter, boolean coloured) {
        super(option, formatter::format, coloured);
        data = yaceControllerData;
    }
}

