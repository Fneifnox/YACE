package net.fneifnox.yace.abstractControllerBuilder.field.number;

import dev.isxander.yacl3.api.Option;
import dev.isxander.yacl3.api.utils.Dimension;
import net.fneifnox.yace.abstractControllerBuilder.field.AbstractYaceFieldControllerBuilder;
import net.fneifnox.yace.controller.YaceControllerData;

import java.util.concurrent.atomic.AtomicBoolean;

public abstract class AbstractYaceNumberFieldControllerBuilder<T, B extends AbstractYaceFieldControllerBuilder<T, B>> extends AbstractYaceFieldControllerBuilder<T, B> {
    protected boolean adjustableByScrolling = false;
    protected double stepSize;

    protected AbstractYaceNumberFieldControllerBuilder(Option<T> option, double stepSize) {
        super(option);

        this.stepSize = stepSize;
    }

    @Override
    protected YaceControllerData createCustomControllerData() {
        return new YaceControllerData(
                itemIcon, entityIcon, isBabyEntityIcon, statusEffectIcon, identifierIcon, pxWidth, pxHeight, textureOffsetX, textureOffsetY, centeredWithoutName,
                adjustableByScrolling, stepSize, new AtomicBoolean(false)
        );
    }

    public B adjustableByScrolling() {
        this.adjustableByScrolling = true;
        return self();
    }

    public B adjustableByScrolling(double stepSize) {
        this.adjustableByScrolling = true;
        this.stepSize = stepSize;
        return self();
    }

    public static boolean isHoveredOverSlider(double mouseX, double mouseY, Dimension<?> dimension, float sliderWidthPercent, int sliderHeight) {
        int dynamicWidth = Math.round(dimension.width().intValue() * sliderWidthPercent);

        int paddingRight = (int) (dimension.width().intValue() * 0.0025f);

        int x = dimension.x().intValue() + dimension.width().intValue() - dynamicWidth - paddingRight;
        int y = dimension.y().intValue() + (dimension.height().intValue() - sliderHeight) / 2;

        return mouseX >= x && mouseX <= x + dynamicWidth && mouseY >= y && mouseY <= y + sliderHeight;
    }
}
