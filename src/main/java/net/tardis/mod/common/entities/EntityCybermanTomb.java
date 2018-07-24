package net.tardis.mod.common.entities;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMoveThroughVillage;
import net.minecraft.entity.ai.EntityAIMoveTowardsRestriction;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIWanderAvoidWater;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityIronGolem;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class EntityCybermanTomb extends EntityCyberman {

	public EntityCybermanTomb(World worldIn) {
		super(worldIn);
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.2);
		this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(4D);
	}

	@Override
	protected void initEntityAI(){
	    this.tasks.addTask(2, new EntityAIAttackMelee(this, 0.75D, false));
	    this.tasks.addTask(5, new EntityAIMoveTowardsRestriction(this, 1.0D));
	    this.tasks.addTask(7, new EntityAIWanderAvoidWater(this, 1.0D));
	    this.tasks.addTask(8, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
	    this.tasks.addTask(8, new EntityAILookIdle(this));
	    this.applyEntityAI();
	}
	
	protected void applyEntityAI(){
	    this.tasks.addTask(6, new EntityAIMoveThroughVillage(this, 1.0D, false));
	    this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, true));
	    this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, true));
	    this.targetTasks.addTask(3, new EntityAINearestAttackableTarget(this, EntityVillager.class, false));
	    this.targetTasks.addTask(3, new EntityAINearestAttackableTarget(this, EntityIronGolem.class, true));
	}
}
