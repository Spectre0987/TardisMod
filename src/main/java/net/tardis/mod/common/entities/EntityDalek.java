package net.tardis.mod.common.entities;

import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.tardis.mod.common.items.TItems;
import net.tardis.mod.common.sounds.TSounds;

public class EntityDalek extends EntityMob implements IRangedAttackMob {


	private ItemStack[] deathItems = new ItemStack[]{new ItemStack(TItems.power_cell, 20 + rand.nextInt(11)), new ItemStack(TItems.gunstick, 1), new ItemStack(TItems.circuts, 7 + rand.nextInt(3))};


	public EntityDalek(World world) {
		super(world);
	}
	
	@Override
	protected void initEntityAI() {
		super.initEntityAI();
		this.tasks.addTask(1, new EntityAILookIdle(this));
		this.tasks.addTask(0, new EntityAIWatchClosest(this, EntityLivingBase.class, 30.0F));
		this.tasks.addTask(0, new EntityAIAttackRanged(this, 0.023D, 60, 30F));
		this.targetTasks.addTask(0, new EntityAIMoveTowardsTarget(this, 0.023D, 30));
		this.targetTasks.addTask(0, new EntityAINearestAttackableTarget(this, EntityLivingBase.class, 100, true, false, input -> !(input instanceof EntityDalek)));
	}
	
	@Override
	public void attackEntityWithRangedAttack(EntityLivingBase target, float distanceFactor) {
		Vec3d look = target.getPositionVector().subtract(this.getPositionVector());
		EntityDalekRay ball = new EntityDalekRay(world, this);
		ball.setPosition(posX + this.getLookVec().x, posY + this.getEyeHeight(), posZ + this.getLookVec().z);
		world.spawnEntity(ball);
		world.playSound(null, this.getPosition(), TSounds.dalek_ray, SoundCategory.HOSTILE, 1F, 1F);
		world.playSound(null, this.getPosition(), TSounds.dalek, SoundCategory.HOSTILE, 1F, 1F);
	}
	
	@Override
	public void setSwingingArms(boolean swingingArms) {
		
	}


	@Override
	public void onUpdate() {
		super.onUpdate();

		if (world.getBlockState(getPosition()).getMaterial() == Material.WATER) {
			BlockPos pos = getPosition().up();
			setPositionAndUpdate(pos.getX(), pos.getY(), pos.getZ());
		}
	}

	@Override
	public void onDeath(DamageSource cause) {
		super.onDeath(cause);
		int index = rand.nextInt(deathItems.length);
		if (!world.isRemote) {
			InventoryHelper.spawnItemStack(world, posX, posY, posZ, deathItems[index]);
		}
	}
	
}
