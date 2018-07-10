package net.tardis.mod.client.renderers;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.tardis.mod.client.worldshell.RenderWorldShell;
import net.tardis.mod.common.blocks.TileEntityHoloprojector;

public class RenderTileHolo extends TileEntitySpecialRenderer<TileEntityHoloprojector> {

	RenderWorldShell renderShell = new RenderWorldShell();
	
	public RenderTileHolo() {}
	
	@Override
	public void render(TileEntityHoloprojector te, double x, double y, double z, float partialTicks, int destroyStage, float alpha) {
		Minecraft mc = Minecraft.getMinecraft();
		GlStateManager.pushMatrix();
		GlStateManager.translate(x + 0.5, y + 1, z + 0.5);
		GlStateManager.scale(0.06,0.06,0.06);
		mc.entityRenderer.disableLightmap();
		try {
			renderShell.doRender(te, 0, 0, 0, 0, partialTicks);
		}
		catch(Exception e) {}
		mc.entityRenderer.enableLightmap();
		GlStateManager.popMatrix();
	}

}
