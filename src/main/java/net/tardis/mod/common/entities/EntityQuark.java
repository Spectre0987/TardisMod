package net.tardis.mod.common.entities;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.monster.EntityIronGolem;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class EntityQuark extends EntityMob implements IRangedAttackMob {

	public EntityQuark(World worldIn) {
		super(worldIn);


	}

	protected void initEntityAI() {
		tasks.addTask(0, new EntityAISwimming(this));
		tasks.addTask(5, new EntityAIMoveTowardsRestriction(this, 0.5D));
		tasks.addTask(7, new EntityAIWanderAvoidWater(this, 0.5D));
		tasks.addTask(8, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
		tasks.addTask(8, new EntityAILookIdle(this));
		applyEntityAI();
	}

	private void applyEntityAI() {
		tasks.addTask(6, new EntityAIMoveThroughVillage(this, 0.5D, false));
		targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, true));
		targetTasks.addTask(3, new EntityAINearestAttackableTarget(this, EntityVillager.class, false));
		targetTasks.addTask(3, new EntityAINearestAttackableTarget(this, EntityIronGolem.class, true));
	}


	@Override
	public void onUpdate() {
		super.onUpdate();
	}

	@Override //TODO - Not just copy pasting the dalek shooting
	public void attackEntityWithRangedAttack(EntityLivingBase target, float distanceFactor) {

	}

	@Override
	public void setSwingingArms(boolean b) {

	}
}
