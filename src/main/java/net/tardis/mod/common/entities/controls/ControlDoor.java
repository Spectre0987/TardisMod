package net.tardis.mod.common.entities.controls;

import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.tardis.mod.common.IDoor;
import net.tardis.mod.common.items.TItems;
import net.tardis.mod.common.sounds.TSounds;
import net.tardis.mod.common.tileentity.TileEntityDoor;
import net.tardis.mod.common.tileentity.TileEntityTardis;
import net.tardis.mod.util.common.helpers.TardisHelper;

public class ControlDoor extends Entity implements IDoor {

	public static final DataParameter<Boolean> IS_OPEN = EntityDataManager.createKey(ControlDoor.class, DataSerializers.BOOLEAN);
	public static final DataParameter<EnumFacing> FACING = EntityDataManager.createKey(ControlDoor.class, DataSerializers.FACING);
	public int antiSpamTicks = 0;

	public ControlDoor(World world) {
		super(world);
		this.setSize(1F, 2F);
	}

	@Override
	protected void entityInit() {
		this.dataManager.register(IS_OPEN, false);
		this.dataManager.register(FACING, EnumFacing.NORTH);
	}

	public TileEntityTardis getConsole() {
		return (TileEntityTardis) world.getTileEntity(TardisHelper.getTardisForPosition(getPosition()));
	}

	@Override
	public boolean attackEntityFrom(DamageSource source, float amount) {
		if (!world.isRemote) {
			this.setDead();
			InventoryHelper.spawnItemStack(world, posX, posY, posZ, new ItemStack(TItems.interior_door));
		}
		return true;
	}

	@Override
	public boolean canBePushed() {
		return false;
	}

	public EnumFacing getFacing() {
		return this.dataManager.get(FACING);
	}

	public void setFacing(EnumFacing facing) {
		this.dataManager.set(FACING, facing);
	}

	public boolean isOpen() {
		return this.dataManager.get(IS_OPEN);
	}

	public void setOpen(boolean b) {
		this.dataManager.set(IS_OPEN, b);
	}

	public long getTime() {
		return 1l;
	}

	@Override
	public boolean processInitialInteract(EntityPlayer player, EnumHand hand) {
		TileEntityTardis tardis = (TileEntityTardis) world.getTileEntity(TardisHelper.getTardisForPosition(this.getPosition()));
		if (tardis == null || tardis.isLocked()) return false;
		if (!player.isSneaking()) {
			boolean state = !this.isOpen();
			this.setOpen(state);
			this.setOtherDoors(state);
			if (!world.isRemote) {
				if (this.isOpen())
					world.playSound(null, this.getPosition(), TSounds.door_open, SoundCategory.BLOCKS, 0.5F, 0.5F);
				else
					world.playSound(null, this.getPosition(), TSounds.door_closed, SoundCategory.BLOCKS, 0.5F, 0.5F);
			}
		}
		return true;
	}

	@Override
	public void onUpdate() {
		super.onUpdate();
		if (antiSpamTicks > 0) --antiSpamTicks;
		TileEntity te = world.getTileEntity(TardisHelper.getTardisForPosition(this.getPosition()));
		if (te == null || !(te instanceof TileEntityTardis)) return;
		TileEntityTardis tardis = (TileEntityTardis) world.getTileEntity(TardisHelper.getTardisForPosition(this.getPosition()));
		if(world.getMinecraftServer() == null) return;
		if (!world.isRemote && this.isOpen()) {
			WorldServer ws = world.getMinecraftServer().getWorld(tardis.dimension);
			List<Entity> entities = world.getEntitiesWithinAABB(Entity.class, this.getEntityBoundingBox());
			for (Entity e : entities) {
				if (e == this || e instanceof IControl || e instanceof IDoor) continue;
				if (!e.isSneaking()) tardis.transferPlayer(e, true);
			}
		}
		if (tardis.isInFlight() && this.isOpen()) {
			AxisAlignedBB voidBB = new AxisAlignedBB(-10, -10, -10, 10, 10, 10).offset(this.getPosition());

			for (Entity entity : world.getEntitiesWithinAABB(Entity.class, voidBB)) {
				if (!entity.isDead) {
					Vec3d dir = entity.getPositionVector().subtract(this.getPositionVector()).normalize().scale(-1).scale(0.12);
					entity.motionX += dir.x;
					entity.motionY += dir.y;
					entity.motionZ += dir.z;
					if (entity instanceof EntityPlayer && entity.getPositionVector().distanceTo(this.getPositionVector()) <= 1) {
						if (!world.isRemote) tardis.transferPlayer(entity, false);
					}
				}
			}
		}
	}
	

	@Override
	public boolean canBeCollidedWith() {
		return true;
	}
	
	@Override
	public boolean shouldRenderInPass(int pass) {
		return true;
	}

	@Override
	protected void readEntityFromNBT(NBTTagCompound compound) {
		this.dataManager.set(IS_OPEN, compound.getBoolean("open"));
	}

	@Override
	protected void writeEntityToNBT(NBTTagCompound compound) {
		compound.setBoolean("open", this.dataManager.get(IS_OPEN));
	}
	
	public void setOtherDoors(boolean open) {
		if(!world.isRemote) {
			TileEntityTardis tardis = this.getConsole();
			if(tardis != null) {
				TileEntityDoor door = (TileEntityDoor)((WorldServer)world).getMinecraftServer().getWorld(tardis.dimension).getTileEntity(tardis.getLocation().up());
				if(door != null) {
					door.setLocked(!open);
				}
			}
		}
	}

}
