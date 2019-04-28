package net.tardis.mod.client.guis;

import java.io.IOException;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.item.ItemStack;
import net.tardis.mod.client.guis.elements.ButtonRecipe;
import net.tardis.mod.common.tileentity.TileEntityEgg;
import net.tardis.mod.common.tileentity.TileEntityTardis;
import net.tardis.mod.network.NetworkHandler;
import net.tardis.mod.network.packets.MessageARSSpawn;

public class GuiARS extends GuiScreen {
	
	public TileEntityTardis tardis;
	
	public GuiARS(TileEntityTardis tardis) {
		this.tardis = tardis;
	}

	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		GuiMonitor.drawMonitorBackground(this, width, height);
		super.drawScreen(mouseX, mouseY, partialTicks);
	}

	@Override
	public void initGui() {
		super.initGui();
		this.buttonList.clear();
		final int guiFirstX = width/2 - GuiMonitor.getGuiWidth()/2 + 13;
		final int guiFirstY = height/2 - GuiMonitor.getGuiHeigt()/2 + 10;
		final int maxItem = 12;
		int id = 0;
		int line = 0;
		int collum = 0;
		for(ItemStack stack : TileEntityEgg.ITEMS) {
			this.addButton(new ButtonRecipe(id, guiFirstX + collum * 18  , guiFirstY + line  * 18 , stack)).addAction(() -> {
				NetworkHandler.NETWORK.sendToServer(new MessageARSSpawn(tardis.getPos(), stack));
				Minecraft.getMinecraft().displayGuiScreen(null);
			});
			++id;
			collum++;
			if (collum >= maxItem){
				collum = 0;
				line++;
			}
		}
	}

	@Override
	protected void actionPerformed(GuiButton button) throws IOException {
		super.actionPerformed(button);
		if(button instanceof ButtonRecipe) {
			((ButtonRecipe)button).doAction();
		}
	}

	@Override
	public boolean doesGuiPauseGame() {
		return false;
	}
}
