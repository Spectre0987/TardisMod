package net.tardis.mod.common.tileentity.decoration;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.common.util.Constants.NBT;
import net.tardis.mod.common.blocks.TBlocks;

public class TileEntityRoundelChest extends TileEntity implements IInventory {
	
	private int blockId = Block.getStateId(TBlocks.hellbent_roundel01.getDefaultState());
	private NonNullList<ItemStack> inv = NonNullList.<ItemStack>withSize(27, ItemStack.EMPTY);
	
	public TileEntityRoundelChest() {
		
	}
	
	@Override
	public void readFromNBT(NBTTagCompound compound) {
		super.readFromNBT(compound);
		int i = 0;
		NBTTagList list = compound.getTagList("inv", NBT.TAG_COMPOUND);
		for(NBTBase tag : list) {
			inv.set(i, new ItemStack((NBTTagCompound)tag));
			++i;
		}
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		NBTTagList list = new NBTTagList();
		for(ItemStack stack : inv) {
			list.appendTag(stack.writeToNBT(new NBTTagCompound()));
		}
		compound.setTag("inv", list);
		return compound;
	}

	public void setBlockId(int i) {
		this.blockId = i;
		this.markDirty();
	}

	public int getBlockId() {
		return this.blockId;
	}
	
	@Override
	public String getName() {
		return "TARDIS";
	}

	@Override
	public boolean hasCustomName() {
		return false;
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
		if(index < inv.size()) {
			return inv.get(index);
		}
		return ItemStack.EMPTY;
	}

	@Override
	public ItemStack decrStackSize(int index, int count) {
		this.markDirty();
		return this.getStackInSlot(index).splitStack(count);
	}

	@Override
	public ItemStack removeStackFromSlot(int index) {
		ItemStack stack = this.getStackInSlot(index);
		this.setInventorySlotContents(index, ItemStack.EMPTY);
		return stack;
	}

	@Override
	public void setInventorySlotContents(int index, ItemStack stack) {
		if(index < this.inv.size()) {
			inv.set(index, stack);
			this.markDirty();
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
	public void closeInventory(EntityPlayer player) {}

	@Override
	public boolean isItemValidForSlot(int index, ItemStack stack) {
		return true;
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
		this.markDirty();
	}

	@Override
	public ITextComponent getDisplayName() {
		return new TextComponentString("TARDIS");
	}

}
