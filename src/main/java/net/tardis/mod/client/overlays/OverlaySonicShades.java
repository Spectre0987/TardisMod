package net.tardis.mod.client.overlays;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.util.ResourceLocation;
import net.tardis.mod.Tardis;
import net.tardis.mod.common.items.TItems;

import java.awt.*;

public class OverlaySonicShades implements IOverlay {

    Minecraft mc = Minecraft.getMinecraft();
    private ResourceLocation background = new ResourceLocation(Tardis.MODID, "textures/overlay/shades.png");

    @Override
    public void render(float partialTicks, ScaledResolution resolution) {

        if (mc.player.getItemStackFromSlot(EntityEquipmentSlot.HEAD).getItem() == TItems.sonicShades) {

            Entity mouseOver = Minecraft.getMinecraft().objectMouseOver.entityHit;
            GlStateManager.pushMatrix();
            if (mc.player != null && mouseOver != null) {
                mc.fontRenderer.drawStringWithShadow("Name: " + mouseOver.getName(), resolution.getScaledWidth() - 160, resolution.getScaledHeight() - 70, Color.GREEN.getRGB());
                mc.fontRenderer.drawStringWithShadow("Distance: " + Math.round(mouseOver.getDistance(mc.player)) + " blocks", resolution.getScaledWidth() - 160, resolution.getScaledHeight() - 60, Color.GREEN.getRGB());
                mc.fontRenderer.drawStringWithShadow("Age: " + mouseOver.ticksExisted + " ticks", resolution.getScaledWidth() - 160, resolution.getScaledHeight() - 50, Color.GREEN.getRGB());
                mc.fontRenderer.drawStringWithShadow("Position: " + mouseOver.getPosition().getX() + " || " + mouseOver.getPosition().getY() + " || " + mouseOver.getPosition().getZ(), resolution.getScaledWidth() - 160, resolution.getScaledHeight() - 40, Color.GREEN.getRGB());
            }
            GlStateManager.popMatrix();
        }
    }

    @Override
    public void renderUpdate() {

    }


}
