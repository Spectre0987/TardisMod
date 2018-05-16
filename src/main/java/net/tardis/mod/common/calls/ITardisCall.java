package net.tardis.mod.common.calls;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.tardis.api.controls.SpaceTimeCoord;

/**
 * 
 * @author Spectre
 *
 */
public interface ITardisCall {
	
	/**
	 * Perform a function when a player answers a call
	 * @param world World in which player anwsers a call (Usually the TARDIS Dimension)
	 * @param player The Player that answers the call
	 * @param consolePos Position of the console this call was sent to
	 */
	void onAwnsered(World world, EntityPlayer player, BlockPos consolePos);
	
	/**
	 * Gets Space-Time Coordinate of the event
	 * 
	 * @return SpaceTimeCoord
	 */
	SpaceTimeCoord getSpaceTimeCoords();

}
