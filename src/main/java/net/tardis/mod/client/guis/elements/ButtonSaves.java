package net.tardis.mod.client.guis.elements;

import net.minecraft.client.Minecraft;
import net.minecraft.world.storage.WorldSummary;

public class ButtonSaves extends ButtonText{

	private WorldSummary world;
	
	public ButtonSaves(int buttonId, int x, int y, WorldSummary file) {
		super(buttonId, x, y, Minecraft.getMinecraft().fontRenderer, file.getFileName());
		this.world = file;
	}

	public WorldSummary getFile() {
		return world;
	}
	
}
