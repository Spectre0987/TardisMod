package net.tardis.mod.client.renderers;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.tardis.mod.Tardis;
import net.tardis.mod.client.models.ModelFoodMachine;
import net.tardis.mod.common.blocks.BlockFoodMachine;
import net.tardis.mod.common.blocks.TBlocks;

public class RenderFoodMachine extends TileEntitySpecialRenderer {
	
	Minecraft mc;
	public ModelFoodMachine model=new ModelFoodMachine();
	public static final ResourceLocation TEXTURE=new ResourceLocation(Tardis.MODID, "textures/blocks/food_machine.png");
	
	public RenderFoodMachine() {
		mc=Minecraft.getMinecraft();
	}

	@Override
	public void render(TileEntity te, double x, double y, double z, float partialTicks, int destroyStage, float alpha) {
		GlStateManager.pushMatrix();
		GlStateManager.translate(x+0.5, y+1.5, z+0.5);
		IBlockState state = mc.world.getBlockState(te.getPos());
		float angle = 0;
		if(state.getBlock() == TBlocks.food_machine) {
			EnumFacing facing = state.getValue(BlockFoodMachine.FACING);
			if(facing.equals(EnumFacing.EAST))
				angle = 270;
			if(facing.equals(EnumFacing.SOUTH))
				angle = 180;
			if(facing.equals(EnumFacing.WEST))
				angle = 90;
		}
		GlStateManager.rotate(angle,0,1,0);
		GlStateManager.rotate(180,1,0,0);
		mc.getTextureManager().bindTexture(TEXTURE);
		model.render(null, 0, 0, 0, 0, 0, 0.0625F);
		GlStateManager.popMatrix();
	}
}
