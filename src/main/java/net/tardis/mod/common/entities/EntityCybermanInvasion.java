package net.tardis.mod.common.entities;

import javax.annotation.Nullable;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackRanged;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMoveThroughVillage;
import net.minecraft.entity.ai.EntityAIMoveTowardsRestriction;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWanderAvoidWater;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityIronGolem;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;

public class EntityCybermanInvasion extends EntityCyberman implements IRangedAttackMob
{

    public EntityCybermanInvasion(World worldIn)
    {
        super(worldIn);
        this.setSize(0.6F, 1.95F);
    }

    protected void initEntityAI()
    {
        this.tasks.addTask(0, new EntityAISwimming(this));
        //this.tasks.addTask(2, new EntityAIAttackMelee(this, 0.75D, false));
        this.tasks.addTask(2, new EntityAIAttackRanged(this, 1.0D, 40, 30F));
        this.tasks.addTask(5, new EntityAIMoveTowardsRestriction(this, 1.0D));
        this.tasks.addTask(7, new EntityAIWanderAvoidWater(this, 1.0D));
        this.tasks.addTask(8, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.tasks.addTask(8, new EntityAILookIdle(this));
        this.applyEntityAI();
    }

    protected void applyEntityAI()
    {
        this.tasks.addTask(6, new EntityAIMoveThroughVillage(this, 1.0D, false));
        this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, true, new Class[0]));
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, true));
        this.targetTasks.addTask(3, new EntityAINearestAttackableTarget(this, EntityVillager.class, false));
        this.targetTasks.addTask(3, new EntityAINearestAttackableTarget(this, EntityIronGolem.class, true));
    }

    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(35.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.23000000417232513D);
        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(3.0D);
        this.getEntityAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(2.0D);
    }

    protected void entityInit()
    {
        super.entityInit();
    }

    

    public boolean attackEntityAsMob(Entity entityIn)
    {
    	entityIn.attackEntityFrom(new EntityCyberman.DamageSourceCyber(), 2F);
       return true;
    }

    @Nullable
    public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, @Nullable IEntityLivingData livingdata)
    {
        return super.onInitialSpawn(difficulty, livingdata);
    }

	@Override
	public void attackEntityWithRangedAttack(EntityLivingBase target, float distanceFactor) {
		
		Vec3d targetPos = this.getPositionVector().subtract(target.getPositionVector());
		
		//EntityRayCyberman ray = new EntityRayCyberman(world, targetPos.x, targetPos.y, targetPos.z);
		EntityRayCyberman ray = new EntityRayCyberman(world, this);
		ray.setPosition(posX + this.getForward().x, posY + this.getEyeHeight(), posZ + this.getForward().z);
		world.spawnEntity(ray);
	}

	@Override
	public void setSwingingArms(boolean swingingArms) {}
    
}