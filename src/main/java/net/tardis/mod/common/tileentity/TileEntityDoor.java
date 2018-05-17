package net.tardis.mod.common.tileentity;

import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.common.network.NetworkRegistry.TargetPoint;
import net.tardis.mod.Tardis;
import net.tardis.mod.common.dimensions.TDimensions;
import net.tardis.mod.common.sounds.TSounds;
import net.tardis.mod.packets.MessageDoorOpen;
import net.tardis.mod.util.TardisTeleporter;
import net.tardis.mod.util.helpers.Helper;
import net.tardis.mod.util.helpers.TardisHelper;

public class TileEntityDoor extends TileEntity implements ITickable {
	
	public BlockPos consolePos;
	public boolean isLocked = false;
	public int ticks = 0;
	public int lockCooldown = 0;
	private int updateTicks=0;
	
	public TileEntityDoor() {
		
	}
	
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
	
	public void transferPlayerToTardis(EntityPlayer player) {
		if (!world.isRemote) {
			if (!this.isLocked || TardisHelper.hasValidKey(player, consolePos)) {
				WorldServer s = (WorldServer) world;
				BlockPos pos = consolePos.south(4);
				s.getMinecraftServer().getPlayerList().transferPlayerToDimension((EntityPlayerMP) player, TDimensions.id, new TardisTeleporter(s));
				player.setPositionAndUpdate(pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5);
			} else if (this.isLocked) {
				world.playSound(null, this.getPos(), TSounds.door_locked, SoundCategory.BLOCKS, 1F, 1F);
			}
		}
	}
	
	public void toggleLocked(EntityPlayer player) {
		if (TardisHelper.hasValidKey(player, consolePos) && lockCooldown == 0) {
			lockCooldown = 20;
			isLocked = isLocked ? false : true;
			this.markDirty();
			if(!world.isRemote)
				Tardis.NETWORK.sendToDimension(new MessageDoorOpen(this.getPos(),this.getLocked()), world.provider.getDimension());
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
	
	@Override
	public void update() {
		ticks++;
		if (lockCooldown > 0)
			--lockCooldown;
		if (ticks > 20) {
			this.ticks = 0;
			if (!world.isRemote) {
				if(!this.isLocked) {
					List<Entity> entityList = world.getEntitiesWithinAABB(Entity.class, Helper.createBB(this.getPos().down(), 1));
					if (entityList != null) {
						for (Entity e : entityList) {
							if (!(e instanceof EntityPlayer)) {
								e.setPosition(this.consolePos.getX() + 0.5, this.consolePos.getY(), this.consolePos.getZ() + 3);
								e.changeDimension(TDimensions.id);
							}
						}
					}
				}
			}
		}
		++this.updateTicks;
		if(this.updateTicks>20) {
			Tardis.NETWORK.sendToAllAround(new MessageDoorOpen(this.getPos(),this.getLocked()),new TargetPoint(this.world.provider.getDimension(),this.getPos().getX(),this.getPos().getY(),this.getPos().getZ(),64D));
			this.updateTicks = 0;
		}
	}
	public boolean getLocked() {
		return this.isLocked;
	}
	
	public class NBT{
		public static final String LOCKED="locked";
	}

	@Override
	public void onLoad() {
		super.onLoad();
		if(!world.isRemote)
			Tardis.NETWORK.sendToDimension(new MessageDoorOpen(this.getPos(),this.getLocked()),world.provider.getDimension());
	}

	@Override
	public AxisAlignedBB getRenderBoundingBox() {
		return new AxisAlignedBB(getPos().getX(),getPos().getY()-1,getPos().getZ(),getPos().getX()+1,getPos().getY()+1,getPos().getZ()+1);
	}
	
}
