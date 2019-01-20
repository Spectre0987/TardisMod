package net.tardis.mod.client.renderers.tiles;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.tardis.mod.Tardis;
import net.tardis.mod.client.models.items.ModelAlembic;
import net.tardis.mod.common.blocks.BlockAlembic;
import net.tardis.mod.util.common.helpers.Helper;

public class RenderAlembic extends TileEntitySpecialRenderer {

	public static final ResourceLocation TEXTURE = new ResourceLocation(Tardis.MODID, "textures/blocks/alembic.png");
	public ModelAlembic model = new ModelAlembic();
	Minecraft mc;

	public RenderAlembic() {
		mc = Minecraft.getMinecraft();
	}

	@Override
	public void render(TileEntity te, double x, double y, double z, float partialTicks, int destroyStage, float alpha) {
		GlStateManager.pushMatrix();
		GlStateManager.translate(x + 0.5, y + 1.5, z + 0.5);
		GlStateManager.rotate(180, 1, 0, 0);
		IBlockState state = te.getWorld().getBlockState(te.getPos());
		if (state.getBlock() instanceof BlockAlembic) {
			GlStateManager.rotate(Helper.getAngleFromFacing(state.getValue(BlockAlembic.FACING)), 0, 1, 0);
		}
		mc.getTextureManager().bindTexture(TEXTURE);
		model.render(null, 0, 0, 0, 0, 0, 0.0625F);
		GlStateManager.popMatrix();
	}
}
