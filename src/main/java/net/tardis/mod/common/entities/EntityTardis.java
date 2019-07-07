package net.tardis.mod.common.entities;

import net.minecraft.entity.Entity;
import net.minecraft.entity.MoverType;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.tardis.mod.client.EnumExterior;
import net.tardis.mod.common.blocks.BlockTardisTop;
import net.tardis.mod.common.blocks.TBlocks;
import net.tardis.mod.common.dimensions.TDimensions;
import net.tardis.mod.common.entities.controls.ControlDoor;
import net.tardis.mod.common.tileentity.TileEntityDoor;
import net.tardis.mod.common.tileentity.TileEntityTardis;

public class EntityTardis extends Entity{

	public static final DataParameter<String> EXTERIOR = EntityDataManager.createKey(EntityTardis.class, DataSerializers.STRING);
	private BlockPos consolePos = BlockPos.ORIGIN;
	
	public EntityTardis(World worldIn) {
		super(worldIn);
	}

	@Override
	protected void entityInit() {
		this.dataManager.register(EXTERIOR, EnumExterior.TT.name());
	}

	@Override
	protected void readEntityFromNBT(NBTTagCompound compound) {
		this.consolePos = BlockPos.fromLong(compound.getLong("console"));
	}

	@Override
	protected void writeEntityToNBT(NBTTagCompound compound) {
		compound.setLong("console", this.consolePos.toLong());
	}

	@Override
	public void onEntityUpdate() {
		super.onEntityUpdate();
		this.move();
		
		if(!world.isRemote && !BlockPos.ORIGIN.equals(this.consolePos)) {
			WorldServer tardisDimension = ((WorldServer)world).getMinecraftServer().getWorld(TDimensions.TARDIS_ID);
			if(tardisDimension != null) {
				TileEntity te = tardisDimension.getTileEntity(this.getConsole());
				if(te instanceof TileEntityTardis) {
					TileEntityTardis tardis = (TileEntityTardis)te;
					tardis.setLocation(this.getPosition());
					
					//Replace exterior
					if(this.onGround) {
						world.setBlockState(this.getPosition(), TBlocks.tardis.getDefaultState());
						if(world.setBlockState(this.getPosition().up(), tardis.getTopBlock().withProperty(BlockTardisTop.FACING, this.getHorizontalFacing()))) {
							TileEntityDoor door = (TileEntityDoor)world.getTileEntity(this.getPosition().up());
							door.setConsolePos(this.getConsole());
							door.setStealth(tardis.isStealthMode());
							door.alpha = 1F;
							ControlDoor intDoor = tardis.getDoor();
							if(intDoor != null){
								intDoor.setBotiUpdate(true);
							}
						}
						this.setDead();
					}
				}
			}
		}
		
	}
	
	public void move() {
		if(!this.onGround)
			motionY -= 0.5D;
		this.move(MoverType.SELF, motionX, motionY, motionZ);
		motionX = motionY = motionZ = 0;
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
}
