package net.tardis.mod.client.guis;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.util.math.BlockPos;
import net.tardis.mod.common.tileentity.TileEntityTardis;
import net.tardis.mod.network.NetworkHandler;
import net.tardis.mod.network.packets.MessageWaypointSave;
import net.tardis.mod.util.SpaceTimeCoord;

import java.io.IOException;

public class GuiWaypointSave extends GuiScreen {

	public BlockPos tardisPos = BlockPos.ORIGIN;

	public GuiTextField field;

	public GuiWaypointSave(BlockPos pos) {
		this.tardisPos = pos;
	}

	@Override
	protected void keyTyped(char typedChar, int keyCode) throws IOException {
		field.textboxKeyTyped(typedChar, keyCode);
		if (keyCode == 28) {
			Minecraft.getMinecraft().displayGuiScreen(null);
			TileEntityTardis tardis = (TileEntityTardis) Minecraft.getMinecraft().world.getTileEntity(tardisPos);
			if (tardis == null) return;
			SpaceTimeCoord coord = new SpaceTimeCoord(tardis.getLocation(), tardis.dimension, field.getText());
			NetworkHandler.NETWORK.sendToServer(new MessageWaypointSave(tardisPos, coord));
		}
		super.keyTyped(typedChar, keyCode);
	}

	@Override
	public void initGui() {
		int x = width / 2 - width / 4, y = height / 2 - Minecraft.getMinecraft().fontRenderer.FONT_HEIGHT;
		field = new GuiTextField(0, Minecraft.getMinecraft().fontRenderer, x, y, width / 2, Minecraft.getMinecraft().fontRenderer.FONT_HEIGHT * 2);
		this.setFocused(true);
	}

	@Override
	protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
		super.mouseClicked(mouseX, mouseY, mouseButton);
		if (mouseX > field.x && mouseX < field.x + field.width) {
			if (mouseY > field.y && mouseY < field.y + field.height) {
				field.setFocused(true);
			}
		}
	}

	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		this.drawDefaultBackground();
		super.drawScreen(mouseX, mouseY, partialTicks);
		field.drawTextBox();
		field.updateCursorCounter();
	}

}
