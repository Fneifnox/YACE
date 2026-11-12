package net.fneifnox.yace.controller;

import dev.isxander.yacl3.api.Controller;
import dev.isxander.yacl3.api.utils.Dimension;
import dev.isxander.yacl3.gui.controllers.ControllerWidget;
import dev.isxander.yacl3.gui.controllers.string.StringControllerElement;
import net.fneifnox.yace.YetAnotherConfigEnhancement;
import net.fneifnox.yace.mixin.accessor.StringControllerElementAccessor;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.InventoryScreen;
import net.minecraft.client.render.model.BakedModel;
import net.minecraft.client.texture.Sprite;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityPose;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.resource.Resource;
import org.joml.Quaternionf;
import org.joml.Vector3f;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public interface WidgetIconRenderer {
    YaceControllerData data();

    Map<EntityType<? extends LivingEntity>, LivingEntity> ENTITY_ICON_CACHE = new ConcurrentHashMap<>();
    Map<EntityType<? extends LivingEntity>, LivingEntity> BABY_ENTITY_ICON_CACHE = new ConcurrentHashMap<>();

    default void renderWidgetIconCore(DrawContext graphics, YaceControllerData data, int iconX, int iconY) {
        if (data == null) return;

        if (data.identifierIcon() != null) {
            if (MinecraftClient.getInstance().getResourceManager().getResource(data.identifierIcon()).isEmpty()) return;
            Optional<Resource> resource = MinecraftClient.getInstance().getResourceManager().getResource(data.identifierIcon());
            int[] textureScale = {16, 16};
            if (resource.isPresent()) {
                try (InputStream stream = resource.get().getInputStream()) {
                    BufferedImage image = ImageIO.read(stream);
                    if (image != null) {
                        textureScale = new int[]{image.getWidth(), image.getHeight()};
                    }
                } catch (Exception e) {
                    YetAnotherConfigEnhancement.LOGGER.error("Could not get the resource from the InputStream", e);
                }
            }
            graphics.drawTexture(
                    data.identifierIcon(), iconX, iconY, data.pxWidth(), data.pxHeight(), data.textureOffsetX(), data.textureOffsetY(), textureScale[0], textureScale[1], textureScale[0], textureScale[1]
            );
        }

        if (data.itemIcon() != null && !data.itemIcon().isEmpty()) {
            float[] textureScale = new float[]{16f, 16f};
            BakedModel model = MinecraftClient.getInstance().getItemRenderer().getModel(data.itemIcon(), MinecraftClient.getInstance().world, MinecraftClient.getInstance().player, 0);
            if (model != null) {
                Sprite sprite = model.getParticleSprite();
                if (sprite != null) {
                    textureScale[0] = sprite.getContents().getWidth();
                    textureScale[1] = sprite.getContents().getHeight();
                }
            }

            graphics.getMatrices().push();
            graphics.getMatrices().translate(iconX, iconY, 0);
            graphics.getMatrices().scale(data.pxWidth() / textureScale[0], data.pxHeight() / textureScale[1], 1f);
            graphics.drawItemWithoutEntity(data.itemIcon(), 0, 0);
            graphics.getMatrices().pop();
        }

        if (data.statusEffectIcon() != null) {
            Sprite statusEffectSprite = MinecraftClient.getInstance().getStatusEffectSpriteManager().getSprite(data.statusEffectIcon());
            graphics.drawSprite(iconX, iconY, 0, data.pxWidth(), data.pxHeight(), statusEffectSprite);
        }

        if (data.entityIcon() != null) {
            if (MinecraftClient.getInstance().world != null) {
                
                Map<EntityType<? extends LivingEntity>, LivingEntity> activeCache =
                        data.isBabyEntityIcon() ? BABY_ENTITY_ICON_CACHE : ENTITY_ICON_CACHE;

                LivingEntity livingEntity = activeCache.computeIfAbsent(data.entityIcon(), type -> {
                    LivingEntity createdLivingEntity = type.create(MinecraftClient.getInstance().world);
                    if (createdLivingEntity instanceof MobEntity mobEntity) {
                        mobEntity.setBaby(data.isBabyEntityIcon());
                    }
                    return createdLivingEntity;
                });

                if (livingEntity == null) return;
                
                if (livingEntity instanceof MobEntity mobEntity && mobEntity.isBaby() != data.isBabyEntityIcon()) {
                    mobEntity.setBaby(data.isBabyEntityIcon());
                }

                livingEntity.setBodyYaw(180f);
                livingEntity.setHeadYaw(180f);

                Quaternionf entityRotation = new Quaternionf().rotationXYZ(0.2f, -0.5f, (float) Math.PI);
                EntityDimensions entityDimensions = livingEntity.getDimensions(EntityPose.STANDING);
                float entityWidth = entityDimensions.width();
                float entityHeight = entityDimensions.height();

                if (livingEntity.isBaby()) {
                    float baseScale = Math.max(livingEntity.getScale(), 0.2f);
                    entityHeight = Math.max(entityHeight / baseScale, entityHeight * 1.35f);
                }

                float maxDimension = Math.max(entityWidth, entityHeight);
                float targetSize = Math.max(1.0f, Math.min(data.pxWidth(), data.pxHeight()) - 3f);
                int scaleBasedOfEntitySize = Math.max(1, Math.round(targetSize / Math.max(maxDimension, 0.1f)));

                float centerX = iconX + (data.pxWidth() / 2.0f);
                float centerY = iconY + (data.pxHeight() / 2.0f);
                int adjustedX = Math.round(centerX);
                int adjustedY = Math.round(centerY + ((entityHeight * scaleBasedOfEntitySize) / 2.0f));

                InventoryScreen.drawEntity(
                        graphics,
                        adjustedX,
                        adjustedY,
                        scaleBasedOfEntitySize,
                        new Vector3f(0, 0, 0),
                        entityRotation,
                        null,
                        livingEntity
                );
            } else {
                if (SpawnEggItem.forEntity(data.entityIcon()) == null) return;
                ItemStack spawnEggIcon = Objects.requireNonNull(SpawnEggItem.forEntity(data.entityIcon())).getDefaultStack();

                float[] textureScale = new float[]{16f, 16f};
                BakedModel model = MinecraftClient.getInstance().getItemRenderer().getModel(spawnEggIcon, MinecraftClient.getInstance().world, MinecraftClient.getInstance().player, 0);
                if (model != null) {
                    Sprite sprite = model.getParticleSprite();
                    if (sprite != null) {
                        textureScale[0] = sprite.getContents().getWidth();
                        textureScale[1] = sprite.getContents().getHeight();
                    }
                }

                graphics.getMatrices().push();
                graphics.getMatrices().translate(iconX, iconY, 0);
                graphics.getMatrices().scale(data.pxWidth() / textureScale[0], data.pxHeight() / textureScale[1], 1f);
                graphics.drawItemWithoutEntity(spawnEggIcon, 0, 0);
                graphics.getMatrices().pop();
            }
        }
    }

    default void renderWidgetIcon(DrawContext graphics, YaceControllerData data, Dimension<Integer> dimension) {
        if (data == null || dimension == null) return;

        int iconX = dimension.x() + 5;
        int iconY = dimension.y() + (dimension.height() - data.pxHeight()) / 2;

        renderWidgetIconCore(graphics, data, iconX, iconY);
    }

    default void renderWidgetIconCentered(DrawContext graphics, YaceControllerData data, Dimension<Integer> dimension, Controller<?> controller, ControllerWidget<?> controllerWidget) {
        if (data == null || dimension == null) return;

        String valueString = controller.formatValue().getString();
        int valueStringWidth = MinecraftClient.getInstance().textRenderer.getWidth(valueString);

        int textWidth = valueStringWidth;
        if (controllerWidget instanceof StringControllerElement stringElement) {
            int inputFieldBoundsWidth = ((StringControllerElementAccessor)stringElement).getInputFieldBounds().width();
            textWidth = Math.min(valueStringWidth, inputFieldBoundsWidth);
        }

        int centerX = dimension.x() + (dimension.width() / 2);
        int iconX = centerX - (textWidth / 2) - data.pxWidth() - 6;
        int iconY = dimension.y() + (dimension.height() - data.pxHeight()) / 2;

        renderWidgetIconCore(graphics, data, iconX, iconY);
    }
}
