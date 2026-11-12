package net.fneifnox.yace.abstractControllerBuilder;

import dev.isxander.yacl3.api.Option;
import dev.isxander.yacl3.api.controller.ControllerBuilder;
import net.fneifnox.yace.controller.YaceControllerData;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Function;

public abstract class AbstractYaceControllerBuilder<T, B extends AbstractYaceControllerBuilder<T, B>> implements ControllerBuilder<T> {
    protected final Option<T> option;

    protected ItemStack itemIcon;
    protected EntityType<? extends LivingEntity> entityIcon;
    protected boolean isBabyEntityIcon = false;
    protected RegistryEntry<StatusEffect> statusEffectIcon;
    protected Identifier identifierIcon;
    protected int pxWidth = 16;
    protected int pxHeight = 16;
    protected int textureOffsetX = 0;
    protected int textureOffsetY = 0;

    protected Function<T, Text> formatter = val -> Text.literal(String.valueOf(val));

    protected AbstractYaceControllerBuilder(Option<T> option) {
        this.option = option;
    }

    protected YaceControllerData createCustomControllerData() {
        return new YaceControllerData(
                itemIcon, entityIcon, isBabyEntityIcon, statusEffectIcon, identifierIcon, pxWidth, pxHeight, textureOffsetX, textureOffsetY, false,
                false, 0.0, new AtomicBoolean(false)
        );
    }

    @SuppressWarnings("unchecked")
    protected B self() {
        return (B) this;
    }


    public B icon(ItemStack itemIcon) {
        this.itemIcon = itemIcon;
        return self();
    }

    public B icon(ItemStack itemIcon, int pxWidth, int pxHeight) {
        this.itemIcon = itemIcon;
        this.pxWidth = pxWidth;
        this.pxHeight = pxHeight;
        return self();
    }

    public B icon(EntityType<? extends LivingEntity> entityIcon) {
        this.entityIcon = entityIcon;
        return self();
    }

    public B icon(EntityType<? extends LivingEntity> entityIcon, int pxScale) {
        this.entityIcon = entityIcon;
        this.pxWidth = pxScale;
        this.pxHeight = pxScale;
        return self();
    }

    public B icon(EntityType<? extends LivingEntity> entityIcon, boolean isBabyEntityIcon) {
        this.entityIcon = entityIcon;
        this.isBabyEntityIcon = isBabyEntityIcon;
        return self();
    }

    public B icon(EntityType<? extends LivingEntity> entityIcon, boolean isBabyEntityIcon, int pxScale) {
        this.entityIcon = entityIcon;
        this.isBabyEntityIcon = isBabyEntityIcon;
        this.pxWidth = pxScale;
        this.pxHeight = pxScale;
        return self();
    }

    public B icon(RegistryEntry<StatusEffect> statusEffectIcon) {
        this.statusEffectIcon = statusEffectIcon;
        return self();
    }

    public B icon(RegistryEntry<StatusEffect> statusEffectIcon, int pxWidth, int pxHeight) {
        this.statusEffectIcon = statusEffectIcon;
        this.pxWidth = pxWidth;
        this.pxHeight = pxHeight;
        return self();
    }

    public B icon(Identifier identifierIcon) {
        this.identifierIcon = identifierIcon;
        return self();
    }

    public B icon(Identifier identifierIcon, int pxWidth, int pxHeight, int textureOffsetX, int textureOffsetY) {
        this.identifierIcon = identifierIcon;
        this.pxWidth = pxWidth;
        this.pxHeight = pxHeight;
        this.textureOffsetX = textureOffsetX;
        this.textureOffsetY = textureOffsetY;
        return self();
    }
}
