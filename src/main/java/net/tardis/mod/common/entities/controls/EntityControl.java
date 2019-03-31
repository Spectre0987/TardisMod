package net.tardis.mod.common.entities.controls;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.tardis.mod.common.sounds.TSounds;
import net.tardis.mod.common.tileentity.TileEntityTardis;
import net.tardis.mod.common.tileentity.TileEntityTardis.EnumCourseCorrect;
import net.tardis.mod.config.TardisConfig;

public abstract class EntityControl extends Entity implements IControl {

	public static final DataParameter<BlockPos> CONSOLE_POS = EntityDataManager.createKey(EntityControl.class, DataSerializers.BLOCK_POS);
	public static final DataParameter<Boolean> IS_GLOWING = EntityDataManager.createKey(EntityControl.class, DataSerializers.BOOLEAN);
	public static SoundEvent[] rand_sounds = {TSounds.control_generic_01};
	public int ticks = 0;
	public int direction = 1;
	public AxisAlignedBB box = null;

	public EntityControl(World worldIn) {
		super(worldIn);
	}

	public EntityControl(TileEntityTardis tardis) {
		super(tardis.getWorld());
		this.dataManager.set(CONSOLE_POS, tardis.getPos().toImmutable());
	}

	public BlockPos getConsolePos() {
		return this.dataManager.get(CONSOLE_POS);
	}

	public void setConsolePos(BlockPos pos) {
		this.dataManager.set(CONSOLE_POS, pos.toImmutable());
	}

	public boolean getGlowing() {
		return this.dataManager.get(IS_GLOWING);
	}

	@Override
	protected void entityInit() {
		this.dataManager.register(CONSOLE_POS, BlockPos.ORIGIN);
		this.dataManager.register(IS_GLOWING, false);
	}

	@Override
	public boolean processInitialInteract(EntityPlayer player, EnumHand hand) {
		TileEntityTardis tardis = (TileEntityTardis) world.getTileEntity(getConsolePos());
		if (!tardis.isInFlight()) preformAction(player);
		else if (tardis.getCourseCorrect() != EnumCourseCorrect.NONE && this.getClass() == tardis.getCourseCorrect().getControl()) {
			tardis.setCourseEvent(EnumCourseCorrect.NONE);
		} else preformAction(player);
		world.playSound(null, getPosition().getX(), getPosition().getY(), getPosition().getZ(), getUseSound(), SoundCategory.BLOCKS, 1.0F, 1.0F);
		return true;
	}

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
		return true;
	}

	@Override
	public boolean canRenderOnFire() {
		return false;
	}

	@Override
	public abstract Vec3d getOffset(TileEntityTardis tardis);

	@Override
	public boolean isGlowing() {
		return false;
	}

	public void setGlowing(boolean glow) {
		this.dataManager.set(IS_GLOWING, glow);
	}

	public abstract void preformAction(EntityPlayer player);

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound tag) {
		return super.writeToNBT(tag);
	}

	@Override
	public void readFromNBT(NBTTagCompound tag) {
		super.readFromNBT(tag);
	}

	@Override
	protected void readEntityFromNBT(NBTTagCompound compound) {
	}

	@Override
	protected void writeEntityToNBT(NBTTagCompound compound) {
	}

	@Override
	public void onUpdate() {
		if (this.firstUpdate) {
			init((TileEntityTardis) world.getTileEntity(this.getConsolePos()));
		}
		super.onUpdate();
		if (ticks > 0) --ticks;
		if (!this.world.isRemote) {
			if (this.getConsolePos() == null || this.getConsolePos().equals(BlockPos.ORIGIN))
				this.setDead();
		}
	}

	@Override
	public boolean isInRangeToRender3d(double x, double y, double z) {
		return true;
	}

	@Override
	public boolean isInRangeToRenderDist(double distance) {
		return true;
	}

	@Override
	public boolean isInvisibleToPlayer(EntityPlayer player) {
		return false;
	}

	@Override
	public boolean attackEntityFrom(DamageSource source, float amount) {
		if(source.getTrueSource() instanceof EntityPlayer)
			this.processInitialInteract((EntityPlayer)source.getTrueSource(), EnumHand.MAIN_HAND);
		return true;
	}

	@Override
	public String getControlName() {
		return this.getDisplayName().getUnformattedText();
	}

	@Override
	public void onKillCommand() {
		if (TardisConfig.MISC.killControlsOnKillCommand) {
			super.onKillCommand();
		}
	}

	@Override
	public SoundEvent getUseSound() {
		return rand_sounds[rand.nextInt(rand_sounds.length)];
	}

	public void init(TileEntityTardis tardis) {
	}

}
