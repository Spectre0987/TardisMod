package net.tardis.mod.client.renderers.exteriors;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.Vec3d;
import net.tardis.mod.Tardis;
import net.tardis.mod.client.models.exteriors.ModelWardrobe;
import net.tardis.mod.client.renderers.tiles.RenderTileDoor;
import net.tardis.mod.client.worldshell.RenderWorldShell;
import net.tardis.mod.common.tileentity.TileEntityDoor;
import net.tardis.mod.util.client.RenderHelper;

public class RenderTileDoorWardrobe extends RenderExterior {

	public static ResourceLocation TEXTURE = new ResourceLocation(Tardis.MODID, "textures/exteriors/wardrobe.png");
	public static ModelWardrobe MODEL = new ModelWardrobe();
	public static RenderWorldShell shell = new RenderWorldShell();
	
	
	@Override
	public void renderExterior(TileEntityDoor te) {
		GlStateManager.pushMatrix();
		GlStateManager.translate(0, -0.5, 0);
		Minecraft.getMinecraft().getTextureManager().bindTexture(TEXTURE);
		if(te.isLocked())
			MODEL.renderClosed(0.0625F);
		else MODEL.renderOpen(0.0625F);
		GlStateManager.popMatrix();
	}

	@Override
	public void renderPortal(TileEntityDoor te, float partialTicks) {
		GlStateManager.pushMatrix();
		GlStateManager.translate(0.5, 0.8125, 0.4);
		GlStateManager.rotate(180, 0, 0, 1);
		RenderHelper.renderPortal(shell, te, partialTicks, te.getDoorAngle() - 180, RenderTileDoor.POSITION, new Vec3d(1, 2.5, 0), false);
		GlStateManager.popMatrix();
	}

	@Override
	public ResourceLocation getTexture() {
		return TEXTURE;
	}

}
