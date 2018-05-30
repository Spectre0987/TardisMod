package net.tardis.mod.common.tileentity;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.fml.common.network.NetworkRegistry.TargetPoint;
import net.tardis.mod.Tardis;
import net.tardis.mod.common.blocks.BlockTardisTop;
import net.tardis.mod.common.blocks.TBlocks;
import net.tardis.mod.common.dimensions.TDimensions;
import net.tardis.mod.common.sounds.TSounds;
import net.tardis.mod.common.strings.TStrings;
import net.tardis.mod.packets.MessageDoorOpen;
import net.tardis.mod.util.helpers.TardisHelper;

public class TileEntityDoor extends TileEntity implements ITickable, IInventory{
	
	public BlockPos consolePos = BlockPos.ORIGIN;
	public boolean isLocked = true;
	public int ticks = 0;
	public int lockCooldown = 0;
	private int updateTicks = 0;
	public int fadeTicks = 0;
	
	public TileEntityDoor() {}
	
	@Override
	public void readFromNBT(NBTTagCompound tag) {
		consolePos = BlockPos.fromLong(tag.getLong("tPos"));
		isLocked = tag.getBoolean("locked");
		super.readFromNBT(tag);
	}
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound tag) {
		tag.setLong("tPos", consolePos.toLong());
		tag.setBoolean("locked", isLocked);
		return super.writeToNBT(tag);
	}
	
	public void toggleLocked(EntityPlayer player) {
		if (TardisHelper.hasValidKey(player, consolePos) && lockCooldown == 0) {
			lockCooldown = 20;
			isLocked = isLocked ? false : true;
			this.markDirty();
			if(!world.isRemote) {
				Tardis.NETWORK.sendToDimension(new MessageDoorOpen(this.getPos(),this.isLocked()), world.provider.getDimension());
				if(isLocked)
					world.playSound(null, getPos(), TSounds.door_closed, SoundCategory.BLOCKS, 0.5F, 1F);
				else world.playSound(null, getPos(), TSounds.door_open, SoundCategory.BLOCKS, 0.5F, 1F);
			}
			player.sendStatusMessage(new TextComponentTranslation(TStrings.TARDIS_LOCKED + isLocked),true);
		}
	}
	
	public void toggleLockedNoKey(EntityPlayer player) {
		if (lockCooldown == 0) {
			lockCooldown = 20;
			isLocked = isLocked ? false : true;
			this.markDirty();
			player.sendStatusMessage(new TextComponentTranslation(TStrings.TARDIS_LOCKED + isLocked),true);
		}
	}
	
	public void setLocked(boolean b) {
		this.isLocked = b;
		this.markDirty();
	}
	
	public EnumFacing getFacing() {
		IBlockState state=world.getBlockState(this.getPos());
		if(state.getBlock() == TBlocks.tardis_top) {
			return state.getValue(BlockTardisTop.FACING);
		}
		return EnumFacing.NORTH;
	}
	
	@Override
	public void update() {
		if(!world.isRemote) {
			ticks++;
			if (lockCooldown > 0)
				--lockCooldown;
			++this.updateTicks;
			if(this.updateTicks>20) {
				Tardis.NETWORK.sendToAllAround(new MessageDoorOpen(this.getPos(),this.isLocked()),new TargetPoint(this.world.provider.getDimension(),this.getPos().getX(),this.getPos().getY(),this.getPos().getZ(),64D));
				this.updateTicks = 0;
			}
		}
		if(fadeTicks > 0)
			--fadeTicks;
	}
	public boolean isLocked() {
		return this.isLocked;
	}
	
	public class NBT{
		public static final String LOCKED = "locked";
	}

	@Override
	public void onLoad() {
		super.onLoad();
		if(!world.isRemote)
			Tardis.NETWORK.sendToDimension(new MessageDoorOpen(this.getPos(),this.isLocked()),world.provider.getDimension());
	}

	@Override
	public AxisAlignedBB getRenderBoundingBox() {
		return new AxisAlignedBB(getPos().getX(),getPos().getY()-1,getPos().getZ(),getPos().getX()+1,getPos().getY()+1.5,getPos().getZ()+1);
	}

	public void fadeIn() {
		this.fadeTicks = 60;
	}
	
	public void setConsolePos(BlockPos pos) {
		this.consolePos = pos.toImmutable();
		this.markDirty();
	}
	
	public BlockPos getConsolePos() {
		return this.consolePos;
	}
	
	public IInventory getLinkedInv() {
		if(!world.isRemote && this.getConsolePos() != null) {
			WorldServer ws = DimensionManager.getWorld(TDimensions.id);
			if(ws != null) {
				TileEntity te = ws.getTileEntity(getConsolePos());
				if(te !=null && te instanceof TileEntityTardis)
					return ((TileEntityTardis)te);
			}
		}
		return DummyTardis.INSTANCE;
	}

	@Override
	public String getName() {
		return this.getLinkedInv().getName();
	}

	@Override
	public boolean hasCustomName() {
		return false;
	}

	@Override
	public int getSizeInventory() {
		return this.getLinkedInv().getSizeInventory();
	}

	@Override
	public boolean isEmpty() {
		return this.getLinkedInv().isEmpty();
	}

	@Override
	public ItemStack getStackInSlot(int index) {
		return this.getLinkedInv().getStackInSlot(index);
	}

	@Override
	public ItemStack decrStackSize(int index, int count) {
		return this.getLinkedInv().decrStackSize(index, count);
	}

	@Override
	public ItemStack removeStackFromSlot(int index) {
		return this.getLinkedInv().removeStackFromSlot(index);
	}

	@Override
	public void setInventorySlotContents(int index, ItemStack stack) {
		this.getLinkedInv().setInventorySlotContents(index, stack);
	}

	@Override
	public int getInventoryStackLimit() {
		return this.getLinkedInv().getInventoryStackLimit();
	}

	@Override
	public boolean isUsableByPlayer(EntityPlayer player) {
		return false;
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
		this.getLinkedInv().clear();
	}
}