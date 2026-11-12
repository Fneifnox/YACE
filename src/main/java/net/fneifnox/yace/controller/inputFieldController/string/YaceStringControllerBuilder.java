package net.fneifnox.yace.controller.inputFieldController.string;

import dev.isxander.yacl3.api.Controller;
import dev.isxander.yacl3.api.Option;
import net.fneifnox.yace.abstractControllerBuilder.field.AbstractYaceFieldControllerBuilder;

public class YaceStringControllerBuilder extends AbstractYaceFieldControllerBuilder<String, YaceStringControllerBuilder> {
    public YaceStringControllerBuilder(Option<String> option) {
        super(option);
    }

    public static YaceStringControllerBuilder create(Option<String> option) {
        return new YaceStringControllerBuilder(option);
    }

    @Override
    public Controller<String> build() {
        return new YaceStringController(option, createCustomControllerData());
    }
}
