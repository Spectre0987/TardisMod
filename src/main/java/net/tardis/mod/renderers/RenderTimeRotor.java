package net.tardis.mod.renderers;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms.TransformType;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.tardis.mod.blocks.TBlocks;
import net.tardis.mod.tileentity.TileEntityTimeRotor;

public class RenderTimeRotor extends TileEntitySpecialRenderer {
	
	Minecraft mc;
	public static final ItemStack STACK=new ItemStack(TBlocks.time_rotor_interior);
	
	public RenderTimeRotor() {
		mc=Minecraft.getMinecraft();
	}

	@Override
	public void render(TileEntity te, double x, double y, double z, float partialTicks, int destroyStage, float alpha) {
		GlStateManager.pushMatrix();
		GlStateManager.translate(x+0.5, y+0.5, z+0.5);
		if(mc.world.getBlockState(te.getPos().down()).getBlock()==TBlocks.console) {
			GlStateManager.disableFog();
			GlStateManager.disableLighting();
			GlStateManager.translate(0, ((TileEntityTimeRotor)te).offsetY, 0);
			Minecraft.getMinecraft().getRenderItem().renderItem(STACK, TransformType.NONE);
			GlStateManager.enableFog();
			GlStateManager.enableLighting();
		}
		GlStateManager.popMatrix();
	}

}
