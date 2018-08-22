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
import net.tardis.mod.common.IDoor;
import net.tardis.mod.common.items.TItems;

public class EntityHellbentDoor extends Entity implements IDoor{

	public static final DataParameter<Boolean> IS_OPEN = EntityDataManager.createKey(EntityHellbentDoor.class, DataSerializers.BOOLEAN);
	
	public EntityHellbentDoor(World worldIn) {
		super(worldIn);
		this.setSize(2, 3);
	}

	@Override
	protected void entityInit() {
		this.dataManager.register(IS_OPEN, false);
	}

	@Override
	protected void readEntityFromNBT(NBTTagCompound compound) {
		this.getDataManager().set(IS_OPEN, compound.getBoolean("open"));
	}

	@Override
	protected void writeEntityToNBT(NBTTagCompound compound) {
		compound.setBoolean("open", this.dataManager.get(IS_OPEN));
	}
	
	public void setOpen(boolean b) {
		this.dataManager.set(IS_OPEN, b);
	}
	
	public boolean isOpen() {
		return this.dataManager.get(IS_OPEN);
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBox() {
		return this.isOpen() ? null : this.getEntityBoundingBox();
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
	public EnumActionResult applyPlayerInteraction(EntityPlayer player, Vec3d vec, EnumHand hand) {
		if(!world.isRemote)this.setOpen(this.isOpen() ? false : true);
		return EnumActionResult.SUCCESS;
	}

	@Override
	public boolean attackEntityFrom(DamageSource source, float amount) {
		if(!world.isRemote)InventoryHelper.spawnItemStack(world, posX, posY, posZ, new ItemStack(TItems.hellbent_door));
		this.setDead();
		return true;
	}

}
