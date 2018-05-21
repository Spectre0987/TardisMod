package net.tardis.mod.common.tileentity;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraftforge.fml.common.network.NetworkRegistry.TargetPoint;
import net.tardis.mod.Tardis;
import net.tardis.mod.common.blocks.BlockTardisTop;
import net.tardis.mod.common.blocks.TBlocks;
import net.tardis.mod.packets.MessageDoorOpen;
import net.tardis.mod.util.helpers.TardisHelper;

public class TileEntityDoor extends TileEntity implements ITickable {
	
	public BlockPos consolePos=BlockPos.ORIGIN;
	public boolean isLocked = true;
	public int ticks = 0;
	public int lockCooldown = 0;
	private int updateTicks=0;
	public float alpha=1F;
	
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
			if(!world.isRemote)
				Tardis.NETWORK.sendToDimension(new MessageDoorOpen(this.getPos(),this.isLocked()), world.provider.getDimension());
			player.sendMessage(new TextComponentTranslation("tardis.locked." + isLocked));
		}
	}
	
	public void toggleLockedNoKey(EntityPlayer player) {
		if (lockCooldown == 0) {
			lockCooldown = 20;
			isLocked = isLocked ? false : true;
			this.markDirty();
			player.sendMessage(new TextComponentTranslation("tardis.locked." + isLocked));
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
		ticks++;
		if (lockCooldown > 0)
			--lockCooldown;
		++this.updateTicks;
		if(this.updateTicks>20) {
			Tardis.NETWORK.sendToAllAround(new MessageDoorOpen(this.getPos(),this.isLocked()),new TargetPoint(this.world.provider.getDimension(),this.getPos().getX(),this.getPos().getY(),this.getPos().getZ(),64D));
			this.updateTicks = 0;
		}
	}
	public boolean isLocked() {
		return this.isLocked;
	}
	
	public class NBT{
		public static final String LOCKED="locked";
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

	public void fadeIn() {}
	
	public void setConsolePos(BlockPos pos) {
		this.consolePos = pos.toImmutable();
	}
	
	public BlockPos getConsolePos() {
		return this.consolePos;
	}
	
}
