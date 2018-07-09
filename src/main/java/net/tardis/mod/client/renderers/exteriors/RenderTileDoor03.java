package net.tardis.mod.client.renderers.exteriors;

import org.lwjgl.opengl.GL11;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.Vec3d;
import net.tardis.mod.Tardis;
import net.tardis.mod.client.models.exteriors.ModelLeftDoor03;
import net.tardis.mod.client.models.exteriors.ModelRightDoor03;
import net.tardis.mod.client.models.exteriors.ModelTardis03;
import net.tardis.mod.client.renderers.RenderDoor;
import net.tardis.mod.client.worldshell.RenderWorldShell;
import net.tardis.mod.common.blocks.BlockTardisTop;
import net.tardis.mod.common.tileentity.TileEntityDoor;
import net.tardis.mod.util.helpers.Helper;

public class RenderTileDoor03 extends TileEntitySpecialRenderer<TileEntityDoor> {

	Minecraft mc;
	RenderWorldShell renderShell = new RenderWorldShell();
	ModelTardis03 model = new ModelTardis03();
	ModelRightDoor03 rd = new ModelRightDoor03();
	ModelLeftDoor03 ld = new ModelLeftDoor03();
	public static final ResourceLocation TEXTURE = new ResourceLocation(Tardis.MODID, "textures/exteriors/03.png");
	
	public RenderTileDoor03() {
		mc = Minecraft.getMinecraft();
	}
	
	@Override
	public void render(TileEntityDoor te, double x, double y, double z, float partialTicks, int destroyStage, float alpha) {
		GlStateManager.pushMatrix();
		GlStateManager.translate(x, y, z);
		IBlockState state = mc.world.getBlockState(te.getPos());
		if(state.getBlock() instanceof BlockTardisTop) {
			EnumFacing facing = state.getValue(BlockTardisTop.FACING);
			switch(facing) {
			case EAST:{
				GlStateManager.translate(0, 0, 1);
				GlStateManager.rotate(90,0,1,0);
			};
			case SOUTH:{
				GlStateManager.translate(0, 0, 1);
				GlStateManager.rotate(90,0,1, 0);
			};
			case WEST:{
				GlStateManager.translate(0, 0, 1);
				GlStateManager.rotate(90,0,1,0);
			};
			default:{
				GlStateManager.translate(0, -1, 0.5);
				GlStateManager.rotate(0,0,0,0);
			};
			}
		}
		GL11.glEnable(GL11.GL_STENCIL_TEST);
		
		// Always write to stencil buffer
		GL11.glStencilFunc(GL11.GL_NEVER, 1, 0xFF);
		GL11.glStencilOp(GL11.GL_REPLACE, GL11.GL_KEEP, GL11.GL_KEEP);
		GL11.glStencilMask(0xFF);
		GL11.glClear(GL11.GL_STENCIL_BUFFER_BIT);

		this.drawOutline();

		// Only pass stencil test if equal to 1
		GL11.glStencilMask(0x00);
		GL11.glStencilFunc(GL11.GL_EQUAL, 1, 0xFF);

		// Draw scene from portal view
		try {
		GlStateManager.pushMatrix();
		GlStateManager.rotate(180,0,1,0);
		mc.entityRenderer.disableLightmap();
		renderShell.doRender((TileEntityDoor)te, -1, 0, -7, 0, partialTicks);
		mc.entityRenderer.enableLightmap();
		GlStateManager.popMatrix();
		}
		catch(Exception e) {}

		GL11.glDisable(GL11.GL_STENCIL_TEST);
		
	// Draw portal stencils so portals wont be drawn over
		GL11.glColorMask(false, false, false, false);
		this.drawOutline();
		
		//Set things back
		GL11.glColorMask(true, true, true, true);
	    GlStateManager.popMatrix();
	    //RenderDoor
	    {
			GlStateManager.pushMatrix();
			GlStateManager.translate(x + 0.5, y + 0.5, z + 0.5);
			GlStateManager.rotate(180, 0, 0, 1);
			boolean open = !te.isLocked();
			if(mc.world.getBlockState(te.getPos()).getBlock() instanceof BlockTardisTop) {
				GlStateManager.rotate(Helper.getAngleFromFacing(mc.world.getBlockState(te.getPos()).getValue(BlockTardisTop.FACING)), 0, 1, 0);
			}
			mc.getTextureManager().bindTexture(TEXTURE);
			model.render(null, 0, 0, 0, 0, 0, 0.0625F);
			GlStateManager.pushMatrix();
			if (open) {
				Vec3d origin = Helper.convertToPixels(7.5, 0, -8.5);
				GlStateManager.translate(origin.x, origin.y, origin.z);
				GlStateManager.rotate(85, 0, 1, 0);
				origin = origin.scale(-1);
				GlStateManager.translate(origin.x, origin.y, origin.z);
			}
			rd.render(null, 0, 0, 0, 0, 0, 0.0625F);
			GlStateManager.popMatrix();
			GlStateManager.pushMatrix();
				if (open) {
					Vec3d origin = Helper.convertToPixels(-7.5, 0, -8.5);
					GlStateManager.translate(origin.x, origin.y, origin.z);
					GlStateManager.rotate(-85, 0, 1, 0);
					origin = origin.scale(-1);
					GlStateManager.translate(origin.x, origin.y, origin.z);
				}
				ld.render(null, 0, 0, 0, 0, 0, 0.0625F);
			GlStateManager.popMatrix();
			GlStateManager.popMatrix();
	    }
	}
	public void drawOutline() {
		mc.getTextureManager().bindTexture(RenderDoor.TEXTURE);
		Tessellator tes = Tessellator.getInstance();
		BufferBuilder buf = tes.getBuffer();
		buf.begin(7, DefaultVertexFormats.POSITION_TEX);
		buf.pos(0, 0, 0).tex(0, 0).endVertex();
		buf.pos(0, 2, 0).tex(0, 1).endVertex();
		buf.pos(1, 2, 0).tex(1, 1).endVertex();
		buf.pos(1, 0, 0).tex(1, 0).endVertex();
		tes.draw();
	}
}