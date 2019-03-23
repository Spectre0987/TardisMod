package net.tardis.mod.client.renderers.exteriors;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.Vec3d;
import net.tardis.mod.Tardis;
import net.tardis.mod.client.models.exteriors.ModelLeftDoor04;
import net.tardis.mod.client.models.exteriors.ModelRightDoor04;
import net.tardis.mod.client.models.exteriors.ModelTardis04;
import net.tardis.mod.client.renderers.tiles.RenderTileDoor;
import net.tardis.mod.client.worldshell.FakeWorldBoti;
import net.tardis.mod.client.worldshell.RenderWorldShell;
import net.tardis.mod.common.tileentity.TileEntityDoor;
import net.tardis.mod.util.client.RenderHelper;

public class RenderTileDoor04 extends RenderExterior {

	public static final ResourceLocation TEXTURE = new ResourceLocation(Tardis.MODID, "textures/exteriors/04.png");
	private static final Vec3d SIZE = new Vec3d(1.1, 2.1, 0);
	private RenderWorldShell shell = new RenderWorldShell();
	private ModelTardis04 model = new ModelTardis04();
	private ModelLeftDoor04 ld = new ModelLeftDoor04();
	private ModelRightDoor04 rd = new ModelRightDoor04();

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
		GlStateManager.translate(0.55, 0.96875, -0.09375);
		GlStateManager.rotate(180, 0, 0, 1);
		RenderHelper.renderPortal(FakeWorldBoti.getFakeWorld(getWorld().provider.getDimension()), partialTicks, te.getDoorAngle() - 180, RenderTileDoor.POSITION, null, false);
		GlStateManager.popMatrix();
	}

	@Override
	public ResourceLocation getTexture() {
		return TEXTURE;
	}


}
