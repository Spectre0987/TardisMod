package net.tardis.mod.common.protocols;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.tardis.mod.common.tileentity.TileEntityTardis;
import net.tardis.mod.util.TardisTeleporter;
import net.tardis.mod.util.common.helpers.TardisHelper;

public class ProtocolEmerEscape implements ITardisProtocol {

	@Override
	public void onActivated(World world, TileEntityTardis tardis) {
		if(!world.isRemote && tardis.fuel > 0.01) {
			WorldServer overworld = ((WorldServer)world).getMinecraftServer().getWorld(0);
			BlockPos pos = overworld.getSpawnPoint();
			pos = overworld.getTopSolidOrLiquidBlock(pos);
			for(EntityPlayer player : world.getEntitiesWithinAABB(EntityPlayer.class, Block.FULL_BLOCK_AABB.offset(tardis.getPos()).grow(TardisHelper.TARDIS_SIZE * 16))) {
				player.changeDimension(0, new TardisTeleporter(pos));
				player.setPositionAndUpdate(pos.getX() + 0.5, pos.getY() + 1, pos.getZ() + 0.5);
			}
			tardis.fuel -= 0.01F;
			tardis.markDirty();
		}
	}

	@Override
	public String getNameKey() {
		return "protocol.tardis.emer.escape";
	}

}
