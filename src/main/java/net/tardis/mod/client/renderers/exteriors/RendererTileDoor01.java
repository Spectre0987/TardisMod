package net.tardis.mod.client.renderers.exteriors;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;
import net.tardis.mod.Tardis;
import net.tardis.mod.client.models.exteriors.ModelLeftDoor02;
import net.tardis.mod.client.models.exteriors.ModelRightDoor02;
import net.tardis.mod.client.models.exteriors.ModelTardis02;
import net.tardis.mod.client.renderers.tiles.RenderTileDoor;
import net.tardis.mod.client.worldshell.RenderWorldShell;
import net.tardis.mod.common.tileentity.TileEntityDoor;
import net.tardis.mod.util.client.RenderHelper;

public class RendererTileDoor01 extends RenderExterior {

	public static final ResourceLocation TEXTURE = new ResourceLocation(Tardis.MODID, "textures/exteriors/02.png");
	RenderWorldShell renderShell = new RenderWorldShell();
	ModelTardis02 model = new ModelTardis02();
	ModelRightDoor02 rd = new ModelRightDoor02();
	ModelLeftDoor02 ld = new ModelLeftDoor02();

	@Override
	public void renderExterior(TileEntityDoor te) {
		GlStateManager.pushMatrix();
		GlStateManager.translate(0, -0.5, 0);
		if (te.isLocked())
			model.renderClosed(0.0625F);
		else model.renderOpen(0.0625F);
		GlStateManager.popMatrix();
	}

	@Override
	public void renderPortal(TileEntityDoor te, float partialTicks) {
		GlStateManager.pushMatrix();
		GlStateManager.translate(0.5, 0.9375, 0);
		GlStateManager.rotate(180, 0, 0, 1);
		RenderHelper.renderPortal(renderShell, te, partialTicks, te.getDoorAngle() - 180, RenderTileDoor.POSITION, null, false);
		GlStateManager.popMatrix();
	}

	@Override
	public ResourceLocation getTexture() {
		return TEXTURE;
	}


}