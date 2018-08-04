package net.tardis.mod.common.entities;

import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class EntityAirshell extends Entity{

	//ThirtySeconds
	private int timeToLive = 600;

	public EntityAirshell(World world) {
		super(world);
	}

	@Override
	protected void entityInit() {
		
	}

	@Override
	protected void readEntityFromNBT(NBTTagCompound compound) {
		this.timeToLive = compound.getInteger("time");
	}

	@Override
	protected void writeEntityToNBT(NBTTagCompound compound) {
		compound.setInteger("time", this.timeToLive);
	}
	
	@Override
	public void onEntityUpdate() {
		super.onEntityUpdate();
		--this.timeToLive;
		if(this.timeToLive <= 0) {
			this.setDead();
		}
	}
}
