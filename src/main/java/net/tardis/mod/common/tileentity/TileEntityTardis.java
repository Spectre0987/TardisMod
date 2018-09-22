package net.tardis.mod.common.tileentity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagInt;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ITickable;
import net.minecraft.util.NonNullList;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.DimensionType;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.common.ForgeChunkManager;
import net.minecraftforge.common.ForgeChunkManager.Ticket;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.util.Constants;
import net.tardis.mod.Tardis;
import net.tardis.mod.api.events.TardisEnterEvent;
import net.tardis.mod.api.events.TardisExitEvent;
import net.tardis.mod.client.models.ModelConsole;
import net.tardis.mod.common.blocks.BlockTardisTop;
import net.tardis.mod.common.blocks.TBlocks;
import net.tardis.mod.common.dimensions.TDimensions;
import net.tardis.mod.common.entities.controls.ControlDimChange;
import net.tardis.mod.common.entities.controls.ControlDirection;
import net.tardis.mod.common.entities.controls.ControlDoor;
import net.tardis.mod.common.entities.controls.ControlDoorSwitch;
import net.tardis.mod.common.entities.controls.ControlFastReturn;
import net.tardis.mod.common.entities.controls.ControlFlight;
import net.tardis.mod.common.entities.controls.ControlFuel;
import net.tardis.mod.common.entities.controls.ControlLandType;
import net.tardis.mod.common.entities.controls.ControlLaunch;
import net.tardis.mod.common.entities.controls.ControlMag;
import net.tardis.mod.common.entities.controls.ControlPhone;
import net.tardis.mod.common.entities.controls.ControlRandom;
import net.tardis.mod.common.entities.controls.ControlSTCButton;
import net.tardis.mod.common.entities.controls.ControlSTCLoad;
import net.tardis.mod.common.entities.controls.ControlSonicSlot;
import net.tardis.mod.common.entities.controls.ControlStabilizers;
import net.tardis.mod.common.entities.controls.ControlTelepathicCircuts;
import net.tardis.mod.common.entities.controls.ControlX;
import net.tardis.mod.common.entities.controls.ControlY;
import net.tardis.mod.common.entities.controls.ControlZ;
import net.tardis.mod.common.entities.controls.EntityControl;
import net.tardis.mod.common.enums.EnumEvent;
import net.tardis.mod.common.enums.EnumTardisState;
import net.tardis.mod.common.sounds.TSounds;
import net.tardis.mod.common.systems.SystemFlight;
import net.tardis.mod.common.systems.TardisSystems;
import net.tardis.mod.common.systems.TardisSystems.BaseSystem;
import net.tardis.mod.config.TardisConfig;
import net.tardis.mod.util.SpaceTimeCoord;
import net.tardis.mod.util.TardisTeleporter;
import net.tardis.mod.util.helpers.Helper;
import net.tardis.mod.util.helpers.RiftHelper;

public class TileEntityTardis extends TileEntity implements ITickable, IInventory {
	
	private Random rand = new Random();
	private int ticksToTravel = 0;
	private int ticks = 0;
	private BlockPos tardisLocation = BlockPos.ORIGIN;
	private BlockPos tardisDestination = BlockPos.ORIGIN;
	public int dimension = 0;
	private int destDim = 0;
	public int dimIndex = 0;
	private boolean isLoading = false;
	private static IBlockState blockBase = TBlocks.tardis.getDefaultState();
	private IBlockState blockTop = TBlocks.tardis_top.getDefaultState();
	/** Time To Travel in Blocks/Tick **/
	private static final int MAX_TARDIS_SPEED = 8;
    public NonNullList<SpaceTimeCoord> saveCoords = NonNullList.withSize(15, SpaceTimeCoord.ORIGIN);
    public NonNullList<ItemStack> buffer = NonNullList.withSize(9, ItemStack.EMPTY);
	public EntityControl[] controls;
	public float fuel = 1F;
	private boolean isFueling = false;
	private boolean shouldDelayLoop = true;
	private Ticket tardisTicket;
	public Ticket tardisLocTicket;
	private boolean chunkLoadTick = true;
	public boolean landOnSurface = true;
	public EnumFacing facing = EnumFacing.NORTH;
	public String currentDimName = "";
	public String targetDimName = "";
	public int totalTimeToTravel;
	public int rotorUpdate = 0;
	public int frame = 0;
	private boolean hadsEnabled = false;
	public int magnitude = 10;
	public EnumEvent currentEvent = EnumEvent.NONE;
	public static float defaultFuelUse = 0.0001F;
	public float fuelUseage = defaultFuelUse;
	public BaseSystem[] systems;
	private EnumTardisState currentState = EnumTardisState.NORMAL;
	public double power = 0;
	public List<Vec3d> coordList = new ArrayList<>();
	private boolean isLocked = false;
	//Is the TARDIS 'parked' in the Vortex?
	private boolean parkingOrbit = false;
	private float hullHealth = 1F;
	private EnumCourseCorrect courseCorrect = EnumCourseCorrect.NONE;
	
	public TileEntityTardis() {
		if(systems == null) {
			this.systems = this.createSystems();
		}
		if(this.getClass() == TileEntityTardis.class) {
			this.coordList.add(Helper.convertToPixels(-8.5, -1.5, -8.25));
			this.coordList.add(Helper.convertToPixels(-8.75, -1.5, -6.75));
			this.coordList.add(Helper.convertToPixels(-9.5, -1.5, -5.75));
			this.coordList.add(Helper.convertToPixels(-10.25, -1.5, -4.5));
		}
	}

	@Override
	public void update() {
		if (this.ticksToTravel > 0) {
			--ticksToTravel;
			this.setFuel(fuel - this.calcFuelUse());
			if (ticksToTravel <= 0) this.travel();
			if (this.ticksToTravel == this.totalTimeToTravel - 1 || this.ticksToTravel == 200)
				world.playSound(null, this.getPos(), TSounds.takeoff, SoundCategory.BLOCKS, 0.5F, 1F);
			else if (this.ticksToTravel > 200 && this.ticksToTravel < this.totalTimeToTravel - 200) {
				if (this.ticksToTravel % 40 == 0) {
					world.playSound(null, this.getPos(), TSounds.loop, SoundCategory.BLOCKS, 0.5F, 1F);
				}
			}
			if (fuel <= 0.0 && this.ticksToTravel % 5 == 0) crash();
			if (world.isRemote) {
				if (frame + 1 >= ModelConsole.frames.length)
					frame = 0;
				else
					++frame;
				if(TardisConfig.MISC.camShake && (this.ticksToTravel < 200 || this.totalTimeToTravel - this.ticksToTravel < 200)) {
					for(EntityPlayer player : world.getEntitiesWithinAABB(EntityPlayer.class, Block.FULL_BLOCK_AABB.offset(this.getPos()).grow(40))) {
						player.rotationPitch += (rand.nextInt(10) - 5) * 0.1;
						player.rotationYaw += (rand.nextInt(10) - 5) * 0.1;
					}
				}
				if(this.getCourseCorrect() != EnumCourseCorrect.NONE) {
					for(EntityPlayer player : world.getEntitiesWithinAABB(EntityPlayer.class, Block.FULL_BLOCK_AABB.offset(this.getPos()).grow(40))) {
						player.rotationPitch += (rand.nextInt(10) - 5) * 0.1;
						player.rotationYaw += (rand.nextInt(10) - 5) * 0.1;
					}
				}
			}
			if(!world.isRemote) {
				if(!this.getCanFly()) {
					this.crash();
				}
			}
		} else if (this.isFueling()) {
			if(!world.isRemote) {
				WorldServer ws = world.getMinecraftServer().getWorld(dimension);
				this.setFuel(fuel + (RiftHelper.isRift(ws.getChunkFromBlockCoords(this.getLocation()).getPos(), ws) ? 0.0005F : 0.0001F));
			}
		}
		++ticks;
		if (ticks >= 20) {
			ticks = 0;
			this.updateServer();
		}
		if (chunkLoadTick) {
			chunkLoadTick = false;
			if (!world.isRemote) {
				WorldServer ws = world.getMinecraftServer().getWorld(this.dimension);
				if(ws == null)return;
				tardisTicket = ForgeChunkManager.requestTicket(Tardis.instance, world, ForgeChunkManager.Type.NORMAL);
				ForgeChunkManager.forceChunk(tardisTicket, world.getChunkFromBlockCoords(this.getPos()).getPos());
				this.tardisLocTicket = ForgeChunkManager.requestTicket(Tardis.instance, ws, ForgeChunkManager.Type.NORMAL);
				ForgeChunkManager.forceChunk(tardisLocTicket, ws.getChunkFromBlockCoords(tardisLocation).getPos());
			}
		}
		if (world.isRemote && !this.isInFlight()) {
			frame = 0;
		}
		if (world.getTotalWorldTime() % 50 == 0 && !world.isRemote) {
			world.playSound(null, getPos(), TSounds.INTERIOR_HUM_1963, SoundCategory.BLOCKS, 0.5F, 1F);
		}
		this.createControls();
		for(BaseSystem sys : this.systems) {
			if(sys != null)sys.onUpdate(world, this.getPos());
			else{
				List<BaseSystem> systems = new ArrayList<>();
				for(int i = 0; i < this.systems.length; ++i) {
					if(this.systems[i] != null) {
						systems.add(this.systems[i]);
					}
				}
				this.systems = systems.toArray(new BaseSystem[] {});
			}
		}
	}
	
	public void travel() {
		if (!world.isRemote) {
			Random rand = new Random();
			this.ticksToTravel = 0;
			World dWorld = world.getMinecraftServer().getWorld(destDim);
			World oWorld = world.getMinecraftServer().getWorld(dimension);
			BlockPos nPos = Helper.isSafe(dWorld, getDestination(), this.facing) ? this.getDestination() : this.getLandingBlock(dWorld, getDestination());
			if (nPos != null) {
				//TnT Stuff
				if(dWorld.getTileEntity(nPos.down()) instanceof TileEntityDoor) {
					TileEntityDoor door = (TileEntityDoor)dWorld.getTileEntity(nPos.down());
					TileEntityTardis otherTardis = (TileEntityTardis)world.getTileEntity(door.getConsolePos());
					if(!door.isDemat && !door.isRemat && otherTardis != null && !otherTardis.hadsEnabled) {
						nPos = ((TileEntityDoor)dWorld.getTileEntity(nPos.down())).getConsolePos().add(rand.nextInt(5) - 2, 0, rand.nextInt(5) - 2);
						dWorld = DimensionManager.getWorld(TDimensions.TARDIS_ID);
						for(int landCheck = 0; landCheck < 10; landCheck++) {
							nPos.add(rand.nextInt(5) - 2, 0, rand.nextInt(5) - 2);
							if(dWorld.getBlockState(nPos).getMaterial() == Material.AIR && dWorld.getBlockState(nPos.up()).getMaterial() == Material.AIR) {
								break;
							}
						}
						this.destDim = TDimensions.TARDIS_ID;
					}
					else if(otherTardis != null && otherTardis.isHADSEnabled()) {
						otherTardis.startHADS();
					}
				}
				dWorld.setBlockState(nPos, blockBase);
				dWorld.setBlockState(nPos.up(), blockTop.withProperty(BlockTardisTop.FACING, facing));
				TileEntity door = dWorld.getTileEntity(nPos.up());
				if (door instanceof TileEntityDoor) {
					((TileEntityDoor) door).setConsolePos(this.getPos());
					((TileEntityDoor) dWorld.getTileEntity(nPos.up())).setRemat();
				}
				this.setLocation(nPos);
				this.dimension = this.destDim;
				this.setDesination(nPos, dimension);
				
			}
			ForgeChunkManager.releaseTicket(tardisLocTicket);
			tardisLocTicket = ForgeChunkManager.requestTicket(Tardis.instance, dWorld, ForgeChunkManager.Type.NORMAL);
			ForgeChunkManager.forceChunk(tardisLocTicket, dWorld.getChunkFromBlockCoords(tardisLocation).getPos());
			this.markDirty();
			DimensionType type = DimensionManager.getProviderType(dimension);
			if (type != null) this.currentDimName = type.getName();
			world.playSound(null, this.getPos(), TSounds.drum_beat, SoundCategory.BLOCKS, 0.5F, 1F);
			for(BaseSystem sys : this.systems) {
				sys.wear();
			}
		}
		shouldDelayLoop = true;
		
		for(BaseSystem sys : systems) {
			sys.onUpdate(this.world, getPos());
		}
	}
	
	public void updateServer() {
		if (!world.isRemote) {
            if (this != null && !this.isInvalid())
                world.getMinecraftServer().getPlayerList().sendToAllNearExcept(null, pos.getX(), pos.getY(), pos.getZ(), 8, TDimensions.TARDIS_ID, this.getUpdatePacket());
		}
	}
	
	public BlockPos getLandingBlock(World world, BlockPos pos) {
		BlockPos landPos = pos;
		Random rand = new Random();
		if (this.landOnSurface) {
			for(int tries = 0; tries < 20; ++tries) {
				landPos = Helper.getSafeHigherPos(world, pos.add(rand.nextInt(20) - 10, 0, rand.nextInt(20) - 10), getFacing());
				if(!landPos.equals(BlockPos.ORIGIN)) {
					return landPos;
				}
			}
			for(int t = 0; t < 20; t++) {
				landPos = Helper.getSafePosLower(landPos, world, getFacing());
				if(!landPos.equals(BlockPos.ORIGIN)) {
					return landPos;
				}
			}
			return pos;
		}
		for(int i = 0; i < 20; ++i) {
			landPos = Helper.getSafePosLower(pos.add(rand.nextInt(20) - 10, 0, rand.nextInt(20) - 10), world, this.getFacing());
			if(!landPos.equals(BlockPos.ORIGIN)) {
				return landPos;
			}
		}
		for(int i = 0; i < 20; ++i) {
			landPos = Helper.getSafeHigherPos(world, landPos, getFacing());
			if(!landPos.equals(BlockPos.ORIGIN)) {
				return landPos;
			}
		}
		return pos;
	}
	
	@Override
	public void readFromNBT(NBTTagCompound tag) {
		super.readFromNBT(tag);
		NBTTagCompound tardisTag = tag.getCompoundTag("tardis");
		{
			this.ticksToTravel = tardisTag.getInteger("timeLeft");
			this.tardisDestination = BlockPos.fromLong(tardisTag.getLong("tardisDest"));
			this.tardisLocation = BlockPos.fromLong(tardisTag.getLong("tardisLoc"));
			this.dimension = tardisTag.getInteger("dim");
			this.destDim = tardisTag.getInteger("destDim");
			this.fuel = tardisTag.getFloat("fuel");
			this.landOnSurface = tardisTag.getBoolean(NBT.LAND_ON_SURFACE);
			NBTTagList coordList = tardisTag.getTagList("coordList", Constants.NBT.TAG_COMPOUND);
			int i = 0;
			for (NBTBase base : coordList) {
				saveCoords.set(i, SpaceTimeCoord.readFromNBT((NBTTagCompound) base));
				++i;
			}
			
			// Components
			NBTTagList componentList = tardisTag.getTagList(NBT.COMPOENET_LIST, Constants.NBT.TAG_COMPOUND);
			int cListIndex = 0;
			for (NBTBase comp : componentList) {
				this.buffer.set(cListIndex, new ItemStack((NBTTagCompound) comp));
				++cListIndex;
			}
			this.totalTimeToTravel = tardisTag.getInteger(NBT.MAX_TIME);
			this.magnitude = tardisTag.getInteger(NBT.MAGNITUDE);
			this.hadsEnabled = tardisTag.getBoolean(NBT.HADS_ENABLED);
			this.blockTop = Block.getStateById(tardisTag.getInteger(NBT.EXTERIOR));
			this.fuelUseage = tardisTag.getFloat(NBT.FUEL_USAGE);
			
			List<BaseSystem> newSystems = new ArrayList<>();
			NBTTagList systemList = tardisTag.getTagList(NBT.SYSTEM_LIST, Constants.NBT.TAG_COMPOUND);
			for(NBTBase base : systemList) {
				NBTTagCompound systemTag = (NBTTagCompound)base;
				BaseSystem sys = TardisSystems.createFromName(systemTag.getString("id"));
				if(sys != null)sys.readFromNBT(systemTag);
				newSystems.add(sys);
			}
			if(newSystems != null) {
				for(BaseSystem sys : this.systems) {
					if(!newSystems.contains(sys)) {
						newSystems.add(sys);
					}
				}
				this.systems = newSystems.toArray(new BaseSystem[] {});
			}
			this.currentState = Enum.valueOf(EnumTardisState.class, tardisTag.getString(NBT.TARDIS_STATE_ID));
			this.isLocked = tardisTag.getBoolean(NBT.IS_LOCKED);
		}
	}
	
	public void setShouldLandOnSurface(boolean b) {
		this.landOnSurface = b;
		this.markDirty();
	}
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound tag) {
		
		NBTTagCompound tardisTag = new NBTTagCompound();
		{
			tardisTag.setInteger("timeLeft", this.ticksToTravel);
			tardisTag.setLong("tardisDest", this.tardisDestination.toLong());
			tardisTag.setLong("tardisLoc", this.tardisLocation.toLong());
			tardisTag.setInteger("dim", this.dimension);
			tardisTag.setInteger("destDim", this.destDim);
			tardisTag.setFloat("fuel", fuel);
			tardisTag.setBoolean(NBT.LAND_ON_SURFACE, this.landOnSurface);
			NBTTagList cList = new NBTTagList();
			for (SpaceTimeCoord co : saveCoords) {
				cList.appendTag(co.writeToNBT(new NBTTagCompound()));
			}
			tardisTag.setTag("coordList", cList);
			// Compoenents
			NBTTagList compoentList = new NBTTagList();
			for (ItemStack stack : buffer) {
				compoentList.appendTag(stack.writeToNBT(new NBTTagCompound()));
			}
			tardisTag.setTag(NBT.COMPOENET_LIST, compoentList);
			
			tardisTag.setInteger(NBT.MAX_TIME, this.totalTimeToTravel);
			tardisTag.setInteger(NBT.MAGNITUDE, this.magnitude);
			tardisTag.setBoolean(NBT.HADS_ENABLED, this.hadsEnabled);
			tardisTag.setInteger(NBT.EXTERIOR, Block.getStateId(this.blockTop));
			tardisTag.setFloat(NBT.FUEL_USAGE, this.fuelUseage);
			
			NBTTagList systemList = new NBTTagList();
			for(BaseSystem sys : systems) {
				if(sys != null) {
					String id = TardisSystems.getIdBySystem(sys);
					if(id == null || id.isEmpty()) {
						System.err.println(id + " IS NOT A VAILD ID");
						break;
					}
					NBTTagCompound sysTag = new NBTTagCompound();
					sysTag.setString("id", id);
					systemList.appendTag(sys.writetoNBT(sysTag));
				}
			}
			tardisTag.setTag(NBT.SYSTEM_LIST, systemList);
			tardisTag.setString(NBT.TARDIS_STATE_ID, this.currentState.name());
			tardisTag.setBoolean(NBT.IS_LOCKED, this.isLocked);
		}
		tag.setTag("tardis", tardisTag);
		
		return super.writeToNBT(tag);
	}
	
	public void setDesination(BlockPos pos, int dimension) {
		this.tardisDestination = pos.down();
		if(Helper.isDimensionBlocked(dimension))
			dimension = 0;
		this.destDim = dimension;
		this.markDirty();
		if (!world.isRemote) {
			DimensionType type = DimensionManager.getProviderType(dimension);
			if (type != null) this.targetDimName = type.getName();
			DimensionType currentType = DimensionManager.getProviderType(this.dimension);
			if (type != null) this.currentDimName = currentType.getName();
		}
	}
	
	public int calcTimeToTravel() {
		double dist = this.tardisLocation.getDistance(this.tardisDestination.getX(), this.tardisDestination.getY(), this.tardisDestination.getZ());
		return (int) ((dist / MAX_TARDIS_SPEED) + 400 + (dimension == destDim ? 0 : 300));
	}
	
	public BlockPos getDestination() {
		return this.tardisDestination;
	}
	
	public BlockPos getLocation() {
		return this.tardisLocation;
	}
	
	public void setLocation(BlockPos pos) {
		this.tardisLocation = pos.toImmutable();
		this.markDirty();
	}
	
	public int getTargetDim() {
		return destDim;
	}
	
	public int getTicks() {
		return this.ticksToTravel;
	}
	
	public boolean isInFlight() {
		return this.ticksToTravel > 0;
	}
	
	public void setLoading(boolean b) {
		this.isLoading = b;
		this.markDirty();
	}
	
	public boolean getLoading() {
		return this.isLoading;
	}
	
	public boolean isFueling() {
		return isFueling;
	}
	
	public void setFueling(boolean b) {
		isFueling = b;
		this.markDirty();
	}
	
	public void setFuel(float f) {
		fuel = MathHelper.clamp(f, 0.0F, 1.0F);
		this.markDirty();
	}
	
	public void setSpaceTimeCoordnate(SpaceTimeCoord co) {
		this.setDesination(co.getPos(), co.getDimension());
	}
	
	public boolean startFlight() {
		if (this.getDestination().equals(BlockPos.ORIGIN)) return false;
		if (fuel <= 0.0F || !getCanFly()) {
			world.playSound(null, this.getPos(), TSounds.engine_stutter, SoundCategory.BLOCKS, 1F, 1F);
			return false;
		}
		this.shouldDelayLoop = true;
		this.ticksToTravel = this.calcTimeToTravel();
		this.totalTimeToTravel = this.ticksToTravel;
		this.setLoading(false);
		this.setFueling(false);
		if (!world.isRemote) {
			WorldServer oWorld = world.getMinecraftServer().getWorld(dimension);
			if(oWorld.getTileEntity(tardisLocation.up()) != null) {				
				((TileEntityDoor) oWorld.getTileEntity(this.tardisLocation.up())).setDemat();
			}
			this.saveCoords.set(this.saveCoords.size() - 1, new SpaceTimeCoord(this.getLocation(), this.dimension));
			ForgeChunkManager.unforceChunk(tardisLocTicket, oWorld.getChunkFromBlockCoords(getLocation()).getPos());
			ForgeChunkManager.releaseTicket(tardisLocTicket);
		}
		this.markDirty();
		return true;
	}
	
	public boolean getCanFly() {
		for(BaseSystem s : systems) {
			if(s.shouldStopFlight())return false;
		}
		return true;
	}

	@Override
	public SPacketUpdateTileEntity getUpdatePacket() {
		return new SPacketUpdateTileEntity(pos, -1, this.getUpdateTag());
	}
	
	@Override
	public NBTTagCompound getUpdateTag() {
		NBTTagCompound tag = new NBTTagCompound();
		tag.setInteger("dim", dimension);
		tag.setInteger("destDim", this.destDim);
		tag.setLong("loc", this.getLocation().toLong());
		tag.setLong("dest", this.getDestination().toLong());
		tag.setFloat("fuel", fuel);
		tag.setInteger("timeLeft", ticksToTravel);
		tag.setInteger(NBT.MAX_TIME, this.totalTimeToTravel);
		tag.setString(NBT.CURRENT_DIM_NAME, this.currentDimName);
		tag.setString(NBT.TARGET_DIM_NAME, this.targetDimName);
		
		if(this.controls != null && this.controls.length > 0) {
			NBTTagList list = new NBTTagList();
			for(EntityControl e : this.controls) {
				list.appendTag(new NBTTagInt(e.getEntityId()));
			}
			tag.setTag(NBT.CONTROL_IDS, list);
		}
		
		tag.setInteger("facing", this.facing.getHorizontalIndex());
		tag.setInteger(NBT.EXTERIOR, Block.getStateId(this.blockTop));
		NBTTagList sysList = new NBTTagList();
		for(BaseSystem s : this.systems) {
			NBTTagCompound sT = new NBTTagCompound();
			sT.setString("id", TardisSystems.getIdBySystem(s));
			s.writetoNBT(sT);
			sysList.appendTag(sT);
		}
		tag.setTag(NBT.SYSTEM_LIST, sysList);
		tag.setString("course_correct", this.getCourseCorrect().name());
		return tag;
	}
	
	@Override
	public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt) {
		if (world.isRemote) {
			NBTTagCompound tag = pkt.getNbtCompound();
			this.destDim = tag.getInteger("destDim");
			this.dimension = tag.getInteger("dim");
			this.tardisDestination = BlockPos.fromLong(tag.getLong("dest"));
			this.tardisLocation = BlockPos.fromLong(tag.getLong("loc"));
			this.fuel = tag.getFloat("fuel");
			this.ticksToTravel = tag.getInteger("timeLeft");
			this.totalTimeToTravel = tag.getInteger(NBT.MAX_TIME);
			this.targetDimName = tag.getString(NBT.TARGET_DIM_NAME);
			this.currentDimName = tag.getString(NBT.CURRENT_DIM_NAME);
			
			NBTTagList list = tag.getTagList(NBT.CONTROL_IDS, Constants.NBT.TAG_INT);
			List<Entity> controls = new ArrayList<Entity>();
			for(NBTBase base : list) {
				if(base instanceof NBTTagInt) {
					int i = ((NBTTagInt)base).getInt();
					controls.add(world.getEntityByID(i));
				}
			}
			this.controls = controls.toArray(new EntityControl[0]);
			this.facing = EnumFacing.getHorizontal(tag.getInteger("facing"));
			this.blockTop = Block.getStateById(tag.getInteger(NBT.EXTERIOR));
			List<BaseSystem> systems = new ArrayList<BaseSystem>();
			for(NBTBase base : tag.getTagList(NBT.SYSTEM_LIST, Constants.NBT.TAG_COMPOUND)) {
				NBTTagCompound sysTag = (NBTTagCompound)base;
				BaseSystem system = TardisSystems.createFromName(sysTag.getString("id"));
				system.readFromNBT(sysTag);
				systems.add(system);
			}
			this.systems = systems.toArray(new BaseSystem[] {});
			this.setCourseEvent(Enum.valueOf(EnumCourseCorrect.class, tag.getString("course_correct")));
		}
	}
	
	public void setTargetDimension(int id) {
		this.setDesination(this.getLocation(), id);
	}
	
	@Override
	public void onLoad() {
		super.onLoad();
		updateServer();
	}
	
	public boolean createControls() {
		if(!world.isRemote) {
			if (controls == null || controls.length == 0) {
				List<EntityControl> ec = new ArrayList<EntityControl>();
				ec.add(new ControlLaunch(this));
				ec.add(new ControlX(this));
				ec.add(new ControlY(this));
				ec.add(new ControlZ(this));
				ec.add(new ControlDimChange(this));
				ec.add(new ControlRandom(this));
				ec.add(new ControlSTCLoad(this));
				ec.add(new ControlFlight(this));
				ec.add(new ControlFuel(this));
				ec.add(new ControlLandType(this));
				ec.add(new ControlDirection(this));
				ec.add(new ControlFastReturn(this));
				ec.add(new ControlTelepathicCircuts(this));
				ec.add(new ControlDoorSwitch(this));
				ec.add(new ControlMag(this));
				ec.add(new ControlPhone(this));
				ec.add(new ControlSonicSlot(this));
				ec.add(new ControlStabilizers(this));
				int id = 0;
				for(Vec3d pos : this.coordList) {
					ec.add(new ControlSTCButton(this, id, pos));
					++id;
				}
				for (EntityControl con : ec) {
					con.setPosition(this.getPos().getX() + con.getOffset(this).x + 0.5, this.getPos().getY() + con.getOffset(this).y + 1, this.getPos().getZ() + con.getOffset(this).z + 0.5);
					world.spawnEntity(con);
					
				}
				this.controls = ec.toArray(new EntityControl[] {});
				return true;
			}
		}
		return false;
	}
	
	@Override
	public void invalidate() {
		ForgeChunkManager.releaseTicket(tardisTicket);
		ForgeChunkManager.releaseTicket(tardisLocTicket);
		for(EntityControl cont : controls) {
			System.out.println("Killing: " + cont.getDisplayName().getFormattedText());
			cont.setDead();
		}
		super.invalidate();
	}
	
	@Override
	public AxisAlignedBB getRenderBoundingBox() {
		return Block.FULL_BLOCK_AABB.offset(this.getPos()).grow(3D);
	}
	
	public <T>T getControl(Class<T> clazz) {
		if(this.controls == null || this.controls.length == 0) {
			this.createControls();
		}
		for(EntityControl c : this.controls) {
			if(c.getClass() == clazz)
				return (T)c;
		}
		return null;
	}
	
	public void crash(boolean explode) {
		if (!world.isRemote) {
			WorldServer ws = world.getMinecraftServer().getWorld(dimension);
			BlockPos crashSite = this.getCurrentPosOnPath();
			System.out.println("Land: " + this.getLocation() + "Crash: " + crashSite);
			this.setDesination(crashSite, dimension);
			if(explode) {
				ws.createExplosion(null, crashSite.getX(), crashSite.getY(), crashSite.getZ(), 3F, true);
				world.playSound(null, this.getPos(), SoundEvents.ENTITY_GENERIC_EXPLODE, SoundCategory.BLOCKS, 1F, 1F);
			}
			this.travel();
			world.playSound(null, this.getPos(), TSounds.cloister_bell, SoundCategory.BLOCKS, 1F, 1F);
		} else if(explode){
			Random rand = new Random();
			for (int i = 0; i < 300; ++i) {
				world.spawnParticle(EnumParticleTypes.EXPLOSION_NORMAL, getPos().getX() + (rand.nextInt(3) - 1), getPos().getY() + (rand.nextInt(3) - 1), getPos().getZ() + (rand.nextInt(3) - 1), 0, 1, 0, 0);
			}
		}
		for(BaseSystem s : systems) {
			s.damage();
		}
	}
	
	public void crash() {
		crash(true);
	}
	
	public void startHADS() {
		if(!world.isRemote && this.hadsEnabled) {
			this.setDesination(this.getLocation().add(rand.nextInt(20) - 10, 0, rand.nextInt(20) - 10), dimension);
			this.startFlight();
			WorldServer ws = world.getMinecraftServer().getWorld(dimension);
			ws.setBlockState(this.getLocation(), Blocks.AIR.getDefaultState());
			ws.setBlockState(this.getLocation().up(), Blocks.AIR.getDefaultState());
		}
	}
	
	public static class NBT {
		public static final String IS_LOCKED = "is_locked";
		public static final String TARDIS_STATE_ID = "tardis_state_id";
		public static final String SYSTEM_LIST = "system_list";
		public static final String FUEL_USAGE = "fuelUseage";
		public static final String HADS_ENABLED = "isHADSEnabled";
		public static final String CONTROL_IDS = "control_ids";
		public static final String COMPOENET_LIST = "componentList";
		public static final String LAND_ON_SURFACE = "landOnGround";
		public static final String MAX_TIME = "maxTime";
		public static final String TARGET_DIM_NAME = "targetDimName";
		public static final String CURRENT_DIM_NAME = "currentDimName";
		public static final String MAGNITUDE = "magnitude";
		public static final String EXTERIOR = "exterior";
	}
	
	public EnumFacing getFacing() {
		return this.facing;
	}
	
	public void setFacing(EnumFacing facing) {
		this.facing = facing;
		this.markDirty();
	}
	
	@Override
	public String getName() {
		return "TARDIS";
	}
	
	@Override
	public boolean hasCustomName() {
		return false;
	}
	
	@Override
	public int getSizeInventory() {
		return buffer.size();
	}
	
	@Override
	public boolean isEmpty() {
		return buffer.isEmpty();
	}
	
	@Override
	public ItemStack getStackInSlot(int index) {
		if (index >= 0 && index < buffer.size()) {
			return buffer.get(index);
		} else
			return ItemStack.EMPTY;
	}
	
	@Override
	public ItemStack decrStackSize(int index, int count) {
		ItemStack stack = this.getStackInSlot(index);
		ItemStack newStack = stack.splitStack(count);
		this.setInventorySlotContents(index, stack);
		this.markDirty();
		return newStack;
	}
	
	@Override
	public ItemStack removeStackFromSlot(int index) {
		ItemStack stack = this.getStackInSlot(index);
		this.setInventorySlotContents(index, ItemStack.EMPTY);
		return stack;
	}
	
	@Override
	public void setInventorySlotContents(int index, ItemStack stack) {
		if (index >= 0 && index < buffer.size()) buffer.set(index, stack);
		this.markDirty();
	}
	
	@Override
	public int getInventoryStackLimit() {
		return 64;
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
		buffer.clear();
	}

	public void setExterior(IBlockState state) {
		this.blockTop = state;
		this.markDirty();
	}
	
	public void setHADS(boolean b) {
		this.hadsEnabled = b;
		this.markDirty();
	}
	
	public boolean isHADSEnabled() {
		return this.hadsEnabled;
	}
	
	public IBlockState getTopBlock() {
		return this.blockTop == null ? TBlocks.tardis_top.getDefaultState() : this.blockTop;
	}
	
	public float calcFuelUse() {
		return defaultFuelUse + (this.hadsEnabled ? (defaultFuelUse * 1.5F) : 0F);
	}

	public int getTimeLeft() {
		return this.ticksToTravel;
	}
	
	private BaseSystem[] createSystems() {
		List<BaseSystem> systems = new ArrayList<>();
		for(String s : TardisSystems.SYSTEMS.keySet()) {
			BaseSystem system = TardisSystems.createFromName(s);
			if(system != null)systems.add(system);
		}
		return systems == null ? new BaseSystem[] {new SystemFlight()} : systems.toArray(new BaseSystem[] {});
		
	}
	
	public EnumTardisState getTardisState() {
		return this.currentState;
	}
	
	public void setTardisState(EnumTardisState state) {
		this.currentState = state;
		this.markDirty();
	}

	public void transferPlayer(Entity entity, boolean checkDoors) {
		WorldServer ws = world.getMinecraftServer().getWorld(dimension);
		if(ws == null)return;
		MinecraftForge.EVENT_BUS.post(new TardisExitEvent(entity, this.getPos()));
		BlockPos pos = this.getLocation();
		if(checkDoors) {
			TileEntityDoor door = (TileEntityDoor)ws.getTileEntity(this.getLocation().up());
			if(door != null) {
				EnumFacing face = ws.getBlockState(door.getPos()).getValue(BlockTardisTop.FACING);
				pos = door.getPos().down().offset(face, 2);
				if(entity instanceof EntityPlayerMP) {
					EntityPlayerMP player = (EntityPlayerMP)entity;
					if(player.dimension != dimension) world.getMinecraftServer().getPlayerList().transferPlayerToDimension(player, dimension, new TardisTeleporter());
					player.connection.setPlayerLocation(pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5, Helper.get360FromFacing(face), 0);
				}
				else if(!(entity instanceof EntityPlayer)){
					entity.setPosition(pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5);
					entity.changeDimension(TDimensions.TARDIS_ID, new TardisTeleporter());
				}
			}
		}
		else {
			if(entity instanceof EntityPlayerMP) {
				EntityPlayerMP player = (EntityPlayerMP)entity;
				if(player.dimension != dimension) world.getMinecraftServer().getPlayerList().transferPlayerToDimension(player, dimension, new TardisTeleporter());
				player.connection.setPlayerLocation(pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5, 0, 0);
			}
		}
	}

	public void enterTARDIS(Entity entity) {
		if(this.getTardisState() != EnumTardisState.NORMAL) return;
		MinecraftForge.EVENT_BUS.post(new TardisEnterEvent(entity, this.getPos()));
		ControlDoor door = this.getDoor();
		Vec3d pos;
		EnumFacing face = EnumFacing.NORTH;
		if(door == null) {
			pos = Helper.blockPosToVec3d(this.getPos());
		}
		else {
			pos = door.getPositionVector().add(door.getLookVec());
			face = door.getHorizontalFacing();
		}
		
		if(entity instanceof EntityPlayerMP) {
			EntityPlayerMP player = (EntityPlayerMP)entity;
			if(player.dimension != TDimensions.TARDIS_ID)
				world.getMinecraftServer().getPlayerList().transferPlayerToDimension(player, TDimensions.TARDIS_ID, new TardisTeleporter());
			player.connection.setPlayerLocation(pos.x, pos.y, pos.z, Helper.get360FromFacing(face), 0);
		}
		else if(!(entity instanceof EntityPlayer)){
			entity.setPosition(pos.x, pos.y, pos.z);
			entity.changeDimension(TDimensions.TARDIS_ID, new TardisTeleporter());
		}
	}
	
	public <T> T getSystem(Class<T> system) {
		for(BaseSystem sys : this.systems) {
			if(sys.getClass() == system) {
				return (T)sys;
			}
		}
		return null;
	}
	
	/**
	 * Returns all tile entities in the interior (8 chunks in all directions);
	 * @return
	 */
	public List<TileEntity> getTilesInTardis(){
		List<TileEntity> tes = new ArrayList<TileEntity>();
		ChunkPos pos = this.world.getChunkFromBlockCoords(this.getLocation()).getPos();
		for(int x = -8; x < 8; ++x) {
			for(int z = -8; z < 8; ++z) {
				tes.addAll(world.getChunkFromChunkCoords(pos.x + x, pos.z + z).getTileEntityMap().values());
			}
		}
		return tes;
	}
	
	public double getPower() {
		return this.power;
	}
	
	public void setPower(double power) {
		this.power = power;
		this.markDirty();
	}

	public ControlDoor getDoor() {
		for(ControlDoor door : world.getEntitiesWithinAABB(ControlDoor.class, Block.FULL_BLOCK_AABB.offset(this.getPos()).grow(40))) {
			return door;
		}
		return null;
	}

	public boolean isLocked() {
		return this.isLocked;
	}
	
	public void setLocked(boolean b) {
		this.isLocked = b;
		this.markDirty();
	}
	
	public BlockPos getCurrentPosOnPath() {
		if(this.isInFlight()) {
			BlockPos dist = this.getDestination().subtract(this.getLocation());
			return this.getLocation().add(Helper.scaleBP(dist, this.ticksToTravel / (float)this.totalTimeToTravel));
		}
		return this.getLocation();
	}
	
	public void setHealth(float health) {
		this.hullHealth = MathHelper.clamp(health, 0.0F, 1.0F);
		this.markDirty();
	}
	
	public float getHealth() {
		return this.hullHealth;
	}
	
	public void setCourseEvent(EnumCourseCorrect event) {
		this.courseCorrect = event;
	}
	
	public EnumCourseCorrect getCourseCorrect() {
		return this.courseCorrect;
	}
	
	public static enum EnumCourseCorrect{
		NONE(null, ""),
		DIRECTION_X(ControlX.class, "course.tardis.direction.x"),
		DIRECTION_Y(ControlY.class, "course.tardis.direction.y"),
		DIRECTION_Z(ControlZ.class, "course.tardis.direction.z"),
		DIMENSION(ControlDimChange.class, "course.tardis.dimension");
		
		Class<? extends EntityControl> control;
		String langKey = "";
		
		EnumCourseCorrect(Class<? extends EntityControl> con, String nameKey) {
			control = con;
			langKey = nameKey;
		}
		
		public Class<? extends EntityControl> getControl(){
			return control;
		}
		
		public TextComponentTranslation getTranslation() {
			return new TextComponentTranslation(langKey);
		} 
	}
}
