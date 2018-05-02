package net.tardis.mod.renderers;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;

public class RenderConsole extends TileEntitySpecialRenderer {
	
	Minecraft mc;
	
	public RenderConsole() {
		mc=Minecraft.getMinecraft();
	}
	
	@Override
	public void render(TileEntity te, double x, double y, double z, float partialTicks, int destroyStage, float alpha) {
		
	}

}
