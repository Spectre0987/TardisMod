package net.tardis.mod.common.protocols;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import net.tardis.mod.common.strings.TStrings;
import net.tardis.mod.common.tileentity.TileEntityTardis;

public class ProtocolEnabledHADS implements ITardisProtocol {

	@Override
	public void onActivated(World world, TileEntityTardis tt) {
		if(!world.isRemote){
			TileEntityTardis tardis = (TileEntityTardis)world.getTileEntity(tt.getPos());
			tardis.setHADS(tardis.isHADSEnabled() ? false : true);
			EntityPlayer player = world.getClosestPlayer(tt.getPos().getX(), tt.getPos().getY(), tt.getPos().getZ(), 10D, false);
			if(player != null) {
				player.sendStatusMessage(new TextComponentTranslation(TStrings.HADS_ENABLED + tardis.isHADSEnabled()), true);
			}
		}
	}

	@Override
	public String getNameKey() {
		return "protocol.hads";
	}

}
