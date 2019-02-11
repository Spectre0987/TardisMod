package net.tardis.mod.client.guis;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;
import net.tardis.mod.Tardis;
import net.tardis.mod.common.containers.ContainerCR;
import net.tardis.mod.common.tileentity.TileEntityComponentRepair;

public class GuiComponentRepair extends GuiContainer {

	public static final ResourceLocation TEXTURE = new ResourceLocation(Tardis.MODID, "textures/gui/circuit_repair.png");
	public static final int WIDTH = 177;
	public static final int HEIGHT = 166;
	private TileEntityComponentRepair cont;
	private ScaledResolution res;

	public GuiComponentRepair(Container inventorySlotsIn) {
		super(inventorySlotsIn);
		cont = ((ContainerCR) inventorySlotsIn).getTile();
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		this.drawDefaultBackground();
		Minecraft.getMinecraft().getTextureManager().bindTexture(TEXTURE);
		this.drawTexturedModalRect(res.getScaledWidth() / 2 - WIDTH / 2, res.getScaledHeight() / 2 - HEIGHT / 2, 0, 0, WIDTH, HEIGHT);
		this.drawTexturedModalRect(res.getScaledWidth() / 2 - 3, res.getScaledHeight() / 2 - 48, 177, 14, Math.round(22 * (float) (200 - cont.progress) / 200), 16);
	}

	@Override
	public void initGui() {
		super.initGui();
		res = new ScaledResolution(Minecraft.getMinecraft());
	}

}
