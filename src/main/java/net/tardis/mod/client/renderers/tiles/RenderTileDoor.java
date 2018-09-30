package net.tardis.mod.client.renderers.tiles;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.client.MinecraftForgeClient;
import net.tardis.mod.Tardis;
import net.tardis.mod.client.models.exteriors.ModelLeftDoor01;
import net.tardis.mod.client.models.exteriors.ModelRightDoor01;
import net.tardis.mod.client.models.exteriors.ModelTardis01;
import net.tardis.mod.client.worldshell.RenderWorldShell;
import net.tardis.mod.common.blocks.BlockTardisTop;
import net.tardis.mod.common.tileentity.TileEntityDoor;
import net.tardis.mod.util.client.RenderHelper;
import net.tardis.mod.util.common.helpers.Helper;

public class RenderTileDoor extends TileEntitySpecialRenderer<TileEntityDoor> {

	Minecraft mc;
	RenderWorldShell renderShell = new RenderWorldShell();
	public ModelTardis01 model = new ModelTardis01();
	public ModelRightDoor01 rd = new ModelRightDoor01();
	public ModelLeftDoor01 ld = new ModelLeftDoor01();
	public static final ResourceLocation TEXTURE = new ResourceLocation(Tardis.MODID, "textures/exteriors/01.png");
	public static final Vec3d POSITION = new Vec3d(0, 0, -0.5);
	
	public RenderTileDoor() {
		mc = Minecraft.getMinecraft();
	}
	
	@Override
	public void render(TileEntityDoor te, double x, double y, double z, float partialTicks, int destroyStage, float alpha) {

		GlStateManager.pushMatrix();
		GlStateManager.translate(x, y, z);
		boolean open = !te.isLocked();
		EnumFacing facing = EnumFacing.NORTH;
		if(te.getWorld() != null) {
			IBlockState state = te.getWorld().getBlockState(te.getPos());
			if(state.getBlock() instanceof BlockTardisTop) {
				facing = state.getValue(BlockTardisTop.FACING);
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
			RenderHelper.renderPortal(renderShell, te, partialTicks, 90, POSITION);
		}
		GlStateManager.popMatrix();
		
		GlStateManager.pushMatrix();
		GlStateManager.translate(x + 0.5, y + 0.5, z + 0.5);
		if(te.getWorld().getBlockState(te.getPos()).getBlock() instanceof BlockTardisTop) {
			EnumFacing face = te.getWorld().getBlockState(te.getPos()).getValue(BlockTardisTop.FACING);
			GlStateManager.rotate(Helper.get360FromFacing(facing), 0, 1, 0);
			if(face == EnumFacing.WEST || face == EnumFacing.EAST) GlStateManager.rotate(180, 0, 1, 0);
		}
		if(MinecraftForgeClient.getRenderPass() == 0) {
			this.mc.getTextureManager().bindTexture(TEXTURE);
			GlStateManager.pushMatrix();
			GlStateManager.rotate(180, 1, 0, 0);
			model.renderGlow(null, 0, 0, 0, 0, 0, 0.0625F, true);

			GlStateManager.pushMatrix();
			GlStateManager.translate(0.46875, 0, -0.53125);
			GlStateManager.rotate(open ? 85 : 0, 0, 1, 0);
			ld.render(null, 0, 0, 0, 0, 0, 0.0625F);
			GlStateManager.popMatrix();
			
			GlStateManager.pushMatrix();
			GlStateManager.translate(-0.46875, 0, -0.53125);
			GlStateManager.rotate(open ? -85 : 0, 0, 1, 0);
			rd.render(null, 0, 0, 0, 0, 0, 0.0625F);
			GlStateManager.popMatrix();
			GlStateManager.popMatrix();
		}
		
		GlStateManager.popMatrix();
	}
	
}