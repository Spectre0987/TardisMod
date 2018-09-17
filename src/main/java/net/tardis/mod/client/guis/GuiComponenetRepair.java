package net.tardis.mod.client.guis;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;
import net.tardis.mod.Tardis;

public class GuiComponenetRepair extends GuiContainer{

	public static final ResourceLocation TEXTURE = new ResourceLocation(Tardis.MODID, "textures/gui/circuit_repair.png");
	public static final int WIDTH = 177;
	public static final int HEIGHT = 166;
	
	public GuiComponenetRepair(Container inventorySlotsIn) {
		super(inventorySlotsIn);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		Minecraft.getMinecraft().getTextureManager().bindTexture(TEXTURE);
		ScaledResolution res = new ScaledResolution(Minecraft.getMinecraft());
		this.drawTexturedModalRect(res.getScaledWidth() / 2 - WIDTH / 2, res.getScaledHeight() / 2 - HEIGHT / 2, 0, 0, WIDTH, HEIGHT);
	}

}
