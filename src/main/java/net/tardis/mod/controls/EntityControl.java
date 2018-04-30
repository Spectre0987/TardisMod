package net.tardis.mod.controls;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.tardis.api.controls.IControl;
import net.tardis.mod.tileentity.TileEntityTardis;

public abstract class EntityControl extends Entity implements IControl{

	public BlockPos tardisPos;
	public int ticks=0;
	public int direction=1;
	
	
	public EntityControl(World worldIn) {
		super(worldIn);
		this.setSize(0.5F,0.5F);
	}
	
	public EntityControl(TileEntityTardis tardis) {
		this(tardis.getWorld());
		tardisPos=tardis.getPos();
	}

	@Override
	protected void entityInit() {}

	@Override
	protected void readEntityFromNBT(NBTTagCompound tag) {}

	@Override
	public boolean processInitialInteract(EntityPlayer player, EnumHand hand) {
		this.preformAction(player);
		return true;
	}

	@Override
	protected void writeEntityToNBT(NBTTagCompound tag) {}

	@Override
	public boolean canBeCollidedWith() {
		return true;
	}

	@Override
	public boolean canBePushed() {
		return false;
	}

	@Override
	protected boolean canBeRidden(Entity entityIn) {
		return false;
	}

	@Override
	public boolean canBeAttackedWithItem() {
		return false;
	}

	@Override
	public boolean canRenderOnFire() {
		return false;
	}
	
	@Override
	public abstract Vec3d getOffset();

	@Override
	public abstract Vec3d getRotation();
	
	@Override
	public boolean isGlowing() {
		return false;
	}
	
	public abstract void preformAction(EntityPlayer player);
	
	@Override
	public void onUpdate() {
		if(!world.isRemote) {
			if(tardisPos==null||tardisPos.equals(BlockPos.ORIGIN))this.setDead();
			if(tardisPos!=null&&world.getTileEntity(this.tardisPos)==null)this.setDead();
		}
		if(ticks>0)--ticks;
		super.onUpdate();
	}


}
