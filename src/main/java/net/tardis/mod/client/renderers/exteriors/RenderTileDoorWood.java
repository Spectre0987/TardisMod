package net.tardis.mod.client.renderers.exteriors;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.util.ResourceLocation;
import net.tardis.mod.client.models.exteriors.ModelTardisWoodDoor;
import net.tardis.mod.client.renderers.tiles.RenderTileDoor;
import net.tardis.mod.client.worldshell.RenderWorldShell;
import net.tardis.mod.common.tileentity.TileEntityDoor;
import net.tardis.mod.util.client.RenderHelper;

public class RenderTileDoorWood extends RenderExterior {

	public static RenderWorldShell renderShell = new RenderWorldShell();
	public static ModelTardisWoodDoor model = new ModelTardisWoodDoor();
	
	@Override
	public void renderExterior(TileEntityDoor te) {
		GlStateManager.pushMatrix();
		GlStateManager.translate(0.5, 0, -1.3125);
		GlStateManager.rotate(180, 0, 0, 1);
		if(te.isLocked())model.renderClosed(0.0625F);
		else model.renderOpen(0.0625f);
		GlStateManager.popMatrix();
	}

	@Override
	public void renderPortal(TileEntityDoor te, float partialTicks) {
		GlStateManager.pushMatrix();
		GlStateManager.translate(0.5, 1, 0.5);
		GlStateManager.rotate(180, 0, 0, 1);
		RenderHelper.renderPortal(renderShell, te, partialTicks, 90, RenderTileDoor.POSITION, null, false);
		GlStateManager.popMatrix();
	}

	@Override
	public ResourceLocation getTexture() {
		return TextureMap.LOCATION_BLOCKS_TEXTURE;
	}

}
