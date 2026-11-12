package net.fneifnox.yace.controller.selectionController.color;

import dev.isxander.yacl3.api.Controller;
import dev.isxander.yacl3.api.Option;
import net.fneifnox.yace.abstractControllerBuilder.AbstractYaceControllerBuilder;

import java.awt.*;

public class YaceColorControllerBuilder extends AbstractYaceControllerBuilder<Color, YaceColorControllerBuilder> {
    private boolean allowAlpha;

    public YaceColorControllerBuilder(Option<Color> option) {
        super(option);
    }

    public static YaceColorControllerBuilder create(Option<Color> option) {
        return new YaceColorControllerBuilder(option);
    }

    public YaceColorControllerBuilder allowAlpha(boolean allowAlpha) {
        this.allowAlpha = allowAlpha;
        return this;
    }

    @Override
    public Controller<Color> build() {
        return new YaceColorController(option, createCustomControllerData(), allowAlpha);
    }
}
