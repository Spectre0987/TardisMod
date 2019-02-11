package net.tardis.mod.common.entities.hellbent;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.tardis.mod.common.items.TItems;

public class EntityHellbentCorridor extends Entity {

	public static final DataParameter<Boolean> IS_OPEN = EntityDataManager.createKey(EntityHellbentCorridor.class, DataSerializers.BOOLEAN);
	public AxisAlignedBB BB = new AxisAlignedBB(-2.5, 0, -2.5, 2.5, 10, 2.5);

	public EntityHellbentCorridor(World worldIn) {
		super(worldIn);
		this.setSize(2, 5);
	}

	@Override
	protected void entityInit() {
		this.dataManager.register(IS_OPEN, false);
	}

	@Override
	protected void readEntityFromNBT(NBTTagCompound compound) {
		this.dataManager.set(IS_OPEN, compound.getBoolean("open"));
	}

	@Override
	protected void writeEntityToNBT(NBTTagCompound compound) {
		compound.setBoolean("open", this.dataManager.get(IS_OPEN));
	}

	public boolean getOpen() {
		return this.getDataManager().get(IS_OPEN);
	}

	public void setOpen(boolean b) {
		this.getDataManager().set(IS_OPEN, b);
	}

	@Override
	public boolean attackEntityFrom(DamageSource source, float amount) {
		this.setDead();
		if (!world.isRemote)
			InventoryHelper.spawnItemStack(world, posX, posY, posZ, new ItemStack(TItems.hellbent_corridor));
		return true;
	}

	@Override
	public EnumActionResult applyPlayerInteraction(EntityPlayer player, Vec3d vec, EnumHand hand) {
		if (!world.isRemote) this.setOpen(!this.getOpen());
		return EnumActionResult.SUCCESS;
	}

	@Override
	public boolean canBeCollidedWith() {
		return true;
	}

	@Override
	public boolean canBePushed() {
		return true;
	}

	@Override
	public boolean canBeAttackedWithItem() {
		return super.canBeAttackedWithItem();
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBox() {
		return this.getOpen() ? null : this.getEntityBoundingBox();
	}

	@Override
	public AxisAlignedBB getCollisionBox(Entity entityIn) {
		return BB;
	}
}
