package net.tardis.mod.common.entities;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityFlying;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.SoundEvents;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
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
import net.tardis.mod.client.EnumExterior;
import net.tardis.mod.common.blocks.TBlocks;
import net.tardis.mod.common.dimensions.TDimensions;
import net.tardis.mod.common.tileentity.TileEntityDoor;
import net.tardis.mod.common.tileentity.TileEntityTardis;
import net.tardis.mod.util.TardisTeleporter;
import net.tardis.mod.util.helpers.Helper;

public class EntityTardis extends EntityFlying {
	
	public static final DataParameter<NBTTagCompound> STATE = EntityDataManager.createKey(EntityTardis.class, DataSerializers.COMPOUND_TAG);
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
	protected void entityInit() {
		super.entityInit();
		this.dataManager.register(STATE, new NBTTagCompound());
	}
	
	@Override
	public void writeEntityToNBT(NBTTagCompound tag) {
		tag.setLong("tPos", consolePos.toLong());
		tag.setInteger("state", this.getState());
		super.writeEntityToNBT(tag);
	}
	
	@Override
	public void readEntityFromNBT(NBTTagCompound tag) {
		super.readEntityFromNBT(tag);
		consolePos = BlockPos.fromLong(tag.getLong("tPos"));
		this.setState(tag.getInteger("state"));
	}
	
	public BlockPos getConsolePos() {
		return consolePos;
	}
	
	public void setConsolePos(BlockPos consolePos) {
		this.consolePos = consolePos.toImmutable();
	}
	
	public int getState() {
		return this.dataManager.get(STATE).getInteger("block");
	}
	
	public void setState(int state) {
		NBTTagCompound tag = new NBTTagCompound();
		tag.setInteger("block", state);
		this.dataManager.set(STATE, tag);
		this.dataManager.setDirty(STATE);
	}
	@Override
	public void onEntityUpdate() {
		super.onEntityUpdate();
		if(world.getBlockState(this.getPosition().down()).getMaterial() == Material.AIR) this.renderRotation += 4;
		if (this.renderRotation > 360) this.renderRotation = 0;
		if (!world.isRemote) {
			Entity e = this.getControllingPassenger();
			if (e != null && e instanceof EntityLivingBase) {
				EntityLivingBase base = (EntityLivingBase)e;
				if(base.moveForward > 0) {
					Vec3d look = e.getLookVec().scale(0.5);
					motionX = look.x;
					motionY = look.y;
					motionZ = look.z;
				}
				else if(base.moveForward < 0) {
					Vec3d look = e.getLookVec().scale(0.5).scale(-1);
					motionX = look.x;
					motionY = look.y;
					motionZ = look.z;
				}
				if(base.moveStrafing > 0) {
					Vec3d look = e.getLookVec().scale(0.5).rotateYaw(90F);
					motionX = look.x;
					motionZ = look.z;
				}
				else if(base.moveStrafing < 0) {
					Vec3d look = e.getLookVec().scale(0.5).rotateYaw(-90F);
					motionX = look.x;
					motionZ = look.z;
				}
				((EntityPlayerMP) e).connection.sendPacket(new SPacketEntityVelocity(this));
			}
			if (this.getControllingPassenger() == null || this.getConsolePos() == null || this.getConsolePos().equals(BlockPos.ORIGIN)) {
				++ticks;
				if(ticks > 10) {
					this.setDead();
				}
			}
		}
		this.prevPosX = this.posX;
		this.prevPosY = this.posY;
		this.prevPosZ = this.posZ;
	}
	
	@Override
	public Entity getControllingPassenger() {
		if (this.getPassengers() != null && this.getPassengers().size() > 0) return this.getPassengers().get(0);
		return null;
	}
	
	@Override
	protected void removePassenger(Entity pas) {
		if (!world.isRemote) {
			if (pas instanceof EntityPlayerMP) {
                WorldServer ws = DimensionManager.getWorld(TDimensions.TARDIS_ID);
				TileEntityTardis tardis = (TileEntityTardis) ws.getTileEntity(getConsolePos());
				world.setBlockState(this.getPosition(), TBlocks.tardis.getDefaultState());
				world.setBlockState(this.getPosition().up(), tardis.getTopBlock());
				tardis.setLocation(this.getPosition());
				((TileEntityDoor) world.getTileEntity(this.getPosition().up())).consolePos = this.getConsolePos();
				BlockPos cPos = this.consolePos.west(3);
				pas.setInvisible(false);
				ForgeChunkManager.forceChunk(((TileEntityTardis) ws.getTileEntity(consolePos)).tardisLocTicket, world.getChunkFromBlockCoords(getPosition()).getPos());
				((EntityPlayerMP) pas).connection.setPlayerLocation(cPos.getX() + 0.5, cPos.getY() + 1, cPos.getZ() + 0.5, Helper.get360FromFacing(EnumFacing.EAST), 0);
                ws.getMinecraftServer().getPlayerList().transferPlayerToDimension((EntityPlayerMP) pas, TDimensions.TARDIS_ID, new TardisTeleporter());
				this.setDead();
				return;
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
			((TileEntityDoor) world.getTileEntity(this.getPosition().up())).consolePos = this.getConsolePos().toImmutable();
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

	public String getExterior() {
		for(EnumExterior e : EnumExterior.values()) {
			if(e.block == Block.getStateById(this.getState()).getBlock()) {
				return e.name();
			}
		}
		return EnumExterior.FIRST.name();
	}

	
}
