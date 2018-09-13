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
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3i;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.DimensionManager;
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
import net.tardis.mod.common.entities.controls.ControlDoor;
import net.tardis.mod.common.enums.EnumTardisState;
import net.tardis.mod.common.sounds.TSounds;
import net.tardis.mod.common.strings.TStrings;
import net.tardis.mod.packets.MessageDemat;
import net.tardis.mod.packets.MessageDoorOpen;
import net.tardis.mod.util.TardisTeleporter;
import net.tardis.mod.util.helpers.Helper;
import net.tardis.mod.util.helpers.TardisHelper;

public class TileEntityDoor extends TileEntity implements ITickable, IInventory, IContainsWorldShell {
	
	public BlockPos consolePos = BlockPos.ORIGIN;
	public SoundEvent knockSound = SoundEvents.ENTITY_ZOMBIE_ATTACK_DOOR_WOOD;
	public boolean isLocked = true;
	public int lockCooldown = 0;
	private int updateTicks = 0;
	public int openingTicks = 0;
	public float alpha = 1;
	public boolean isDemat = false;
	public boolean isRemat = false;
	public static int radius = 20;
	private WorldShell worldShell = new WorldShell(BlockPos.ORIGIN);
	private AxisAlignedBB SHELL_AABB = new AxisAlignedBB(-10, -10, -10, 10, 10, 10);
	
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
	}
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound tag) {
		tag.setLong("tPos", consolePos.toLong());
		tag.setBoolean("locked", isLocked);
		tag.setBoolean("demat", this.isDemat);
		tag.setBoolean("remat", this.isRemat);
		tag.setFloat("alpha", this.alpha);
		return super.writeToNBT(tag);
	}
	
	public void toggleLocked(EntityPlayer player) {
		if(!world.isRemote) {
			TileEntityTardis tardis = (TileEntityTardis)((WorldServer)world).getMinecraftServer().getWorld(TDimensions.TARDIS_ID).getTileEntity(getConsolePos());
			if(tardis != null && tardis.getTardisState() == EnumTardisState.NORMAL) {
				if (TardisHelper.hasValidKey(player, consolePos) && lockCooldown == 0 && alpha >= 1.0F && !tardis.isLocked()) {
					lockCooldown = 20;
		            isLocked = !isLocked;
					this.markDirty();
					Tardis.NETWORK.sendToDimension(new MessageDoorOpen(this.getPos(), this), world.provider.getDimension());
					if (isLocked)
						world.playSound(null, getPos(), TSounds.door_closed, SoundCategory.BLOCKS, 0.5F, 1F);
					else
						world.playSound(null, getPos(), TSounds.door_open, SoundCategory.BLOCKS, 0.5F, 1F);
					player.sendStatusMessage(new TextComponentTranslation(TStrings.TARDIS_LOCKED + isLocked), true);
					ControlDoor door = tardis.getDoor();
					if(door != null) {
						door.setOpen(!this.isLocked());
						for(Entity e : tardis.getWorld().getEntitiesWithinAABB(Entity.class, Block.FULL_BLOCK_AABB.offset(door.getPositionVector()).grow(20))) {
							if(e instanceof IDoor) {
								((IDoor)e).setOpen(!this.isLocked());
							}
						}
					}
				}
				else if(tardis.isLocked()) {
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
	
	public void setLocked(boolean b) {
		this.isLocked = b;
		this.markDirty();
	}
	
	public EnumFacing getFacing() {
		IBlockState state = world.getBlockState(this.getPos());
		if (state.getBlock() instanceof BlockTardisTop) {
			return state.getValue(BlockTardisTop.FACING);
		}
		return EnumFacing.NORTH;
	}
	
	public static AxisAlignedBB aabb = new AxisAlignedBB(0.25, 0, 0.25, 0.75, 2, 0.75);
	
	@Override
	public void update() {
		if (!world.isRemote) {
			WorldServer ws = (WorldServer) world;
			
			AxisAlignedBB bounds = aabb.offset(getPos().down().offset(getFacing()));
			
			List<Entity> entities = world.getEntitiesWithinAABB(Entity.class, bounds);
			TileEntityTardis tardis = (TileEntityTardis)((WorldServer)world).getMinecraftServer().getWorld(TDimensions.TARDIS_ID).getTileEntity(getConsolePos());
			if(tardis != null)tardis.setLocation(this.getPos().down());
			if (!entities.isEmpty() && !this.isLocked()) {
				for (Entity entity : entities) {
					entity.dismountRidingEntity();
					entity.removePassengers();
					tardis.enterTARDIS(entity);
				}
			}

			//HADS
			List<Entity> projectiles = world.getEntitiesWithinAABB(Entity.class, Block.FULL_BLOCK_AABB.offset(this.getPos()).grow(1D));
			for(Entity e : projectiles) {
				if(e instanceof IProjectile || e instanceof IMob) {
					try{
                        ((TileEntityTardis) DimensionManager.getWorld(TDimensions.TARDIS_ID).getTileEntity(getConsolePos())).startHADS();
					}
					catch(Exception exc) {}
				}
			}
			if (lockCooldown > 0) --lockCooldown;
			++this.updateTicks;
			if (this.updateTicks > 20) {
				Tardis.NETWORK.sendToAllAround(new MessageDoorOpen(this.getPos(), this), new TargetPoint(this.world.provider.getDimension(), this.getPos().getX(), this.getPos().getY(), this.getPos().getZ(), 64D));
				this.updateTicks = 0;
			}
			//World Shell
			if(world.getWorldTime() % 5 == 0) {
				if(!this.isLocked()) {
					if(tardis.getDoor() == null)return;
					worldShell = new WorldShell(this.getConsolePos());
	                WorldServer tardisWorld = ws.getMinecraftServer().getWorld(TDimensions.TARDIS_ID);
					for(BlockPos pos : BlockPos.getAllInBox(worldShell.getOffset().subtract(new Vec3i(radius, radius, radius)), worldShell.getOffset().add(new Vec3i(radius, radius, 6)))) {
						IBlockState state = tardisWorld.getBlockState(pos);
						if(state.getBlock() != Blocks.AIR) {
							worldShell.blockMap.put(pos, new BlockStorage(state, tardisWorld.getTileEntity(pos), 15));
						}
					}
					List<NBTTagCompound> lists = new ArrayList<NBTTagCompound>();
					List<PlayerStorage> players = new ArrayList<PlayerStorage>();
					for(Entity e : tardisWorld.getEntitiesWithinAABB(Entity.class, Helper.createBB(getConsolePos(), 10))) {
						if(EntityList.getKey(e) != null && !(e instanceof ControlDoor)) {
							NBTTagCompound tag = new NBTTagCompound();
							e.writeToNBT(tag);
							tag.setString("id", EntityList.getKey(e).toString());
							lists.add(tag);
						}
						else if(e instanceof EntityPlayer) {
							players.add(new PlayerStorage((EntityPlayer)e));
						}
					}
					worldShell.setPlayers(players);
					worldShell.setEntities(lists);
					Tardis.NETWORK.sendToAllAround(new MessageSyncWorldShell(worldShell, this.getPos()), new TargetPoint(world.provider.getDimension(), this.getPos().getX(),this.getPos().getY(),this.getPos().getZ(), 16D));
				}
			}
		}
		if(openingTicks > 0) {
			--openingTicks;
		}
		
		if(isRemat) {
			if(alpha < 1.0F) {
				alpha += 0.005F;
				if(!world.isRemote) {
					BlockPos tp = this.getConsolePos().offset(EnumFacing.SOUTH, 3);
					for(Entity e : world.getEntitiesWithinAABB(Entity.class, new AxisAlignedBB(0,0,0,1,2,1).offset(this.getPos().down()))) {
						if(e instanceof EntityPlayerMP) {
							EntityPlayerMP mp = (EntityPlayerMP)e;
							mp.connection.setPlayerLocation(tp.getX(), tp.getY(), tp.getZ(), 0, 0);
							((WorldServer)world).getMinecraftServer().getPlayerList().transferPlayerToDimension(mp, TDimensions.TARDIS_ID, new TardisTeleporter());
						}
						else {
							e.setPositionAndUpdate(tp.getX(), tp.getY(), tp.getZ());
							e.changeDimension(TDimensions.TARDIS_ID);
							System.out.println("Works: " + e.getDisplayName().getFormattedText() + " has been sent!");
							System.out.println("Dimension: " + e.dimension + ", BlockPos: " + Helper.formatBlockPos(e.getPosition()));
						}
					}
				}
			}
			else {
				this.isRemat = false;
				this.alpha = 1.0F;
			}
		}
		if(isDemat) {
			alpha -= 0.005F;
			if(alpha <= 0) {
				this.isDemat = false;
				this.world.setBlockState(this.getPos(), Blocks.AIR.getDefaultState());
				this.world.setBlockState(this.getPos().down(), Blocks.AIR.getDefaultState());
			}
		}
		if(!this.isRemat && !this.isDemat)this.alpha = 1.0F;
	}
	
	public boolean canOpen() {
		return !this.isDemat && !this.isRemat ? true : false;
	}

	public void sendDematPacket(boolean demat) {
		if(!world.isRemote)Tardis.NETWORK.sendToAllAround(new MessageDemat(this.getPos(), demat), new TargetPoint(this.world.provider.getDimension(), getPos().getX(), getPos().getY(), getPos().getZ(), 64));
	}
	
	public boolean isLocked() {
		return this.isLocked;
	}
	
	public class NBT {
		public static final String LOCKED = "locked";
	}
	
	@Override
	public void onLoad() {
		super.onLoad();
		if (!world.isRemote) Tardis.NETWORK.sendToDimension(new MessageDoorOpen(this.getPos(), this), world.provider.getDimension());
	}
	
	@Override
	public AxisAlignedBB getRenderBoundingBox() {
		return new AxisAlignedBB(getPos().getX(), getPos().getY() - 1, getPos().getZ(), getPos().getX() + 1, getPos().getY() + 1.5, getPos().getZ() + 1);
	}
	
	public void setConsolePos(BlockPos pos) {
		this.consolePos = pos.toImmutable();
		this.markDirty();
	}
	
	public BlockPos getConsolePos() {
		return this.consolePos;
	}
	
	public void knock() {
		if(!world.isRemote && this.getConsolePos() != null) {
            WorldServer ws = DimensionManager.getWorld(TDimensions.TARDIS_ID);
			if(ws != null) {
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
	public void openInventory(EntityPlayer player) {}
	
	@Override
	public void closeInventory(EntityPlayer player) {}
	
	@Override
	public boolean isItemValidForSlot(int index, ItemStack stack) {
		return true;
	}
	
	@Override
	public int getField(int id) {
		return 0;
	}
	
	@Override
	public void setField(int id, int value) {}
	
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

	@Override
	public int getDimnesion() {
		return TDimensions.TARDIS_ID;
	}
}
