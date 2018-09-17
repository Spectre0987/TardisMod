package net.tardis.mod.client.renderers.exteriors;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.tardis.mod.Tardis;
import net.tardis.mod.client.models.exteriors.ModelLeftDoor05;
import net.tardis.mod.client.models.exteriors.ModelRightDoor05;
import net.tardis.mod.client.models.exteriors.ModelTardis05;
import net.tardis.mod.client.worldshell.RenderWorldShell;

public class RenderTileDoor05 extends TileEntitySpecialRenderer {

	public static final ResourceLocation TEXTURE = new ResourceLocation(Tardis.MODID, "textures/exteriors/05.png");
	private RenderWorldShell renderShell = new RenderWorldShell();
	private ModelLeftDoor05 ld = new ModelLeftDoor05();
	private ModelRightDoor05 rd = new ModelRightDoor05();
	private ModelTardis05 model = new ModelTardis05();
	
	public RenderTileDoor05() {}
	
	@Override
	public void render(TileEntity te, double x, double y, double z, float partialTicks, int destroyStage, float alpha) {
		GlStateManager.pushMatrix();
		Minecraft.getMinecraft().getTextureManager().bindTexture(TEXTURE);
		GlStateManager.translate(x + 0.5, y + 0.5, z + 0.5);
		GlStateManager.rotate(180, 1, 0, 0);
		model.render(null, 0, 0, 0, 0, 0, 0.0625F);
		GlStateManager.popMatrix();
	}

}
