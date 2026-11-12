package net.fneifnox.yace.abstractControllerBuilder.dropdown;

import dev.isxander.yacl3.api.Option;
import net.fneifnox.yace.abstractControllerBuilder.AbstractYaceControllerBuilder;
import net.fneifnox.yace.controller.YaceControllerData;

import java.util.concurrent.atomic.AtomicBoolean;

public abstract class AbstractYaceDropdownControllerBuilder<T, B extends AbstractYaceControllerBuilder<T, B>> extends AbstractYaceControllerBuilder<T, B> {
    protected boolean centeredWithoutName = false;

    protected AbstractYaceDropdownControllerBuilder(Option<T> option) {
        super(option);
    }

    @Override
    protected YaceControllerData createCustomControllerData() {
        return new YaceControllerData(
                itemIcon, entityIcon, isBabyEntityIcon, statusEffectIcon, identifierIcon, pxWidth, pxHeight, textureOffsetX, textureOffsetY, centeredWithoutName,
                false, 0.0, new AtomicBoolean(false)
        );
    }

    public B centeredWithoutName() {
        this.centeredWithoutName = true;
        return self();
    }
}
