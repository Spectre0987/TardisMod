package net.tardis.mod.client.renderers.exteriors;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.Vec3d;
import net.tardis.mod.Tardis;
import net.tardis.mod.api.disguise.DisguiseRegistry;
import net.tardis.mod.client.models.ModelBlocks;
import net.tardis.mod.client.renderers.tiles.RenderTileDoor;
import net.tardis.mod.client.worldshell.FakeWorldBoti;
import net.tardis.mod.client.worldshell.RenderWorldShell;
import net.tardis.mod.common.tileentity.TileEntityDoor;
import net.tardis.mod.util.client.RenderHelper;

public class RenderTileDoorCC extends RenderExterior {

	public static ModelBlocks model = new ModelBlocks(new ResourceLocation(Tardis.MODID, "shells/cactus.json"));
	public static Vec3d position = new Vec3d(0.75, 1.8, 0);
	RenderWorldShell renderShell = new RenderWorldShell();
	RenderTileDoorTT renderTT = new RenderTileDoorTT();

	@Override
	public void renderExterior(TileEntityDoor te) {
		GlStateManager.pushMatrix();
		ResourceLocation rl = DisguiseRegistry.getDisguise(te.getWorld().getBiome(te.getPos()).getRegistryName().toString());
		if (rl != null) {
			GlStateManager.translate(-0.5, 1, 0.5);
			GlStateManager.rotate(180, 1, 0, 0);
			if (!model.getKey().toString().equals(rl.toString()))
				model = new ModelBlocks(rl);
			model.render();
		} else {
			renderTT.renderExterior(te);
		}
		GlStateManager.popMatrix();
	}

	@Override
	public void renderPortal(TileEntityDoor te, float partialTicks) {
		if (DisguiseRegistry.getDisguise(te.getWorld().getBiome(te.getPos()).getRegistryName().toString()) != null) {
			GlStateManager.pushMatrix();
			GlStateManager.translate(0.375, 1, -0.501);
			GlStateManager.rotate(180, 0, 0, 1);
			RenderHelper.renderPortal(FakeWorldBoti.getFakeWorld(getWorld().provider.getDimension()), partialTicks, te.getDoorAngle() - 180, RenderTileDoor.POSITION, null, false);
			GlStateManager.popMatrix();
		} else {
			renderTT.renderPortal(te, partialTicks);
		}
	}

	@Override
	public ResourceLocation getTexture() {
		return RenderTileDoor.TEXTURE;
	}


}
