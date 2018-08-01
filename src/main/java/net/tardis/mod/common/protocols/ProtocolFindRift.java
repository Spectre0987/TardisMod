package net.tardis.mod.common.protocols;

import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.tardis.mod.common.tileentity.TileEntityTardis;
import net.tardis.mod.util.helpers.RiftHelper;

public class ProtocolFindRift implements ITardisProtocol {

	public ProtocolFindRift() {
		
	}
	
	@Override
	public void onActivated(World world, TileEntityTardis tardis) {
		if(!world.isRemote) {
			WorldServer ws = ((WorldServer)world).getMinecraftServer().getWorld(tardis.getTargetDim());
			ChunkPos pos = ws.getChunkFromBlockCoords(tardis.getLocation()).getPos();
			
			for(int x = pos.x - 5; x < 10; x++) {
				for(int z = pos.z - 5; z < 10; z++) {
					if(RiftHelper.isRift(new ChunkPos(x, z), ws)) {
						TileEntityTardis t = (TileEntityTardis)world.getTileEntity(tardis.getPos());
						t.setDesination(new BlockPos(x >> 4, tardis.getDestination().getY(), z >> 4).add(8, 0, 8), ws.provider.getDimension());
						return;
					}
				}
			}
		}
	}

	@Override
	public String getNameKey() {
		return "protocol.tardis.find_rift";
	}

}
