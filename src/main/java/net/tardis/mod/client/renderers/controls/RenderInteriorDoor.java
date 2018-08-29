package net.tardis.mod.client.renderers.controls;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tardis.mod.client.EnumExterior;
import net.tardis.mod.client.renderers.RenderHelper;
import net.tardis.mod.client.worldshell.RenderWorldShell;
import net.tardis.mod.client.worldshell.WorldShell;
import net.tardis.mod.common.blocks.BlockFacingDecoration;
import net.tardis.mod.common.tileentity.TileEntityInteriorDoor;
import net.tardis.mod.common.tileentity.TileEntityTardis;
import net.tardis.mod.util.helpers.Helper;

@SideOnly(Side.CLIENT)
public class RenderInteriorDoor extends TileEntitySpecialRenderer<TileEntityInteriorDoor> {
	
	private WorldShell shell;
	private RenderWorldShell render;
	
	public RenderInteriorDoor() {
		render = new RenderWorldShell();
	}
	
	@Override
	public void render(TileEntityInteriorDoor te, double x, double y, double z, float partialTicks, int destroyStage, float alpha) {
		boolean open = te.getOpen();
		GlStateManager.pushMatrix();
		GlStateManager.translate(x + 0.5, y - 1, z + 0.5);
		TileEntityInteriorDoor door = (TileEntityInteriorDoor)te;
		TileEntityTardis tardis = null;
		for(TileEntity liste : te.getWorld().getChunkFromBlockCoords(te.getPos()).getTileEntityMap().values()) {
			if(liste instanceof TileEntityTardis) {
				tardis = (TileEntityTardis)liste;
			}
		}
		EnumExterior ext = tardis != null ? (tardis.getTopBlock() != null ? EnumExterior.getExteriorFromBlock(tardis.getTopBlock().getBlock()): EnumExterior.FIRST) : EnumExterior.FIRST;
		IBlockState state = te.getWorld().getBlockState(te.getPos());
		if(state.getBlock() instanceof BlockFacingDecoration) {
			GlStateManager.rotate(Helper.getAngleFromFacing(state.getValue(BlockFacingDecoration.FACING)), 0, 1, 0);
			EnumFacing facing = state.getValue(BlockFacingDecoration.FACING);
			if(facing == EnumFacing.NORTH || facing == EnumFacing.SOUTH)GlStateManager.rotate(180, 0, 1, 0);
		}
		GlStateManager.translate(-0.5, 1, 0.475);
		if(open) {
			ext.interiorModel.renderOpen();
			RenderHelper.renderPortal(render, te, partialTicks);
		} else {
			GlStateManager.translate(0.25, 0, 0);
			ext.interiorModel.renderClosed();
		}
		
		
		GlStateManager.popMatrix();
	}

}
