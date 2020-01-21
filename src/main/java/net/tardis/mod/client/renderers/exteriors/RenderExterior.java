package net.tardis.mod.client.renderers.exteriors;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.GlStateManager.DestFactor;
import net.minecraft.client.renderer.GlStateManager.SourceFactor;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.MinecraftForgeClient;
import net.tardis.mod.common.blocks.BlockTardisTop;
import net.tardis.mod.common.tileentity.TileEntityDoor;
import net.tardis.mod.misc.TimedDecal;
import net.tardis.mod.util.common.helpers.Helper;

public abstract class RenderExterior extends TileEntitySpecialRenderer<TileEntityDoor> {
	
	@Override
	public void render(TileEntityDoor te, double x, double y, double z, float partialTicks, int destroyStage, float alpha) {
		GlStateManager.pushMatrix();
		GlStateManager.enableBlend();
		Minecraft.getMinecraft().getTextureManager().bindTexture(getTexture());
		GlStateManager.translate(x + 0.5, y, z + 0.5);
		GlStateManager.rotate(180, 1, 0, 0);
		if (te.getWorld() == null || te.getPos() == null) return;
		if (!(te.getWorld().getBlockState(te.getPos()).getBlock() instanceof BlockTardisTop)) return;
		GlStateManager.rotate(Helper.get180Rot(te.getWorld().getBlockState(te.getPos()).getValue(BlockTardisTop.FACING)), 0, 1, 0);
		GlStateManager.blendFunc(SourceFactor.SRC_ALPHA, DestFactor.ONE_MINUS_SRC_ALPHA);
		GlStateManager.color(1, 1, 1, te.alpha);
		if (MinecraftForgeClient.getRenderPass() == 0 && !te.isStealth()) {
			renderExterior(te);
		}
		if (!te.isLocked()) {
			this.setLightmapDisabled(true);
			renderPortal(te, partialTicks);
			this.setLightmapDisabled(false);
		}
		GlStateManager.disableBlend();
		GlStateManager.popMatrix();
		
		for(TimedDecal dec : te.forcefieldHits) {
			//TODO: Render hit image
		}
	}
	
	public abstract void renderExterior(TileEntityDoor te);
	
	public abstract void renderPortal(TileEntityDoor te, float partialTicks);
	
	public abstract ResourceLocation getTexture();
	
	
}