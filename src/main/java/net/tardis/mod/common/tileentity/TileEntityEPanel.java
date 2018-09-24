package net.tardis.mod.common.tileentity;

import javax.annotation.Nullable;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.tardis.mod.common.blocks.TBlocks;
import net.tardis.mod.util.helpers.TardisHelper;

public class TileEntityEPanel extends TileEntity implements ITickable, IInventory {
	
	private int stateID = Block.getStateId(TBlocks.electric_panel.getDefaultState());
	
	public TileEntityEPanel() {}
	
	@Override
	public void update() {
		if(!world.isRemote && world.getWorldTime() % 120 == 0) {
			for(EntityPlayerMP player : world.getEntitiesWithinAABB(EntityPlayerMP.class, Block.FULL_BLOCK_AABB.offset(getPos()).grow(64))) {
				player.connection.sendPacket(this.getUpdatePacket());
			}
		}
	}
	
	@Override
	public void readFromNBT(NBTTagCompound compound) {
		super.readFromNBT(compound);
		this.stateID = compound.getInteger("stateID");
	}
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		compound.setInteger("stateID", stateID);
		return super.writeToNBT(compound);
	}
	
	@Nullable
	public BlockPos getConsolePos() {
		return TardisHelper.getTardisForPosition(this.getPos());
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

	@Override
	public SPacketUpdateTileEntity getUpdatePacket() {
		return new SPacketUpdateTileEntity(this.getPos(), -1, this.getUpdateTag());
	}

	@Override
	public NBTTagCompound getUpdateTag() {
		NBTTagCompound tag = new NBTTagCompound();
		tag.setInteger("id", this.stateID);
		return tag;
	}

	@Override
	public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt) {
		this.stateID = pkt.getNbtCompound().getInteger("id");
		super.onDataPacket(net, pkt);
	}
	
	public int getID() {
		return this.stateID;
	}

	public void setID(IBlockState defaultState) {
		this.stateID = Block.getStateId(defaultState);
		world.checkLight(this.getPos());
		world.scheduleUpdate(this.getPos(), TBlocks.electric_panel, 1);
		this.markDirty();
	}
}
