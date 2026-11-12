package net.fneifnox.yace.controller.booleanController.tickBox;

import dev.isxander.yacl3.api.Controller;
import dev.isxander.yacl3.api.Option;
import net.fneifnox.yace.abstractControllerBuilder.AbstractYaceControllerBuilder;

public class YaceTickBoxControllerBuilder extends AbstractYaceControllerBuilder<Boolean, YaceTickBoxControllerBuilder> {

    public YaceTickBoxControllerBuilder(Option<Boolean> option) {
        super(option);
    }

    public static YaceTickBoxControllerBuilder create(Option<Boolean> option) {
        return new YaceTickBoxControllerBuilder(option);
    }

    @Override
    public Controller<Boolean> build() {
        return new YaceTickBoxController(option, createCustomControllerData());
    }
}
