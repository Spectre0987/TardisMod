package net.tardis.mod.client.guis;

import java.io.IOException;
import java.text.DecimalFormat;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.text.TextComponentTranslation;
import net.tardis.mod.client.guis.elements.ButtonMonitor;
import net.tardis.mod.common.systems.TardisSystems;
import net.tardis.mod.common.systems.TardisSystems.BaseSystem;
import net.tardis.mod.common.tileentity.TileEntityTardis;
import net.tardis.mod.network.NetworkHandler;
import net.tardis.mod.network.packets.MessageDamageSystem;

public class GuiSystem extends GuiScreen{

	private TileEntityTardis tardis;
	private DecimalFormat FORMAT = new DecimalFormat("#");
	
	public GuiSystem(TileEntityTardis tardis) {
		this.tardis = tardis;
	}
	
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		this.drawDefaultBackground();
		GuiMonitor.drawMonitorBackground(this, width, height);
		this.drawCenteredString(Minecraft.getMinecraft().fontRenderer, "Subsystem Readout", width / 2, height / 2 - 55, 0xFFFFFF);
		super.drawScreen(mouseX, mouseY, partialTicks);
	}

	@Override
	protected void actionPerformed(GuiButton button) throws IOException {
		super.actionPerformed(button);
		if(button instanceof ButtonMonitor)
			((ButtonMonitor)button).doAction();
	}

	@Override
	public void initGui() {
		super.initGui();
		int id = 0;
		for(BaseSystem system : tardis.systems) {
			this.addButton(addButton(id, "> " + new TextComponentTranslation(system.getNameKey()).getFormattedText() + " " + FORMAT.format(system.getHealth() * 100) + "%")).addAction(() -> {
				NetworkHandler.NETWORK.sendToServer(new MessageDamageSystem(tardis.getPos(), TardisSystems.getIdBySystem(system)));
				Minecraft.getMinecraft().displayGuiScreen(null);
			});
			++id;
		}
	}
	
	public ButtonMonitor addButton(int id, String name) {
		return new ButtonMonitor(id, width / 2 - 110, (height / 2 + 50) - Minecraft.getMinecraft().fontRenderer.FONT_HEIGHT * id, name);
	}
	
}