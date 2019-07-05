package net.tardis.mod.common.entities;

import net.minecraft.entity.Entity;
import net.minecraft.entity.MoverType;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class EntityTardis extends Entity{

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
	}
	
	public void move() {
		if(!this.onGround)
			motionY -= 0.23D;
		this.move(MoverType.SELF, motionX, motionY, motionZ);
		motionX = motionY = motionZ = 0;
	}

}
