package net.tardis.mod.client.renderers.controls;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.tardis.mod.Tardis;
import net.tardis.mod.client.models.console.contols.ModelAllControls;
import net.tardis.mod.common.entities.controls.EntityControl;
import net.tardis.mod.common.items.TItems;
import net.tardis.mod.common.tileentity.TileEntityTardis;

public abstract class RenderControl extends Render<EntityControl> {
	
	protected Minecraft mc;
	public static final ResourceLocation CONTROL_TEXTURE = new ResourceLocation(Tardis.MODID, "textures/controls/control_sheet.png");
	public ModelAllControls control_model = new ModelAllControls();
	
	public RenderControl() {
		super(Minecraft.getMinecraft().getRenderManager());
		mc = Minecraft.getMinecraft();
	}

    public static void renderText(Entity entity, String text) {
    	System.out.println(text);
    	 GlStateManager.pushMatrix();
         GlStateManager.color(0, 0, 0, 0.4f);
         FontRenderer fontrenderer = Minecraft.getMinecraft().fontRenderer;
         GlStateManager.translate(0, 1.1 - entity.getEntityBoundingBox().maxY, 0);

        // float offset = MathHelper.cos(entity.ticksExisted * 0.1F) * -0.09F;
        // GlStateManager.translate(0, -offset, 0);

         GL11.glNormal3f(0.0F, 1.0F, 0.0F);
      ///   GL11.glScalef(0.5F, 0.5F, 0.5F);
         GlStateManager.rotate(180, 1, 0, 0);
         GlStateManager.rotate(-Minecraft.getMinecraft().getRenderManager().playerViewY, 0.0F, 1.0F, 0.0F);
         GlStateManager.rotate(Minecraft.getMinecraft().getRenderManager().playerViewX, 1.0F, 0.0F, 0.0F);
         GlStateManager.disableLighting();
         GlStateManager.depthMask(false);
         GlStateManager.disableDepth();
         GlStateManager.enableBlend();
         GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
         Tessellator tessellator = Tessellator.getInstance();
         BufferBuilder bufferBuilder = tessellator.getBuffer();

         GlStateManager.disableTexture2D();
         bufferBuilder.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION);
         int stringLength = fontrenderer.getStringWidth(text) / 2;
         bufferBuilder.color(0.0F, 0.0F, 0.0F, 0.25F);
         bufferBuilder.pos(-stringLength - 1, (-1), 0.0D).endVertex();
         bufferBuilder.pos(-stringLength - 1, (8), 0.0D).endVertex();
         bufferBuilder.pos(stringLength + 1, (8), 0.0D).endVertex();
         bufferBuilder.pos(stringLength + 1, (-1), 0.0D).endVertex();
         tessellator.draw();
         GlStateManager.enableTexture2D();

         GL11.glPushMatrix();
     //    GL11.glScalef(0.3F, 0.3F, 0.3F);
         fontrenderer.drawStringWithShadow(text, -fontrenderer.getStringWidth(text) / 2, (int) 0, 553648127);
         GL11.glPopMatrix();

         GlStateManager.enableDepth();
         GlStateManager.depthMask(true);

         GL11.glPushMatrix();
       //  GL11.glScalef(0.3F, 0.3F, 0.3F);
         fontrenderer.drawStringWithShadow(text, -fontrenderer.getStringWidth(text) / 2, (int) 0, -1);
         GL11.glPopMatrix();

         GlStateManager.enableLighting();
         GlStateManager.disableBlend();
         GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
         GlStateManager.popMatrix();
	}
	
	public abstract void renderControl(EntityControl entity, double x, double y, double z, float entityYaw, float partialTicks, TileEntityTardis tardis);
	
	@Override
	protected ResourceLocation getEntityTexture(EntityControl entity) {
		return CONTROL_TEXTURE;
	}

    @Override
    public void doRender(EntityControl entity, double x, double y, double z, float entityYaw, float partialTicks) {
        renderControl(entity, x, y, z, entityYaw, partialTicks, (TileEntityTardis) mc.world.getTileEntity(entity.getConsolePos()));
        if (mc.player.getHeldItemMainhand().getItem() == TItems.manual) {
            Entity look = mc.objectMouseOver.entityHit;
            if (look != null && entity != null && look.getEntityId() == entity.getEntityId()) {
                RenderControl.renderText(entity, entity.getDisplayName().getUnformattedText());
                this.renderLivingLabel(entity, entity.getDisplayName().getUnformattedText(), x,y, z, 46);
            }
        }
    }

}
