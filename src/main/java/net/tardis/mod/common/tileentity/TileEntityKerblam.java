package net.tardis.mod.common.tileentity;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public class TileEntityKerblam extends TileEntity {
	
	private boolean hasBeenOpened = false;
	private ItemStack stack = ItemStack.EMPTY;
	
	public void setHasBeenOpened(boolean hasBeenOpen) {
		this.hasBeenOpened = hasBeenOpen;
	}
	
	public boolean hasBeenOpened() {
		return hasBeenOpened;
	}
	
	public ItemStack getStack() {
		return stack;
	}
	
	public void setStack(ItemStack stack) {
		this.stack = stack;
	}
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		compound.setBoolean("hasBeenOpened", hasBeenOpened);
		return super.writeToNBT(compound);
	}
	
	@Override
	public void readFromNBT(NBTTagCompound compound) {
		setHasBeenOpened(compound.getBoolean("hasBeenOpened"));
		super.readFromNBT(compound);
	}
}
