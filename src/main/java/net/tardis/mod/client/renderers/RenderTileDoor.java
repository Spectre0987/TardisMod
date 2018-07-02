package net.tardis.mod.client.renderers;

import org.lwjgl.opengl.GL11;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.Vec3d;
import net.tardis.mod.Tardis;
import net.tardis.mod.client.models.ModelTardis;
import net.tardis.mod.client.models.console.contols.ModelExteriorDoorL;
import net.tardis.mod.client.models.console.contols.ModelExteriorDoorR;
import net.tardis.mod.client.worldshell.RenderWorldShell;
import net.tardis.mod.common.blocks.BlockTardisTop;
import net.tardis.mod.common.blocks.TBlocks;
import net.tardis.mod.common.tileentity.TileEntityDoor;
import net.tardis.mod.util.helpers.Helper;

public class RenderTileDoor extends TileEntitySpecialRenderer {
	
	Minecraft mc;
	ModelTardis model = new ModelTardis();
	RenderWorldShell renderShell;
	ModelExteriorDoorR door_r = new ModelExteriorDoorR();
	ModelExteriorDoorL door_l = new ModelExteriorDoorL();
	public static final ResourceLocation TEXTURE = new ResourceLocation(Tardis.MODID, "textures/controls/tardis.png");
	public static final ResourceLocation BLACK_DOOR = new ResourceLocation(Tardis.MODID, "textures/blocks/door.png");
	
	public RenderTileDoor() {
		mc = Minecraft.getMinecraft();
		renderShell = new RenderWorldShell();
	}
	
	@Override
	public void render(TileEntity te, double x, double y, double z, float partialTicks, int destroyStage, float alpha) {
		
		GlStateManager.pushMatrix();
		GlStateManager.translate(x, y - 1, z + 0.5);
		GlStateManager.pushMatrix();
		GL11.glEnable(GL11.GL_STENCIL_TEST);
		
		// Always write to stencil buffer
		GL11.glStencilFunc(GL11.GL_NEVER, 1, 0xFF);
		GL11.glStencilOp(GL11.GL_REPLACE, GL11.GL_KEEP, GL11.GL_KEEP);
		GL11.glStencilMask(0xFF);
		GL11.glClear(GL11.GL_STENCIL_BUFFER_BIT);

		this.drawOutline();

		GL11.glClear(GL11.GL_DEPTH_BUFFER_BIT);
		// Only pass stencil test if equal to 1
		GL11.glStencilMask(0x00);
		GL11.glStencilFunc(GL11.GL_EQUAL, 1, 0xFF);

		// Draw scene from portal view
		GlStateManager.pushMatrix();
		GlStateManager.rotate(180,0,1,0);
		renderShell.doRender((TileEntityDoor)te, -1, 0, -4, 0, partialTicks);
		GlStateManager.popMatrix();

		GL11.glPopMatrix();
		GL11.glDisable(GL11.GL_STENCIL_TEST);

		// Draw portal stencils so portals wont be drawn over
		GL11.glColorMask(false, false, false, false);
		GL11.glClear(GL11.GL_DEPTH_BUFFER_BIT);
		//GL11.glEnable(GL11.GL_DEPTH_TEST);
		this.drawOutline();
		
		GL11.glColorMask(true, true, true, true);
	    GlStateManager.popMatrix();
	    this.renderHumanNautureExterior(te, x, y, z, partialTicks, destroyStage, alpha);
	}
	
	public void drawOutline() {
		mc.getTextureManager().bindTexture(BLACK_DOOR);
		Tessellator tes = Tessellator.getInstance();
		BufferBuilder buf = tes.getBuffer();
		buf.begin(7, DefaultVertexFormats.POSITION_TEX);
		buf.pos(0, 0, 0).tex(0, 0).endVertex();
		buf.pos(0, 2, 0).tex(0, 1).endVertex();
		buf.pos(1, 2, 0).tex(1, 1).endVertex();
		buf.pos(1, 0, 0).tex(1, 0).endVertex();
		tes.draw();
	}
	
	public void renderHumanNautureExterior(TileEntity te, double x, double y, double z, float partialTicks, int destroyStage, float alpha) {
		TileEntityDoor door = (TileEntityDoor)te;
		boolean open = !door.isLocked();
		GlStateManager.pushMatrix();
		{
			GlStateManager.translate(x + 0.5, y + 0.5, z + 0.5);
			GlStateManager.rotate(180, 1, 0, 0);
			IBlockState state = mc.world.getBlockState(te.getPos());
			if (state.getBlock() == TBlocks.tardis_top) {
				EnumFacing facing = state.getValue(BlockTardisTop.FACING);
				float angle = Helper.getAngleFromFacing(facing);
				GlStateManager.rotate(angle - 180, 0, 1, 0);
			}
			mc.getTextureManager().bindTexture(TEXTURE);
			model.render(null, 0, 0, 0, 0, 0, 0.0625F);
			GlStateManager.pushMatrix();
			{
				if (open) {
					Vec3d origin = Helper.convertToPixels(-6.5, 0, -8.5);
					GlStateManager.translate(origin.x, origin.y, origin.z);
					GlStateManager.rotate(-85, 0, 1, 0);
					origin = origin.scale(-1);
					GlStateManager.translate(origin.x, origin.y, origin.z);
				}
				door_l.render(null, 0, 0, 0, 0, 0, 0.0625F);
			}
			GlStateManager.popMatrix();
			
			GlStateManager.pushMatrix();
			{
				if (open) {
					Vec3d origin = Helper.convertToPixels(6.5, 0, -8.5);
					GlStateManager.translate(origin.x, origin.y, origin.z);
					GlStateManager.rotate(90, 0, 1, 0);
					origin = origin.scale(-1);
					GlStateManager.translate(origin.x, origin.y, origin.z);
				}
				door_r.render(null, 0, 0, 0, 0, 0, 0.0625F);
			}
			GlStateManager.popMatrix();
			GlStateManager.popMatrix();
		}
	}
	@Override
	public void renderTileEntityFast(TileEntity te, double x, double y, double z, float partialTicks, int destroyStage, float partial, BufferBuilder buffer) {
		// TODO Auto-generated method stub
		super.renderTileEntityFast(te, x, y, z, partialTicks, destroyStage, partial, buffer);
	}
	
}
