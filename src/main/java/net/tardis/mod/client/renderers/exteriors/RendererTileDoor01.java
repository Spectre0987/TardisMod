package net.tardis.mod.client.renderers.exteriors;

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
import net.tardis.mod.client.models.exteriors.ModelLeftDoor02;
import net.tardis.mod.client.models.exteriors.ModelRightDoor02;
import net.tardis.mod.client.models.exteriors.ModelTardis02;
import net.tardis.mod.client.renderers.RenderHelper;
import net.tardis.mod.client.renderers.controls.RenderDoor;
import net.tardis.mod.client.worldshell.RenderWorldShell;
import net.tardis.mod.common.blocks.BlockTardisTop;
import net.tardis.mod.common.tileentity.TileEntityDoor;
import net.tardis.mod.util.helpers.Helper;

public class RendererTileDoor01 extends TileEntitySpecialRenderer<TileEntityDoor> {

	Minecraft mc;
	RenderWorldShell renderShell = new RenderWorldShell();
	ModelTardis02 model = new ModelTardis02();
	ModelRightDoor02 rd = new ModelRightDoor02();
	ModelLeftDoor02 ld = new ModelLeftDoor02();
	public static final ResourceLocation TEXTURE = new ResourceLocation(Tardis.MODID, "textures/exteriors/02.png");
	
	public RendererTileDoor01() {
		mc = Minecraft.getMinecraft();
	}
	
	@Override
	public void render(TileEntityDoor te, double x, double y, double z, float partialTicks, int destroyStage, float alpha) {
		GlStateManager.pushMatrix();
		GlStateManager.translate(x, y, z);
		boolean open = !te.isLocked();
		IBlockState state = mc.world.getBlockState(te.getPos());
		if(state.getBlock() instanceof BlockTardisTop) {
			EnumFacing facing = state.getValue(BlockTardisTop.FACING);
			switch(facing) {
			case EAST:{
				GlStateManager.translate(0, 0, 1);
				GlStateManager.rotate(90,0,1,0);
            }
                case SOUTH: {
				GlStateManager.translate(0, 0, 1);
				GlStateManager.rotate(90,0,1, 0);
                }
                case WEST: {
				GlStateManager.translate(0, 0, 1);
				GlStateManager.rotate(90,0,1,0);
                }
                default: {
				GlStateManager.translate(0, -1, 0.5);
				GlStateManager.rotate(0,0,0,0);
                }
            }
		}
		if(open)RenderHelper.renderPortal(renderShell, te, partialTicks);
	    GlStateManager.popMatrix();
	    //RenderDoor
	    {
			GlStateManager.pushMatrix();
			GlStateManager.translate(x + 0.5, y + 0.5, z + 0.5);
			GlStateManager.rotate(180, 0, 0, 1);
			if(mc.world.getBlockState(te.getPos()).getBlock() instanceof BlockTardisTop) {
				GlStateManager.rotate(Helper.getAngleFromFacing(mc.world.getBlockState(te.getPos()).getValue(BlockTardisTop.FACING)), 0, 1, 0);
			}
			mc.getTextureManager().bindTexture(TEXTURE);
			model.render(null, 0, 0, 0, 0, 0, 0.0625F);
			GlStateManager.pushMatrix();
			if (open) {
				Vec3d origin = Helper.convertToPixels(7, 0, -8.5);
				GlStateManager.translate(origin.x, origin.y, origin.z);
				GlStateManager.rotate(85, 0, 1, 0);
				origin = origin.scale(-1);
				GlStateManager.translate(origin.x, origin.y, origin.z);
			}
			rd.render(null, 0, 0, 0, 0, 0, 0.0625F);
			GlStateManager.popMatrix();
			GlStateManager.pushMatrix();
				if (open) {
					Vec3d origin = Helper.convertToPixels(-7, 0, -8.5);
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