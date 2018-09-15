package net.tardis.mod.client.renderers.tiles;

import java.util.List;

import org.lwjgl.opengl.GL11;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.tardis.mod.client.worldshell.RenderWorldShell;
import net.tardis.mod.common.tileentity.TileEntityHoloprojector;
import net.tardis.mod.util.helpers.Helper;

public class RenderTileHolo extends TileEntitySpecialRenderer<TileEntityHoloprojector> {

	RenderWorldShell renderShell = new RenderWorldShell();
	
	public RenderTileHolo() {}
	
	@Override
	public void render(TileEntityHoloprojector te, double x, double y, double z, float partialTicks, int destroyStage, float alpha) {
		Minecraft mc = Minecraft.getMinecraft();
		GlStateManager.pushMatrix();
		GlStateManager.translate(x + 0.4688, y + Helper.precentToPixels(6), z + 0.4688);
		GlStateManager.scale(0.06, 0.06, 0.06);
		try {
			mc.entityRenderer.disableLightmap();
			renderShell.doRender(te, 0, 0, 0, 0, 0, null);
			mc.entityRenderer.enableLightmap();
		}
		catch(Exception e) {}
		GlStateManager.popMatrix();
	}

}
