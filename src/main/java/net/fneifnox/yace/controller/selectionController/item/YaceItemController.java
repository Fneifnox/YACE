package net.fneifnox.yace.controller.selectionController.item;

import dev.isxander.yacl3.api.Option;
import dev.isxander.yacl3.gui.controllers.dropdown.ItemController;
import net.fneifnox.yace.controller.WidgetIconRenderer;
import net.fneifnox.yace.controller.YaceControllerData;
import net.minecraft.item.Item;

public class YaceItemController extends ItemController implements WidgetIconRenderer {
    public final YaceControllerData data;

    @Override
    public YaceControllerData data() {
        return this.data;
    }

    public YaceItemController(Option<Item> option, YaceControllerData yaceControllerData) {
        super(option);
        this.data = yaceControllerData;
    }
}
