package net.tardis.mod.controls;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.tardis.api.controls.IControl;
import net.tardis.mod.tileentity.TileEntityTardis;

public abstract class EntityControl extends Entity implements IControl{

	public static final DataParameter<BlockPos> CONSOLE_POS=EntityDataManager.createKey(EntityControl.class, DataSerializers.BLOCK_POS);
	public static final DataParameter<Boolean> IS_GLOWING=EntityDataManager.createKey(EntityControl.class, DataSerializers.BOOLEAN);
	public int ticks=0;
	public int direction=1;
	
	
	public EntityControl(World worldIn) {
		super(worldIn);
		this.setSize(0.5F,0.5F);
	}
	
	public EntityControl(TileEntityTardis tardis) {
		this(tardis.getWorld());
		this.dataManager.set(CONSOLE_POS, tardis.getPos().toImmutable());
	}

	public void setConsolePos(BlockPos pos) {
		this.dataManager.set(CONSOLE_POS, pos.toImmutable());
	}
	
	public BlockPos getConsolePos() {
		return this.dataManager.get(CONSOLE_POS);
	}
	
	public boolean getGlowing() {
		return this.dataManager.get(IS_GLOWING);
	}
	
	public void setGlowing(boolean glow) {
		this.dataManager.set(IS_GLOWING, glow);
	}
	@Override
	protected void entityInit() {
		this.dataManager.register(CONSOLE_POS, BlockPos.ORIGIN);
		this.dataManager.register(IS_GLOWING, false);
	}

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
			if(getConsolePos()==null||getConsolePos().equals(BlockPos.ORIGIN))
				this.setDead();
			if(getConsolePos()!=null&&world.getTileEntity(this.getConsolePos())==null)
				this.setDead();
		}
		if(ticks>0)
			--ticks;
		super.onUpdate();
	}


}
