package net.fneifnox.yace.controller;

import dev.isxander.yacl3.api.Option;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.sound.PositionedSoundInstance;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.MathHelper;
import org.jetbrains.annotations.Nullable;

import java.math.BigDecimal;
import java.util.Locale;
import java.util.function.Consumer;

public interface WidgetValueScroller {

    default <T extends Number> boolean adjustIntegerValueByScrolling(double verticalAmount, double stepSize, Option<T> option, Number min, Number max, @Nullable Consumer<String> valueAsStringConsumer) {
        if (verticalAmount != 0) {
            Class<?> type = option.pendingValue().getClass();
            double finalStepSize = Math.max(Math.round(stepSize), 1.0);

            long delta = Math.round(Math.signum(verticalAmount) * finalStepSize);
            long currentValue = option.pendingValue().longValue();
            long newValue = MathHelper.clamp(currentValue + delta, min.longValue(), max.longValue());

            if (newValue != currentValue) {
                option.requestSet(convertLongToType(newValue, type));

                if (valueAsStringConsumer != null) {
                    valueAsStringConsumer.accept(String.valueOf(option.pendingValue()));
                }

                MinecraftClient.getInstance().getSoundManager().play(
                        PositionedSoundInstance.master(SoundEvents.UI_BUTTON_CLICK.value(), 1.2f, 0.33f)
                );
            }
        }
        return true;
    }

    default <T extends Number> boolean adjustDecimalValueByScrolling(double verticalAmount, double stepSize, Option<T> option, Number min, Number max, @Nullable Consumer<String> valueAsStringConsumer) {
        if (verticalAmount != 0) {
            Class<?> type = option.pendingValue().getClass();
            double delta = Math.signum(verticalAmount) * stepSize;
            int decimalPlaces = Math.max(0, (BigDecimal.valueOf(stepSize)).stripTrailingZeros().scale());

            double currentValue = option.pendingValue().doubleValue();
            double newValue = Math.round((currentValue + delta) * Math.pow(10, decimalPlaces)) / Math.pow(10, decimalPlaces);
            newValue = MathHelper.clamp(newValue, min.doubleValue(), max.doubleValue());

            if (newValue != currentValue) {
                option.requestSet(convertDoubleToType(newValue, type));

                String formattedString;
                if (decimalPlaces > 0) {
                    formattedString = String.format(Locale.getDefault(), "%." + decimalPlaces + "f", newValue);
                }
                else {
                    formattedString = String.format(Locale.getDefault(), "%.0f", newValue);
                }

                if (valueAsStringConsumer != null) {
                    valueAsStringConsumer.accept(formattedString);
                }

                MinecraftClient.getInstance().getSoundManager().play(
                        PositionedSoundInstance.master(SoundEvents.UI_BUTTON_CLICK.value(), 1.2f, 0.33f)
                );
            }
        }
        return true;
    }

    @SuppressWarnings("unchecked")
    private <T extends Number> T convertDoubleToType(double value, Class<?> type) {
        if (type == Float.class) return (T) Float.valueOf((float) value);
        if (type == Double.class) return (T) Double.valueOf(value);
        throw new IllegalArgumentException("Unsupported number type: " + type);
    }

    @SuppressWarnings("unchecked")
    private <T extends Number> T convertLongToType(long value, Class<?> type) {
        if (type == Integer.class) return (T) Integer.valueOf(Math.round(value));
        if (type == Long.class) return (T) Long.valueOf(Math.round(value));
        if (type == Short.class) return (T) Short.valueOf((short) Math.round(value));
        if (type == Byte.class) return (T) Byte.valueOf((byte) Math.round(value));
        throw new IllegalArgumentException("Unsupported number type: " + type);
    }
}
