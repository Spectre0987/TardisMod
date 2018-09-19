package net.tardis.mod.common.tileentity;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.common.util.Constants;
import net.tardis.mod.common.recipes.RepairRecipes;

public class TileEntityComponentRepair extends TileEntity implements IInventory, ITickable{
	
	private NonNullList<ItemStack> inv = NonNullList.<ItemStack>withSize(3, ItemStack.EMPTY);
	public int progress = 200;
	
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
	public void closeInventory(EntityPlayer player) {}

	@Override
	public boolean isItemValidForSlot(int index, ItemStack stack) {
		return false;
	}

	@Override
	public int getField(int id) {
		return this.progress;
	}

	@Override
	public void setField(int id, int value) {
		this.progress = value;
	}

	@Override
	public int getFieldCount() {
		return 1;
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

	@Override
	public void update() {
		ItemStack comp = this.getStackInSlot(0);
		if(!world.isRemote) {
			if(!comp.isEmpty() && comp.getItemDamage() > 0 && RepairRecipes.getRepairItem(comp.getItem()) != null) {
				if(RepairRecipes.getRepairItem(this.getStackInSlot(0).getItem()) == this.getStackInSlot(1).getItem()) {
					--this.progress;
					if(this.progress <= 0) {
						this.complete();
					}
					for(EntityPlayerMP player : world.getEntitiesWithinAABB(EntityPlayerMP.class, Block.FULL_BLOCK_AABB.offset(this.getPos()).grow(16))) {
						player.connection.sendPacket(this.getUpdatePacket());
					}
				}
				else progress = 200;
			}
			else progress = 200;
		}
		
	}
	
	public void complete() {
		this.progress = 200;
		Item item = this.getStackInSlot(0).getItem();
		ItemStack ing = this.getStackInSlot(1).copy();
		ing.shrink(1);
		this.setInventorySlotContents(0, ItemStack.EMPTY);
		this.setInventorySlotContents(1, ing);
		this.setInventorySlotContents(2, new ItemStack(item));
	}

	@Override
	public SPacketUpdateTileEntity getUpdatePacket() {
		return new SPacketUpdateTileEntity(this.getPos(), -1, this.getUpdateTag());
	}

	@Override
	public NBTTagCompound getUpdateTag() {
		NBTTagCompound tag = new NBTTagCompound();
		tag.setInteger("pro", progress);
		return tag;
	}

	@Override
	public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt) {
		this.progress = pkt.getNbtCompound().getInteger("pro");
		super.onDataPacket(net, pkt);
	}
}
