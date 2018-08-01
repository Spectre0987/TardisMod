package net.tardis.mod.client.renderers.tiles;

import org.lwjgl.opengl.GL11;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.tardis.mod.Tardis;
import net.tardis.mod.client.models.exteriors.ModelLeftDoor01;
import net.tardis.mod.client.models.exteriors.ModelRightDoor01;
import net.tardis.mod.client.models.exteriors.ModelTardis01;
import net.tardis.mod.client.renderers.RenderHelper;
import net.tardis.mod.client.worldshell.RenderWorldShell;
import net.tardis.mod.common.blocks.BlockTardisTop;
import net.tardis.mod.common.tileentity.TileEntityDoor;
import net.tardis.mod.util.helpers.Helper;

public class RenderTileDoor extends TileEntitySpecialRenderer<TileEntityDoor> {

	Minecraft mc;
	RenderWorldShell renderShell = new RenderWorldShell();
	public ModelTardis01 model = new ModelTardis01();
	public ModelRightDoor01 rd = new ModelRightDoor01();
	public ModelLeftDoor01 ld = new ModelLeftDoor01();
	public static final ResourceLocation TEXTURE = new ResourceLocation(Tardis.MODID, "textures/exteriors/01.png");
	
	public RenderTileDoor() {
		mc = Minecraft.getMinecraft();
	}
	
	@Override
	public void render(TileEntityDoor te, double x, double y, double z, float partialTicks, int destroyStage, float alpha) {

		boolean airBelow = mc.world.getBlockState(new BlockPos(x, y - 1, z)).getBlock() == Blocks.AIR;

		GlStateManager.pushMatrix();
		GlStateManager.translate(x, y, z);
		boolean open = !te.isLocked();
		if(te.getWorld() != null) {
			IBlockState state = te.getWorld().getBlockState(te.getPos());
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
		}
		if(open) {
			RenderHelper.renderPortal(renderShell, te, partialTicks);
		}

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
			GlStateManager.enableBlend();
			GlStateManager.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
			GlStateManager.color(1.0f, 1.0f, 1.0f, te.alpha);
			model.render(null, 0, 0, 0, 0, 0, 0.0625F);
			GlStateManager.translate(0, -0.03125F, 0);
			GlStateManager.pushMatrix();
			if (open) {
				Vec3d origin = Helper.convertToPixels(7, 0, -8.5);
				GlStateManager.translate(origin.x, origin.y, origin.z);
				GlStateManager.rotate(85, 0, 1, 0);
				origin = origin.scale(-1);
				GlStateManager.translate(origin.x, origin.y, origin.z);
			}
			GlStateManager.color(1.0f, 1.0f, 1.0f, te.alpha);
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
				GlStateManager.color(1.0f, 1.0f, 1.0f, te.alpha);
				ld.render(null, 0, 0, 0, 0, 0, 0.0625F);
			GlStateManager.color(1.0f, 1.0f, 1.0f, te.alpha);
			GlStateManager.popMatrix();
			GlStateManager.popMatrix();
	    }
	}
	
}