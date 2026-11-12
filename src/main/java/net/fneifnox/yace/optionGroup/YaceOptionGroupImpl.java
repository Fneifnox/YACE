package net.fneifnox.yace.optionGroup;

import com.google.common.collect.ImmutableList;
import dev.isxander.yacl3.api.Option;
import dev.isxander.yacl3.api.OptionDescription;
import dev.isxander.yacl3.api.OptionGroup;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;

public class YaceOptionGroupImpl implements YaceOptionGroup {

    private final OptionGroup delegate;
    private final ItemStack itemIcon;
    private final EntityType<? extends LivingEntity> entityIcon;
    private final boolean isBabyEntityIcon;
    private final RegistryEntry<StatusEffect> statusEffectIcon;
    private final Identifier identifierIcon;
    private final int pxWidth;
    private final int pxHeight;
    private final int textureOffsetX;
    private final int textureOffsetY;

    public YaceOptionGroupImpl(OptionGroup delegate, ItemStack itemIcon, EntityType<? extends LivingEntity> entityIcon, boolean isBabyEntityIcon, RegistryEntry<StatusEffect> statusEffectIcon, Identifier identifierIcon, int pxWidth, int pxHeight, int textureOffsetX, int textureOffsetY) {
        this.delegate = delegate;
        this.itemIcon = itemIcon;
        this.entityIcon = entityIcon;
        this.isBabyEntityIcon = isBabyEntityIcon;
        this.statusEffectIcon = statusEffectIcon;
        this.identifierIcon = identifierIcon;
        this.pxWidth = pxWidth;
        this.pxHeight = pxHeight;
        this.textureOffsetX = textureOffsetX;
        this.textureOffsetY = textureOffsetY;
    }

    @Override
    public ItemStack itemIcon() {
        return itemIcon;
    }

    @Override
    public EntityType<? extends LivingEntity> entityIcon() {
        return entityIcon;
    }

    @Override
    public boolean isBabyEntityIcon() {
        return isBabyEntityIcon;
    }

    @Override
    public RegistryEntry<StatusEffect> statusEffectIcon() {
        return statusEffectIcon;
    }

    @Override
    public Identifier identifierIcon() {
        return identifierIcon;
    }

    @Override
    public Integer pxWidth() {
        return pxWidth;
    }

    @Override
    public Integer pxHeight() {
        return pxHeight;
    }

    @Override
    public Integer textureOffsetX() {
        return textureOffsetX;
    }

    @Override
    public Integer textureOffsetY() {
        return textureOffsetY;
    }

    @Override
    public Text name() {
        return delegate.name();
    }

    @Override
    public OptionDescription description() {
        return delegate.description();
    }

    @Override
    public Text tooltip() {
        return delegate.tooltip();
    }

    @Override
    public @NotNull ImmutableList<? extends Option<?>> options() {
        return delegate.options();
    }

    @Override
    public boolean collapsed() {
        return delegate.collapsed();
    }

    @Override
    public boolean isRoot() {
        return delegate.isRoot();
    }


    public static class Builder implements YaceOptionGroup.Builder {
        private final OptionGroup.Builder yaclBuilder = OptionGroup.createBuilder();

        private ItemStack itemIcon;
        private EntityType<? extends LivingEntity> entityIcon;
        private boolean isBabyEntityIcon;
        private RegistryEntry<StatusEffect> statusEffectIcon;
        private Identifier identifierIcon;
        private int pxWidth = 16;
        private int pxHeight = 16;
        private int textureOffsetX = 0;
        private int textureOffsetY = 0;

        @Override
        public Builder name(@NotNull Text name) {
            yaclBuilder.name(name);
            return this;
        }

        @Override
        public Builder description(@NotNull OptionDescription description) {
            yaclBuilder.description(description);
            return this;
        }

        @Override
        public Builder option(@NotNull Option<?> option) {
            yaclBuilder.option(option);
            return this;
        }

        @Override
        public OptionGroup.Builder options(@NotNull Collection<? extends Option<?>> options) {
            yaclBuilder.options(options);
            return this;
        }

        @Override
        public Builder collapsed(boolean collapsible) {
            yaclBuilder.collapsed(collapsible);
            return this;
        }

        @Override
        public Builder icon(ItemStack itemIcon) {
            this.itemIcon = itemIcon;
            return this;
        }

        @Override
        public YaceOptionGroup.Builder icon(ItemStack itemIcon, int pxWidth, int pxHeight) {
            this.itemIcon = itemIcon;
            this.pxWidth = pxWidth;
            this.pxHeight = pxHeight;
            return this;
        }

        @Override
        public Builder icon(EntityType<? extends LivingEntity> entityIcon) {
            this.entityIcon = entityIcon;
            return this;
        }

        @Override
        public YaceOptionGroup.Builder icon(EntityType<? extends LivingEntity> entityIcon, int pxScale) {
            this.entityIcon = entityIcon;
            this.pxWidth = pxScale;
            this.pxHeight = pxScale;
            return this;
        }

        @Override
        public Builder icon(EntityType<? extends LivingEntity> entityIcon, boolean isBabyEntityIcon) {
            this.entityIcon = entityIcon;
            this.isBabyEntityIcon = isBabyEntityIcon;
            return this;
        }

        @Override
        public YaceOptionGroup.Builder icon(EntityType<? extends LivingEntity> entityIcon, boolean isBabyEntityIcon, int pxScale) {
            this.entityIcon = entityIcon;
            this.isBabyEntityIcon = isBabyEntityIcon;
            this.pxWidth = pxScale;
            this.pxHeight = pxScale;
            return this;
        }

        public Builder icon(RegistryEntry<StatusEffect> statusEffectIcon) {
            this.statusEffectIcon = statusEffectIcon;
            return this;
        }

        @Override
        public Builder icon(RegistryEntry<StatusEffect> statusEffectIcon, int pxWidth, int pxHeight) {
            this.statusEffectIcon = statusEffectIcon;
            this.pxWidth = pxWidth;
            this.pxHeight = pxHeight;
            return this;
        }

        @Override
        public Builder icon(Identifier identifierIcon) {
            this.identifierIcon = identifierIcon;
            return this;
        }

        @Override
        public Builder icon(Identifier identifierIcon, int pxWidth, int pxHeight, int textureOffsetX, int textureOffsetY) {
            this.identifierIcon = identifierIcon;
            this.pxWidth = pxWidth;
            this.pxHeight = pxHeight;
            this.textureOffsetX = textureOffsetX;
            this.textureOffsetY = textureOffsetY;
            return this;
        }

        @Override
        public YaceOptionGroup build() {
            return new YaceOptionGroupImpl(yaclBuilder.build(), itemIcon, entityIcon, isBabyEntityIcon, statusEffectIcon, identifierIcon, pxWidth, pxHeight, textureOffsetX, textureOffsetY);
        }
    }
}
