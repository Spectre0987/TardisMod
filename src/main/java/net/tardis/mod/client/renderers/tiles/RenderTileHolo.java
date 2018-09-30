package net.tardis.mod.client.renderers.tiles;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.tardis.mod.client.worldshell.RenderWorldShell;
import net.tardis.mod.common.tileentity.TileEntityHoloprojector;
import net.tardis.mod.util.common.helpers.Helper;

public class RenderTileHolo extends TileEntitySpecialRenderer<TileEntityHoloprojector> {

	RenderWorldShell renderShell = new RenderWorldShell();
	
	public RenderTileHolo() {}
	
	@Override
	public void render(TileEntityHoloprojector te, double x, double y, double z, float partialTicks, int destroyStage, float alpha) {
		Minecraft mc = Minecraft.getMinecraft();
		GlStateManager.pushMatrix();
		GlStateManager.translate(x + 0.4688, y + Helper.precentToPixels(6), z + 0.4688);
		GlStateManager.scale(0.06, 0.06, 0.06);
		try {
			mc.entityRenderer.disableLightmap();
			renderShell.doRender(te, 0, 0, 0, 0, 0, null);
			mc.entityRenderer.enableLightmap();
		}
		catch(Exception e) {}
		GlStateManager.popMatrix();
	}

}
