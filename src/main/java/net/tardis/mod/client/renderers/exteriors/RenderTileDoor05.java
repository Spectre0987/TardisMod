package net.tardis.mod.client.renderers.exteriors;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.util.ResourceLocation;
import net.tardis.mod.Tardis;
import net.tardis.mod.client.models.exteriors.ModelLeftDoor05;
import net.tardis.mod.client.models.exteriors.ModelRightDoor05;
import net.tardis.mod.client.models.exteriors.ModelTardis05;
import net.tardis.mod.client.renderers.RenderHelper;
import net.tardis.mod.client.renderers.tiles.RenderTileDoor;
import net.tardis.mod.client.worldshell.RenderWorldShell;
import net.tardis.mod.common.blocks.BlockTardisTop;
import net.tardis.mod.common.tileentity.exteriors.TileEntityDoor05;
import net.tardis.mod.util.helpers.Helper;

public class RenderTileDoor05 extends TileEntitySpecialRenderer<TileEntityDoor05> {

	public static final ResourceLocation TEXTURE = new ResourceLocation(Tardis.MODID, "textures/exteriors/05.png");
	private RenderWorldShell renderShell = new RenderWorldShell();
	private ModelLeftDoor05 ld = new ModelLeftDoor05();
	private ModelRightDoor05 rd = new ModelRightDoor05();
	private ModelTardis05 model = new ModelTardis05();
	
	public RenderTileDoor05() {}
	
	@Override
	public void render(TileEntityDoor05 te, double x, double y, double z, float partialTicks, int destroyStage, float alpha) {
		GlStateManager.pushMatrix();
		Minecraft.getMinecraft().getTextureManager().bindTexture(TEXTURE);
		GlStateManager.translate(x + 0.5, y + 0.5, z + 0.5);
		GlStateManager.rotate(180, 1, 0, 0);
		if(te.getWorld().getBlockState(te.getPos()).getBlock() instanceof BlockTardisTop) {
			GlStateManager.rotate(Helper.get360FromFacing(te.getWorld().getBlockState(te.getPos()).getValue(BlockTardisTop.FACING)), 0, 1, 0);
		}
		if(!te.isLocked()) {
			GlStateManager.pushMatrix();
			GlStateManager.rotate(180, 0, 0, 1);
			GlStateManager.translate(-0.5, -1.5, -0.125);
			RenderHelper.renderPortal(renderShell, te, partialTicks, 90, RenderTileDoor.POSITION);
			GlStateManager.popMatrix();
		}
		model.render(null, 0, 0, 0, 0, 0, 0.0625F);
		
		GlStateManager.pushMatrix();
		GlStateManager.translate(0.46875, 0, -0.5625);
		GlStateManager.rotate(te.isLocked() ? 0 : 85, 0, 1, 0);
		rd.render(null, 0, 0, 0, 0, 0, 0.0625F);
		GlStateManager.popMatrix();
		
		GlStateManager.pushMatrix();
		GlStateManager.translate(-0.46875, 0, -0.5625);
		GlStateManager.rotate(te.isLocked() ? 0 : -85, 0, 1, 0);
		ld.render(null, 0, 0, 0, 0, 0, 0.0625F);
		GlStateManager.popMatrix();
		
		GlStateManager.popMatrix();
	}

}
