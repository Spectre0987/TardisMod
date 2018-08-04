package net.tardis.mod.common.tileentity;

import java.util.UUID;

import javax.annotation.Nullable;

import ic2.api.energy.tile.IEnergySink;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.tardis.mod.util.helpers.TardisHelper;

public class TileEntityEPanel extends TileEntity implements ITickable, IInventory {
	
	private UUID owner;
	
	public TileEntityEPanel() {}
	
	@Override
	public void update() {
		if(!this.getConsolePos().equals(BlockPos.ORIGIN)) {
			TileEntityTardis tardis = this.getConsoleP();
			if(tardis != null) {
				for(EnumFacing f : EnumFacing.values()) {
					TileEntity te = world.getTileEntity(this.getPos().offset(f));
					if(te != null && te instanceof IEnergySink) {
						System.out.println("Exsists");
						((IEnergySink)te).injectEnergy(f, 10, 32.0);
					}
				}
			}
		}
	}
	
	@Override
	public void readFromNBT(NBTTagCompound compound) {
		super.readFromNBT(compound);
		this.owner = UUID.fromString(compound.getString("owner"));
	}
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		if(owner != null)compound.setString("owner", owner.toString());
		return super.writeToNBT(compound);
	}

	public void setOwner(UUID id) {
		this.owner = id;
		this.markDirty();
	}
	
	@Nullable
	public BlockPos getConsolePos() {
		if(owner != null && TardisHelper.hasTardis(owner)) {
			return TardisHelper.getTardis(owner);
		}
		return BlockPos.ORIGIN;
	}
	
	public IInventory getConsole() {
		IInventory inv = (TileEntityTardis)this.world.getTileEntity(getConsolePos());
		return inv != null ? inv : DummyTardis.INSTANCE;
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
		return getConsole().getSizeInventory();
	}

	@Override
	public boolean isEmpty() {
		return getConsole().isEmpty();
	}

	@Override
	public ItemStack getStackInSlot(int index) {
		return getConsole().getStackInSlot(index);
	}

	@Override
	public ItemStack decrStackSize(int index, int count) {
		return getConsole().decrStackSize(index, count);
	}

	@Override
	public ItemStack removeStackFromSlot(int index) {
		return getConsole().removeStackFromSlot(index);
	}

	@Override
	public void setInventorySlotContents(int index, ItemStack stack) {
		getConsole().setInventorySlotContents(index, stack);
	}

	@Override
	public int getInventoryStackLimit() {
		return getConsole().getInventoryStackLimit();
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
		getConsole().clear();
	}

	public TileEntityTardis getConsoleP() {
		BlockPos pos = this.getConsolePos();
		if(!pos.equals(BlockPos.ORIGIN)) {
			TileEntityTardis tardis = (TileEntityTardis)world.getTileEntity(getConsolePos());
			return tardis;
		}
		return null;
	}
}
