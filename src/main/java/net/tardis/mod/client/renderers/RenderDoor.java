package net.tardis.mod.client.renderers;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.tardis.mod.Tardis;
import net.tardis.mod.common.entities.controls.ControlDoor;

public class RenderDoor extends Render {
	
	public static final ResourceLocation TEXTURE=new ResourceLocation(Tardis.MODID,"textures/blocks/door.png");
	Minecraft mc;
	
	public RenderDoor() {
		super(Minecraft.getMinecraft().getRenderManager());
		mc=Minecraft.getMinecraft();
	}
	
	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		return TEXTURE;
	}
	
	@Override
	public void doRender(Entity entity, double x, double y, double z, float entityYaw, float partialTicks) {
		GlStateManager.pushMatrix();
		GlStateManager.translate(x-0.5, y, z+0.48);
		mc.getTextureManager().bindTexture(TEXTURE);
		boolean open = ((ControlDoor)entity).isOpen();
		GlStateManager.pushMatrix();
			if(open) {
				GlStateManager.rotate(90, 0, 1, 0);
			}
			this.drawDoorShape();
			GlStateManager.rotate(180,0,1,0);
			GlStateManager.translate(-1, 0, 0);
			this.drawDoorShape();
		GlStateManager.popMatrix();
		GlStateManager.translate(0, 0, 0.01);
		if(open) {
			GlStateManager.color(0F, 0F, 0F);
			this.drawDoorShape();
			GlStateManager.color(1F, 1F, 1F);
		}
		
		GlStateManager.popMatrix();
	}
	
	public void drawDoorShape() {
		Tessellator tes=Tessellator.getInstance();
		BufferBuilder buf=tes.getBuffer();
		buf.begin(7, DefaultVertexFormats.POSITION_TEX);
		
		buf.pos(0, 0, 0).tex(0, 0).endVertex();
		buf.pos(0, 2, 0).tex(0, 2).endVertex();
		buf.pos(1, 2, 0).tex(1, 2).endVertex();
		buf.pos(1, 0, 0).tex(1, 0).endVertex();
		
		tes.draw();
	}
	
}
