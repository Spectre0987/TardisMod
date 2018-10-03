package net.tardis.mod.common.entities;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.monster.EntityIronGolem;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.tardis.mod.common.sounds.TSounds;

public class EntityQuark extends EntityMob implements IRangedAttackMob {

    public EntityQuark(World worldIn) {
        super(worldIn);


    }

    protected void initEntityAI() {
        this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(5, new EntityAIMoveTowardsRestriction(this, 0.5D));
        this.tasks.addTask(7, new EntityAIWanderAvoidWater(this, 0.5D));
        this.tasks.addTask(8, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.tasks.addTask(8, new EntityAILookIdle(this));
        this.applyEntityAI();
    }

    private void applyEntityAI() {
        this.tasks.addTask(6, new EntityAIMoveThroughVillage(this, 0.5D, false));
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, true));
        this.targetTasks.addTask(3, new EntityAINearestAttackableTarget(this, EntityVillager.class, false));
        this.targetTasks.addTask(3, new EntityAINearestAttackableTarget(this, EntityIronGolem.class, true));
    }


    @Override
    public void onUpdate() {
        super.onUpdate();
    }

    @Override //TODO - Not just copy pasting the dalek shooting
    public void attackEntityWithRangedAttack(EntityLivingBase target, float distanceFactor) {
        Vec3d look = target.getPositionVector().subtract(this.getPositionVector());
        faceEntity(target, 30, 30);
        EntityDalekRay ball = new EntityDalekRay(world, this);
        ball.setPosition(posX + this.getLookVec().x, posY + this.getEyeHeight(), posZ + this.getLookVec().z);
        world.spawnEntity(ball);
        world.playSound(null, this.getPosition(), TSounds.dalek_ray, SoundCategory.HOSTILE, 1F, 1F);
        if (this.rand.nextInt(3) == 0)
            world.playSound(null, this.getPosition(), TSounds.dalek, SoundCategory.HOSTILE, 1F, 1F);
    }

    @Override
    public void setSwingingArms(boolean b) {
        //what even is this for
    }
}
