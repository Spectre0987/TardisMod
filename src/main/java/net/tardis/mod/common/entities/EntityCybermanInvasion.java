package net.tardis.mod.common.entities;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.monster.EntityIronGolem;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class EntityCybermanInvasion extends EntityCyberman implements IRangedAttackMob
{

    public EntityCybermanInvasion(World worldIn)
    {
        super(worldIn);
        setSize(0.6F, 1.95F);
    }

    protected void initEntityAI()
    {
        tasks.addTask(2, new EntityAIAttackRanged(this, 1.0D, 100, 30F));
        tasks.addTask(5, new EntityAIMoveTowardsRestriction(this, 1.0D));
        tasks.addTask(7, new EntityAIWanderAvoidWater(this, 1.0D));
        tasks.addTask(8, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        tasks.addTask(8, new EntityAILookIdle(this));
        applyEntityAI();
    }

    protected void applyEntityAI()
    {
        tasks.addTask(6, new EntityAIMoveThroughVillage(this, 1.0D, false));
        targetTasks.addTask(1, new EntityAIHurtByTarget(this, true));
        targetTasks.addTask(2, new EntityAINearestAttackableTarget<>(this, EntityPlayer.class, true));
        targetTasks.addTask(3, new EntityAINearestAttackableTarget<>(this, EntityVillager.class, false));
        targetTasks.addTask(3, new EntityAINearestAttackableTarget<>(this, EntityIronGolem.class, true));
    }

    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(35.0D);
        getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.23000000417232513D);
        getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(3.0D);
        getEntityAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(2.0D);
    }

    protected void entityInit()
    {
        super.entityInit();
    }


    @Override
    public void onUpdate() {
        super.onUpdate();
    }

    @Override
    public boolean attackEntityAsMob(Entity entityIn) {
        entityIn.attackEntityFrom(new EntityCyberman.DamageSourceCyber(), 2F);
        return true;
    }

	@Override
	public boolean getCanSpawnHere() {
        return !world.canBlockSeeSky(this.getPosition().down()) && posY < 64 && world.getBlockState(getPosition().down()).getLightValue() < 2;
    }

    /**
     * Attack the specified entity using a ranged attack.
     *
     * @param target
     * @param distanceFactor
     */
    @Override
    public void attackEntityWithRangedAttack(EntityLivingBase target, float distanceFactor) {
        faceEntity(target, 10, 30);
        EntityLaserRay laser = new EntityLaserRay(world, this, 7, new DamageSourceCyber(), new Vec3d(0, 0, 1));
        double x = target.posX - this.posX;
        double y = target.getEntityBoundingBox().minY + (double) (target.height / 3.0F) - laser.posY;
        double z = target.posZ - this.posZ;
        double d3 = (double) MathHelper.sqrt(x * x + z * z);
        laser.shoot(x, y + d3 * 0.20000000298023224D, z, 1.6F, (float) (14 - this.world.getDifficulty().getId() * 4));
        this.world.spawnEntity(laser);
    }

	@Override
	public void setSwingingArms(boolean swingingArms) {}
    
}