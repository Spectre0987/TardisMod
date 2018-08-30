package net.tardis.mod.common.entities;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.pathfinding.PathNavigateGround;
import net.minecraft.pathfinding.PathNodeType;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;


public class EntityAdipose extends EntityMob {

    public EntityAdipose(World worldIn) {
        super(worldIn);
        this.setSize(0.6F, 0.85F);
    }

    @Override
    protected void initEntityAI() {
        setPathPriority(PathNodeType.WATER, -1.0F);
        setMoveForward(0);
        setAIMoveSpeed(0.2F);

        this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(1, new EntityAIPanic(this, 0.4F));
        this.tasks.addTask(2, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.tasks.addTask(3, new EntityAILookIdle(this));
        this.tasks.addTask(2, new EntityAIWanderAvoidWater(this, 0.2F));
    }

    @Override
    public EnumActionResult applyPlayerInteraction(EntityPlayer player, Vec3d vec, EnumHand hand) {
        if (!player.isBeingRidden() && !player.isRiding()){
            this.startRiding(player);
            return EnumActionResult.SUCCESS;
        }
        return EnumActionResult.FAIL;
    }

    @Override
    protected PathNavigate createNavigator(World worldIn) {
        final PathNavigateGround navigator = new PathNavigateGround(this, worldIn);
        navigator.setCanSwim(true);
        return navigator;
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(10.0D);
    }

    @Override
    protected void dropLoot(boolean wasRecentlyHit, int lootingModifier, DamageSource source) {
        ItemStack[] drops = new ItemStack[] {new ItemStack(Items.SLIME_BALL, 2)};
        for(ItemStack s : drops) {
            if(!world.isRemote) {
                InventoryHelper.spawnItemStack(world, posX, posY, posZ, s);
            }
        }
    }

    @Override
    public void updateRidden() {
        super.updateRidden();
        if (this.getRidingEntity().isSneaking()){
            this.dismountRidingEntity();
        }
        else {
            //Vec3d vec = this.getRidingEntity().getLookVec().add(this.getRidingEntity().getPositionVector()).addVector(0, this.getRidingEntity().getMountedYOffset()+0.3f,-0.7F);
            Vec3d vec = this.getRidingEntity().getPositionVector().addVector(0,1.9,0);
            this.setPositionAndRotation(vec.x, vec.y, vec.z,this.getRidingEntity().getRotationYawHead(),this.getRidingEntity().rotationPitch);
        }
    }
}
