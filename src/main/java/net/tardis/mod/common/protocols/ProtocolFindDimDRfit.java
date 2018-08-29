package net.tardis.mod.common.protocols;

import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.tardis.mod.common.tileentity.TileEntityTardis;

public class ProtocolFindDimDRfit implements ITardisProtocol {

	@Override
	public void onActivated(World world, TileEntityTardis tardis) {
		if(!world.isRemote) {
			WorldServer ws = world.getMinecraftServer().getWorld(tardis.dimension);
			for(TileEntity te : ws.loadedTileEntityList) {
				if(te.getPos().distanceSq(tardis.getLocation()) < Math.pow(16, 2)) {
					if(TileEntity.getKey(te.getClass()).toString().equals("dimdoors:floating_rift")) {
						ws.setBlockState(te.getPos(), Blocks.AIR.getDefaultState());
						tardis.setFuel(1.0F);
					}
				}
			}
		}
	}

	@Override
	public String getNameKey() {
		return "protocol.tardis.destroy.rift";
	}

}
