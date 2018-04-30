package net.tardis.mod.renderers;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.tardis.mod.Tardis;
import net.tardis.mod.models.console.ConsoleModel;

public class RenderConsole extends TileEntitySpecialRenderer {
	
	public ConsoleModel model=new ConsoleModel();
	public static final ResourceLocation TEXTURE=new ResourceLocation(Tardis.MODID,"textures/controls/console1.png");
	Minecraft mc;
	
	public RenderConsole() {
		mc=Minecraft.getMinecraft();
	}
	
	@Override
	public void render(TileEntity te, double x, double y, double z, float partialTicks, int destroyStage, float alpha) {
		mc.getTextureManager().bindTexture(TEXTURE);
		GlStateManager.pushMatrix();
		GlStateManager.translate(x+0.5, y+1, z+0.5);
		GlStateManager.rotate(180, 1, 0, 0);
		model.render(null,0,0,0,0,0,0.0625F);
		GlStateManager.popMatrix();
	}

}
