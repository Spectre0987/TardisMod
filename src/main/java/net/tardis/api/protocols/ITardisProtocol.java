package net.tardis.api.protocols;

import net.minecraft.world.World;
import net.tardis.mod.tileentity.TileEntityTardis;

public interface ITardisProtocol {

	/**
	 * 
	 * @param world
	 * @param tardis
	 */
	void onActivated(World world,TileEntityTardis tardis);
	
	String getNameKey();
}
