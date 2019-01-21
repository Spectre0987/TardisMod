package net.tardis.mod.client.renderers.exteriors;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.Vec3d;
import net.tardis.mod.Tardis;
import net.tardis.mod.client.models.exteriors.ModelTardisClock;
import net.tardis.mod.client.renderers.tiles.RenderTileDoor;
import net.tardis.mod.client.worldshell.RenderWorldShell;
import net.tardis.mod.common.tileentity.TileEntityDoor;
import net.tardis.mod.util.client.RenderHelper;

public class RenderTileDoorClock extends RenderExterior {

	public static final ResourceLocation TEXTURE = new ResourceLocation(Tardis.MODID, "textures/exteriors/clock.png");
	public static final Vec3d SIZE = new Vec3d(0.5, 1.25, 0);
	public ModelTardisClock model = new ModelTardisClock();
	public RenderWorldShell renderShell = new RenderWorldShell();

	@Override
	public void renderExterior(TileEntityDoor te) {
		GlStateManager.pushMatrix();
		GlStateManager.translate(0, -0.5, 0);
		if (te.isLocked()) model.renderClosed(0.0625F);
		else model.renderOpen(0.0625F);
		GlStateManager.popMatrix();
	}

	@Override
	public void renderPortal(TileEntityDoor te, float partialTicks) {
		GlStateManager.pushMatrix();
		GlStateManager.translate(0.25, 0.6, -0.171875);
		GlStateManager.rotate(180, 0, 0, 1);
		RenderHelper.renderPortal(renderShell, te, partialTicks, te.getDoorAngle() - 180, RenderTileDoor.POSITION, SIZE, false);
		GlStateManager.popMatrix();
	}

	@Override
	public ResourceLocation getTexture() {
		return TEXTURE;
	}

}
