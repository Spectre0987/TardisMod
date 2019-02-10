package net.tardis.mod.common.entities.brak;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.World;

public class EntityDoorsBrakSecondary extends Entity{

	public static final DataParameter<Boolean> OPEN = EntityDataManager.createKey(EntityDoorsBrakSecondary.class, DataSerializers.BOOLEAN);
	
	public EntityDoorsBrakSecondary(World worldIn) {
		super(worldIn);
	}

	@Override
	protected void entityInit() {
		this.dataManager.register(OPEN, false);
	}

	@Override
	protected void readEntityFromNBT(NBTTagCompound compound) {
		this.dataManager.set(OPEN, compound.getBoolean("open"));
	}

	@Override
	protected void writeEntityToNBT(NBTTagCompound compound) {
		compound.setBoolean("open", this.dataManager.get(OPEN));
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBox() {
		return super.getCollisionBoundingBox();
	}

	@Override
	public boolean processInitialInteract(EntityPlayer player, EnumHand hand) {
		this.dataManager.set(OPEN, !this.dataManager.get(OPEN));
		return true;
	}

	@Override
	public AxisAlignedBB getCollisionBox(Entity entityIn) {
		return super.getCollisionBox(entityIn);
	}

	@Override
	public void onKillCommand() {
		return;
	}

}
