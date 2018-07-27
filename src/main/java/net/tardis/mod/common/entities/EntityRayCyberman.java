package net.tardis.mod.common.entities;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class EntityRayCyberman extends EntityThrowable{

	public static final float SPEED = 1.5F;
	
	public EntityRayCyberman(World worldIn) {
		super(worldIn);
	}
	
	public EntityRayCyberman(World worldIn, EntityLivingBase base) {
		super(worldIn, base);
		this.shoot(base, base.rotationPitch, base.rotationYawHead, 0, SPEED, 0);
	}
	public EntityRayCyberman(World worldIn, double x, double y, double z) {
		super(worldIn, x, y, z);
		this.shoot(x, y, z, SPEED, 0);
	}

	@Override
	protected void onImpact(RayTraceResult result) {
		if(result.typeOfHit == RayTraceResult.Type.BLOCK) {
			if(world.getBlockState(result.getBlockPos()).causesSuffocation()) {
				this.setDead();
			}
		}
		else if(result.entityHit != null) {
			result.entityHit.attackEntityFrom(EntityCyberman.DamageSourceCyberGun.causeIndirectDamage(this, this.getThrower()), 2F);
			this.setDead();
		}
	}

}
