package net.tardis.mod.common.protocols;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.tardis.mod.common.strings.TStrings;
import net.tardis.mod.common.tileentity.TileEntityTardis;
import net.tardis.mod.util.helpers.PlayerHelper;

public class ProtocolLock implements ITardisProtocol {

	@Override
	public void onActivated(World world, TileEntityTardis tardis) {
		if(!world.isRemote) {
			tardis.setLocked(!tardis.isLocked());
			EntityPlayer player = world.getClosestPlayer(tardis.getPos().getX(), tardis.getPos().getY(), tardis.getPos().getZ(), 16, false);	
			if(player != null) {
				PlayerHelper.sendMessage(player, TStrings.DOUBLE_LOCKED + tardis.isLocked(), true);
			}
		}
	}

	@Override
	public String getNameKey() {
		return "protocol.tardis.lock";
	}

}
