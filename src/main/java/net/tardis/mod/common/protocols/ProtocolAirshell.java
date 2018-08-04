package net.tardis.mod.common.protocols;

import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.tardis.mod.common.entities.EntityAirshell;
import net.tardis.mod.common.tileentity.TileEntityTardis;

public class ProtocolAirshell implements ITardisProtocol {

	public ProtocolAirshell() {}
	
	@Override
	public void onActivated(World world, TileEntityTardis tardis) {
		if(!world.isRemote && tardis.fuel > 0.05F) {
			WorldServer ws = ((WorldServer)world).getMinecraftServer().getWorld(tardis.dimension);
			EntityAirshell air = new EntityAirshell(ws);
			air.setPosition(tardis.getLocation().getX() + 0.5, tardis.getLocation().getY(), tardis.getLocation().getZ() + 0.5);
			ws.spawnEntity(air);
			((TileEntityTardis)world.getTileEntity(tardis.getPos())).fuel -= 0.05F;
		}
	}

	@Override
	public String getNameKey() {
		return "protocol.tardis.airshell";
	}

}
