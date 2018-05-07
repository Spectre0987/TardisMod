package net.tardis.mod.common.entities;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IProjectile;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.tardis.mod.common.sounds.TSounds;

public class EntityForceField extends Entity {
	
	int ticksToLive = 600;
	
	public EntityForceField(World worldIn) {
		super(worldIn);
		this.setSize(8, 8);
	}
	
	public EntityForceField(World worldIn, BlockPos pos) {
		this(worldIn);
		this.setPosition(pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5);
	}
	
	@Override
	protected void entityInit() {}
	
	@Override
	protected void readEntityFromNBT(NBTTagCompound compound) {}
	
	@Override
	protected void writeEntityToNBT(NBTTagCompound compound) {}
	
	@Override
	public void onEntityUpdate() {
		super.onEntityUpdate();
		--ticksToLive;
		if (ticksToLive <= 0) this.setDead();
		if (ticksToLive % 20 == 0) world.playSound(null, this.getPosition(), TSounds.force_field, SoundCategory.PLAYERS, 0.5F, 1F);
	}
	
	@Override
	public void applyEntityCollision(Entity entityIn) {
		if (entityIn instanceof EntityLivingBase) {
			entityIn.motionX *= -2;
			entityIn.motionY *= -2;
			entityIn.motionZ *= -2;
		} else if (entityIn instanceof IProjectile) entityIn.setDead();
	}
	
	@Override
	public boolean canBeCollidedWith() {
		return true;
	}
	
	@Override
	public boolean canBePushed() {
		return true;
	}
	
}
