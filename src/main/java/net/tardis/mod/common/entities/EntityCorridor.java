package net.tardis.mod.common.entities;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.network.play.server.SPacketEntityVelocity;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.Vec3d;
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

	@Override
	public AxisAlignedBB getCollisionBoundingBox() {
		return super.getCollisionBoundingBox();
	}

	@Override
	public boolean canBeCollidedWith() {
		return true;
	}

	@Override
	public boolean canBePushed() {
		return true;
	}

	@Override
	public boolean isPushedByWater() {
		return false;
	}

	@Override
	public boolean processInitialInteract(EntityPlayer player, EnumHand hand) {
		this.setOpen(this.isOpen() ? false : true);
		return true;
	}

	@Override
	public AxisAlignedBB getCollisionBox(Entity entityIn) {
		return new AxisAlignedBB(0,0,0, 2, 2, 2);
	}

	@Override
	public void applyEntityCollision(Entity entityIn) {
		if(!this.isOpen()) {
			entityIn.motionX = 0;
			entityIn.motionY = 0;
			entityIn.motionZ = 0;
			entityIn.applyEntityCollision(this);
			if(entityIn instanceof EntityPlayerMP) {
				((EntityPlayerMP)entityIn).connection.sendPacket(new SPacketEntityVelocity(entityIn));
			}
		}
	}
}
