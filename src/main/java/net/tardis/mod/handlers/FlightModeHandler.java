package net.tardis.mod.handlers;


import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.fml.common.Mod;
import net.tardis.mod.Tardis;
import net.tardis.mod.common.blocks.BlockTardisTop;
import net.tardis.mod.common.blocks.TBlocks;
import net.tardis.mod.common.dimensions.TDimensions;
import net.tardis.mod.common.tileentity.TileEntityTardis;

@Mod.EventBusSubscriber(modid = Tardis.MODID)
public class FlightModeHandler {
	
	public static boolean start(EntityPlayer player, TileEntityTardis tileEntityTardis) {
		Tardis.LOG.warn("Starting real world flight for: " + player.getName());
		int dim = tileEntityTardis.getTargetDim();
		BlockPos oldPos = tileEntityTardis.getLocation();
		WorldServer dimWorld = DimensionManager.getWorld(dim);
		
		//We want to remove the old exterior, if we don't we dupe
		dimWorld.setBlockToAir(oldPos);
		dimWorld.setBlockToAir(oldPos.down());
		
		tileEntityTardis.transferPlayer(player, false); //We move the player to the place the exterior was
		
		//We deny the player the right to break blocks and allow them to fly
		player.capabilities.allowFlying = true;
		player.capabilities.isFlying = true;
		player.capabilities.allowEdit = false;
		player.capabilities.disableDamage = true;
		setPlayerFlying(player, true, tileEntityTardis.getPos().south(4));
		return true;
	}
	
	public static void update(EntityPlayer player, TileEntityTardis tileEntityTardis) {
	
	}
	
	
	public static void stop(EntityPlayer player, TileEntityTardis tileEntityTardis) {
		Tardis.LOG.warn("Ending real world flight for: " + player.getName());
		int dim = tileEntityTardis.getTargetDim();
		WorldServer dimWorld = DimensionManager.getWorld(dim);
		
		//Let's put the exterior back again
		dimWorld.setBlockState(player.getPosition(), TBlocks.tardis.getDefaultState());
		dimWorld.setBlockState(player.getPosition().up(), tileEntityTardis.getTopBlock().withProperty(BlockTardisTop.FACING, tileEntityTardis.facing));
		
		tileEntityTardis.setLocation(player.getPosition()); //We tell the tardis that we have moved, what uses is moving otherwise
		
		tileEntityTardis.enterTARDIS(player); //We return the the player to the interior
		
		//We allow the player the right to break blocks and deny them flight
		player.capabilities.allowFlying = false;
		player.capabilities.isFlying = false;
		player.capabilities.allowEdit = true;
		player.capabilities.disableDamage = false;
		setPlayerFlying(player, false, null);
	}
	
	//Sets whether the player is flying or not
	public static void setPlayerFlying(EntityPlayer player, boolean flying, BlockPos pos) {
		player.getEntityData().setBoolean(Tardis.MODID + ":flying", flying);
		player.getEntityData().setInteger(Tardis.MODID + ":tardisdim", getDimension());
		if (pos != null) {
			player.getEntityData().setLong(Tardis.MODID + ":pos", pos.toLong());
		}
	}
	
	public static int getDimension() {
		return TDimensions.TARDIS_ID;
	}
	
	//Returns whether the player is flying or not
	public static boolean isPlayerFlying(EntityPlayer player) {
		NBTTagCompound data = player.getEntityData();
		if (data.hasKey(Tardis.MODID + ":flying")) {
			return data.getBoolean(Tardis.MODID + ":flying");
		} else {
			setPlayerFlying(player, false, null);
		}
		return false;
	}
	
	public BlockPos getInteriorPos(EntityPlayer player) {
		return BlockPos.fromLong(player.getEntityData().getLong(Tardis.MODID + ":pos"));
	}
}
