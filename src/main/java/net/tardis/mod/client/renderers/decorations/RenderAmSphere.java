package net.tardis.mod.client.renderers.decorations;

import net.minecraft.block.BlockHorizontal;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.tardis.mod.Tardis;
import net.tardis.mod.client.models.decoration.ModelASphere;
import net.tardis.mod.common.blocks.TBlocks;
import net.tardis.mod.util.common.helpers.Helper;

public class RenderAmSphere extends TileEntitySpecialRenderer {

	public static final ResourceLocation TEXTURE = new ResourceLocation(Tardis.MODID, "textures/blocks/am_sphere.png");
	public ModelASphere model = new ModelASphere();
	
	@Override
	public void render(TileEntity te, double x, double y, double z, float partialTicks, int destroyStage, float alpha) {
		GlStateManager.pushMatrix();
		GlStateManager.translate(x + 0.5, y + 1.5, z + 0.5);
		GlStateManager.rotate(180, 0, 0, 1);
		this.bindTexture(TEXTURE);
		if(te.getWorld() != null && te.getWorld().getBlockState(te.getPos()).getBlock() == TBlocks.am_sphere) {
			GlStateManager.rotate(Helper.getAngleFromFacing(te.getWorld().getBlockState(te.getPos()).getValue(BlockHorizontal.FACING)), 0, 1, 0);
		}
		model.render(null, 0, 0, 0, 0, 0, 0.0625F);
		GlStateManager.popMatrix();
	}

	@Override
	protected void bindTexture(ResourceLocation location) {
		Minecraft.getMinecraft().getTextureManager().bindTexture(location);
	}

}
