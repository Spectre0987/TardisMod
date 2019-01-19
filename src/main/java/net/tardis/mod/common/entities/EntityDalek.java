package net.tardis.mod.common.entities;


import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityFlying;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.tardis.mod.api.entities.IDontSufficate;
import net.tardis.mod.common.TDamageSources;
import net.tardis.mod.common.items.TItems;
import net.tardis.mod.common.sounds.TSounds;

public class EntityDalek extends EntityMob implements IRangedAttackMob, EntityFlying, IDontSufficate {

	private ItemStack[] deathItems = new ItemStack[]{new ItemStack(TItems.power_cell, 20 + rand.nextInt(11)), new ItemStack(TItems.gunstick, 1), new ItemStack(TItems.circuts, 7 + rand.nextInt(3))};

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

	@Override
	public void attackEntityWithRangedAttack(EntityLivingBase target, float distanceFactor) {
		faceEntity(target, 10, 30);
		EntityLaserRay laser = new EntityLaserRay(world, this, 7, TDamageSources.DALEK, new Vec3d(0, 1, 0));
		double x = target.posX - this.posX;
		double y = target.getEntityBoundingBox().minY + (double) (target.height / 3.0F) - laser.posY;
		double z = target.posZ - this.posZ;
		double d3 = (double) MathHelper.sqrt(x * x + z * z);
		laser.shoot(x, y + d3 * 0.20000000298023224D, z, 1.6F, (float) (14 - this.world.getDifficulty().getId() * 4));
		this.world.spawnEntity(laser);
		world.playSound(null, this.getPosition(), TSounds.dalek, SoundCategory.HOSTILE, 1F, 1F);
	}

	@Override
	public void setSwingingArms(boolean swingingArms) {
	}

	@Override
	protected void jump() {
		setNoGravity(true);
	}

	@Override
	public void onUpdate() {
		super.onUpdate();
		if (this.getAttackTarget() != null) {
			if (this.getAttackTarget().posY >= this.posY + 1 || this.isInWater() || isAirBorne) {
				this.setNoGravity(true);
				this.motionY = 0.02;
			} else if (this.hasNoGravity()) this.motionY = -0.02;
			this.fallDistance = 0;
			if (this.hasNoGravity() && this.getAttackTarget().getPositionVector().distanceTo(this.getPositionVector()) > 5) {
				Vec3d dir = this.getAttackTarget().getPositionVector().subtract(this.getPositionVector()).normalize().scale(0.3D);
				this.motionX = dir.x;
				this.motionZ = dir.z;
				this.faceEntity(this.getAttackTarget(), 3F, 1F);
			}
		}
		if (this.onGround || !this.isAirBorne) this.setNoGravity(false);
		if (this.rotationYaw - this.rotationYawHead > 90) {
			this.rotationYaw = this.rotationYawHead;
		}
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
	public float getEyeHeight() {
		return 1F;
	}
}
