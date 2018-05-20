package net.tardis.mod.client.renderers;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntityItemStackRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.tardis.mod.Tardis;
import net.tardis.mod.client.models.ModelFoodMachine;

public class RenderItemFoodMachine extends TileEntityItemStackRenderer {
	
	Minecraft mc;
	public ModelFoodMachine model=new ModelFoodMachine();
	public static final ResourceLocation TEXTURE=new ResourceLocation(Tardis.MODID,"textures/blocks/food_machine.png");
	
	public RenderItemFoodMachine() {
		mc=Minecraft.getMinecraft();
	}

	@Override
	public void renderByItem(ItemStack itemStackIn) {
		GlStateManager.pushMatrix();
		GlStateManager.rotate(180,1,0,0);
		GlStateManager.scale(0.5, 0.5, 0.5);
		mc.getTextureManager().bindTexture(TEXTURE);
		model.render(null, 0, 0, 0, 0, 0, 0.0625F);
		GlStateManager.popMatrix();
	}

}
