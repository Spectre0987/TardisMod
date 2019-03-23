package net.tardis.mod.client.worldshell;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.renderer.BlockRendererDispatcher;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.math.BlockPos;

public class RenderWorldShell {
	
	public static void renderWorldShell(WorldClient world, BlockPos origin, int radius) {
		GlStateManager.pushMatrix();
		
		BlockRendererDispatcher dispatcher = Minecraft.getMinecraft().getBlockRendererDispatcher();
		Tessellator tess = Tessellator.getInstance();
		BufferBuilder buffer = tess.getBuffer();
		
		for (BlockPos.MutableBlockPos pos : BlockPos.getAllInBoxMutable(origin, origin.add(radius, radius, radius))) {
			IBlockState state = world.getBlockState(pos);
			Block block = state.getBlock();
			
			if (block.getDefaultState().getRenderType() != EnumBlockRenderType.INVISIBLE) {
				dispatcher.renderBlock(state, pos, world, buffer);
			}
		}
		GlStateManager.popMatrix();
	}
}