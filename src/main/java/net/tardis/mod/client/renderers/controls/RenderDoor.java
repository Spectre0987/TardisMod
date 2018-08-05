package net.tardis.mod.client.renderers.controls;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.Vec3d;
import net.tardis.mod.Tardis;
import net.tardis.mod.client.EnumExterior;
import net.tardis.mod.client.renderers.RenderHelper;
import net.tardis.mod.client.worldshell.RenderWorldShell;
import net.tardis.mod.common.entities.controls.ControlDoor;
import net.tardis.mod.common.tileentity.TileEntityTardis;
import net.tardis.mod.util.helpers.Helper;

public class RenderDoor extends Render<ControlDoor> {
	
	public static final ResourceLocation BLACK = new ResourceLocation(Tardis.MODID, "textures/blocks/black.png");
	
	RenderWorldShell shellRender;
	Minecraft mc;
	
	public RenderDoor() {
		super(Minecraft.getMinecraft().getRenderManager());
		mc = Minecraft.getMinecraft();
		shellRender = new RenderWorldShell();
	}
	
	@Override
	protected ResourceLocation getEntityTexture(ControlDoor entity) {
		return null;
	}
	
	@Override
	public void doRender(ControlDoor entity, double x, double y, double z, float entityYaw, float partialTicks) {
		GlStateManager.pushMatrix();
		GlStateManager.translate(x - 0.25, y, z + 0.499);
		mc.getTextureManager().bindTexture(BLACK);
		boolean open = entity.isOpen();
		TileEntityTardis tardis = (TileEntityTardis)mc.world.getTileEntity(entity.getConsolePos());
		EnumExterior ext = EnumExterior.getExteriorFromBlock(tardis.getTopBlock().getBlock());
		if(open) {
			try {
				Vec3d offset = null;
				EnumFacing facing = entity.getFacing();
				if(facing == EnumFacing.NORTH) {
					offset = new Vec3d(-1,1,-12);
				}
				else if(facing == EnumFacing.EAST){
					offset = new Vec3d(11,1, -1);
				}
				else if(facing == EnumFacing.SOUTH) {
					offset = new Vec3d(0,1, 11);
				}
				else if(facing == EnumFacing.WEST) {
					offset = new Vec3d(-12,1,0);
				}
				mc.getTextureManager().bindTexture(BLACK);
				GlStateManager.translate(-0.25, 0, 0);
				
				ext.interiorModel.renderOpen();
				if(!tardis.isInFlight())RenderHelper.renderPortal(shellRender, entity, partialTicks, Helper.getAngleFromFacing(facing), offset, new Vec3d(1, 2,0));
				else RenderHelper.drawOutline(new Vec3d(1, 2,0));
			}
			catch(Exception e) {}
		}
		else {
			ext.interiorModel.renderClosed();
		}
		GlStateManager.popMatrix();
	}
	
	public void drawDoorShape() {
		GlStateManager.pushMatrix();
		GlStateManager.translate(0, 0, -1);
		Tessellator tes = Tessellator.getInstance();
		BufferBuilder buf = tes.getBuffer();
		buf.begin(7, DefaultVertexFormats.POSITION_TEX);
		
		buf.pos(0, 0, 0).tex(0, 0).endVertex();
		buf.pos(0, 2, 0).tex(0, 1).endVertex();
		buf.pos(1, 2, 0).tex(1, 1).endVertex();
		buf.pos(1, 0, 0).tex(1, 0).endVertex();
		
		tes.draw();
		GlStateManager.popMatrix();
	}
}
