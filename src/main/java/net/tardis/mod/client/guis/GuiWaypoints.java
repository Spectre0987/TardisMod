package net.tardis.mod.client.guis;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.math.BlockPos;
import net.tardis.mod.client.guis.elements.ButtonText;
import net.tardis.mod.common.tileentity.TileEntityTardis;
import net.tardis.mod.network.NetworkHandler;
import net.tardis.mod.network.packets.MessageWaypointLoad;

import java.io.IOException;

public class GuiWaypoints extends GuiScreen {

	public ButtonText save;
	public ButtonText load;
	public ButtonText delete;

	public BlockPos pos;
	int max = 3;

	public GuiWaypoints(BlockPos pos) {
		this.pos = pos;
	}

	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		this.drawDefaultBackground();
		super.drawScreen(mouseX, mouseY, partialTicks);
	}

	@Override
	protected void actionPerformed(GuiButton button) throws IOException {
		super.actionPerformed(button);
		TileEntityTardis tardis = (TileEntityTardis) Minecraft.getMinecraft().world.getTileEntity(this.pos);
		if (tardis == null) return;
		if (button == save) {
			Minecraft.getMinecraft().displayGuiScreen(new GuiWaypointSave(pos));
			return;
		} else if (button == load) {
			NetworkHandler.NETWORK.sendToServer(new MessageWaypointLoad(this.pos, false));
			Minecraft.getMinecraft().displayGuiScreen(null);
		} else if (button == delete) {
			NetworkHandler.NETWORK.sendToServer(new MessageWaypointLoad(this.pos, true));
			Minecraft.getMinecraft().displayGuiScreen(null);
		}
	}

	@Override
	public void initGui() {
		this.addButton(save = addButton(0, "Save a new Waypoint"));
		this.addButton(load = addButton(1, "Load current Waypoint"));
		this.addButton(delete = addButton(2, "Delete Current Waypoint"));
	}

	public ButtonText addButton(int id, String text) {
		return new ButtonText(id, width / 2, (int) (height / 2.5 + (id * Minecraft.getMinecraft().fontRenderer.FONT_HEIGHT * 1.25)), Minecraft.getMinecraft().fontRenderer, text);
	}

	@Override
	public boolean doesGuiPauseGame() {
		return false;
	}

}
