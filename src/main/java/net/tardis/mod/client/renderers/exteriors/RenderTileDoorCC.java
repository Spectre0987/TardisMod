package net.tardis.mod.client.renderers.exteriors;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.tardis.mod.Tardis;
import net.tardis.mod.client.models.ModelBlocks;
import net.tardis.mod.client.renderers.RenderHelper;
import net.tardis.mod.client.worldshell.RenderWorldShell;
import net.tardis.mod.common.blocks.BlockTardisTop;
import net.tardis.mod.common.tileentity.TileEntityDoor;
import net.tardis.mod.util.helpers.Helper;

public class RenderTileDoorCC extends TileEntitySpecialRenderer<TileEntityDoor> {

	public static ModelBlocks model = new ModelBlocks(new ResourceLocation(Tardis.MODID, "shells/tree.json"));
	public static final ResourceLocation CACTUS = new ResourceLocation(Tardis.MODID, "shells/cactus.json");
	public static final ResourceLocation TREE = new ResourceLocation(Tardis.MODID, "shells/tree.json");
	
	RenderWorldShell renderShell;
	
	public RenderTileDoorCC() {
		this.renderShell = new RenderWorldShell();
	}
	
	@Override
	public void render(TileEntityDoor te, double x, double y, double z, float partialTicks, int destroyStage, float alpha) {
		GlStateManager.pushMatrix();
		GlStateManager.translate(x, y - 1, z);
		ResourceLocation rl = model.loc;
		String name = te.getWorld().getBiome(te.getPos()).getBiomeName().toLowerCase();
		if(name.contains("desert") && !model.loc.equals(CACTUS)) model = new ModelBlocks(CACTUS);
		if(name.contains("taiga") && !model.loc.equals(TREE)) model = new ModelBlocks(TREE);
		if(rl != null) {
			model.render();
		}
		IBlockState state = te.getWorld().getBlockState(te.getPos());
		if(state.getBlock() instanceof BlockTardisTop) {
			EnumFacing facing = state.getValue(BlockTardisTop.FACING);
			GlStateManager.rotate(Helper.getAngleFromFacing(facing), 0, 1, 0);
			if(facing == EnumFacing.EAST || facing == EnumFacing.WEST) {
				GlStateManager.rotate(180, 0, 1, 0);
			}
			if(facing == EnumFacing.WEST) GlStateManager.translate(-1.01, 0, 0);
			if(facing == EnumFacing.EAST) GlStateManager.translate(0, 0, -1.01);
			if(facing == EnumFacing.SOUTH) GlStateManager.translate(-1.01, 0, -1.01);
			if(!te.isLocked())RenderHelper.renderPortal(renderShell, te, partialTicks, 0);
		}
		GlStateManager.popMatrix();
	}

}
