package net.tardis.mod.common.entities.controls;

import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.DimensionManager;
import net.tardis.mod.common.blocks.BlockTardisTop;
import net.tardis.mod.common.sounds.TSounds;
import net.tardis.mod.common.strings.TStrings;
import net.tardis.mod.common.tileentity.TileEntityDoor;
import net.tardis.mod.common.tileentity.TileEntityTardis;
import net.tardis.mod.util.TardisTeleporter;
import net.tardis.mod.util.helpers.Helper;

public class ControlDoor extends EntityControl {
	
	public static final DataParameter<Boolean> IS_OPEN = EntityDataManager.createKey(ControlDoor.class, DataSerializers.BOOLEAN);
	public int antiSpamTicks = 0;
	
	public ControlDoor(TileEntityTardis tardis) {
		super(tardis);
		this.setSize(1F, 2F);
	}
	
	public ControlDoor(World world) {
		super(world);
		this.setSize(1F, 2F);
	}
	
	@Override
	protected void entityInit() {
		super.entityInit();
		this.dataManager.register(IS_OPEN, false);
	}
	
	@Override
	public boolean canBePushed() {
		return false;
	}
	
	@Override
	public Vec3d getOffset() {
		return new Vec3d(0, -1, 6);
	}
	
	public void setOpen(boolean b) {
		this.dataManager.set(IS_OPEN, b);
	}
	
	public boolean isOpen() {
		return this.dataManager.get(IS_OPEN);
	}
	
	@Override
	public void preformAction(EntityPlayer player) {
		if (this.getConsolePos() != null) {
			TileEntityTardis tardis = (TileEntityTardis) world.getTileEntity(this.getConsolePos());
			if (!tardis.isInFlight()) {
				if (!player.isSneaking()) {
					this.setOpen(!this.isOpen());
					if (!world.isRemote) {
						if (this.isOpen())
							world.playSound(null, this.getPosition(), TSounds.door_open, SoundCategory.BLOCKS, 0.5F, 0.5F);
						else
							world.playSound(null, this.getPosition(), TSounds.door_closed, SoundCategory.BLOCKS, 0.5F, 0.5F);
						WorldServer ws = DimensionManager.getWorld(tardis.dimension);
						TileEntity te = ws.getTileEntity(tardis.getLocation().up());
						if (te instanceof TileEntityDoor) {
							((TileEntityDoor) te).setLocked(!this.isOpen());
							player.sendStatusMessage(new TextComponentTranslation(TStrings.TARDIS_LOCKED + !this.isOpen()), true);
						}
					}
				}
			} else {
				world.playSound(null, this.getPosition(), TSounds.door_locked, SoundCategory.BLOCKS, 1F, 1F);
			}
		}
	}
	
	@Override
	public void applyEntityCollision(Entity entityIn) {
		
	}
	
	@Override
	public void onUpdate() {
		super.onUpdate();
		if (antiSpamTicks > 0) --antiSpamTicks;
		if(!world.isRemote && this.isOpen()) {
			TileEntityTardis tardis = (TileEntityTardis) world.getTileEntity(getConsolePos());
			AxisAlignedBB bb = this.getEntityBoundingBox();
			WorldServer ws = DimensionManager.getWorld(tardis.dimension);
			if(ws.getBlockState(tardis.getLocation().up()).getBlock() instanceof BlockTardisTop) {
				List<Entity> entities = world.getEntitiesWithinAABB(Entity.class, bb);
				EnumFacing facing = ws.getBlockState(tardis.getLocation().up()).getValue(BlockTardisTop.FACING);
				BlockPos pos = tardis.getLocation().offset(facing, 2);
				for(Entity e : entities) {
					if(e instanceof EntityPlayerMP) {
						EntityPlayerMP mp = (EntityPlayerMP)e;
						ws.getMinecraftServer().getPlayerList().transferPlayerToDimension(mp, tardis.dimension, new TardisTeleporter());
						mp.connection.setPlayerLocation(pos.getX(),pos.getY(),pos.getZ(), Helper.get360FromFacing(facing), 0);
					}
				}
			}
		}
	}

	@Override
	public boolean canBeCollidedWith() {
		return true;
	}
	
}
