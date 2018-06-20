package net.tardis.mod.client.renderers;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.ResourceLocation;
import net.tardis.mod.Tardis;
import net.tardis.mod.common.dimensions.TDimensions;
import net.tardis.mod.common.entities.controls.ControlDoor;
import net.tardis.mod.common.tileentity.TileEntityTardis;

public class RenderDoor extends Render<ControlDoor> {
	
	public static final ResourceLocation TEXTURE = new ResourceLocation(Tardis.MODID, "textures/blocks/door.png");
	public static final ResourceLocation BLACK_HOLE_TEXTURE = new ResourceLocation(Tardis.MODID, "textures/blocks/black_hole.png");
	Minecraft mc;
	
	public RenderDoor() {
		super(Minecraft.getMinecraft().getRenderManager());
		mc = Minecraft.getMinecraft();
	}
	
	@Override
	protected ResourceLocation getEntityTexture(ControlDoor entity) {
		return TEXTURE;
	}
	
	@Override
	public void doRender(ControlDoor entity, double x, double y, double z, float entityYaw, float partialTicks) {
		GlStateManager.pushMatrix();
		GlStateManager.translate(x - 0.5, y, z + 0.48);
		mc.getTextureManager().bindTexture(TEXTURE);
		boolean open = entity.isOpen();
		GlStateManager.pushMatrix();
		if (open) {
			GlStateManager.rotate(90, 0, 1, 0);
		}
		this.drawDoorShape();
		GlStateManager.rotate(180, 0, 1, 0);
		GlStateManager.translate(-1, 0, 0);
		this.drawDoorShape();
		GlStateManager.popMatrix();
		GlStateManager.translate(0, 0, 0.01);
		if (open) {
			TileEntityTardis tardis = (TileEntityTardis)mc.world.getTileEntity(entity.getConsolePos());
			if(tardis.dimension == TDimensions.BLACK_HOLE_ID) {
				GlStateManager.pushMatrix();
				mc.getTextureManager().bindTexture(BLACK_HOLE_TEXTURE);
				mc.entityRenderer.disableLightmap();
				Tessellator tes = Tessellator.getInstance();
				BufferBuilder buf = tes.getBuffer();
				buf.begin(7, DefaultVertexFormats.POSITION_TEX);
				buf.pos(0, 0, 0).tex(0, 0).endVertex();
				buf.pos(0, 2, 0).tex(0, 1).endVertex();
				buf.pos(1, 2, 0).tex(1, 1).endVertex();
				buf.pos(1, 0, 0).tex(1, 0).endVertex();
				tes.draw();
				mc.entityRenderer.enableLightmap();
				GlStateManager.popMatrix();
			}
			else {
				GlStateManager.color(0F, 0F, 0F);
				this.drawDoorShape();
				GlStateManager.color(1F, 1F, 1F);
			}
		}
		
		GlStateManager.popMatrix();
	}
	
	public void drawDoorShape() {
		Tessellator tes = Tessellator.getInstance();
		BufferBuilder buf = tes.getBuffer();
		buf.begin(7, DefaultVertexFormats.POSITION_TEX);
		
		buf.pos(0, 0, 0).tex(0, 0).endVertex();
		buf.pos(0, 2, 0).tex(0, 2).endVertex();
		buf.pos(1, 2, 0).tex(1, 2).endVertex();
		buf.pos(1, 0, 0).tex(1, 0).endVertex();
		
		tes.draw();
	}
	
}
