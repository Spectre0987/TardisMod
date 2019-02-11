package net.tardis.mod.common.protocols;

import net.minecraft.world.World;
import net.tardis.mod.common.tileentity.TileEntityTardis;

public interface ITardisProtocol {

	/**
	 * @param world
	 * @param tardis
	 */
	void onActivated(World world, TileEntityTardis tardis);

	String getNameKey();
}
