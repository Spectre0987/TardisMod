package net.tardis.mod.common.protocols;

import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.tardis.mod.common.entities.EntityForceField;
import net.tardis.mod.common.tileentity.TileEntityTardis;

public class TardisProtocolForceField implements ITardisProtocol {
	
	@Override
	public void onActivated(World world, TileEntityTardis tardis) {
		if (!world.isRemote) {
			if (!tardis.isInFlight()) {
				WorldServer ws = world.getMinecraftServer().getWorld(tardis.dimension);
				EntityForceField ef = new EntityForceField(ws, tardis.getLocation());
				ws.spawnEntity(ef);
			}
		}
	}
	
	@Override
	public String getNameKey() {
		return "tardis.gui.protocol.force_field";
	}
	
}
