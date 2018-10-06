package net.tardis.mod.client.renderers.exteriors;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.GlStateManager.DestFactor;
import net.minecraft.client.renderer.GlStateManager.SourceFactor;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.Vec3d;
import net.tardis.mod.Tardis;
import net.tardis.mod.client.models.exteriors.ModelTardisClock;
import net.tardis.mod.client.renderers.tiles.RenderTileDoor;
import net.tardis.mod.client.worldshell.RenderWorldShell;
import net.tardis.mod.common.blocks.BlockTardisTop;
import net.tardis.mod.common.tileentity.exteriors.TileEntityDoorClock;
import net.tardis.mod.util.client.RenderHelper;
import net.tardis.mod.util.common.helpers.Helper;

public class RenderTileDoorClock extends TileEntitySpecialRenderer<TileEntityDoorClock>{

	public static final ResourceLocation TEXTURE = new ResourceLocation(Tardis.MODID, "textures/exteriors/clock.png");
	public static final Vec3d SIZE = new Vec3d(0.5, 1.25, 0);
	public ModelTardisClock model = new ModelTardisClock();
	public RenderWorldShell renderShell = new RenderWorldShell();

	@Override
	public void render(TileEntityDoorClock te, double x, double y, double z, float partialTicks, int destroyStage, float alpha) {
		GlStateManager.pushMatrix();
		GlStateManager.translate(x + 0.5, y + 0.5, z + 0.5);
		Minecraft.getMinecraft().getTextureManager().bindTexture(TEXTURE);
		GlStateManager.rotate(180, 1, 0, 0);
		GlStateManager.enableAlpha();
		GlStateManager.enableBlend();
		GlStateManager.blendFunc(SourceFactor.SRC_ALPHA, DestFactor.ONE_MINUS_SRC_ALPHA);
		if(te.getWorld().getBlockState(te.getPos()).getBlock() instanceof BlockTardisTop) {
			GlStateManager.rotate(Helper.get360FromFacing(te.getWorld().getBlockState(te.getPos()).getValue(BlockTardisTop.FACING)), 0, 1, 0);
		}
		if(!te.isLocked()) {
			GlStateManager.pushMatrix();
			GlStateManager.rotate(180, 0, 0, 1);
			GlStateManager.translate(-0.25, -1.1, -0.165);
			RenderHelper.renderPortal(renderShell, te, partialTicks, 90, RenderTileDoor.POSITION, SIZE, false);
			GlStateManager.popMatrix();
		}
		if(te.isDemat || te.isRemat)GlStateManager.color(1, 1, 1, te.alpha);
		if(te.isLocked())model.renderClosed(0.0625F);
		else model.renderOpen(0.0625F);
		GlStateManager.disableAlpha();
		GlStateManager.disableBlend();
		GlStateManager.popMatrix();
	}

}
