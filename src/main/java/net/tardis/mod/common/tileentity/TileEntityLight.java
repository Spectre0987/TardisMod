package net.tardis.mod.common.tileentity;

import net.minecraft.tileentity.TileEntity;

public class TileEntityLight extends TileEntity{

	private int light = 15;
	
	public int getLightValue() {
		return light;
	}
	
	public void setLight(int l) {
		this.light = l;
	}

}
