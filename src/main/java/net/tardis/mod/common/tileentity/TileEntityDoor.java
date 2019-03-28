package net.tardis.mod.common.tileentity;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.IProjectile;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.fml.common.network.NetworkRegistry.TargetPoint;
import net.tardis.mod.client.worldshell.BlockStorage;
import net.tardis.mod.client.worldshell.IContainsWorldShell;
import net.tardis.mod.client.worldshell.MessageSyncWorldShell;
import net.tardis.mod.client.worldshell.MessageSyncWorldShell.EnumType;
import net.tardis.mod.client.worldshell.WorldShell;
import net.tardis.mod.common.IDoor;
import net.tardis.mod.common.TDamageSources;
import net.tardis.mod.common.blocks.BlockTardisTop;
import net.tardis.mod.common.dimensions.TDimensions;
import net.tardis.mod.common.entities.controls.ControlDoor;
import net.tardis.mod.common.enums.EnumTardisState;
import net.tardis.mod.common.sounds.TSounds;
import net.tardis.mod.common.strings.TStrings;
import net.tardis.mod.network.NetworkHandler;
import net.tardis.mod.network.packets.MessageDemat;
import net.tardis.mod.network.packets.MessageDoorOpen;
import net.tardis.mod.network.packets.MessageRequestBOTI;
import net.tardis.mod.util.TardisTeleporter;
import net.tardis.mod.util.common.helpers.TardisHelper;

public class TileEntityDoor extends TileEntity implements ITickable, IInventory, IContainsWorldShell {

	public static int radius = 20;
	public static AxisAlignedBB aabb = new AxisAlignedBB(0.25, 0, 0.25, 0.75, 2, 0.75);
	private static AxisAlignedBB RENDER_BB = new AxisAlignedBB(-5, -5, -5, 5, 5, 5);
	public BlockPos consolePos = BlockPos.ORIGIN;
	public SoundEvent knockSound = SoundEvents.ENTITY_ZOMBIE_ATTACK_DOOR_WOOD;
	public boolean isLocked = true;
	public int lockCooldown = 0;
	public boolean forceField = false;
	public int openingTicks = 0;
	public float alpha = 1;
	public boolean isDemat, isRemat = false;
	private int updateTicks = 0;
	private WorldShell worldShell = new WorldShell(BlockPos.ORIGIN);
	private int lightLevel = 0;
	//The rotation to render the interior as
	private float renderAngle = 90;
	
	public static final AxisAlignedBB NORTH = new AxisAlignedBB(0, 0, -0.1, 1, 2, 0);
	public static final AxisAlignedBB EAST = new AxisAlignedBB(1, 0, 0, 1.1, 2, 1);
	public static final AxisAlignedBB WEST = new AxisAlignedBB(-0.1, 0, 0, 0, 2, 1);
	public static final AxisAlignedBB SOUTH = new AxisAlignedBB(0, 0, 1, 1, 2, 1.1);

	public TileEntityDoor() {
		this.isRemat = true;
		this.alpha = 0.0F;
	}

	@Override
	public void readFromNBT(NBTTagCompound tag) {
		super.readFromNBT(tag);
		consolePos = BlockPos.fromLong(tag.getLong("tPos"));
		isLocked = tag.getBoolean("locked");
		this.isDemat = tag.getBoolean("demat");
		this.isRemat = tag.getBoolean("remat");
		this.alpha = tag.getFloat("alpha");
		this.forceField = tag.getBoolean("forcefield");
		this.renderAngle = tag.getFloat("renderAngle");
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound tag) {
		tag.setLong("tPos", consolePos.toLong());
		tag.setBoolean("locked", isLocked);
		tag.setBoolean("demat", this.isDemat);
		tag.setBoolean("remat", this.isRemat);
		tag.setFloat("alpha", this.alpha);
		tag.setBoolean("forcefield", forceField);
		tag.setFloat("renderAngle", renderAngle);
		return super.writeToNBT(tag);
	}

	public void toggleLocked(EntityPlayer player) {
		if (!world.isRemote) {
			TileEntityTardis tardis = (TileEntityTardis) world.getMinecraftServer().getWorld(TDimensions.TARDIS_ID).getTileEntity(getConsolePos());
			if (tardis != null && tardis.getTardisState() == EnumTardisState.NORMAL) {
				if (TardisHelper.hasValidKey(player, consolePos) && lockCooldown == 0 && alpha >= 1.0F && !tardis.isLocked()) {
					lockCooldown = 20;
					isLocked = !isLocked;
					this.markDirty();
					NetworkHandler.NETWORK.sendToDimension(new MessageDoorOpen(this.getPos(), this), world.provider.getDimension());
					if (isLocked)
						world.playSound(null, getPos(), TSounds.door_closed, SoundCategory.BLOCKS, 0.5F, 1F);
					else
						world.playSound(null, getPos(), TSounds.door_open, SoundCategory.BLOCKS, 0.5F, 1F);
					player.sendStatusMessage(new TextComponentTranslation(TStrings.TARDIS_LOCKED + isLocked), true);
					ControlDoor door = tardis.getDoor();
					if (door != null) {
						door.setOpen(!this.isLocked());
						for (Entity e : tardis.getWorld().getEntitiesWithinAABB(Entity.class, Block.FULL_BLOCK_AABB.offset(door.getPositionVector()).grow(20))) {
							if (e instanceof IDoor) {
								((IDoor) e).setOpen(!this.isLocked());
							}
						}
					}
				} else if (tardis.isLocked()) {
					player.sendStatusMessage(new TextComponentTranslation(TStrings.DOUBLE_LOCKED + true), false);
				}
			}
		}
	}

	public void toggleLockedNoKey(EntityPlayer player) {
		if (lockCooldown == 0) {
			lockCooldown = 20;
			isLocked = !isLocked;
			this.markDirty();
			player.sendStatusMessage(new TextComponentTranslation(TStrings.TARDIS_LOCKED + isLocked), true);
		}
	}

	public EnumFacing getFacing() {
		IBlockState state = world.getBlockState(this.getPos());
		if (state.getBlock() instanceof BlockTardisTop) {
			return state.getValue(BlockTardisTop.FACING);
		}
		return EnumFacing.NORTH;
	}

	@Override
	public void update() {
		if (!world.isRemote) {
			WorldServer ws = (WorldServer) world;

			AxisAlignedBB bounds;
			if(this.getFacing() == EnumFacing.NORTH)
				bounds = NORTH.offset(getPos().down());
			else if(this.getFacing() == EnumFacing.EAST)
				bounds = EAST.offset(this.getPos().down());
			else if(this.getFacing() == EnumFacing.SOUTH)
				bounds = SOUTH.offset(this.getPos().down());
			else bounds = WEST.offset(this.getPos().down());

			List<Entity> entities = world.getEntitiesWithinAABB(Entity.class, bounds);
			TileEntityTardis tardis = (TileEntityTardis) world.getMinecraftServer().getWorld(TDimensions.TARDIS_ID).getTileEntity(getConsolePos());
			if (tardis == null) return;
			if (tardis != null) tardis.setLocation(this.getPos().down());

			forceField = tardis.isForceFieldEnabled();

			if (!entities.isEmpty() && !this.isLocked()) {
				for (Entity entity : entities) {
					entity.dismountRidingEntity();
					entity.removePassengers();
					tardis.enterTARDIS(entity);
				}
			}
			if (tardis.getDoor() != null)
				this.lightLevel = world.getMinecraftServer().getWorld(TDimensions.TARDIS_ID).getLight(tardis.getDoor().getPosition());

			if (canOpen() && forceField) {
				handleForceField();
			}
			
			if(world.getWorldTime() % 20 == 0 && tardis.getDoor() != null) {
				this.renderAngle = tardis.getDoor().rotationYaw;
				for(EntityPlayer player : world.playerEntities) {
					((EntityPlayerMP)player).connection.sendPacket(this.getUpdatePacket());
				}
			}


			//HADS
			List<Entity> projectiles = world.getEntitiesWithinAABB(Entity.class, Block.FULL_BLOCK_AABB.offset(this.getPos()).grow(1D));
			for (Entity e : projectiles) {
				if (e instanceof IProjectile || e instanceof IMob) {
					try {
						((TileEntityTardis) DimensionManager.getWorld(TDimensions.TARDIS_ID).getTileEntity(getConsolePos())).startHADS();
					}
					catch (Exception exc) {}
				}
			}
			if (lockCooldown > 0) --lockCooldown;
			++this.updateTicks;
			if (this.updateTicks > 20) {
				NetworkHandler.NETWORK.sendToAllAround(new MessageDoorOpen(this.getPos(), this), new TargetPoint(this.world.provider.getDimension(), this.getPos().getX(), this.getPos().getY(), this.getPos().getZ(), 64D));
				this.updateTicks = 0;
			}
			//World Shell
				if (!this.isLocked()) {
					if (tardis.getDoor() == null) return;
					EnumFacing face = tardis.getDoor().getHorizontalFacing();
					BlockPos doorPos = tardis.getDoor().getPosition();
					AxisAlignedBB BB;
					if(face == EnumFacing.NORTH) {
						BB = new AxisAlignedBB(-radius, -radius, -radius, radius, radius, 0);
						doorPos = doorPos.add(1, 0, 0);
					}
					else if(face == EnumFacing.SOUTH) {
						BB = new AxisAlignedBB(-radius, -radius, 0, radius, radius, radius);
						doorPos = doorPos.add(0, 0, 0);
					}
					else if(face == EnumFacing.EAST) {
						BB = new AxisAlignedBB(0, -radius, -radius, radius, radius, radius);
						doorPos = doorPos.add(0, 0, 1);
					}
					else {
						BB = new AxisAlignedBB(-radius, -radius, -radius, 0, radius, radius);
						doorPos = doorPos.add(0, 0, 0);
					}
					if(worldShell == null || !worldShell.getOffset().equals(doorPos))
						worldShell = new WorldShell(doorPos);
					
					if(world.getWorldTime() % 5 == 1) {
						for(BlockPos pos : this.getBlocksInAABB(BB.offset(doorPos))) {
							IBlockState state = tardis.getWorld().getBlockState(pos);
							if((state.getBlock().hasTileEntity() || state.getRenderType() != EnumBlockRenderType.INVISIBLE) || state.getBlock() instanceof BlockTardisTop)
								worldShell.blockMap.put(pos, new BlockStorage(tardis.getWorld().getBlockState(pos), tardis.getWorld().getTileEntity(pos), tardis.getWorld().getLight(pos, true)));
						}
					}
					if(world.getWorldTime() % 5 == 0) {
						List<NBTTagCompound> bEnt = new ArrayList<NBTTagCompound>();
						for(Entity e : tardis.getWorld().getEntitiesWithinAABB(Entity.class, BB.offset(doorPos))) {
							if(EntityList.getKey(e) != null && !(e instanceof ControlDoor)) {
								NBTTagCompound tag = new NBTTagCompound();
								e.writeToNBT(tag);
								tag.setString("id", EntityList.getKey(e).toString());
								bEnt.add(tag);
							}
						}
						worldShell.setEntities(bEnt);
					}
			}
		}
		if (openingTicks > 0) {
			--openingTicks;
		}

		if (isRemat) {
			if (alpha < 1.0F) {
				alpha += 0.005F;
				if (!world.isRemote) {
					BlockPos tp = this.getConsolePos().offset(EnumFacing.SOUTH, 3);
					for (Entity e : world.getEntitiesWithinAABB(Entity.class, new AxisAlignedBB(0, 0, 0, 1, 2, 1).offset(this.getPos().down()))) {
						if (e instanceof EntityPlayerMP) {
							EntityPlayerMP mp = (EntityPlayerMP) e;
							mp.connection.setPlayerLocation(tp.getX(), tp.getY(), tp.getZ(), 0, 0);
							world.getMinecraftServer().getPlayerList().transferPlayerToDimension(mp, TDimensions.TARDIS_ID, new TardisTeleporter(tp));
						} else {
							e.setPositionAndUpdate(tp.getX(), tp.getY(), tp.getZ());
							e.changeDimension(TDimensions.TARDIS_ID);
						}
					}
				}
			} else {
				this.isRemat = false;
				this.alpha = 1.0F;
			}
		}
		if (isDemat) {
			alpha -= 0.005F;
			if (alpha <= 0) {
				this.isDemat = false;
				this.world.setBlockState(this.getPos(), Blocks.AIR.getDefaultState());
				this.world.setBlockState(this.getPos().down(), Blocks.AIR.getDefaultState());
			}
		}
		if (!this.isRemat && !this.isDemat) this.alpha = 1.0F;
		
		if(world.isRemote && (this.worldShell == null || this.worldShell.getOffset().equals(BlockPos.ORIGIN))) {
			NetworkHandler.NETWORK.sendToServer(new MessageRequestBOTI(this.getPos()));
		}
	}

	public boolean canOpen() {
		return !this.isDemat && !this.isRemat;
	}

	public void sendDematPacket(boolean demat) {
		if (!world.isRemote)
			NetworkHandler.NETWORK.sendToAllAround(new MessageDemat(this.getPos(), demat), new TargetPoint(this.world.provider.getDimension(), getPos().getX(), getPos().getY(), getPos().getZ(), 64));
	}

	public boolean isLocked() {
		return this.isLocked;
	}

	public void sendBOTI(EnumType type) {
		NetworkHandler.NETWORK.sendToAllAround(new MessageSyncWorldShell(this.worldShell, this.getPos(), type), new TargetPoint(world.provider.getDimension(), (double)this.getPos().getX(), (double)this.getPos().getY(), (double)this.getPos().getZ(), 40));
	}
	
	public void setLocked(boolean b) {
		this.isLocked = b;
		this.markDirty();
	}

	@Override
	public void onLoad() {
		super.onLoad();
		if (!world.isRemote)
			NetworkHandler.NETWORK.sendToDimension(new MessageDoorOpen(this.getPos(), this), world.provider.getDimension());
	}
	
	public List<BlockPos> getBlocksInAABB(AxisAlignedBB bb){
		List<BlockPos> poses = new ArrayList<BlockPos>();
		if(bb == null) return poses;
		for(int x = (int)bb.minX; x < bb.maxX; ++x) {
			for(int y = (int)bb.minY; y < bb.maxY; ++y) {
				for(int z = (int)bb.minZ; z < bb.maxZ; ++z) {
					poses.add(new BlockPos(x, y, z));
				}
			}
		}
		return poses;
	}

	@Override
	public AxisAlignedBB getRenderBoundingBox() {
		return RENDER_BB.offset(this.getPos());
	}

	public void handleForceField() {
		world.getEntitiesWithinAABB(Entity.class, RENDER_BB.offset(this.getPos())).forEach(entity -> {

			if (entity instanceof IProjectile) {
				entity.setDead();
			}

			if (entity instanceof IMob) {
				entity.attackEntityFrom(TDamageSources.FORCEFIELD, 4.0F);
			}

		});
	}

	public BlockPos getConsolePos() {
		return this.consolePos;
	}

	public void setConsolePos(BlockPos pos) {
		this.consolePos = pos.toImmutable();
		this.markDirty();
	}

	public void knock() {
		if (!world.isRemote && this.getConsolePos() != null) {
			WorldServer ws = world.getMinecraftServer().getWorld(TDimensions.TARDIS_ID);
			if (ws != null) {
				ws.playSound(null, getConsolePos(), knockSound, SoundCategory.BLOCKS, 1.0F, 1.0F);
			}
		}
	}

	public IInventory getLinkedInv() {
		if (!world.isRemote && this.getConsolePos() != null) {
			WorldServer ws = DimensionManager.getWorld(TDimensions.TARDIS_ID);
			if (ws != null) {
				TileEntity te = ws.getTileEntity(getConsolePos());
				if (te instanceof TileEntityTardis) return ((TileEntityTardis) te);
			}
		}
		return DummyTardis.INSTANCE;
	}

	@Override
	public String getName() {
		return this.getLinkedInv().getName();
	}

	@Override
	public boolean hasCustomName() {
		return false;
	}

	@Override
	public int getSizeInventory() {
		return this.getLinkedInv().getSizeInventory();
	}

	@Override
	public boolean isEmpty() {
		return this.getLinkedInv().isEmpty();
	}

	@Override
	public ItemStack getStackInSlot(int index) {
		return this.getLinkedInv().getStackInSlot(index);
	}

	@Override
	public ItemStack decrStackSize(int index, int count) {
		return this.getLinkedInv().decrStackSize(index, count);
	}

	@Override
	public ItemStack removeStackFromSlot(int index) {
		return this.getLinkedInv().removeStackFromSlot(index);
	}

	@Override
	public void setInventorySlotContents(int index, ItemStack stack) {
		this.getLinkedInv().setInventorySlotContents(index, stack);
	}

	@Override
	public int getInventoryStackLimit() {
		return this.getLinkedInv().getInventoryStackLimit();
	}

	@Override
	public boolean isUsableByPlayer(EntityPlayer player) {
		return false;
	}

	@Override
	public void openInventory(EntityPlayer player) {
	}

	@Override
	public void closeInventory(EntityPlayer player) {
	}

	@Override
	public boolean isItemValidForSlot(int index, ItemStack stack) {
		return true;
	}

	@Override
	public int getField(int id) {
		return 0;
	}

	@Override
	public void setField(int id, int value) {
	}

	@Override
	public int getFieldCount() {
		return 0;
	}

	@Override
	public void clear() {
		this.getLinkedInv().clear();
	}

	@Override
	public WorldShell getWorldShell() {
		return this.worldShell;
	}

	@Override
	public void setWorldShell(WorldShell worldShell) {
		this.worldShell = worldShell;
	}

	public void setRemat() {
		this.alpha = 0;
		this.isRemat = true;
		this.sendDematPacket(false);
	}

	public void setDemat() {
		this.alpha = 1;
		this.isDemat = true;
		this.setLocked(true);
		this.sendDematPacket(true);
	}

	@Override
	public boolean shouldRenderInPass(int pass) {
		return true;
	}

	public int getLightLevel() {
		return this.lightLevel;
	}

	public void setLightLevel(int light) {
		this.lightLevel = light;
		world.checkLight(this.getPos());
	}

	@Override
	public int getDimension() {
		return TDimensions.TARDIS_ID;
	}

	public class NBT {
		public static final String LOCKED = "locked";
	}

	public float getDoorAngle() {
		return this.renderAngle;
	}

	@Override
	public SPacketUpdateTileEntity getUpdatePacket() {
		return new SPacketUpdateTileEntity(this.getPos(), -1, this.getUpdateTag());
	}

	@Override
	public NBTTagCompound getUpdateTag() {
		NBTTagCompound tag = new NBTTagCompound();
		tag.setFloat("angle", this.renderAngle);
		return tag;
	}

	@Override
	public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt) {
		super.onDataPacket(net, pkt);
		this.renderAngle = pkt.getNbtCompound().getFloat("angle");
	}
}
