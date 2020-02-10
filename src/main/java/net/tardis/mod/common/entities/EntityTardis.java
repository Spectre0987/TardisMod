package net.tardis.mod.common.entities;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.network.play.client.CPacketVehicleMove;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tardis.mod.client.EnumExterior;
import net.tardis.mod.common.blocks.BlockTardisTop;
import net.tardis.mod.common.blocks.TBlocks;
import net.tardis.mod.common.dimensions.TDimensions;
import net.tardis.mod.common.entities.controls.ControlDoor;
import net.tardis.mod.common.enums.EnumFlightState;
import net.tardis.mod.common.tileentity.TileEntityDoor;
import net.tardis.mod.common.tileentity.TileEntityTardis;
import net.tardis.mod.util.TardisTeleporter;

public class EntityTardis extends Entity{

	public static final DataParameter<Integer> OPEN_STATE = EntityDataManager.createKey(EntityTardis.class, DataSerializers.VARINT);
	public static final DataParameter<Integer> EXTERIOR = EntityDataManager.createKey(EntityTardis.class, DataSerializers.VARINT);
	private BlockPos consolePos = BlockPos.ORIGIN;
	private int ticksOnGround = 0;
	private NBTTagCompound doorTag;
	private IBlockState state = TBlocks.tardis_top_tt.getDefaultState();
	
	public EntityTardis(World worldIn) {
		super(worldIn);
	}

	@Override
	protected void entityInit() {
		this.dataManager.register(OPEN_STATE, EnumFlightState.CLOSED.ordinal());
		this.dataManager.register(EXTERIOR, Block.getStateId(TBlocks.tardis_top_tt.getDefaultState()));
	}

	@Override
	protected void readEntityFromNBT(NBTTagCompound compound) {
		this.consolePos = BlockPos.fromLong(compound.getLong("console"));
		this.doorTag = compound.getCompoundTag("door_tag");
		this.state = Block.getStateById(compound.getInteger("exterior_state"));
	}

	@Override
	protected void writeEntityToNBT(NBTTagCompound compound) {
		compound.setLong("console", this.consolePos.toLong());
		if(doorTag!= null && !doorTag.isEmpty())
			compound.setTag("door_tag", doorTag);
		compound.setInteger("exterior_state", Block.getStateId(state));
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
		
		this.setNoGravity(!this.getPassengers().isEmpty());
		
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
						++this.ticksOnGround;
						if(this.ticksOnGround > 60) {
							this.createDoorTile();
							this.setDead();
						}
					}
				}
			}
		}
		
		if(world.isRemote && this.isInsideOfMaterial(Material.WATER)) {
			for(int i = 0; i < 20; ++i) {
				world.spawnParticle(EnumParticleTypes.WATER_BUBBLE, posX + Math.sin(Math.toRadians(i * 18)), posY, posZ + Math.cos(Math.toRadians(i * 18)), 0, 0.5, 0, 0);
			}
		}
		
		if(world.isRemote && this.isInsideOfMaterial(Material.FIRE)) {
			for(int i = 0; i < 20; ++i) {
				world.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, posX + Math.sin(Math.toRadians(i * 18)), posY, posZ + Math.cos(Math.toRadians(i * 18)), 0, 0.5, 0, 0);
			}
		}
		
		//Update Exterior Enum
		if(world.isRemote && this.ticksExisted % 20 == 0)
			this.state = Block.getStateById(this.dataManager.get(EXTERIOR));
		
	}
	
	public void move() {
		if(!this.onGround && !this.hasNoGravity())
			motionY -= 0.5D;
		this.move(MoverType.SELF, motionX, motionY, motionZ);
		motionX = motionZ = motionY = 0;
		this.prevPosX = posX;
		this.prevPosY = posY;
		this.prevPosZ = posZ;
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
		if(base.moveStrafing > 0) {
			Vec3d move = look.rotateYaw(-80);
			motionX = move.x;
			motionZ = move.z;
		}
		else if(base.moveStrafing < 0) {
			Vec3d move = look.rotateYaw(80);
			motionX = move.x;
			motionZ = move.z;
		}
		
		if(world.isRemote)
			world.sendPacketToServer(new CPacketVehicleMove(this));
		
	}

	public void setConsole(BlockPos console) {
		this.consolePos = console;
	}
	
	public BlockPos getConsole() {
		return this.consolePos;
	}
	
	@SideOnly(Side.CLIENT)
	public EnumExterior getExteriorEnum() {
		return EnumExterior.getExteriorFromBlock(this.state.getBlock());
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
		double offsetX = Math.sin(angle) * 0.5, offsetZ = Math.cos(angle) * 0.5;
		passenger.setPosition(posX + offsetX, posY + this.getMountedYOffset() + passenger.getYOffset(), posZ + offsetZ);
		passenger.fallDistance = 0;
	}

	@Override
	protected void removePassenger(Entity pass) {
		super.removePassenger(pass);
		if(!world.isRemote) {
			if(this.getOpenState() == EnumFlightState.CLOSED) {
				((WorldServer)world).addScheduledTask(new Runnable() {
					@Override
					public void run() {
						WorldServer tardisWorld = ((WorldServer)world).getMinecraftServer().getWorld(TDimensions.TARDIS_ID);
						if(tardisWorld != null) {
							BlockPos pos = getConsole().south();
							pass.setPosition(pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5);
							if(pass instanceof EntityPlayerMP)
								world.getMinecraftServer().getPlayerList().transferPlayerToDimension((EntityPlayerMP)pass, TDimensions.TARDIS_ID, new TardisTeleporter());
						}
					}
				});
			}
			else {
				double x = Math.sin(Math.toRadians(this.rotationYaw)), z = Math.cos(Math.toRadians(this.rotationYaw));
				pass.setPosition(this.posX + z, this.posY, this.posZ + z);
			}
		}
	}
	
	public IBlockState getBlockState() {
		return this.state;
	}
	
	public void setBlockState(IBlockState state) {
		this.state = state;
		this.dataManager.set(EXTERIOR, Block.getStateId(state));
	}
	
	public TileEntityDoor createDoorTile() {
		world.setBlockState(this.getPosition().up(), this.getBlockState().withProperty(BlockTardisTop.FACING, this.getHorizontalFacing()));
		world.setBlockState(this.getPosition(), TBlocks.tardis.getDefaultState());
		TileEntity te = world.getTileEntity(this.getPosition().up());
		if(te instanceof TileEntityDoor && this.doorTag != null && !this.doorTag.isEmpty()) {
			((TileEntityDoor)te).deserializeNBT(doorTag);
			te.setPos(this.getPosition().up());
		}
		return (TileEntityDoor) te;
	}
	
	public void setTag(NBTTagCompound tag) {
		this.doorTag = tag;
	}
	
}
