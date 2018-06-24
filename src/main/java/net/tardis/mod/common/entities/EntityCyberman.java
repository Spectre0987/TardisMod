package net.tardis.mod.common.entities;

import net.minecraft.entity.monster.EntityMob;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class EntityCyberman extends EntityMob{

	public EntityCyberman(World worldIn) {
		super(worldIn);
	}

	@Override
	public void onLivingUpdate() {
		// TODO Auto-generated method stub
		super.onLivingUpdate();
	}

	@Override
	public boolean attackEntityFrom(DamageSource source, float amount) {
		// TODO Auto-generated method stub
		return super.attackEntityFrom(source, amount);
	}

	@Override
	protected void initEntityAI() {
		super.initEntityAI();
	}

}
