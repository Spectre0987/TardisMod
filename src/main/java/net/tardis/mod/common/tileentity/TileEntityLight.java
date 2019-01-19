package net.tardis.mod.common.tileentity;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public class TileEntityLight extends TileEntity {

	private int light = 15;

	public int getLightValue() {
		return light;
	}

	public void setLight(int l) {
		this.light = l;
	}

	@Override
	public void readFromNBT(NBTTagCompound compound) {
		super.readFromNBT(compound);
		light = compound.getInteger("time");
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		compound.setInteger("light", light);
		return super.writeToNBT(compound);
	}
}
