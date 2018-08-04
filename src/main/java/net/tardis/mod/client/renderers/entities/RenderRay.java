package net.tardis.mod.client.renderers.entities;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.tardis.mod.Tardis;
import net.tardis.mod.client.models.ModelRay;
import net.tardis.mod.common.entities.EntityDalekRay;

public class RenderRay extends Render<EntityDalekRay> {
	
	Minecraft mc;
	public ModelRay model = new ModelRay();
	public static final ResourceLocation TEXTURE = new ResourceLocation(Tardis.MODID, "textures/entity/ray.png");
	
	public RenderRay() {
		super(Minecraft.getMinecraft().getRenderManager());
		mc = Minecraft.getMinecraft();
	}
	
	@Override
	protected ResourceLocation getEntityTexture(EntityDalekRay entity) {
		return TEXTURE;
	}
	
	@Override
	public void doRender(EntityDalekRay entity, double x, double y, double z, float entityYaw, float partialTicks) {
		Entity e = entity.world.getEntityByID(entity.getThrowerID());
		if(e != null) {
			GlStateManager.pushMatrix();
			GlStateManager.translate(x, y, z);
			
			BufferBuilder bb = Tessellator.getInstance().getBuffer();
			GL11.glLineWidth(8F);
			bb.begin(GL11.GL_LINES, DefaultVertexFormats.POSITION_TEX);
			mc.getTextureManager().bindTexture(TEXTURE);
			bb.pos(0, 0, 0).tex(0, 0).endVertex();
			bb.pos(e.posX - entity.posX, e.posY - entity.posY, e.posZ - entity.posZ).tex(1, 1).endVertex();
			Tessellator.getInstance().draw();
			GL11.glLineWidth(1F);
			
			GlStateManager.popMatrix();
		}
	}
	
}
