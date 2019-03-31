package net.tardis.mod.capability;

import java.util.Objects;
import java.util.UUID;

import javax.annotation.Nonnull;

import com.google.common.base.Predicate;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.tardis.mod.Tardis;
import net.tardis.mod.capability.TardisCapStorage.TardisCapProvider;
import net.tardis.mod.common.blocks.BlockTardisTop;
import net.tardis.mod.common.blocks.TBlocks;
import net.tardis.mod.common.dimensions.TDimensions;
import net.tardis.mod.common.sounds.TSounds;
import net.tardis.mod.common.systems.TardisSystems;
import net.tardis.mod.common.tileentity.TileEntityDoor;
import net.tardis.mod.common.tileentity.TileEntityTardis;
import net.tardis.mod.network.NetworkHandler;
import net.tardis.mod.network.packets.MessageCapabilityDoorOpen;
import net.tardis.mod.network.packets.MessagePlayFlySound;
import net.tardis.mod.network.packets.MessageSyncCap;
import net.tardis.mod.util.common.helpers.PlayerHelper;
import net.tardis.mod.util.common.helpers.TardisHelper;

public class CapabilityTardis implements ITardisCap {
	
	private EntityPlayer player;
	private BlockPos pos = BlockPos.ORIGIN;
	private boolean isInFlight;
	private int exterior = Block.getStateId(TBlocks.tardis_top.getDefaultState());
	private boolean hasFuel = true;
	private int timeOnGround = 0;
	private boolean isOpen = false;
	private static AttributeModifier mod = new AttributeModifier(UUID.fromString("ad4ab5e6-6904-4429-9883-15ac8aeef97d"), "Flight mode", 0.12F, 0);
	
	public CapabilityTardis() {
	}
	
	public CapabilityTardis(EntityPlayer player) {
		this.player = player;
	}
	
	@Override
	public void setTardis(BlockPos pos) {
		this.pos = pos;
	}
	
	@Override
	public BlockPos getTardis() {
		return pos;
	}
	
	@Override
	public boolean isInFlight() {
		return isInFlight;
	}
	
	@Override
	public void setInFlight(boolean inFlight) {
		isInFlight = inFlight;
	}
	
	private static final Predicate<Entity> ENTITY = p_apply_1_ -> p_apply_1_ instanceof EntityLiving;
	
	@Override
	public void update() {
		if (!isInFlight && player.capabilities.getFlySpeed() == 0.75F) {
			setSpeeds(player, true);
		}
		
		//Set the players Tardis position when they are in the Tardis Dimension
		if (getTardis().equals(BlockPos.ORIGIN) && player.dimension == TDimensions.TARDIS_ID) {
			setTardis(TardisHelper.getTardisForPosition(player.getPosition()));
		} else {
			
			if (!getTardis().equals(BlockPos.ORIGIN) && player.dimension != TDimensions.TARDIS_ID && !isInFlight) {
				setTardis(BlockPos.ORIGIN);
			}
		}
		
		if (player.dimension != TDimensions.TARDIS_ID) {
			if (!getTardis().equals(BlockPos.ORIGIN)) {
				if (isInFlight()) {
					
					if (isOpen()) {
						//	for (Entity entity : player.world.getEntitiesInAABBexcluding(player, player.getCollisionBoundingBox().grow(4), ENTITY)) {
						//		if (entity != null && !entity.isDead) {
						//			ITardisCap cap = get(player);
						//			TileEntityTardis console = TardisHelper.getConsole(cap.getTardis());
						//			if (console != null) {
						//				console.enterTARDIS(entity);
						//			}
						//		}
						//	}
					}
					
					if (player.world.getBlockState(player.getPosition().down()).getBlock() != Blocks.AIR) {
						timeOnGround++;
					}
					else {
						timeOnGround = 0;
					}
					
					if (timeOnGround >= 50 && player.isSneaking()) {
						endFlight(player);
					}
					
					if (hasFuel) {
						if(!player.capabilities.allowFlying) {
							player.capabilities.allowFlying = true;
							setSpeeds(player, false);
							player.velocityChanged = true;
						}
					} else {
						if (player.ticksExisted % 100 == 0) {
							player.world.playSound(null, player.getPosition(), TSounds.cloister_bell, SoundCategory.BLOCKS, 0.5F, 1F);
						}
						setSpeeds(player, true);
						player.capabilities.isFlying = false;
						player.capabilities.allowFlying = false;
						player.velocityChanged = true;
					}
				} else {
					endFlight(player);
					setTardis(BlockPos.ORIGIN);
				}
			}
		} else {
			if (isInFlight()) {
				endFlight(player);
			}
		}
		if(player.dimension == TDimensions.TARDIS_ID && !this.getTardis().equals(BlockPos.ORIGIN)) {
			if(player.getPosition().distanceSq(this.getTardis()) > 16384)
				player.setPositionAndUpdate(this.getTardis().getX(), this.getTardis().getY() + 2, this.getTardis().getZ());
		}
	}
	
	public static void setSpeeds(EntityPlayer player, boolean reset) {
		if(reset || player.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).hasModifier(mod))
			player.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).removeModifier(mod.getID());
		else player.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).applyModifier(mod);
	}
	
	@Override
	public void setExterior(IBlockState exterior) {
		this.exterior = Block.getStateId(exterior);
	}
	
	@Override
	public IBlockState getExterior() {
		return Block.getStateById(exterior);
	}
	
	@Override
	public void sync() {
		NetworkHandler.NETWORK.sendToAll(new MessageSyncCap(player, serializeNBT()));
	}
	
	@Override
	public void setHasFuel(boolean b) {
		hasFuel = b;
	}
	
	@Override
	public boolean hasFuel() {
		return hasFuel;
	}
	
	@Override
	public int timeOnGround() {
		return timeOnGround;
	}
	
	@Override
	public void setTimeOnGround(int time) {
		timeOnGround = time;
	}
	
	@Override
	public void setDoorsOpen(boolean open) {
		isOpen = open;
	}
	
	@Override
	public boolean isOpen() {
		return isOpen;
	}
	
	@Override
	public NBTTagCompound serializeNBT() {
		NBTTagCompound nbt = new NBTTagCompound();
		nbt.setInteger("exterior", exterior);
		nbt.setBoolean("inFlight", isInFlight);
		nbt.setBoolean("hasFuel", hasFuel());
		nbt.setInteger("groundTime", timeOnGround);
		nbt.setBoolean("open", isOpen);
		return nbt;
	}
	
	@Override
	public void deserializeNBT(NBTTagCompound nbt) {
		exterior = nbt.getInteger("exterior");
		isInFlight = nbt.getBoolean("inFlight");
		hasFuel = nbt.getBoolean("hasFuel");
		timeOnGround = nbt.getInteger("groundTime");
		isOpen = nbt.getBoolean("open");
	}
	
	
	//===== CAPABILITY EVENTS =====
	@Mod.EventBusSubscriber(modid = Tardis.MODID)
	public static class Events {
		
		@SubscribeEvent
		public static void attach(AttachCapabilitiesEvent<Entity> event) {
			if (event.getObject() instanceof EntityPlayer)
				event.addCapability(new ResourceLocation(Tardis.MODID, "tardis_cap"), new TardisCapProvider((EntityPlayer) event.getObject()));
		}
		
		@SubscribeEvent
		public static void update(LivingUpdateEvent event) {
			CapabilityTardis cap = event.getEntityLiving().getCapability(TardisCapStorage.CAP, null);
			if (cap != null)
				cap.update();
		}
		
		@SubscribeEvent
		public static void onPlayerClone(PlayerEvent.Clone event) {
			Capability.IStorage storage = TardisCapStorage.CAP.getStorage();
			
			ITardisCap oldCap = get(event.getOriginal());
			ITardisCap newCap = get(event.getEntityPlayer());
			
			NBTTagCompound nbt = (NBTTagCompound) storage.writeNBT(TardisCapStorage.CAP, oldCap, null);
			storage.readNBT(TardisCapStorage.CAP, newCap, null, nbt);
			get(event.getEntityPlayer()).sync();
		}
		
		@SubscribeEvent
		public static void playerTracking(PlayerEvent.StartTracking event) {
			get(event.getEntityPlayer()).sync();
		}
		
		@SubscribeEvent
		public static void onPunch(PlayerInteractEvent.RightClickEmpty empty) {
			EntityPlayer pilot = empty.getEntityPlayer();
			ITardisCap data = get(pilot);
			if (data.isInFlight()) {
				NetworkHandler.NETWORK.sendToServer(new MessageCapabilityDoorOpen());
			}
		}
		
		@SubscribeEvent
		public static void onBreakBlock(BlockEvent.BreakEvent event){
			EntityPlayer breaker = event.getPlayer();
			ITardisCap data = get(breaker);
			event.setCanceled(data.isInFlight());
		}
		
		@SubscribeEvent
		public static void onHurtPilot(LivingHurtEvent event){
			if(event.getEntity() instanceof EntityPlayer){
				EntityPlayer victim = (EntityPlayer) event.getEntity();
				ITardisCap data = get(victim);
				event.setAmount(0.0F);
				for (TardisSystems.BaseSystem s : Objects.requireNonNull(TardisHelper.getConsole(data.getTardis())).systems) {
					s.damage();
				}
				victim.world.playSound(null, victim.getPosition(), TSounds.cloister_bell, SoundCategory.BLOCKS, 0.5F, 1F);
			}
		}
		
	}
	
	//===== HELPERS =====
	
	@Nonnull
	public static ITardisCap get(EntityPlayer player) {
		if (player.hasCapability(TardisCapStorage.CAP, null)) {
			return player.getCapability(TardisCapStorage.CAP, null);
		}
		throw new IllegalStateException("Missing Tardis capability: " + player + ", please report this to the issue tracker");
	}
	
	public static void setupFlight(EntityPlayer player) {
		setSpeeds(player, false);
		ITardisCap cap = get(player);
		cap.setTimeOnGround(0);
		TileEntityTardis console = TardisHelper.getConsole(cap.getTardis());
		if (console != null && !console.hasPilot() && console.fuel > 0) {
			console.setFlightPilot(player);
			console.transferPlayer(player, false);
			NetworkHandler.NETWORK.sendToDimension(new MessagePlayFlySound(TSounds.flyLoop, player.getUniqueID().toString()), player.dimension);
			cap.setInFlight(true);
			cap.setExterior(console.getTopBlock());
			cap.setHasFuel(true);
			player.capabilities.allowFlying = true;
			player.capabilities.isFlying = true;
			player.capabilities.allowEdit = false;
			player.capabilities.disableDamage = true;
			player.motionY += 8;
			player.velocityChanged = true;
			player.eyeHeight = 2;
			player.sendPlayerAbilities();
			player.setEntityInvulnerable(true);
			cap.sync();
			WorldServer world = DimensionManager.getWorld(console.dimension);
			world.setBlockState(console.getLocation(), Blocks.AIR.getDefaultState());
			world.setBlockState(console.getLocation().up(), Blocks.AIR.getDefaultState());
		} else {
			PlayerHelper.sendMessage(player, new TextComponentTranslation("tardis.message.has_pilot"), true);
		}
	}
	
	public static void endFlight(EntityPlayer player) {
		setSpeeds(player, true);
		ITardisCap cap = get(player);
		TileEntityTardis console = TardisHelper.getConsole(cap.getTardis());
		BlockPos bPos = player.getPosition();
		if (console != null) {
			cap.setInFlight(false);
			console.enterTARDIS(player);
			player.capabilities.allowFlying = player.isCreative();
			player.capabilities.isFlying = player.isCreative();
			player.capabilities.allowEdit = true;
			player.capabilities.disableDamage = false;
			player.velocityChanged = true;
			player.setEntityInvulnerable(player.isCreative());
			cap.sync();
			player.eyeHeight = player.getDefaultEyeHeight();
			player.sendPlayerAbilities();
			
			if (player.world.isRemote) return;
			WorldServer exteriorWorld = FMLCommonHandler.instance().getMinecraftServerInstance().getWorld(console.dimension);
			
			exteriorWorld.getChunkProvider().loadChunk(bPos.getX() * 16, bPos.getZ() * 16);
			exteriorWorld.setBlockState(bPos, TBlocks.tardis.getDefaultState());
			exteriorWorld.setBlockState(bPos.up(), console.getTopBlock().withProperty(BlockTardisTop.FACING, player.getHorizontalFacing()));
			BlockPos consolePos = cap.getTardis();
			exteriorWorld.addScheduledTask(() -> {
				TileEntity door = exteriorWorld.getTileEntity(bPos.up());
				if (door instanceof TileEntityDoor) {
					((TileEntityDoor) door).setConsolePos(consolePos);
					((TileEntityDoor) Objects.requireNonNull(exteriorWorld.getTileEntity(bPos.up()))).forceVisible();
				}
			});
			console.setLocation(bPos);
			console.setFlightPilot(null);
			cap.setTimeOnGround(0);
		}
	}
	
}
