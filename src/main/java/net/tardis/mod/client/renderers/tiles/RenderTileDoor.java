package net.tardis.mod.client.renderers.tiles;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.Vec3d;
import net.tardis.mod.Tardis;
import net.tardis.mod.client.models.exteriors.ModelLeftDoor01;
import net.tardis.mod.client.models.exteriors.ModelRightDoor01;
import net.tardis.mod.client.models.exteriors.ModelTardis01;
import net.tardis.mod.client.renderers.exteriors.RenderExterior;
import net.tardis.mod.client.worldshell.RenderWorldShell;
import net.tardis.mod.common.tileentity.TileEntityDoor;
import net.tardis.mod.util.client.RenderHelper;

public class RenderTileDoor extends RenderExterior {

	public static final ResourceLocation TEXTURE = new ResourceLocation(Tardis.MODID, "textures/exteriors/01.png");
	public static final Vec3d POSITION = new Vec3d(0, 0, -0.5);
	public ModelTardis01 model = new ModelTardis01();
	public ModelRightDoor01 rd = new ModelRightDoor01();
	public ModelLeftDoor01 ld = new ModelLeftDoor01();
	RenderWorldShell renderShell = new RenderWorldShell();

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
		GlStateManager.translate(0.5, 1, 0);
		GlStateManager.rotate(180, 0, 0, 1);
		RenderHelper.renderPortal(renderShell, te, partialTicks, 90, RenderTileDoor.POSITION, null, false);
		GlStateManager.popMatrix();
	}

	@Override
	public ResourceLocation getTexture() {
		return TEXTURE;
	}


}