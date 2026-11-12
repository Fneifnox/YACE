package net.fneifnox.yace.mixin;

import dev.isxander.yacl3.api.OptionGroup;
import dev.isxander.yacl3.gui.OptionListWidget;
import net.fneifnox.yace.controller.WidgetIconRenderer;
import net.fneifnox.yace.controller.YaceControllerData;
import net.fneifnox.yace.optionGroup.YaceOptionGroup;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.concurrent.atomic.AtomicBoolean;

@Mixin(OptionListWidget.GroupSeparatorEntry.class)
public abstract class OptionListWidgetMixin implements WidgetIconRenderer {

    @Final
    @Shadow
    protected OptionGroup group;

    @Inject(method = "renderContent", at = @At("TAIL"))
    private void renderOptionGroupIcon(DrawContext graphics, int mouseX, int mouseY, boolean hovered, float deltaTicks, CallbackInfo ci) {
        OptionListWidget.GroupSeparatorEntry entry = (OptionListWidget.GroupSeparatorEntry)(Object)this;

        if (group instanceof YaceOptionGroup customOptionGroup) {
            int textWidth = MinecraftClient.getInstance().textRenderer.getWidth(this.group.name());
            int centerX = entry.getX() + (entry.getWidth() / 2);
            int iconX = centerX - (textWidth / 2) - customOptionGroup.pxWidth() - 6;
            int iconY = entry.getY() + (entry.getHeight() - customOptionGroup.pxHeight()) / 2;

            YaceControllerData data = new YaceControllerData(
                    customOptionGroup.itemIcon(),
                    customOptionGroup.entityIcon(),
                    customOptionGroup.isBabyEntityIcon(),
                    customOptionGroup.statusEffectIcon(),
                    customOptionGroup.identifierIcon(),
                    customOptionGroup.pxWidth(),
                    customOptionGroup.pxHeight(),
                    customOptionGroup.textureOffsetX(),
                    customOptionGroup.textureOffsetY(),
                    false,
                    false, 0.0, new AtomicBoolean(false)
            );

            renderWidgetIconCore(graphics, data, iconX, iconY);
        }
    }
}
