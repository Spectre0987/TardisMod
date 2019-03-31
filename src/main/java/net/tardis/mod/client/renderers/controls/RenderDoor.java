package net.tardis.mod.client.renderers.controls;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.client.MinecraftForgeClient;
import net.tardis.mod.Tardis;
import net.tardis.mod.client.EnumExterior;
import net.tardis.mod.client.worldshell.RenderWorldShell;
import net.tardis.mod.common.entities.controls.ControlDoor;
import net.tardis.mod.common.tileentity.TileEntityTardis;
import net.tardis.mod.util.client.RenderHelper;
import net.tardis.mod.util.common.helpers.Helper;

public class RenderDoor extends Render<ControlDoor> {
	
	public static final ResourceLocation BLACK = new ResourceLocation(Tardis.MODID, "textures/blocks/black.png");
	
	RenderWorldShell shellRender;
	Minecraft mc;
	
	public RenderDoor(RenderManager manager) {
		super(manager);
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
		GlStateManager.translate(x, y, z);
		GlStateManager.rotate(Helper.get360FromFacing(entity.getHorizontalFacing()), 0, 1, 0);
		GlStateManager.rotate(entity.getHorizontalFacing() == EnumFacing.NORTH || entity.getHorizontalFacing() == EnumFacing.SOUTH ? -180 : 0, 0, 1, 0);
		GlStateManager.translate(-0.5, 0, 0.5);
		mc.getTextureManager().bindTexture(BLACK);
		boolean open = entity.isOpen();
		TileEntityTardis tardis = entity.getTardis();
		EnumExterior ext = tardis != null ? EnumExterior.getExteriorFromBlock(tardis.getTopBlock().getBlock()) : EnumExterior.TT;
		GlStateManager.translate(open ? 0 : 0.25, 0, 0);
		if(MinecraftForgeClient.getRenderPass() == 0) {
			if(open)
				ext.interiorModel.renderOpen();
			else {
				ext.interiorModel.renderClosed();
			}
		}
		else if(open && tardis != null) {
			EnumFacing face = tardis.getFacing();
			Vec3d vec = new Vec3d(0, 1, 0);
			float angle = 0;
			if(face == EnumFacing.NORTH)
				vec = new Vec3d(-1, 1, -9);
			else if(face == EnumFacing.EAST) {
				vec = new Vec3d(0, 1, 0);
				angle = 0;
			}
			GlStateManager.translate(0, 0, -0.001);
			RenderHelper.renderPortal(shellRender, entity, partialTicks, angle, vec);
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