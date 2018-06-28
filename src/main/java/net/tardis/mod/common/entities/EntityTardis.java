package net.tardis.mod.common.entities;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityFlying;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.SoundEvents;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.play.server.SPacketEntityVelocity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.common.ForgeChunkManager;
import net.tardis.mod.common.blocks.TBlocks;
import net.tardis.mod.common.dimensions.TDimensions;
import net.tardis.mod.common.tileentity.TileEntityDoor;
import net.tardis.mod.common.tileentity.TileEntityTardis;
import net.tardis.mod.util.TardisTeleporter;
import net.tardis.mod.util.helpers.Helper;

public class EntityTardis extends EntityFlying {
	
	public BlockPos consolePos = BlockPos.ORIGIN;
	public int renderRotation = 0;
	int ticks;
	
	public EntityTardis(World worldIn) {
		super(worldIn);
		this.setSize(1.2F, 2.5F);
	}
	
	public EntityTardis(World worldIn, Entity e) {
		this(worldIn);
	}
	
	@Override
	public void writeEntityToNBT(NBTTagCompound tag) {
		tag.setLong("tPos", consolePos.toLong());
		super.writeEntityToNBT(tag);
	}
	
	@Override
	public void readEntityFromNBT(NBTTagCompound tag) {
		super.readEntityFromNBT(tag);
		consolePos = BlockPos.fromLong(tag.getLong("tPos"));
	}
	
	public BlockPos getConsolePos() {
		return consolePos;
	}
	
	public void setConsolePos(BlockPos consolePos) {
		this.consolePos = consolePos.toImmutable();
	}
	
	@Override
	public void onEntityUpdate() {
		super.onEntityUpdate();
		this.renderRotation += 4;
		if (this.renderRotation > 360) this.renderRotation = 0;
		if (!world.isRemote) {
			Entity e = this.getControllingPassenger();
			if (e != null && ((EntityLivingBase) e).moveForward > 0) {
				Vec3d look = e.getLookVec();
				motionX = look.x;
				motionY = look.y;
				motionZ = look.z;
				((EntityPlayerMP) e).connection.sendPacket(new SPacketEntityVelocity(this));
			}
			if (this.getControllingPassenger() == null || this.getConsolePos() == null || this.getConsolePos().equals(BlockPos.ORIGIN)) {
				++ticks;
				if(ticks > 10) {
					this.setDead();
				}
			}
		}
	}
	
	@Override
	public Entity getControllingPassenger() {
		if (this.getPassengers() != null && this.getPassengers().size() > 0) return this.getPassengers().get(0);
		return null;
	}
	
	@Override
	protected void removePassenger(Entity pas) {
		if (!world.isRemote) {
			if (pas instanceof EntityPlayer) {
				world.setBlockState(this.getPosition(), TBlocks.tardis.getDefaultState());
				world.setBlockState(this.getPosition().up(), TBlocks.tardis_top.getDefaultState());
				WorldServer ws = DimensionManager.getWorld(TDimensions.id);
				System.out.println("BlockPos is" + this.getConsolePos());
				((TileEntityTardis) ws.getTileEntity(getConsolePos())).setLocation(this.getPosition());
				((TileEntityDoor) world.getTileEntity(this.getPosition().up())).consolePos = this.getConsolePos();
				this.setDead();
				BlockPos cPos = this.consolePos.west(3);
				pas.setInvisible(false);
				ForgeChunkManager.forceChunk(((TileEntityTardis) ws.getTileEntity(consolePos)).tardisLocTicket, world.getChunkFromBlockCoords(getPosition()).getPos());
				ws.getMinecraftServer().getPlayerList().transferPlayerToDimension((EntityPlayerMP) pas, TDimensions.id, new TardisTeleporter());
				((EntityPlayerMP) pas).connection.setPlayerLocation(cPos.getX() + 0.5, cPos.getY() + 1, cPos.getZ() + 0.5, Helper.get360FromFacing(EnumFacing.EAST), 0);
			}
		}
		super.removePassenger(pas);
	}
	
	@Override
	public boolean shouldDismountInWater(Entity rider) {
		return false;
	}
	
	@Override
	public void onDeath(DamageSource cause) {
		super.onDeath(cause);
		if (!world.isRemote) {
			world.setBlockState(this.getPosition(), TBlocks.tardis.getDefaultState());
			world.setBlockState(this.getPosition().up(), TBlocks.tardis_top.getDefaultState());
			((TileEntityDoor) world.getTileEntity(this.getPosition().up())).consolePos = this.getConsolePos();
		}
	}
	
	@Override
	public boolean attackEntityFrom(DamageSource source, float amount) {
		return true;
	}
	
	@Override
	public void applyEntityCollision(Entity entityIn) {
		super.applyEntityCollision(entityIn);
		if (!world.isRemote) {
			if (getSpeed() > 0.15) {
				if (entityIn != this.getControllingPassenger()) {
					entityIn.attackEntityFrom(DamageSource.GENERIC, 10F);
					world.playSound(null, this.getPosition(), SoundEvents.ITEM_SHIELD_BLOCK, SoundCategory.PLAYERS, 1F, 1F);
				}
			}
		}
	}
	
	public double getSpeed() {
		return (Math.abs(motionX) + Math.abs(motionY) + Math.abs(motionZ)) / 3;
	}
	
}
