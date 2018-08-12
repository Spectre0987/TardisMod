package net.tardis.mod.client.renderers.controls;

import java.util.List;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.tardis.mod.client.EnumExterior;
import net.tardis.mod.common.tileentity.TileEntityTardis;

public class RenderInteriorDoor extends TileEntitySpecialRenderer {
	
	@Override
	public void render(TileEntity te, double x, double y, double z, float partialTicks, int destroyStage, float alpha) {
		GlStateManager.pushMatrix();
		List<TileEntity> list = te.getWorld().loadedTileEntityList;
		TileEntityTardis tardis = null;
		for(TileEntity liste : list) {
			if(liste instanceof TileEntityTardis) {
				tardis = (TileEntityTardis)liste;
			}
		}
		EnumExterior ext = tardis != null ? (tardis.getTopBlock() != null ? EnumExterior.getExteriorFromBlock(tardis.getTopBlock().getBlock()): EnumExterior.FIRST) : EnumExterior.FIRST;
		ext.interiorModel.renderOpen();
		GlStateManager.popMatrix();
		System.out.println("rendering");
	}

}
