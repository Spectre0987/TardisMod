package net.tardis.mod.entities;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.ai.EntityAIMoveTowardsTarget;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.tardis.mod.dimensions.TDimensions;
import net.tardis.mod.items.TItems;
import net.tardis.mod.tileentity.TileEntityDoor;

public class EntityAngel extends EntityMob{

	public boolean shouldMove=true;
	int ticks=0;
	
	public EntityAngel(World worldIn) {
		super(worldIn);
		this.isImmuneToFire=true;
	}

	@Override
	protected void initEntityAI() {
		super.initEntityAI();
        //this.tasks.addTask(1, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        //this.tasks.addTask(3, new EntityAILookIdle(this));
        this.tasks.addTask(1, new EntityAIAttackMelee(this,0.2F,true));
        this.targetTasks.addTask(0, new EntityAINearestAttackableTarget(this, EntityPlayer.class,false));
        this.tasks.addTask(0, new EntityAIMoveTowardsTarget(this,0.4F,120));
	}
	
	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.5D);
		this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(10D);
		this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(120.0D);
	}

	@Override
	public void onLivingUpdate() {
		if(shouldMove)super.onLivingUpdate();
		++ticks;
		if(ticks>5) {
			ticks=0;
			this.shouldMove=true;
			if(!world.isRemote) {
				for(TileEntity te:world.loadedTileEntityList) {
					if(this.getPosition().distanceSq(te.getPos())<16) {
						if(te instanceof TileEntityDoor&&!((TileEntityDoor)te).isLocked){
							TileEntityDoor door=(TileEntityDoor)te;
							BlockPos pos=door.consolePos.south(5);
							this.setPosition(pos.getX(),pos.getY(),pos.getZ());
							this.changeDimension(TDimensions.id);
						}
					}
					if(this.getAttackTarget()==null) {
						if(this.getPosition().distanceSq(te.getPos())<400) {
							this.moveHelper.setMoveTo(te.getPos().getX(),te.getPos().getY(),te.getPos().getZ(),1.0F);
						}
					}
				}
				if(world.isDaytime())this.setDead();
			}
		}
	}

	@Override
	public boolean attackEntityFrom(DamageSource source, float amount) {
		if(this.shouldMove)return super.attackEntityFrom(source, amount);
		return false;
	}

	@Override
	protected Item getDropItem() {
		return TItems.key;
	}

}
