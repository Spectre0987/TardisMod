package net.tardis.mod.common.protocols;

import net.minecraft.world.World;
import net.tardis.mod.common.tileentity.TileEntityTardis;

public class ProtocolLock implements ITardisProtocol {

	@Override
	public void onActivated(World world, TileEntityTardis tardis) {
		if(!world.isRemote) {
			tardis.setLocked(!tardis.isLocked());
		}
	}

	@Override
	public String getNameKey() {
		return "protocol.tardis.lock";
	}

}
