package net.tardis.mod.common.entities;

import net.minecraft.entity.*;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.monster.EntityIronGolem;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;

import javax.annotation.Nullable;

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
        this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, true));
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


    @Override
    public void onUpdate() {
        super.onUpdate();

    }

    @Override
    public boolean attackEntityAsMob(Entity entityIn) {
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
		ray.setPosition(posX, posY + this.getEyeHeight(), posZ);
		world.spawnEntity(ray);
	}

	@Override
	public void setSwingingArms(boolean swingingArms) {}
    
}