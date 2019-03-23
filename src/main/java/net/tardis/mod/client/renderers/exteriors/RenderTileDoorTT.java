package net.tardis.mod.client.renderers.exteriors;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;
import net.tardis.mod.Tardis;
import net.tardis.mod.client.models.exteriors.ModelTardisTT;
import net.tardis.mod.client.renderers.tiles.RenderTileDoor;
import net.tardis.mod.client.worldshell.FakeWorldBoti;
import net.tardis.mod.client.worldshell.RenderWorldShell;
import net.tardis.mod.common.tileentity.TileEntityDoor;
import net.tardis.mod.util.client.RenderHelper;

public class RenderTileDoorTT extends RenderExterior {

	public static final ResourceLocation TEXTURE = new ResourceLocation(Tardis.MODID, "textures/exteriors/tt.png");
	public static RenderWorldShell renderShell = new RenderWorldShell();
	public static ModelTardisTT model = new ModelTardisTT();

	@Override
	public void renderExterior(TileEntityDoor te) {
		GlStateManager.pushMatrix();
		Minecraft.getMinecraft().getTextureManager().bindTexture(TEXTURE);
		GlStateManager.translate(0, -0.5, 0);
		if (te.isLocked()) model.renderClosed(0.0625F);
		else model.renderOpen(0.0625F);
		GlStateManager.popMatrix();
	}

	@Override
	public void renderPortal(TileEntityDoor te, float partialTicks) {
		GlStateManager.pushMatrix();
		GlStateManager.translate(0.5, 0.75, 0);
		GlStateManager.rotate(180, 0, 0, 1);
		RenderHelper.renderPortal(FakeWorldBoti.getFakeWorld(getWorld().provider.getDimension()), partialTicks, te.getDoorAngle() - 180, RenderTileDoor.POSITION, null, false);
		GlStateManager.popMatrix();
	}

	@Override
	public ResourceLocation getTexture() {
		return TEXTURE;
	}
}
