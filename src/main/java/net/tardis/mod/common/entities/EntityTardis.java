package net.tardis.mod.common.entities;

import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.tardis.mod.client.EnumExterior;
import net.tardis.mod.common.blocks.BlockTardisTop;
import net.tardis.mod.common.blocks.TBlocks;
import net.tardis.mod.common.dimensions.TDimensions;
import net.tardis.mod.common.entities.controls.ControlDoor;
import net.tardis.mod.common.enums.EnumFlightState;
import net.tardis.mod.common.tileentity.TileEntityDoor;
import net.tardis.mod.common.tileentity.TileEntityTardis;

public class EntityTardis extends Entity{

	public static final DataParameter<String> EXTERIOR = EntityDataManager.createKey(EntityTardis.class, DataSerializers.STRING);
	public static final DataParameter<Integer> OPEN_STATE = EntityDataManager.createKey(EntityTardis.class, DataSerializers.VARINT);
	private BlockPos consolePos = BlockPos.ORIGIN;
	
	public EntityTardis(World worldIn) {
		super(worldIn);
		this.setNoGravity(true);
	}

	@Override
	protected void entityInit() {
		this.dataManager.register(EXTERIOR, "TT");
		this.dataManager.register(OPEN_STATE, EnumFlightState.CLOSED.ordinal());
	}

	@Override
	protected void readEntityFromNBT(NBTTagCompound compound) {
		this.consolePos = BlockPos.fromLong(compound.getLong("console"));
		this.dataManager.set(EXTERIOR, compound.getString("exterior"));
	}

	@Override
	protected void writeEntityToNBT(NBTTagCompound compound) {
		compound.setLong("console", this.consolePos.toLong());
		compound.setString("exterior", this.dataManager.get(EXTERIOR));
	}

	@Override
	public void onEntityUpdate() {
		super.onEntityUpdate();
		
		this.move();
		
		//Allows this to be driven
		if(this.getPassengers().size() > 0) {
			Entity entity = this.getPassengers().get(0);
			if(entity instanceof EntityLivingBase)
				this.handleRider((EntityLivingBase)entity);
		}
		
		this.setNoGravity(true);//(!this.getPassengers().isEmpty());
		
		//Rotate the entity
		if(this.hasNoGravity())
			this.rotationYaw = (rotationYaw + 0.5F) % 360;
		
		if(!world.isRemote && !BlockPos.ORIGIN.equals(this.consolePos)) {
			WorldServer tardisDimension = ((WorldServer)world).getMinecraftServer().getWorld(TDimensions.TARDIS_ID);
			if(tardisDimension != null) {
				TileEntity te = tardisDimension.getTileEntity(this.getConsole());
				if(te instanceof TileEntityTardis) {
					TileEntityTardis tardis = (TileEntityTardis)te;
					tardis.setLocation(this.getPosition());
					if(tardis.getTardisEntity() != this)
						tardis.setTardisEntity(this);
					
					//Update door constantly
					ControlDoor intDoor = tardis.getDoor();
					if(intDoor != null){
						if(this.ticksExisted % 20 == 0) {
							intDoor.getDataManager().set(ControlDoor.FACING, this.rotationYaw);
							intDoor.setBotiUpdate(true);
							intDoor.getDataManager().set(ControlDoor.MOTION, new Vec3d(this.motionX, this.motionY, this.motionZ));
						}
					}
					tardis.setMotion(this.motionX, this.motionY, this.motionZ);
					
					//Replace exterior
					if(this.onGround) {
						world.setBlockState(this.getPosition(), TBlocks.tardis.getDefaultState());
						if(world.setBlockState(this.getPosition().up(), tardis.getTopBlock().withProperty(BlockTardisTop.FACING, this.getHorizontalFacing()))) {
							TileEntityDoor door = (TileEntityDoor)world.getTileEntity(this.getPosition().up());
							door.setConsolePos(this.getConsole());
							door.setStealth(tardis.isStealthMode());
							door.alpha = 1F;
							if(intDoor != null){
								intDoor.setBotiUpdate(true);
							}
						}
						this.setDead();
					}
				}
			}
		}
		
		if(world.isRemote && this.isInsideOfMaterial(Material.WATER)) {
			for(int i = 0; i < 20; ++i) {
				world.spawnParticle(EnumParticleTypes.WATER_BUBBLE, posX + Math.sin(Math.toRadians(i * 18)), posY, posZ + Math.cos(Math.toRadians(i * 18)), 0, 0.5, 0, 0);
			}
		}
		
	}
	
	public void move() {
		if(!this.onGround && !this.hasNoGravity())
			motionY -= 0.5D;
		this.move(MoverType.SELF, motionX, motionY, motionZ);
		motionX = motionY = motionZ = 0;
	}
	
	public void handleRider(EntityLivingBase base) {
		Vec3d look = base.getLookVec().scale(0.3D);
		
		if(base.moveForward > 0) {
			motionX = look.x;
			motionZ = look.z;
		}
		else if(base.moveForward < 0) {
			motionX = -look.x;
			motionZ = -look.z;
		}
		
	}

	public void setConsole(BlockPos console) {
		this.consolePos = console;
	}
	
	public BlockPos getConsole() {
		return this.consolePos;
	}
	
	public EnumExterior getExteriorEnum() {
		return EnumExterior.valueOf(this.getDataManager().get(EXTERIOR));
	}

	public void setExteior(EnumExterior exterior) {
		this.dataManager.set(EXTERIOR, exterior.name());
	}

	@Override
	public EnumActionResult applyPlayerInteraction(EntityPlayer player, Vec3d vec, EnumHand hand) {
		player.startRiding(this);
		return super.applyPlayerInteraction(player, vec, hand);
	}

	@Override
	public boolean canBeCollidedWith() {
		return true;
	}

	@Override
	public boolean canBePushed() {
		return true;
	}
	
	public void setOpenState(EnumFlightState state) {
		this.dataManager.set(OPEN_STATE, state.ordinal());
	}
	
	public EnumFlightState getOpenState() {
		return EnumFlightState.values()[this.dataManager.get(OPEN_STATE)];
	}

	@Override
	public double getMountedYOffset() {
		return 0;
	}

	@Override
	public void updatePassenger(Entity passenger) {
		super.updatePassenger(passenger);
		double angle = Math.toRadians(-this.rotationYaw);
		double offsetX = Math.sin(angle), offsetZ = Math.cos(angle);
		passenger.setPosition(posX + offsetX, posY + this.getMountedYOffset() + passenger.getYOffset(), posZ + offsetZ);
	}

	@Override
	protected void removePassenger(Entity pass) {
		super.removePassenger(pass);
		if(!world.isRemote) {
			((WorldServer)world).addScheduledTask(new Runnable() {
				@Override
				public void run() {
					WorldServer tardisWorld = ((WorldServer)world).getMinecraftServer().getWorld(TDimensions.TARDIS_ID);
					if(tardisWorld != null) {
						TileEntity te = tardisWorld.getTileEntity(getConsole());
						if(te instanceof TileEntityTardis) {
							((TileEntityTardis)te).enterTARDIS(pass);
						}
					}
				}
			});
		}
	}
	
}
