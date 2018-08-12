package net.tardis.mod.client.renderers.controls;

import java.util.List;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.tardis.mod.client.EnumExterior;
import net.tardis.mod.common.blocks.BlockFacingDecoration;
import net.tardis.mod.common.tileentity.TileEntityInteriorDoor;
import net.tardis.mod.common.tileentity.TileEntityTardis;
import net.tardis.mod.util.helpers.Helper;

public class RenderInteriorDoor extends TileEntitySpecialRenderer {
	
	@Override
	public void render(TileEntity te, double x, double y, double z, float partialTicks, int destroyStage, float alpha) {
		boolean open = false;
		GlStateManager.pushMatrix();
		TileEntityInteriorDoor door = (TileEntityInteriorDoor)te;
		List<TileEntity> list = te.getWorld().loadedTileEntityList;
		TileEntityTardis tardis = null;
		for(TileEntity liste : list) {
			if(liste instanceof TileEntityTardis) {
				tardis = (TileEntityTardis)liste;
			}
		}
		EnumExterior ext = tardis != null ? (tardis.getTopBlock() != null ? EnumExterior.getExteriorFromBlock(tardis.getTopBlock().getBlock()): EnumExterior.FIRST) : EnumExterior.FIRST;
		GlStateManager.translate((float)x, (float)y, (float)z);
		IBlockState state = te.getWorld().getBlockState(te.getPos());
		if(state.getBlock() instanceof BlockFacingDecoration) {
			GlStateManager.rotate(Helper.getAngleFromFacing(state.getValue(BlockFacingDecoration.FACING)), 0, 1, 0);
		}
		
		if(open) {
			ext.interiorModel.renderOpen();
			//TODO: WorldShell/WorldPortal here
		} else {
			ext.interiorModel.renderClosed();
		}
		
		
		GlStateManager.popMatrix();
	}

}
