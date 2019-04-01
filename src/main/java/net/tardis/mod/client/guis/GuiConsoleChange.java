package net.tardis.mod.client.guis;

import java.awt.Color;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.tardis.mod.client.guis.elements.ButtonMonitor;
import net.tardis.mod.common.tileentity.TileEntityTardis;
import net.tardis.mod.network.NetworkHandler;
import net.tardis.mod.network.packets.MessageConsoleChange;

public class GuiConsoleChange extends GuiScreen {

	private TileEntityTardis tardis;
	private GuiButton NEXT;
	private GuiButton PREV;
	private GuiButton SUBMIT;
	private int index = 0;
	public static HashMap<IBlockState, TileEntityTardis> TARDISes = new HashMap<>();
	
	public GuiConsoleChange(TileEntityTardis tardis) {
		this.tardis = tardis;
	}

	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		Minecraft.getMinecraft().getTextureManager().bindTexture(GuiToggleHum.TEXTURE);
		this.drawTexturedModalRect(width / 2 - 242 / 2, height / 2 - 132 / 2, 0, 0, 242, 132);
		super.drawScreen(mouseX, mouseY, partialTicks);
		this.drawCenteredString(Minecraft.getMinecraft().fontRenderer, "Console Menu", width / 2, height / 2 - 55, Color.WHITE.getRGB());
		TileEntityTardis tardis = getTardis(index);
		if(tardis != null) {
			GlStateManager.pushMatrix();
			GlStateManager.color(1F, 1F, 1F);
			GlStateManager.translate(width / 2 + 75, height / 2 + 30, 200);
			GlStateManager.scale(25, 25, 25);
			GlStateManager.rotate(180, 0, 0, 1);
			GlStateManager.rotate(22.5F, 1, 0, 0);
			GlStateManager.rotate(45, 0, 1, 0);
			TileEntityRendererDispatcher.instance.getRenderer(tardis).render(tardis, 0, 0, 0, partialTicks, 0, 1);
			GlStateManager.popMatrix();
		}
	}

	private TileEntityTardis getTardis(int index) {
		int i = 0;
		for(TileEntityTardis tardis : TARDISes.values()) {
			if(i == index)
				return tardis;
			++i;
		}
		return null;
	}
	
	@Override
	protected void actionPerformed(GuiButton button) throws IOException {
		super.actionPerformed(button);
		if(button == NEXT) {
			if(index + 1 > TARDISes.size() - 1)
				index = 0;
			else ++index;
		}
		else if(button == PREV) {
			if(index - 1 < 0)
				index = TARDISes.size() - 1;
			else --index;
		}
		else if(button == SUBMIT) {
			int stateID = 0;
			int in = 0;
			Iterator<IBlockState> it = TARDISes.keySet().iterator();
			while(it.hasNext()) {
				IBlockState state = it.next();
				if(in == index) {
					stateID = Block.getStateId(state);
				}
				++in;
			}
			NetworkHandler.NETWORK.sendToServer(new MessageConsoleChange(tardis.getPos(), stateID));
			Minecraft.getMinecraft().displayGuiScreen(null);
		}
	}

	@Override
	public void initGui() {
		super.initGui();
		this.buttonList.clear();
		int gw = width / 2 - 110, gh = height / 2 + 45;
		this.addButton(NEXT = this.createButton(0, gw, gh, "> Next Console"));
		this.addButton(SUBMIT = this.createButton(1, gw, gh, "> Select Console"));
		this.addButton(PREV = this.createButton(2, gw, gh, "> Previous Console"));
	}
	
	public ButtonMonitor createButton(int id, int x, int y, String name) {
		return new ButtonMonitor(id, x, y - (int)((Minecraft.getMinecraft().fontRenderer.FONT_HEIGHT) * id), name);
	}

	@Override
	public boolean doesGuiPauseGame() {
		return false;
	}
}
