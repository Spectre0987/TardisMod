package net.tardis.mod.common.protocols;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import net.tardis.mod.common.systems.TardisSystems.ISystem;
import net.tardis.mod.common.tileentity.TileEntityTardis;

public class ProtocolSystemReadout implements ITardisProtocol {

	@Override
	public void onActivated(World world, TileEntityTardis tardis) {
		if(!world.isRemote) {
			EntityPlayer player = world.getClosestPlayer(tardis.getPos().getX(), tardis.getPos().getY(), tardis.getPos().getZ(), 20, false);
			if(player != null) {
				for(ISystem sys : tardis.systems) {
					player.sendStatusMessage(new TextComponentString(new TextComponentTranslation(sys.getNameKey()).getFormattedText() + ": " + Math.round(sys.getHealth() * 100) + "%"), false);
				}
			}
		}
	}

	@Override
	public String getNameKey() {
		return "protocol.tardis.systemreadout";
	}

}
