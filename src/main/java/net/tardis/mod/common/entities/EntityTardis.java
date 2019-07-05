package net.tardis.mod.common.entities;

import net.minecraft.entity.Entity;
import net.minecraft.entity.MoverType;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.tardis.mod.common.dimensions.TDimensions;
import net.tardis.mod.common.tileentity.TileEntityTardis;

public class EntityTardis extends Entity{

	private BlockPos consolePos = BlockPos.ORIGIN;
	
	public EntityTardis(World worldIn) {
		super(worldIn);
	}

	@Override
	protected void entityInit() {
		
	}

	@Override
	protected void readEntityFromNBT(NBTTagCompound compound) {
		
	}

	@Override
	protected void writeEntityToNBT(NBTTagCompound compound) {
		
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
					
				}
			}
		}
	}
	
	public void move() {
		if(!this.onGround)
			motionY -= 0.23D;
		this.move(MoverType.SELF, motionX, motionY, motionZ);
		motionX = motionY = motionZ = 0;
	}

	public void setConsole(BlockPos console) {
		this.consolePos = console;
	}
	
	public BlockPos getConsole() {
		return this.consolePos;
	}

}
