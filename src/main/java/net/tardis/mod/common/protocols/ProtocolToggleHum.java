package net.tardis.mod.common.protocols;

import net.minecraft.world.World;
import net.tardis.mod.Tardis;
import net.tardis.mod.common.tileentity.TileEntityTardis;
import net.tardis.mod.proxy.ServerProxy.GuiContext;

public class ProtocolToggleHum implements ITardisProtocol {
	
	@Override
	public void onActivated(World world, TileEntityTardis tardis) {
		Tardis.proxy.openGui(0, new GuiContext(world.provider.getDimension(), 0, tardis.getPos()));
	}
	
	@Override
	public String getNameKey() {
		return "protocol.hum";
	}
}
