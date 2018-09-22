package net.tardis.mod.common.protocols;


import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.tardis.mod.common.IDoor;
import net.tardis.mod.common.strings.TStrings;
import net.tardis.mod.common.tileentity.TileEntityDoor;
import net.tardis.mod.common.tileentity.TileEntityTardis;
import net.tardis.mod.util.helpers.PlayerHelper;

public class ProtocolLock implements ITardisProtocol {

	@Override
	public void onActivated(World world, TileEntityTardis tardis) {
		if(!world.isRemote) {
			tardis.setLocked(!tardis.isLocked());
			if(tardis.isLocked()) {
				WorldServer ws = world.getMinecraftServer().getWorld(tardis.dimension);
				TileEntityDoor door = (TileEntityDoor)ws.getBlockState(tardis.getLocation().up());
				if(door != null) {
					door.setLocked(true);
				}
				for(Entity entity : world.getEntitiesWithinAABB(Entity.class, Block.FULL_BLOCK_AABB.offset(tardis.getPos()).grow(8 * 16))) {
					if(entity instanceof IDoor) {
						((IDoor)entity).setOpen(!tardis.isLocked());
					}
				}
			}
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
