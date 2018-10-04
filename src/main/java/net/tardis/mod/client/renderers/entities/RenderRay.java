package net.tardis.mod.client.renderers.entities;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.tardis.mod.Tardis;
import net.tardis.mod.client.models.entity.projectile.ModelRay;
import net.tardis.mod.common.entities.EntityDalekRay;

public class RenderRay extends Render<EntityDalekRay> {
	
	Minecraft mc;
	public ModelRay model = new ModelRay();
	public static final ResourceLocation TEXTURE = new ResourceLocation(Tardis.MODID, "textures/entity/ray.png");
	
	public RenderRay(RenderManager manager) {
		super(manager);
		mc = Minecraft.getMinecraft();
	}
	
	@Override
	protected ResourceLocation getEntityTexture(EntityDalekRay entity) {
		return TEXTURE;
	}
	
	@Override
	public void doRender(EntityDalekRay entity, double x, double y, double z, float entityYaw, float partialTicks) {
		Entity e = entity.world.getEntityByID(entity.getEntityId());
		if(e != null) {
			EntityPlayer player = Minecraft.getMinecraft().player;
			Minecraft.getMinecraft().entityRenderer.disableLightmap();
			GlStateManager.pushMatrix();
			GlStateManager.disableTexture2D();
			BufferBuilder bb = Tessellator.getInstance().getBuffer();
			bb.begin(GL11.GL_LINES, DefaultVertexFormats.POSITION_COLOR);
			GL11.glLineWidth(8F);
			bb.pos(e.posX, e.posY, e.posZ).color(1F, 0, 1F, 1F).endVertex();
			bb.pos(entity.posX - e.posX, entity.posX - e.posY, entity.posX - e.posZ).color(0, 0, 1F, 1F).endVertex();
			Tessellator.getInstance().draw();
			GlStateManager.popMatrix();
			GlStateManager.enableTexture2D();
			GL11.glLineWidth(1F);
			Minecraft.getMinecraft().entityRenderer.enableLightmap();
		}
		else System.out.println("E is null!");
	}
	
}
