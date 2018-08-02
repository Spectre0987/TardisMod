package net.tardis.mod.common.entities;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.tardis.mod.common.entities.EntityDalek.DamageSourceDalek;

public class EntityDalekRay extends EntityThrowable {

	public static final float SPEED = 2F;
	
	public EntityDalekRay(World worldIn) {
		super(worldIn);
	}
	
	public EntityDalekRay(World worldIn, EntityLivingBase base) {
		super(worldIn, base);
		this.shoot(base, base.rotationPitch, base.rotationYawHead, 0, SPEED, 0);
	}
	
	@Override
	protected void onImpact(RayTraceResult res) {

		if(isInWater()){
			setDead();
		}

		if(world != null) {
			if (res != null && res.entityHit instanceof EntityLivingBase) {
				res.entityHit.attackEntityFrom(new DamageSourceDalek().causeIndirectDamage(this, this.getThrower()), 4F);
				this.setDead();
			}
			if (res.typeOfHit == res.typeOfHit.BLOCK) {
				IBlockState state = world.getBlockState(res.getBlockPos());
				if(!state.causesSuffocation())
					return;
				this.setDead();
			}
		}
	}
	
	@Override
	protected float getGravityVelocity() {
		return 0.000F;
	}
	
	@Override
	public void onUpdate() {
		super.onUpdate();
		if(this.motionX + this.motionY + this.motionZ == 0 || this.ticksExisted >= 200) {
			this.setDead();
		}
	}
	
}
