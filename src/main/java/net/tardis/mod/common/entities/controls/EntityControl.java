package net.tardis.mod.common.entities.controls;

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
import net.tardis.mod.common.tileentity.TileEntityTardis;

public abstract class EntityControl extends Entity implements IControl {
	
	public static final DataParameter<BlockPos> CONSOLE_POS = EntityDataManager.createKey(EntityControl.class, DataSerializers.BLOCK_POS);
	public static final DataParameter<Boolean> IS_GLOWING = EntityDataManager.createKey(EntityControl.class, DataSerializers.BOOLEAN);
	public int ticks = 0;
	public int direction = 1;
	
	public EntityControl(World worldIn) {
		super(worldIn);
		this.setSize(0.5F, 0.5F);
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
	public boolean processInitialInteract(EntityPlayer player, EnumHand hand) {
		this.preformAction(player);
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
		return false;
	}
	
	@Override
	public boolean canRenderOnFire() {
		return false;
	}
	
	@Override
	public abstract Vec3d getOffset();
	
	@Override
	public boolean isGlowing() {
		return false;
	}
	
	public abstract void preformAction(EntityPlayer player);
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound tag) {
		tag.setLong(NBT.CONSOLE_POS, this.getConsolePos().toLong());
		return super.writeToNBT(tag);
	}
	
	@Override
	public void readFromNBT(NBTTagCompound tag) {
		this.setConsolePos(BlockPos.fromLong(tag.getLong(NBT.CONSOLE_POS)));
		super.readFromNBT(tag);
	}
	
	public class NBT {
		public static final String CONSOLE_POS = "CONSOELPOS";
	}
	
	@Override
	protected void readEntityFromNBT(NBTTagCompound compound) {}
	
	@Override
	protected void writeEntityToNBT(NBTTagCompound compound) {}
	
	@Override
	public void onUpdate() {
		super.onUpdate();
		if (ticks > 0) --ticks;
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
	
}
