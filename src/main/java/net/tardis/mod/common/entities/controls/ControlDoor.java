package net.tardis.mod.common.entities.controls;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec3i;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.fml.common.network.NetworkRegistry.TargetPoint;
import net.tardis.mod.Tardis;
import net.tardis.mod.client.worldshell.BlockStorage;
import net.tardis.mod.client.worldshell.IContainsWorldShell;
import net.tardis.mod.client.worldshell.MessageSyncWorldShell;
import net.tardis.mod.client.worldshell.PlayerStorage;
import net.tardis.mod.client.worldshell.WorldShell;
import net.tardis.mod.common.blocks.BlockTardisTop;
import net.tardis.mod.common.dimensions.TDimensions;
import net.tardis.mod.common.sounds.TSounds;
import net.tardis.mod.common.strings.TStrings;
import net.tardis.mod.common.tileentity.TileEntityDoor;
import net.tardis.mod.common.tileentity.TileEntityTardis;
import net.tardis.mod.util.TardisTeleporter;
import net.tardis.mod.util.helpers.Helper;

public class ControlDoor extends EntityControl implements IContainsWorldShell{
	
	public static final DataParameter<Boolean> IS_OPEN = EntityDataManager.createKey(ControlDoor.class, DataSerializers.BOOLEAN);
	public static final DataParameter<EnumFacing> FACING = EntityDataManager.createKey(ControlDoor.class, DataSerializers.FACING);
	public static final DataParameter<NBTTagCompound> WORLD_TIME = EntityDataManager.createKey(ControlDoor.class, DataSerializers.COMPOUND_TAG);
	public int antiSpamTicks = 0;
	private WorldShell shell = new WorldShell(BlockPos.ORIGIN);
	
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
		this.dataManager.register(FACING, EnumFacing.NORTH);
		this.dataManager.register(WORLD_TIME, new NBTTagCompound());
	}
	
	@Override
	public boolean canBePushed() {
		return false;
	}
	
	@Override
	public Vec3d getOffset(TileEntityTardis tardis) {
		return new Vec3d(0, -1, 6);
	}
	
	public void setFacing(EnumFacing facing) {
		this.dataManager.set(FACING, facing);
	}
	
	public EnumFacing getFacing() {
		return this.dataManager.get(FACING);
	}
	
	public void setOpen(boolean b) {
		this.dataManager.set(IS_OPEN, b);
	}
	
	public boolean isOpen() {
		return this.dataManager.get(IS_OPEN);
	}
	
	public void setTime(long time) {
		NBTTagCompound tag = this.dataManager.get(WORLD_TIME);
		tag.setLong("time", time);
		this.dataManager.set(WORLD_TIME, tag);
		this.dataManager.setDirty(WORLD_TIME);
	}
	
	public long getTime() {
		return this.dataManager.get(WORLD_TIME).getLong("time");
	}
	@Override
	public void preformAction(EntityPlayer player) {
		if (this.getConsolePos() != null) {
			TileEntityTardis tardis = (TileEntityTardis) world.getTileEntity(this.getConsolePos());
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
		}
	}
	
	@Override
	public void applyEntityCollision(Entity entityIn) {
		
	}
	
	@Override
	public void onUpdate() {
		super.onUpdate();
		if (antiSpamTicks > 0) --antiSpamTicks;
		TileEntityTardis tardis = (TileEntityTardis) world.getTileEntity(getConsolePos());
		if(!world.isRemote && this.isOpen()) {
			AxisAlignedBB bb = new AxisAlignedBB(0, 0, 0.9, 1, 2, 1).offset(this.getPosition());
			WorldServer ws = ((WorldServer)world).getMinecraftServer().getWorld(tardis.dimension);
			if(ws.getBlockState(tardis.getLocation().up()).getBlock() instanceof BlockTardisTop) {
				List<Entity> entities = world.getEntitiesWithinAABB(Entity.class, bb);
				EnumFacing facing = ws.getBlockState(tardis.getLocation().up()).getValue(BlockTardisTop.FACING);
				BlockPos pos = tardis.getLocation().offset(facing, 2);
				for(Entity e : entities) {
					if(e instanceof EntityPlayerMP) {
						EntityPlayerMP mp = (EntityPlayerMP)e;
						if(!mp.isSneaking()) {
							if(tardis.dimension != TDimensions.TARDIS_ID)
								ws.getMinecraftServer().getPlayerList().transferPlayerToDimension(mp, tardis.dimension, new TardisTeleporter());
							mp.connection.setPlayerLocation(pos.getX() + 0.5,pos.getY(),pos.getZ() + 0.5, Helper.get360FromFacing(facing), 0);
							mp.setSpawnPoint(pos, true);
						}
					}
					else if(e != this){
						e.setPosition(pos.getX(), pos.getY(), pos.getZ());
						e.changeDimension(tardis.dimension, new TardisTeleporter());
					}
				}
			}
			if(this.ticksExisted % 5 == 0) {
				this.shell = new WorldShell(tardis.getLocation().up().offset(this.getFacing(), 11));
				Vec3i r = new Vec3i(10, 10, 10);
				IBlockState doorState = ws.getBlockState(tardis.getLocation().up());
				EnumFacing facing = EnumFacing.NORTH;
				if(doorState != null && doorState.getBlock() instanceof BlockTardisTop) {
					facing = doorState.getValue(BlockTardisTop.FACING);
				}
				for(BlockPos pos : BlockPos.getAllInBox(shell.getOffset().subtract(r), shell.getOffset().add(r))) {
					IBlockState state = ws.getBlockState(pos);
					if(state.getBlock() != Blocks.AIR && !(state.getBlock() instanceof BlockTardisTop)) {
						this.shell.blockMap.put(pos, new BlockStorage(state, ws.getTileEntity(pos), ws.getLight(pos)));
					}
				}
				this.setFacing(facing);
                List<NBTTagCompound> list = new ArrayList<>();
				for(Entity e : ws.getEntitiesWithinAABB(Entity.class, Helper.createBB(tardis.getLocation().offset(facing, 10), 10))) {
					if(EntityList.getKey(e) != null) {
						NBTTagCompound tag = new NBTTagCompound();
						e.writeToNBT(tag);
						tag.setString("id", EntityList.getKey(e).toString());
						list.add(tag);
					}
				}
				List<PlayerStorage> players = new ArrayList<PlayerStorage>();
				for(EntityPlayer player : ws.getEntitiesWithinAABB(EntityPlayer.class, Helper.createBB(tardis.getLocation().offset(facing, 10), 10))) {
					players.add(new PlayerStorage(player));
				}
				shell.setPlayers(players);
				shell.setEntities(list);
				Tardis.NETWORK.sendToAllAround(new MessageSyncWorldShell(shell, this.getEntityId()), new TargetPoint(world.provider.getDimension(), posX, posY, posZ, 16D));
			}
		}
		try {
			if(tardis.isInFlight() && this.isOpen()) {
				AxisAlignedBB voidBB = new AxisAlignedBB(-10, -10, -10, 10, 10, 10).offset(this.getPosition());
				
				for(Entity entity : world.getEntitiesWithinAABB(Entity.class, voidBB)) {
					if(!entity.isDead) {
						Vec3d dir = entity.getPositionVector().subtract(this.getPositionVector()).normalize().scale(-1).scale(0.12);
						entity.motionX += dir.x;
						entity.motionY += dir.y;
						entity.motionZ += dir.z;
					}
				}
			}
		}
		catch(Exception e) {}
	}

	@Override
	public boolean canBeCollidedWith() {
		return true;
	}

	@Override
	public WorldShell getWorldShell() {
		return this.shell;
	}

	@Override
	public void setWorldShell(WorldShell worldShell) {
		this.shell = worldShell;
	}

	@Override
	public boolean shouldRenderInPass(int pass) {
		return true;
	}
	
}
