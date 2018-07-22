package net.tardis.mod.common.entities;

import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.world.World;

public class EntityCorridor extends Entity{

	public static final DataParameter<Boolean> IS_OPEN = EntityDataManager.createKey(EntityCorridor.class, DataSerializers.BOOLEAN);
	
	public EntityCorridor(World worldIn) {
		super(worldIn);
	}

	@Override
	protected void entityInit() {
		this.dataManager.register(IS_OPEN, false);
	}
	
	public void setOpen(boolean open) {
		this.dataManager.set(IS_OPEN, open);
	}
	
	public boolean isOpen() {
		return this.dataManager.get(IS_OPEN);
	}

	@Override
	protected void readEntityFromNBT(NBTTagCompound compound) {
		this.dataManager.set(IS_OPEN, compound.getBoolean("open"));
	}

	@Override
	protected void writeEntityToNBT(NBTTagCompound compound) {
		compound.setBoolean("open", this.dataManager.get(IS_OPEN));
	}

}
