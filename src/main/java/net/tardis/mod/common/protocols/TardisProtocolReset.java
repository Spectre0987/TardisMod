package net.tardis.mod.common.protocols;

import net.minecraft.world.World;
import net.tardis.mod.Tardis;
import net.tardis.mod.common.tileentity.TileEntityTardis;
import net.tardis.mod.packets.MessageControlReset;

public class TardisProtocolReset implements ITardisProtocol {

	@Override
	public void onActivated(World world, TileEntityTardis tardis) {
		if(world.isRemote) {
			Tardis.NETWORK.sendToServer(new MessageControlReset(tardis.getPos()));
		}
	}

	@Override
	public String getNameKey() {
		return "tardis.protocol.reset";
	}

}
