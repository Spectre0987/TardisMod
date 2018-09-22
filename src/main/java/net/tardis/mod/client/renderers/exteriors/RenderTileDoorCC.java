package net.tardis.mod.client.renderers.exteriors;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.client.MinecraftForgeClient;
import net.tardis.mod.Tardis;
import net.tardis.mod.api.disguise.DisguiseRegistry;
import net.tardis.mod.client.models.ModelBlocks;
import net.tardis.mod.client.renderers.RenderHelper;
import net.tardis.mod.client.worldshell.RenderWorldShell;
import net.tardis.mod.common.blocks.BlockTardisTop;
import net.tardis.mod.common.tileentity.TileEntityDoor;
import net.tardis.mod.util.helpers.Helper;

public class RenderTileDoorCC extends TileEntitySpecialRenderer<TileEntityDoor> {

	public static ModelBlocks model = new ModelBlocks(new ResourceLocation(Tardis.MODID, "shells/cactus.json"));
	public static Vec3d position = new Vec3d(0.75, 1.8, 0);
	RenderWorldShell renderShell;
	
	public RenderTileDoorCC() {
		this.renderShell = new RenderWorldShell();
	}
	
	@Override
	public void render(TileEntityDoor te, double x, double y, double z, float partialTicks, int destroyStage, float alpha) {
		GlStateManager.pushMatrix();
		GlStateManager.translate(x, y - 1, z);
		String name = te.getWorld().getBiome(te.getPos()).getRegistryName().toString();
		ResourceLocation rl = null;
		rl = DisguiseRegistry.getDisguise(name);
		if(rl == null) rl = DisguiseRegistry.CACTUS;
		if(!model.getKey().equals(rl) && rl != null) model = new ModelBlocks(rl);
		if(MinecraftForgeClient.getRenderPass() == 0 && model != null && model.getKey() != null) {
			model.render();
		}
		IBlockState state = te.getWorld().getBlockState(te.getPos());
		if(state.getBlock() instanceof BlockTardisTop) {
			EnumFacing facing = state.getValue(BlockTardisTop.FACING);
			GlStateManager.rotate(Helper.getAngleFromFacing(facing), 0, 1, 0);
			if(facing == EnumFacing.EAST || facing == EnumFacing.WEST) {
				GlStateManager.rotate(180, 0, 1, 0);
			}
			if(facing == EnumFacing.WEST) GlStateManager.translate(-0.875, 0, -0.001);
			if(facing == EnumFacing.EAST) GlStateManager.translate(0.125, 0, -1.001);
			if(facing == EnumFacing.SOUTH) GlStateManager.translate(-0.875, 0, -1.01);
			if(facing == EnumFacing.NORTH) GlStateManager.translate(0.125, 0, -0.001);
			if(!te.isLocked())RenderHelper.renderPortal(renderShell, te, partialTicks, 0, null, position);
		}
		GlStateManager.popMatrix();
	}

}
