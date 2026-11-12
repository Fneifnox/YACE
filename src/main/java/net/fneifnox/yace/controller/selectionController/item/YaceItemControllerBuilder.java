package net.fneifnox.yace.controller.selectionController.item;

import dev.isxander.yacl3.api.Controller;
import dev.isxander.yacl3.api.Option;
import net.fneifnox.yace.abstractControllerBuilder.dropdown.AbstractYaceDropdownControllerBuilder;
import net.minecraft.item.Item;

public class YaceItemControllerBuilder extends AbstractYaceDropdownControllerBuilder<Item, YaceItemControllerBuilder> {
    public YaceItemControllerBuilder(Option<Item> option) {
        super(option);
    }

    public static YaceItemControllerBuilder create(Option<Item> option) {
        return new YaceItemControllerBuilder(option);
    }

    @Override
    public Controller<Item> build() {
        return new YaceItemController(option, createCustomControllerData());
    }
}
