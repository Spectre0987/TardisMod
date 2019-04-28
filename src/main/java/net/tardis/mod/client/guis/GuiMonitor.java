package net.tardis.mod.client.guis;

import java.io.IOException;
import java.util.Map.Entry;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;
import net.tardis.mod.Tardis;
import net.tardis.mod.client.guis.elements.ButtonMonitor;
import net.tardis.mod.common.protocols.ITardisProtocol;
import net.tardis.mod.common.protocols.TardisProtocol;
import net.tardis.mod.common.tileentity.TileEntityTardis;
import net.tardis.mod.network.NetworkHandler;
import net.tardis.mod.network.packets.MessageProtocol;

public class GuiMonitor extends GuiScreen{
	
	public static final ResourceLocation TEXTURE = new ResourceLocation(Tardis.MODID, "textures/gui/monitor.png");
	private TileEntityTardis tardis;

	private static int guiWidth = 242;
	private static int guiHeigt = 132;
	
	public GuiMonitor(TileEntityTardis tardis) {
		this.tardis = tardis;
	}
	
	public GuiMonitor(BlockPos pos) {
		TileEntity te = Minecraft.getMinecraft().world.getTileEntity(pos);
		if(te instanceof TileEntityTardis) {
			this.tardis = (TileEntityTardis)te;
		}
	}

	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		this.drawDefaultBackground();
		Minecraft.getMinecraft().getTextureManager().bindTexture(TEXTURE);
		this.drawTexturedModalRect(width / 2 - 242 / 2, height / 2 - 132 / 2, 0, 0, 242, 132);
		this.drawCenteredString(Minecraft.getMinecraft().fontRenderer, "Protocols", width / 2, height / 2 - 55, 0xFFFFFF);
		super.drawScreen(mouseX, mouseY, partialTicks);
	}

	@Override
	protected void actionPerformed(GuiButton button) throws IOException {
		super.actionPerformed(button);
		if(button instanceof ButtonMonitor) {
			Minecraft.getMinecraft().displayGuiScreen(null);
			((ButtonMonitor)button).doAction();
		}
	}
	
	public static void drawMonitorBackground(GuiScreen screen, int width, int height) {
		Minecraft.getMinecraft().getTextureManager().bindTexture(TEXTURE);
		screen.drawTexturedModalRect(width / 2 - 242 / 2, height / 2 - 132 / 2, 0, 0, 242, 132);
	}

	@Override
	public void initGui() {
		super.initGui();
		int index = 0;
		for(Entry<Integer, ITardisProtocol> entry : TardisProtocol.protocols.entrySet()) {
			ButtonMonitor text = new ButtonMonitor(index, width / 2 - 105, (height / 2 + 50) - index * Minecraft.getMinecraft().fontRenderer.FONT_HEIGHT, "> " + new TextComponentTranslation(entry.getValue().getNameKey()).getFormattedText());
			text.addAction(() -> {
				NetworkHandler.NETWORK.sendToServer(new MessageProtocol(tardis.getPos(), entry.getKey()));
				entry.getValue().onActivated(Minecraft.getMinecraft().world, tardis);
			});
			this.addButton(text);
			++index;
		}
	}

	@Override
	public boolean doesGuiPauseGame() {
		return false;
	}

	public static int getGuiWidth() {
		return guiWidth;
	}

	public static int getGuiHeigt() {
		return guiHeigt;
	}
}