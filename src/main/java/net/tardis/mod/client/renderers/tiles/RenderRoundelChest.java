package net.tardis.mod.client.renderers.tiles;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.tardis.mod.common.tileentity.decoration.TileEntityRoundelChest;

public class RenderRoundelChest extends TileEntitySpecialRenderer<TileEntityRoundelChest> {

	public RenderRoundelChest() {}

	@Override
	public void render(TileEntityRoundelChest te, double x, double y, double z, float partialTicks, int destroyStage, float alpha) {
		GlStateManager.pushMatrix();
		GlStateManager.translate(x, y, z + 1);
		Minecraft.getMinecraft().getTextureManager().bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);
		IBlockState state = Block.getStateById(te.getBlockId());
		Minecraft.getMinecraft().getBlockRendererDispatcher().renderBlockBrightness(state, 1F);
		GlStateManager.popMatrix();
	}
	
}
