package net.tardis.mod.common.tileentity;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.IProjectile;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.fml.common.network.NetworkRegistry.TargetPoint;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tardis.mod.client.worldshell.BlockStorage;
import net.tardis.mod.client.worldshell.IContainsWorldShell;
import net.tardis.mod.client.worldshell.WorldBoti;
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
	private WorldShell worldShell = new WorldShell(BlockPos.ORIGIN);
	private int lightLevel = 0;
	//The rotation to render the interior as
	private float renderAngle = 90;
	private boolean requiresUpdate = true;
	//Only use this client side - This should be a WorldBOTI
	public World clientWorld;
	private boolean isStealth = true;
	
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
		this.isStealth = tag.getBoolean("stealth");
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
		tag.setBoolean("stealth", this.isStealth);
		return super.writeToNBT(tag);
	}
	
	public void toggleLocked(EntityPlayer player) {
		if (world != null && !world.isRemote) {
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
					IBlockState state = world.getBlockState(this.getPos());
					world.notifyBlockUpdate(getPos(), state, state, 2);
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
		if (world == null) return;
		this.handleEnter();
		if (!world.isRemote) {
			WorldServer ws = (WorldServer) world;
			TileEntityTardis tardis = (TileEntityTardis) ws.getMinecraftServer().getWorld(TDimensions.TARDIS_ID).getTileEntity(getConsolePos());
			if (tardis == null) return;
			if (this.world.getTotalWorldTime() % 20 == 0)
				this.updateWorldShell();
			
			//HADS
			List<Entity> projectiles = world.getEntitiesWithinAABB(Entity.class, Block.FULL_BLOCK_AABB.offset(this.getPos()).grow(1D));
			for (Entity e : projectiles) {
				if (e instanceof IProjectile || e instanceof IMob) {
					try {
						tardis.startHADS();
					} catch (Exception exc) {
					}
				}
			}
			if (lockCooldown > 0)
				--lockCooldown;
		}
		if (openingTicks > 0)
			--openingTicks;
		if (isRemat) {
			if (alpha < 1.0F) {
				alpha += 0.005F;
				if (!world.isRemote) {
					for (Entity e : world.getEntitiesWithinAABB(Entity.class, aabb.offset(this.getPos().down()))) {
						try {
							((TileEntityTardis) world.getMinecraftServer().getWorld(TDimensions.TARDIS_ID).getTileEntity(getConsolePos())).enterTARDIS(e);
						} catch (Exception exc) {
						}
					}
				}
			} else {
				this.isRemat = false;
				this.alpha = 1.0F;
				world.notifyBlockUpdate(getPos(), world.getBlockState(getPos()), world.getBlockState(getPos()), 2);
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
		if (!this.isRemat && !this.isDemat)
			this.alpha = 1.0F;
		
		if (world.isRemote && !this.isLocked() && (this.worldShell.getOffset().equals(BlockPos.ORIGIN) || this.requiresUpdate)) {
			NetworkHandler.NETWORK.sendToServer(new MessageRequestBOTI(this.getPos()));
			this.setupClientWorld();
			this.requiresUpdate = false;
		}
		
		if (this.isLocked() && !this.worldShell.getOffset().equals(BlockPos.ORIGIN)) {
			this.worldShell = new WorldShell(BlockPos.ORIGIN);
		}
		
		if (forceField) {
			handleForceField();
		}
	}
	
	@SideOnly(Side.CLIENT)
	public void setupClientWorld() {
		this.clientWorld = new WorldBoti(this.world.provider.getDimension(), (WorldClient) world, this.worldShell);
	}
	
	public void handleEnter() {
		if (world != null && !world.isRemote) {
			if (this.isLocked()) return;
			TileEntityTardis tardis = (TileEntityTardis) world.getMinecraftServer().getWorld(TDimensions.TARDIS_ID).getTileEntity(getConsolePos());
			if (tardis == null) return;
			AxisAlignedBB bb;
			EnumFacing face = this.getFacing();
			if (face == EnumFacing.NORTH)
				bb = NORTH;
			else if (face == EnumFacing.EAST)
				bb = EAST;
			else if (face == EnumFacing.SOUTH)
				bb = SOUTH;
			else bb = WEST;
			bb = bb.offset(this.getPos().down());
			for (Entity e : world.getEntitiesWithinAABB(Entity.class, bb)) {
				tardis.enterTARDIS(e);
			}
		}
	}
	
	
	public boolean canOpen() {
		return !this.isDemat && !this.isRemat;
	}
	
	public void sendDematPacket(boolean demat) {
		if (world != null && !world.isRemote) {
			NetworkHandler.NETWORK.sendToAllAround(new MessageDemat(this.getPos(), demat), new TargetPoint(this.world.provider.getDimension(), getPos().getX(), getPos().getY(), getPos().getZ(), 64));
		}
	}
	
	public boolean isLocked() {
		return this.isLocked;
	}
	
	public void setLocked(boolean b) {
		this.isLocked = b;
		this.markDirty();
		if (!world.isRemote)
			world.notifyBlockUpdate(this.getPos(), world.getBlockState(this.getPos()), world.getBlockState(this.getPos()), 2);
	}
	
	public List<BlockPos> getBlocksInAABB(AxisAlignedBB bb) {
		List<BlockPos> poses = new ArrayList<BlockPos>();
		if (bb == null) return poses;
		for (int x = (int) bb.minX; x < bb.maxX; ++x) {
			for (int y = (int) bb.minY; y < bb.maxY; ++y) {
				for (int z = (int) bb.minZ; z < bb.maxZ; ++z) {
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
	
	public void forceVisible() {
		this.alpha = 1;
		isDemat = false;
		isRemat = false;
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
		tag.setBoolean("demat", this.isDemat);
		tag.setBoolean("remat", this.isRemat);
		tag.setBoolean("locked", this.isLocked);
		tag.setInteger("light", this.lightLevel);
		tag.setBoolean("stealth", this.isStealth);
		return tag;
	}
	
	@Override
	public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt) {
		super.onDataPacket(net, pkt);
		this.renderAngle = pkt.getNbtCompound().getFloat("angle");
		this.isDemat = pkt.getNbtCompound().getBoolean("demat");
		this.isRemat = pkt.getNbtCompound().getBoolean("remat");
		this.isLocked = pkt.getNbtCompound().getBoolean("locked");
		this.lightLevel = pkt.getNbtCompound().getInteger("light");
		this.isStealth = pkt.getNbtCompound().getBoolean("stealth");
		this.requiresUpdate = true;
	}
	
	public void updateWorldShell() {
		if (worldShell == null || !worldShell.getOffset().equals(this.getConsolePos()))
			this.worldShell = new WorldShell(this.getOffset());
		if (this.worldShell.blockMap.isEmpty()) {
		}
		WorldServer ws = world.getMinecraftServer().getWorld(TDimensions.TARDIS_ID);
		TileEntityTardis tardis = (TileEntityTardis) ws.getTileEntity(this.getConsolePos());
		if (tardis == null) return;
		ControlDoor door = tardis.getDoor();
		AxisAlignedBB BOTI = Block.FULL_BLOCK_AABB.grow(20);
		if (door != null) {
			BOTI = BOTI.offset(door.getPosition().offset(door.getHorizontalFacing(), ((int) BOTI.maxX) - 1));
		} else BOTI = BOTI.offset(this.getConsolePos());
		for (BlockPos pos : this.getBlocksInAABB(BOTI)) {
			if (ws.getBlockState(pos).getMaterial() != Material.AIR)
				this.worldShell.blockMap.put(pos, new BlockStorage(ws.getBlockState(pos), ws.getTileEntity(pos), 15));
		}
		List<NBTTagCompound> entities = new ArrayList<NBTTagCompound>();
		for (Entity e : ws.getEntitiesWithinAABB(Entity.class, BOTI)) {
			ResourceLocation key = EntityList.getKey(e);
			if (key != null && !(e instanceof ControlDoor)) {
				NBTTagCompound tag = new NBTTagCompound();
				tag.setString("id", key.toString());
				entities.add(e.writeToNBT(tag));
			}
		}
		this.worldShell.setEntities(entities);
		this.renderAngle = tardis.getDoor() == null ? 90 : tardis.getDoor().rotationYaw;
		this.requiresUpdate = true;
		IBlockState state = world.getBlockState(getPos());
		this.world.notifyBlockUpdate(getPos(), state, state, 2);
		this.requiresUpdate = false;
	}
	
	public BlockPos getOffset() {
		if (!world.isRemote) {
			TileEntityTardis tardis = (TileEntityTardis) world.getMinecraftServer().getWorld(TDimensions.TARDIS_ID).getTileEntity(getConsolePos());
			if (tardis != null) {
				ControlDoor door = tardis.getDoor();
				if (door != null) {
					return door.getPosition();
				}
			}
		}
		return this.getConsolePos().offset(this.getFacing().getOpposite(), 11);
	}
	
	@Override
	public boolean shouldRefresh(World world, BlockPos pos, IBlockState oldState, IBlockState newState) {
		return oldState.getBlock() != newState.getBlock();
	}
	
	
	@Override
	public World getRenderWorld() {
		return this.clientWorld;
	}

	public void setStealth(boolean isStealth) {
		this.isStealth = isStealth;
		if(!world.isRemote)
			world.notifyBlockUpdate(this.getPos(), world.getBlockState(this.getPos()), world.getBlockState(this.getPos()), 2);
	}
	
	public boolean isStealth() {
		return this.isStealth;
	}
}
