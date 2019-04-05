package net.tardis.mod.common.protocols;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.tardis.mod.common.tileentity.TileEntityTardis;
import net.tardis.mod.util.common.helpers.PlayerHelper;

public class ProtocolStealth implements ITardisProtocol {
	
	@Override
	public void onActivated(World world, TileEntityTardis tardis) {
		if (!world.isRemote) {
			tardis.setStealthMode(!tardis.isStealthMode());
			for (EntityPlayer player : world.getEntitiesWithinAABB(EntityPlayer.class, Block.FULL_BLOCK_AABB.offset(tardis.getPos()).grow(16))) {
				PlayerHelper.sendMessage(player, "protocol.tardis.stealth." + tardis.isStealthMode(), true);
			}
		}
	}
	
	@Override
	public String getNameKey() {
		return "protocol.tardis.stealth";
	}
	
}
