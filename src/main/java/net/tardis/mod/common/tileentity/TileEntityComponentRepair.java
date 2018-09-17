package net.tardis.mod.common.tileentity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.common.util.Constants;

public class TileEntityComponentRepair extends TileEntity implements IInventory{
	
	private NonNullList<ItemStack> inv = NonNullList.<ItemStack>withSize(3, ItemStack.EMPTY);
	
	public TileEntityComponentRepair() {}

	@Override
	public String getName() {
		return "Steady State Micro Welding";
	}

	@Override
	public boolean hasCustomName() {
		return false;
	}

	@Override
	public ITextComponent getDisplayName() {
		return new TextComponentString(this.getName());
	}

	@Override
	public int getSizeInventory() {
		return inv.size();
	}

	@Override
	public boolean isEmpty() {
		return inv.isEmpty();
	}

	@Override
	public ItemStack getStackInSlot(int index) {
		return index >= inv.size() ? ItemStack.EMPTY : inv.get(index);
	}

	@Override
	public ItemStack decrStackSize(int index, int count) {
		return this.getStackInSlot(index).splitStack(count);
	}

	@Override
	public ItemStack removeStackFromSlot(int index) {
		ItemStack stack = this.getStackInSlot(index);
		if(index < inv.size()) {
			inv.set(index, ItemStack.EMPTY);
		}
		return stack;
	}

	@Override
	public void setInventorySlotContents(int index, ItemStack stack) {
		if(index < inv.size()) {
			inv.set(index, stack);
		}
	}

	@Override
	public int getInventoryStackLimit() {
		return 64;
	}

	@Override
	public boolean isUsableByPlayer(EntityPlayer player) {
		return true;
	}

	@Override
	public void openInventory(EntityPlayer player) {}

	@Override
	public void closeInventory(EntityPlayer player) {
		InventoryHelper.dropInventoryItems(player.world, player, this);
	}

	@Override
	public boolean isItemValidForSlot(int index, ItemStack stack) {
		return false;
	}

	@Override
	public int getField(int id) {
		return 0;
	}

	@Override
	public void setField(int id, int value) {}

	@Override
	public int getFieldCount() {
		return 0;
	}

	@Override
	public void clear() {
		inv.clear();
	}

	@Override
	public void readFromNBT(NBTTagCompound compound) {
		super.readFromNBT(compound);
		NBTTagList list = compound.getTagList("inv", Constants.NBT.TAG_COMPOUND);
		int id = 0;
		for(NBTBase base : list) {
			inv.set(id, new ItemStack((NBTTagCompound)base));
			++id;
		}
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		NBTTagList list = new NBTTagList();
		for(ItemStack stack : inv) {
			list.appendTag(stack.writeToNBT(new NBTTagCompound()));
		}
		compound.setTag("inv", list);
		return super.writeToNBT(compound);
	}
}
