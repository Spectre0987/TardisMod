package net.tardis.mod.client.overlays;

import java.awt.Color;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.model.ModelPlayer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.tardis.mod.Tardis;
import net.tardis.mod.common.items.TItems;
import net.tardis.mod.util.helpers.Helper;
import net.tardis.mod.util.helpers.RiftHelper;

public class OverlaySonicShades implements IOverlay {

    private Minecraft mc = Minecraft.getMinecraft();
    private ResourceLocation background = new ResourceLocation(Tardis.MODID, "textures/overlay/shades.png");
    private ModelPlayer biped = new ModelPlayer(1.0F, false);

    public static void drawEntityOnScreen(int posX, int posY, int scale, Entity ent) {
        GlStateManager.enableColorMaterial();
        GlStateManager.pushMatrix();
        GlStateManager.translate((float) posX, (float) posY, 50.0F);
        GlStateManager.scale((float) (-scale), (float) scale, (float) scale);
        GlStateManager.rotate(180.0F, 0.0F, 0.0F, 1.0F);
        GlStateManager.rotate(135.0F, 0.0F, 1.0F, 0.0F);
        RenderHelper.enableStandardItemLighting();
        GlStateManager.rotate(-135.0F, 0.0F, 1.0F, 0.0F);
        GlStateManager.translate(0.0F, 0.0F, 0.0F);
        RenderManager rendermanager = Minecraft.getMinecraft().getRenderManager();
        rendermanager.setRenderShadow(false);
        rendermanager.renderEntity(ent, 0.0D, 0.0D, 0.0D, 0.0F, 1.0F, false);
        GlStateManager.popMatrix();
        RenderHelper.disableStandardItemLighting();
        GlStateManager.disableRescaleNormal();
        GlStateManager.setActiveTexture(OpenGlHelper.lightmapTexUnit);
        GlStateManager.disableTexture2D();
        GlStateManager.setActiveTexture(OpenGlHelper.defaultTexUnit);
    }

    @Override
    public void renderUpdate() {

    }

    @Override
    public void pre(RenderGameOverlayEvent.Pre e, float partialTicks, ScaledResolution resolution) {
        if (Minecraft.getMinecraft().player.getItemStackFromSlot(EntityEquipmentSlot.HEAD).getItem() != TItems.sonic_shades)
            return;
            e.setCanceled(e.getType() == RenderGameOverlayEvent.ElementType.FOOD || e.getType() == RenderGameOverlayEvent.ElementType.HEALTH);
    }

    @Override
    public void post(RenderGameOverlayEvent.Post e, float partialTicks, ScaledResolution resolution) {

    	if(mc.currentScreen != null)return;
        if (mc.player.getItemStackFromSlot(EntityEquipmentSlot.HEAD).getItem() == TItems.sonic_shades) {
        if (Minecraft.getMinecraft().player.getItemStackFromSlot(EntityEquipmentSlot.HEAD).getItem() != TItems.sonic_shades)
            return;

            Entity mouseOver = Minecraft.getMinecraft().objectMouseOver.entityHit;

            GlStateManager.pushMatrix();

            //Head
            GlStateManager.pushMatrix();
            GlStateManager.translate(mc.displayWidth / 2 - 370, mc.displayHeight / 2 - 190, 50.0F);
            GlStateManager.scale(30, 30, 30);
            mc.getTextureManager().bindTexture(mc.player.getLocationSkin());
            GlStateManager.enableColorMaterial();
            RenderHelper.enableStandardItemLighting();
            biped.bipedHeadwear.rotateAngleX = biped.bipedHead.rotateAngleX = 0.7f;
            biped.bipedHeadwear.rotateAngleY = biped.bipedHead.rotateAngleY = (float) (-Math.PI / 4);
            biped.bipedHeadwear.rotateAngleZ = biped.bipedHead.rotateAngleZ = -0.5f;
            biped.bipedHead.render(0.064f);
            biped.bipedHeadwear.render(0.0625F);
            GlStateManager.color(1, 1, 1, 1);
            GlStateManager.popMatrix();
            RenderHelper.disableStandardItemLighting();
            GlStateManager.disableRescaleNormal();
            GlStateManager.setActiveTexture(OpenGlHelper.lightmapTexUnit);
            GlStateManager.disableTexture2D();
            GlStateManager.setActiveTexture(OpenGlHelper.defaultTexUnit);

            //Entity and info
            if (mc.player != null && mouseOver != null) {
                GlStateManager.pushMatrix();
                drawEntityOnScreen(mc.displayWidth / 2 - 110, mc.displayHeight / 2 - 80, 50, mouseOver);
                GlStateManager.popMatrix();

                mc.fontRenderer.drawStringWithShadow("Name: " + mouseOver.getName(), resolution.getScaledWidth() - 160, resolution.getScaledHeight() - 70, Color.GREEN.getRGB());
                mc.fontRenderer.drawStringWithShadow("Distance: " + Math.round(mouseOver.getDistance(mc.player)) + " blocks", resolution.getScaledWidth() - 160, resolution.getScaledHeight() - 60, Color.GREEN.getRGB());
                mc.fontRenderer.drawStringWithShadow("Age: " + mouseOver.ticksExisted / 20 + " seconds", resolution.getScaledWidth() - 160, resolution.getScaledHeight() - 50, Color.GREEN.getRGB());
                mc.fontRenderer.drawStringWithShadow("Position: " + Helper.formatBlockPos(mouseOver.getPosition()), resolution.getScaledWidth() - 160, resolution.getScaledHeight() - 40, Color.GREEN.getRGB());
            }

            //Compass
            EnumFacing face = mc.player.getHorizontalFacing();
            String direction = face == EnumFacing.NORTH ? "N" : (face == EnumFacing.EAST ? "E" : (face == EnumFacing.WEST ? "W" : "S"));
            mc.fontRenderer.drawStringWithShadow(direction, resolution.getScaledWidth() / 2 - mc.fontRenderer.getStringWidth(direction) / 2, mc.fontRenderer.FONT_HEIGHT / 2, Color.GREEN.getRGB());
            GlStateManager.popMatrix();
            
            //Rift?
           if(RiftHelper.isRift(mc.world.getChunkFromBlockCoords(mc.player.getPosition()).getPos(), mc.world)) {
        	   String riftString = "Rift Detected!";
               mc.fontRenderer.drawStringWithShadow(riftString, resolution.getScaledWidth() / 2 - mc.fontRenderer.getStringWidth(riftString) / 2, resolution.getScaledHeight() - mc.fontRenderer.FONT_HEIGHT * 5, Color.GREEN.getRGB());
           }
        }
	}
}