package net.tardis.mod.client.renderers.exteriors;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.util.ResourceLocation;
import net.tardis.mod.Tardis;
import net.tardis.mod.client.models.exteriors.ModelLeftDoor04;
import net.tardis.mod.client.models.exteriors.ModelRightDoor04;
import net.tardis.mod.client.models.exteriors.ModelTardis04;
import net.tardis.mod.client.renderers.RenderHelper;
import net.tardis.mod.client.renderers.tiles.RenderTileDoor;
import net.tardis.mod.client.worldshell.RenderWorldShell;
import net.tardis.mod.common.blocks.BlockTardisTop;
import net.tardis.mod.common.tileentity.exteriors.TileEntityDoor04;
import net.tardis.mod.util.helpers.Helper;

public class RenderTileDoor04 extends TileEntitySpecialRenderer<TileEntityDoor04> {

	public static final ResourceLocation TEXTURE = new ResourceLocation(Tardis.MODID, "textures/exteriors/04.png");
	private RenderWorldShell shell = new RenderWorldShell();
	private ModelTardis04 model = new ModelTardis04();
	private ModelLeftDoor04 ld = new ModelLeftDoor04();
	private ModelRightDoor04 rd = new ModelRightDoor04();
	
	public RenderTileDoor04() {}
	
	@Override
	public void render(TileEntityDoor04 te, double x, double y, double z, float partialTicks, int destroyStage, float alpha) {
		Minecraft.getMinecraft().getTextureManager().bindTexture(TEXTURE);
		GlStateManager.pushMatrix();
		GlStateManager.translate(x + 0.5, y + 0.5, z + 0.5);
		GlStateManager.rotate(180, 1, 0, 0);
		if(te.getWorld().getBlockState(te.getPos()).getBlock() instanceof BlockTardisTop)
			GlStateManager.rotate(Helper.get360FromFacing(te.getWorld().getBlockState(te.getPos()).getValue(BlockTardisTop.FACING)), 0, 1, 0);
		
		if(!te.isLocked()) {
			GlStateManager.pushMatrix();
			GlStateManager.translate(0.5, 1.5, 0);
			GlStateManager.rotate(180, 0, 0, 1);
			RenderHelper.renderPortal(shell, te, partialTicks, 90, RenderTileDoor.POSITION);
			GlStateManager.popMatrix();
		}
		
		model.render(null, 0, 0, 0, 0, 0, 0.0625F);
		
		GlStateManager.pushMatrix();
		GlStateManager.translate(-0.46875, 0, -0.5625);
		GlStateManager.rotate(te.isLocked() ? 0 : -85, 0, 1, 0);
		ld.render(null, 0, 0, 0, 0, 0, 0.0625F);
		GlStateManager.popMatrix();
		
		GlStateManager.pushMatrix();
		GlStateManager.translate(0.46875, 0, -0.5625);
		GlStateManager.rotate(te.isLocked() ? 0 : 85, 0, 1, 0);
		rd.render(null, 0, 0, 0, 0, 0, 0.0625F);
		GlStateManager.popMatrix();
		
		GlStateManager.popMatrix();
	}

}
