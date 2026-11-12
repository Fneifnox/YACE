package net.fneifnox.yace.optionGroup;

import dev.isxander.yacl3.api.OptionGroup;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;

public interface YaceOptionGroup extends OptionGroup {
    ItemStack itemIcon();
    EntityType<? extends LivingEntity> entityIcon();
    boolean isBabyEntityIcon();
    RegistryEntry<StatusEffect> statusEffectIcon();
    Identifier identifierIcon();
    Integer pxWidth();
    Integer pxHeight();
    Integer textureOffsetX();
    Integer textureOffsetY();

    static Builder createBuilder() {
        return new YaceOptionGroupImpl.Builder();
    }

    interface Builder extends OptionGroup.Builder {
        Builder icon(ItemStack itemIcon);
        Builder icon(ItemStack itemIcon, int pxWidth, int pxHeight);

        Builder icon(EntityType<? extends LivingEntity> entityIcon);
        Builder icon(EntityType<? extends LivingEntity> entityIcon, int pxScale);
        Builder icon(EntityType<? extends LivingEntity> entityIcon, boolean isBabyEntityIcon);
        Builder icon(EntityType<? extends LivingEntity> entityIcon, boolean isBabyEntityIcon, int pxScale);

        Builder icon(RegistryEntry<StatusEffect> statusEffectIcon);
        Builder icon(RegistryEntry<StatusEffect> statusEffectIcon, int pxWidth, int pxHeight);

        Builder icon(Identifier identifierIcon);
        Builder icon(Identifier identifierIcon, int pxWidth, int pxHeight, int textureOffsetX, int textureOffsetY);

        @Override
        YaceOptionGroup build();
    }
}
