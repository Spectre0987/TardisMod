package net.tardis.mod.capability;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.tardis.mod.Tardis;
import net.tardis.mod.capability.TardisCapStorage.TardisCapProvider;
import net.tardis.mod.client.EnumExterior;
import net.tardis.mod.common.blocks.BlockTardisTop;
import net.tardis.mod.common.blocks.TBlocks;
import net.tardis.mod.common.dimensions.TDimensions;
import net.tardis.mod.common.tileentity.TileEntityTardis;
import net.tardis.mod.network.NetworkHandler;
import net.tardis.mod.network.packets.MessageSyncCap;
import net.tardis.mod.util.common.helpers.TardisHelper;

import javax.annotation.Nonnull;
import java.awt.*;

public class CapabilityTardis implements ITardisCap {
	
	private EntityPlayer player;
	private BlockPos pos = BlockPos.ORIGIN;
	private boolean isInFlight;
	private int exterior = Block.getStateId(TBlocks.tardis_top.getDefaultState());
	
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
	
	@Override
	public void update() {
		
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
					//TODO FLIGHT STUFF
				} else {
					endFlight(player);
					setTardis(BlockPos.ORIGIN);
				}
			}
		} else {
			if(isInFlight()){
				endFlight(player);
			}
		}
		
		
		//Return the player to the interior when they attempt to move away from it 
		if (player.dimension == TDimensions.TARDIS_ID && !getTardis().equals(BlockPos.ORIGIN)) {
			if (player.getPosition().distanceSq(getTardis()) > 16384)
				player.setPositionAndUpdate(getTardis().getX(), getTardis().getY(), getTardis().getZ());
		}
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
	public NBTTagCompound serializeNBT() {
		NBTTagCompound nbt = new NBTTagCompound();
		nbt.setInteger("exterior", exterior);
		nbt.setBoolean("inFlight", isInFlight);
		return nbt;
	}
	
	@Override
	public void deserializeNBT(NBTTagCompound nbt) {
		exterior = nbt.getInteger("exterior");
		isInFlight = nbt.getBoolean("inFlight");
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
		ITardisCap cap = CapabilityTardis.get(player);
		TileEntityTardis console = TardisHelper.getConsole(cap.getTardis());
		if (console != null) {
			cap.setInFlight(true);
			cap.setExterior(console.getTopBlock());
			player.capabilities.allowFlying = true;
			player.capabilities.isFlying = true;
			player.capabilities.allowEdit = false;
			player.capabilities.disableDamage = true;
			cap.sync();
			console.transferPlayer(player, false);
			WorldServer world = DimensionManager.getWorld(console.dimension);
			world.setBlockState(console.getLocation(), Blocks.DIAMOND_ORE.getDefaultState());
			world.setBlockState(console.getLocation().up(), Blocks.DIAMOND_ORE.getDefaultState());
		}
	}
	
	public static void endFlight(EntityPlayer player) {
		ITardisCap cap = CapabilityTardis.get(player);
		TileEntityTardis console = TardisHelper.getConsole(cap.getTardis());
		if (console != null) {
			cap.setInFlight(false);
			player.capabilities.allowFlying = player.isCreative();
			player.capabilities.isFlying = player.isCreative();
			player.capabilities.allowEdit = true;
			player.capabilities.disableDamage = false;
			cap.sync();
			player.world.setBlockState(player.getPosition(), TBlocks.tardis.getDefaultState());
			player.world.setBlockState(player.getPosition(), console.getTopBlock().withProperty(BlockTardisTop.FACING, player.getHorizontalFacing()));
			console.setLocation(player.getPosition());
			console.enterTARDIS(player);
		}
	}
	
}
