package net.tardis.mod.client.guis;

import java.io.IOException;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.tardis.mod.client.guis.elements.ButtonMonitor;
import net.tardis.mod.common.ars.ConsoleRoom;
import net.tardis.mod.common.tileentity.TileEntityTardis;
import net.tardis.mod.network.NetworkHandler;
import net.tardis.mod.network.packets.MessageChangeInterior;

public class GuiChangeInterior extends GuiScreen{
	
	private TileEntityTardis tardis;
	private int index = 0;
	
	public GuiChangeInterior(TileEntityTardis tardis) {
		this.tardis = tardis;
	}

	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		this.drawDefaultBackground();
		GuiMonitor.drawMonitorBackground(this, width, height);
		super.drawScreen(mouseX, mouseY, partialTicks);
		Minecraft.getMinecraft().getTextureManager().bindTexture(ConsoleRoom.CONSOLE_ROOMS.get(index).getPreview());
		double ratio = 1.77;
		int pX = width / 2 - 100, pY = height / 2 - 50, pHeight = 75, pWidth = (int) Math.floor(pHeight * ratio);
		pX += pWidth / 4;
		BufferBuilder bb = Tessellator.getInstance().getBuffer();
		bb.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_TEX);
		bb.pos(pX, pY, 200).tex(0, 0).endVertex();
		bb.pos(pX, pY + pHeight, 200).tex(0, 1).endVertex();
		bb.pos(pX + pWidth, pY + pHeight, 200).tex(1, 1).endVertex();
		bb.pos(pX + pWidth, pY, 200).tex(1, 0).endVertex();
		Tessellator.getInstance().draw();
	}

	@Override
	protected void actionPerformed(GuiButton button) throws IOException {
		super.actionPerformed(button);
		if(button instanceof ButtonMonitor) {
			((ButtonMonitor)button).doAction();
		}
	}

	@Override
	public void initGui() {
		super.initGui();
		this.buttonList.clear();
		this.addButton(this.addButton(2, "> Next Room")).addAction(() ->{
			if(index + 1 > ConsoleRoom.CONSOLE_ROOMS.size() - 1)
				index = 0;
			else ++index;
		});
		this.addButton(this.addButton(1, "> Select Room")).addAction(() -> {
			NetworkHandler.NETWORK.sendToServer(new MessageChangeInterior(index, tardis.getPos()));
			Minecraft.getMinecraft().displayGuiScreen(null);
		});
		this.addButton(this.addButton(0, "> Prev Room")).addAction(() -> {
			if(index - 1 < 0)
				index = ConsoleRoom.CONSOLE_ROOMS.size() - 1;
			else --index;
		});
	}
	
	public ButtonMonitor addButton(int id, String name) {
		return new ButtonMonitor(id, width / 2 - 110, (height / 2 + 50) - Minecraft.getMinecraft().fontRenderer.FONT_HEIGHT * id, name);
	}
}