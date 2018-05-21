package net.tardis.mod.common.entities.controls;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.DimensionManager;
import net.tardis.mod.common.blocks.BlockTardisTop;
import net.tardis.mod.common.sounds.TSounds;
import net.tardis.mod.common.tileentity.TileEntityDoor;
import net.tardis.mod.common.tileentity.TileEntityTardis;
import net.tardis.mod.util.TardisTeleporter;

public class ControlDoor extends EntityControl {
	
	public static final DataParameter<Boolean> IS_OPEN=EntityDataManager.createKey(ControlDoor.class, DataSerializers.BOOLEAN);
	
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
		return true;
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
					this.setOpen(this.isOpen() ? false : true);
				} else if(!world.isRemote){
					WorldServer ws = ((WorldServer) world).getMinecraftServer().getWorld(tardis.dimension);
					((TileEntityDoor) ws.getTileEntity(tardis.getLocation().up())).toggleLockedNoKey(player);
				}
			}
			else {
				world.playSound(null, this.getPosition(), TSounds.door_locked, SoundCategory.BLOCKS, 1F, 1F);
			}
		}
	}
	
	@Override
	public void applyEntityCollision(Entity entityIn) {
		if(!world.isRemote && this.isOpen()) {
			TileEntityTardis tardis=(TileEntityTardis)world.getTileEntity(getConsolePos());
			if(entityIn instanceof EntityPlayer) {
				WorldServer ws=DimensionManager.getWorld(tardis.dimension);
				IBlockState state=ws.getBlockState(tardis.getLocation().up());
				if(state.getBlock() instanceof BlockTardisTop) {
					BlockPos pos=tardis.getLocation().offset(state.getValue(BlockTardisTop.FACING), 1);
					entityIn.setPositionAndUpdate(pos.getX()-0.5,pos.getY(),pos.getZ()+0.5);
					ws.getMinecraftServer().getPlayerList().transferPlayerToDimension((EntityPlayerMP)entityIn, tardis.dimension, new TardisTeleporter(ws));
				}
				else ((EntityPlayer)entityIn).sendMessage(new TextComponentTranslation("tardis.missing"));
			}
		}
	}

}
