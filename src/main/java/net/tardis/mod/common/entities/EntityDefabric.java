package net.tardis.mod.common.entities;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class EntityDefabric extends EntityThrowable{

	public static float SPEED = 2f;
	
	
	public EntityDefabric(World worldIn) {
		super(worldIn);
	}

	public EntityDefabric(World worldIn, EntityLivingBase entity) {
		super(worldIn, entity);
		this.shoot(entity, entity.rotationPitch, entity.rotationYawHead, 0, SPEED, 0);
	}

	@Override
	protected void onImpact(RayTraceResult result) {
		
		//Kill when it hits a block
		if(result.typeOfHit == RayTraceResult.Type.BLOCK) {
			if(world.getBlockState(result.getBlockPos()).causesSuffocation()) {
				this.setDead();
			}
		}
		else if(result.typeOfHit == RayTraceResult.Type.ENTITY) {
			Entity hit = result.entityHit;
			if(hit instanceof EntityLivingBase) {
				EntityLivingBase base = (EntityLivingBase)hit;
				base.getArmorInventoryList().forEach((stack) -> {
					stack.damageItem(80, base);
					System.out.println("Attemped to damage " + stack.getDisplayName());
				});
			}
		}
	}

}
