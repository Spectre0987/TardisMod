package net.tardis.mod.common.protocols;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import net.tardis.mod.common.strings.TStrings;
import net.tardis.mod.common.tileentity.TileEntityTardis;

public class ProtocolRepair implements ITardisProtocol {

	@Override
	public void onActivated(World world, TileEntityTardis tardis) {
		if(!world.isRemote) {
			tardis.setRepairing(!tardis.getRepairing());
			for(EntityPlayerMP player : world.getEntitiesWithinAABB(EntityPlayerMP.class, Block.FULL_BLOCK_AABB.offset(tardis.getPos()).grow(20))) {
				player.sendStatusMessage(new TextComponentTranslation(TStrings.REPAIR + tardis.getRepairing()), true);
			}
		}
	}

	@Override
	public String getNameKey() {
		return "protocol.tardis.repair";
	}

}
