package net.tardis.mod.common.entities;


import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackRanged;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAIMoveThroughVillage;
import net.minecraft.entity.ai.EntityAIMoveTowardsRestriction;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWanderAvoidWater;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityFlying;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.tardis.mod.api.entities.IDontSufficate;
import net.tardis.mod.common.TDamageSources;
import net.tardis.mod.common.items.TItems;
import net.tardis.mod.common.sounds.TSounds;
import net.tardis.mod.util.common.helpers.EntityHelper;

public class EntityDalek extends EntityMob implements IRangedAttackMob, EntityFlying, IDontSufficate {
	
	private ItemStack[] deathItems = new ItemStack[]{new ItemStack(TItems.power_cell, 20 + rand.nextInt(11)), new ItemStack(TItems.gunstick, 1), new ItemStack(TItems.circuts, 7 + rand.nextInt(3))};
	
	/**
	 * Random offset used in floating behaviour
	 */
	private float heightOffset = 0.5F;
	/**
	 * ticks until heightOffset is randomized
	 */
	private int heightOffsetUpdateTime;
	
	public EntityDalek(World world) {
		super(world);
	}
	
	@Override
	protected void initEntityAI() {
		tasks.addTask(0, new EntityAISwimming(this));
		tasks.addTask(2, new EntityAIAttackRanged(this, 0.75D, 40, 30F));
		tasks.addTask(5, new EntityAIMoveTowardsRestriction(this, 1.0D));
		tasks.addTask(7, new EntityAIWanderAvoidWater(this, 1.0D));
		tasks.addTask(8, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
		applyEntityAI();
		this.isImmuneToFire = true;
	}
	
	protected void applyEntityAI() {
		tasks.addTask(6, new EntityAIMoveThroughVillage(this, 1.0D, false));
		targetTasks.addTask(1, new EntityAIHurtByTarget(this, true));
		targetTasks.addTask(2, new EntityAINearestAttackableTarget<>(this, EntityPlayer.class, true));
		targetTasks.addTask(0, new EntityAINearestAttackableTarget<>(this, EntityLivingBase.class, 100, true, false, input -> !(input instanceof EntityDalek)));
	}
	
	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(35.0D);
		this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.3D);
		this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(3.0D);
		this.getEntityAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(2.0D);
	}
	
	
	/**
	 * Attack the specified entity using a ranged attack.
	 */
	@Override
	public void attackEntityWithRangedAttack(EntityLivingBase target, float distanceFactor) {
		EntityLaserRay laser = new EntityLaserRay(world, this, 7, TDamageSources.DALEK, new Vec3d(0, 1, 0));
		double d0 = target.posX - this.posX;
		double d1 = target.getEntityBoundingBox().minY + (double) (target.height / 3.0F) - laser.posY;
		double d2 = target.posZ - this.posZ;
		double d3 = (double) MathHelper.sqrt(d0 * d0 + d2 * d2);
		laser.shoot(d0, d1 + d3 * 0.20000000298023224D, d2, 1.6F, (float) (14 - this.world.getDifficulty().getId() * 4));
		this.playSound(TSounds.dalek_ray, 1.0F, 1.0F / (this.getRNG().nextFloat() * 0.4F + 0.8F));
		EntityHelper.lookAt(target.posX, target.posY, target.posZ, this);
		this.world.spawnEntity(laser);
	}
	
	
	@Override
	public void setSwingingArms(boolean swingingArms) {
	}
	
	@Override
	protected void jump() {
		super.jump();
	}
	
	@Override
	public void onLivingUpdate() {
		if (!this.onGround && this.motionY < 0.0D) {
			this.motionY *= 0.6D;
		}
		
		if (this.world.isRemote) {
			if (!onGround || world.isAirBlock(getPosition().down())) {
				for (int x = 0; x <= 13; x++) {
					world.spawnParticle(EnumParticleTypes.REDSTONE, posX + (world.rand.nextDouble() - 0.5D) * 0.5D - 0.3D, this.posY, this.posZ + (world.rand.nextDouble() - 0.5D) * 0.5D - 0.2D, 1D, 1D, 2D);
					world.spawnParticle(EnumParticleTypes.REDSTONE, posX + (world.rand.nextDouble() + 0.5D) * 0.5D - 0.3D, this.posY, this.posZ + (world.rand.nextDouble() + 0.5D) * 0.5D - 0.2D, 1D, 1D, 2D);
				}
			}
		}
		
		super.onLivingUpdate();
	}
	
	
	@Override
	public void onDeath(DamageSource cause) {
		super.onDeath(cause);
		if (!world.isRemote) {
			int index = rand.nextInt(deathItems.length);
			InventoryHelper.spawnItemStack(world, posX, posY, posZ, deathItems[index]);
		}
	}
	
	@Override
	protected void updateAITasks() {
		--this.heightOffsetUpdateTime;
		
		if (this.heightOffsetUpdateTime <= 0) {
			this.heightOffsetUpdateTime = 100;
			this.heightOffset = 0.5F + (float) this.rand.nextGaussian() * 3.0F;
		}
		
		EntityLivingBase entitylivingbase = this.getAttackTarget();
		
		if (entitylivingbase != null && entitylivingbase.posY + (double) entitylivingbase.getEyeHeight() > this.posY + (double) this.getEyeHeight() + (double) this.heightOffset) {
			this.motionY += (0.30000001192092896D - this.motionY) * 0.30000001192092896D;
			this.isAirBorne = true;
		}
		
		super.updateAITasks();
	}
	
	public void fall(float distance, float damageMultiplier) {
	}
	
	@Override
	public float getEyeHeight() {
		return 1F;
	}
}
