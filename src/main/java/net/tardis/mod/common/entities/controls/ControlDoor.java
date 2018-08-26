package net.tardis.mod.common.entities.controls;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
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
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.common.network.NetworkRegistry.TargetPoint;
import net.tardis.mod.Tardis;
import net.tardis.mod.client.worldshell.BlockStorage;
import net.tardis.mod.client.worldshell.IContainsWorldShell;
import net.tardis.mod.client.worldshell.MessageSyncWorldShell;
import net.tardis.mod.client.worldshell.PlayerStorage;
import net.tardis.mod.client.worldshell.WorldShell;
import net.tardis.mod.common.IDoor;
import net.tardis.mod.common.blocks.BlockTardisTop;
import net.tardis.mod.common.dimensions.TDimensions;
import net.tardis.mod.common.items.TItems;
import net.tardis.mod.common.sounds.TSounds;
import net.tardis.mod.common.tileentity.TileEntityTardis;
import net.tardis.mod.util.TardisTeleporter;
import net.tardis.mod.util.helpers.Helper;

public class ControlDoor extends Entity implements IContainsWorldShell, IDoor{
	
	public static final DataParameter<Boolean> IS_OPEN = EntityDataManager.createKey(ControlDoor.class, DataSerializers.BOOLEAN);
	public static final DataParameter<EnumFacing> FACING = EntityDataManager.createKey(ControlDoor.class, DataSerializers.FACING);
	public int antiSpamTicks = 0;
	private WorldShell shell = new WorldShell(BlockPos.ORIGIN);

	public ControlDoor(World world) {
		super(world);
		this.setSize(1F, 2F);
	}
	
	@Override
	protected void entityInit() {
		this.dataManager.register(IS_OPEN, false);
		this.dataManager.register(FACING, EnumFacing.NORTH);
	}
	
	public BlockPos getConsolePos() {
		BlockPos pos = BlockPos.ORIGIN;
		for(TileEntity te : world.loadedTileEntityList) {
			if(te instanceof TileEntityTardis && te.getPos().distanceSq(this.getPosition()) < Math.pow(40, 2)) {
				return te.getPos();
			}
		}
		return pos;
	}

	@Override
	public boolean attackEntityFrom(DamageSource source, float amount) {
		if(!world.isRemote) {
			this.setDead();
			InventoryHelper.spawnItemStack(world, posX, posY, posZ, new ItemStack(TItems.interior_door));
		}
		return true;
	}

	@Override
	public boolean canBePushed() {
		return false;
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
	
	public long getTime() {
		return 1l;
	}
	
	@Override
	public boolean processInitialInteract(EntityPlayer player, EnumHand hand) {
		if (this.getConsolePos() != null) {
			TileEntityTardis tardis = (TileEntityTardis) world.getTileEntity(this.getConsolePos());
			if (!player.isSneaking()) {
				this.setOpen(!this.isOpen());
				if (!world.isRemote) {
					if (this.isOpen())
						world.playSound(null, this.getPosition(), TSounds.door_open, SoundCategory.BLOCKS, 0.5F, 0.5F);
					else
						world.playSound(null, this.getPosition(), TSounds.door_closed, SoundCategory.BLOCKS, 0.5F, 0.5F);
				}
			}
		}
		return true;
	}

	@Override
	public void onUpdate() {
		super.onUpdate();
		if (antiSpamTicks > 0) --antiSpamTicks;
		if(!(world.getTileEntity(getConsolePos()) instanceof TileEntityTardis))return;
		TileEntityTardis tardis = (TileEntityTardis) world.getTileEntity(getConsolePos());
		if(tardis == null) return;
		if(!world.isRemote && this.isOpen()) {
			AxisAlignedBB bb = new AxisAlignedBB(0, 0, 0, 1, 2, 1).offset(this.getPosition());
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
                List<PlayerStorage> players = new ArrayList<PlayerStorage>();
				for(Entity e : ws.getEntitiesWithinAABB(Entity.class, Helper.createBB(tardis.getLocation().offset(facing, 10), 10))) {
					if(EntityList.getKey(e) != null) {
						NBTTagCompound tag = new NBTTagCompound();
						e.writeToNBT(tag);
						tag.setString("id", EntityList.getKey(e).toString());
						list.add(tag);
					}
					else if(e instanceof EntityPlayer) {
						players.add(new PlayerStorage((EntityPlayer)e));
					}
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
						if(entity instanceof EntityPlayer && entity.getPositionVector().distanceTo(this.getPositionVector()) <= 1) {
							if(!world.isRemote)tardis.transferPlayer((EntityPlayer)entity, false);
						}
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

	@Override
	protected void readEntityFromNBT(NBTTagCompound compound) {
		this.dataManager.set(IS_OPEN, compound.getBoolean("open"));
	}

	@Override
	protected void writeEntityToNBT(NBTTagCompound compound) {
		compound.setBoolean("open", this.dataManager.get(IS_OPEN));
	}
	
}
