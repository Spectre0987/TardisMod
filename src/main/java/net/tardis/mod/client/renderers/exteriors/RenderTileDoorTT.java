package net.tardis.mod.client.renderers.exteriors;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.Vec3d;
import net.tardis.mod.Tardis;
import net.tardis.mod.client.models.exteriors.ModelTardisTT;
import net.tardis.mod.client.models.exteriors.TileEntityDoorTT;
import net.tardis.mod.client.renderers.tiles.RenderTileDoor;
import net.tardis.mod.client.worldshell.RenderWorldShell;
import net.tardis.mod.common.blocks.BlockTardisTop;
import net.tardis.mod.util.client.RenderHelper;
import net.tardis.mod.util.common.helpers.Helper;

public class RenderTileDoorTT extends TileEntitySpecialRenderer<TileEntityDoorTT> {

	public static RenderWorldShell renderShell = new RenderWorldShell();
	public static final ResourceLocation TEXTURE = new ResourceLocation(Tardis.MODID, "textures/exteriors/tt.png");
	public static ModelTardisTT model = new ModelTardisTT();
	
	@Override
	public void render(TileEntityDoorTT te, double x, double y, double z, float partialTicks, int destroyStage, float alpha) {
		Minecraft.getMinecraft().getTextureManager().bindTexture(TEXTURE);
		GlStateManager.pushMatrix();
		GlStateManager.enableAlpha();
		GlStateManager.enableBlend();
		if(te.isDemat || te.isRemat)GlStateManager.color(1, 1, 1, te.alpha);
		GlStateManager.translate(x + 0.5, y + 0.5, z + 0.5);
		GlStateManager.rotate(180, 1, 0, 0);
		if(te.getWorld().getBlockState(te.getPos()).getBlock() instanceof BlockTardisTop) {
			GlStateManager.rotate(Helper.get360FromFacing(te.getWorld().getBlockState(te.getPos()).getValue(BlockTardisTop.FACING)), 0, 1, 0);
		}
		
		if(!te.isLocked()) {
			GlStateManager.pushMatrix();
			GlStateManager.rotate(180, 0, 0, 1);
			GlStateManager.translate(-0.5, -1.25, 0);
			RenderHelper.renderPortal(renderShell, te, partialTicks, 90, RenderTileDoor.POSITION, new Vec3d(1, 2, 0), false);
			GlStateManager.popMatrix();
			
		}
		
		if(te.isLocked())model.renderClosed(0.0625F);
		else model.renderOpen(0.0625F);
		GlStateManager.disableAlpha();
		GlStateManager.disableBlend();
		GlStateManager.popMatrix();
	}

}
