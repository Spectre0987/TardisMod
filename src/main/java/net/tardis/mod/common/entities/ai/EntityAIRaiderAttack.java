package net.tardis.mod.common.entities.ai;

import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.monster.EntityZombie;
import net.tardis.mod.common.entities.EntityRaider;

public class EntityAIRaiderAttack extends EntityAIAttackMelee
{
    private final EntityRaider raider;

    public EntityAIRaiderAttack(EntityRaider raiderIn, double speedIn, boolean longMemoryIn)
    {
        super(raiderIn, speedIn, longMemoryIn);
        this.raider = raiderIn;
    }

    /**
     * Execute a one shot task or start executing a continuous task
     */
    public void startExecuting()
    {
        super.startExecuting();
    }

    /**
     * Reset the task's internal state. Called when this task is interrupted by another one
     */
    public void resetTask()
    {
        super.resetTask();
    }

    /**
     * Keep ticking a continuous task that has already been started
     */
    public void updateTask()
    {
        super.updateTask();
    }
}