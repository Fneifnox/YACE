package net.fneifnox.yace.controller;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;

import java.util.concurrent.atomic.AtomicBoolean;

public record YaceControllerData(
        ItemStack itemIcon,
        EntityType<? extends LivingEntity> entityIcon,
        boolean isBabyEntityIcon,
        RegistryEntry<StatusEffect> statusEffectIcon,
        Identifier identifierIcon,
        int pxWidth,
        int pxHeight,
        int textureOffsetX,
        int textureOffsetY,
        boolean centeredWithoutName,
        boolean adjustableByScrolling,
        double stepSize,
        AtomicBoolean hoveredOverCorrectly
) {

    public boolean hasIcon() {
        return (itemIcon != null && !itemIcon.isEmpty() || entityIcon != null || statusEffectIcon != null || identifierIcon != null);
    }
}
