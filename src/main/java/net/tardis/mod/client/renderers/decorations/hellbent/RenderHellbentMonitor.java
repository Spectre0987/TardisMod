package net.tardis.mod.client.renderers.decorations.hellbent;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.DimensionManager;
import net.tardis.mod.Tardis;
import net.tardis.mod.client.models.decoration.ModelHellbentMonitor;
import net.tardis.mod.common.blocks.BlockFacingDecoration;
import net.tardis.mod.common.tileentity.TileEntityTardis;
import net.tardis.mod.util.common.helpers.Helper;
import net.tardis.mod.util.common.helpers.TardisHelper;

import java.awt.*;

public class RenderHellbentMonitor extends TileEntitySpecialRenderer {

	Minecraft mc;
	public ModelHellbentMonitor model = new ModelHellbentMonitor();
	public static final ResourceLocation TEXTURE = new ResourceLocation(Tardis.MODID, "textures/blocks/hellbent/monitor.png");
	
	public RenderHellbentMonitor() {
		mc = Minecraft.getMinecraft();
	}
	
	@Override
	public void render(TileEntity te, double x, double y, double z, float partialTicks, int destroyStage, float alpha) {
		GlStateManager.pushMatrix();
		GlStateManager.translate(x + 0.5, y + 1.25, z + 0.5);
		GlStateManager.rotate(180, 1, 0, 0);
		IBlockState state = te.getWorld().getBlockState(te.getPos());
		if(state.getBlock() instanceof BlockFacingDecoration) {
			GlStateManager.rotate(Helper.getAngleFromFacing(state.getValue(BlockFacingDecoration.FACING)), 0, 1, 0);
		}
		GlStateManager.translate(0, -Helper.precentToPixels(4), Helper.precentToPixels(5));
		mc.getTextureManager().bindTexture(TEXTURE);
		model.render(null, 0, 0, 0, 0, 0, 0.0625F);
		
		TileEntityTardis tardis = (TileEntityTardis)te.getWorld().getTileEntity(TardisHelper.getTardisForPosition(te.getPos()));
		if(tardis != null) {
			GlStateManager.pushMatrix();
			double scale = 0.007;
			GlStateManager.translate(-Helper.precentToPixels(8), Helper.precentToPixels(8), -1);
			GlStateManager.scale(scale, scale, scale);
			drawInfo(tardis);
			GlStateManager.popMatrix();
		}
		
		GlStateManager.popMatrix();
	}

	private void drawInfo(TileEntityTardis tardis) {

		mc.fontRenderer.drawString("Location: " + Helper.formatBlockPos(tardis.getLocation()), 0, mc.fontRenderer.FONT_HEIGHT * 0, Color.WHITE.getRGB());
		mc.fontRenderer.drawString("Dimension: " + Helper.formatDimensionName(DimensionManager.createProviderFor(tardis.dimension).getDimensionType().getName()), 0, mc.fontRenderer.FONT_HEIGHT * 1, Color.WHITE.getRGB());
		mc.fontRenderer.drawString("Destination: " + Helper.formatBlockPos(tardis.getDestination()), 0, mc.fontRenderer.FONT_HEIGHT * 2, Color.WHITE.getRGB());
		mc.fontRenderer.drawString("Target Dim: " + Helper.formatDimensionName(DimensionManager.createProviderFor(tardis.getTargetDim()).getDimensionType().getName()), 0, mc.fontRenderer.FONT_HEIGHT * 3, Color.WHITE.getRGB());
		mc.fontRenderer.drawString("Artron Banks: " + Math.round(tardis.fuel * 100) + "%", 0, mc.fontRenderer.FONT_HEIGHT * 4, Color.white.getRGB());
		if (tardis.isInFlight())
			mc.fontRenderer.drawString("Time Left " + tardis.getTimeLeft() / 20, 0, mc.fontRenderer.FONT_HEIGHT * 5, Color.WHITE.getRGB());
		else mc.fontRenderer.drawString("TARDIS Landed", 0, mc.fontRenderer.FONT_HEIGHT * 5, Color.WHITE.getRGB());
		mc.fontRenderer.drawString("TARDIS Facing: " + tardis.facing.getName(), 0, mc.fontRenderer.FONT_HEIGHT * 6, Color.WHITE.getRGB());
		String health = (tardis.getHealth() * 100 + "");
		mc.fontRenderer.drawString("TARDIS Hull: " + health.substring(0, health.indexOf(".")) + "%", 0, mc.fontRenderer.FONT_HEIGHT * 7, Color.WHITE.getRGB());
	}

}
