package net.tardis.mod.client.renderers;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.tardis.mod.Tardis;
import net.tardis.mod.client.models.ModelConsole;
import net.tardis.mod.client.models.console.contols.ModelAllControls;
import net.tardis.mod.common.tileentity.TileEntityTardis;

public class RenderConsole extends TileEntitySpecialRenderer {
	
	Minecraft mc;
	ModelAllControls controlModel = new ModelAllControls();
	ModelConsole consoleModel = new ModelConsole();
	
	public static final ResourceLocation CONTROL_TEXTURE = new ResourceLocation(Tardis.MODID, "textures/controls/control_sheet.png");
	public static final ResourceLocation CONSOLE_TEXTURE = new ResourceLocation(Tardis.MODID, "textures/controls/console.png");
	
	public RenderConsole() {
		mc = Minecraft.getMinecraft();
	}
	
	@Override
	public void render(TileEntity te, double x, double y, double z, float partialTicks, int destroyStage, float alpha) {
		GlStateManager.pushMatrix();
		mc.getTextureManager().bindTexture(CONTROL_TEXTURE);
		GlStateManager.translate(x + 0.5, y + 1.5, z + 0.5);
		GlStateManager.rotate(180, 1, 0, 0);
		controlModel.render(null, 0, 0, 0, 0, 0, 0.0625F);
		mc.getTextureManager().bindTexture(CONSOLE_TEXTURE);
		consoleModel.render(null, ((TileEntityTardis) te).frame, partialTicks, 0, 0, 0, 0.0625F);
		GlStateManager.popMatrix();
	}
	
}
