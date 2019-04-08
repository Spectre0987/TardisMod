package net.tardis.mod.client.guis;

import net.minecraft.client.gui.GuiScreen;
import net.tardis.mod.common.tileentity.TileEntityTardis;

public class GuiChangeInterior extends GuiScreen{
	
	private TileEntityTardis tardis;
	
	public GuiChangeInterior(TileEntityTardis tardis) {
		this.tardis = tardis;
	}
}