package net.tardis.mod.renderers;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.tardis.mod.Tardis;
import net.tardis.mod.models.console.contols.ModelAllControls;

public class RenderConsole extends TileEntitySpecialRenderer {
	
	Minecraft mc;
	ModelAllControls model=new ModelAllControls();
	
	public RenderConsole() {
		mc=Minecraft.getMinecraft();
	}
	
	@Override
	public void render(TileEntity te, double x, double y, double z, float partialTicks, int destroyStage, float alpha) {
		GlStateManager.pushMatrix();
		mc.getTextureManager().bindTexture(new ResourceLocation(Tardis.MODID,"textures/controls/control_sheet.png"));
		GlStateManager.translate(x+0.5, y+1.5, z+0.5);
		GlStateManager.rotate(180,1,0,0);
		model.render(null, 0, 0, 0, 0, 0, 0.0625F);
		GlStateManager.popMatrix();
	}

}
