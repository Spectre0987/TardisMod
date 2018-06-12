package net.tardis.mod.common.entities;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

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
		if (res != null && res.entityHit instanceof EntityLivingBase) {
			res.entityHit.attackEntityFrom(this.getThrower() != null ? DamageSource.causeIndirectDamage(this, getThrower()) : DamageSource.FIREWORKS, 10F);
		}
		if (res.typeOfHit == res.typeOfHit.BLOCK) this.setDead();
	}
	
	@Override
	protected float getGravityVelocity() {
		return 0.000F;
	}
	
}
