package net.tardis.mod.common.protocols;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.tardis.mod.common.tileentity.TileEntityLight;
import net.tardis.mod.common.tileentity.TileEntityTardis;

public class ProtocolLight implements ITardisProtocol {

	@Override
	public void onActivated(World world, TileEntityTardis tardis) {
		System.out.println(world);
		for(TileEntity te : tardis.getTilesInTardis()) {
			if(te instanceof TileEntityLight) {
				TileEntityLight light = (TileEntityLight)te;
				light.setLight(light.getLightValue() > 0 ? 0 : 15);
				System.out.println(light + ", " + light.getLightValue());
				world.markBlockRangeForRenderUpdate(te.getPos(), te.getPos());
			}
		}
	}

	@Override
	public String getNameKey() {
		return "protocol.tardis.light";
	}

}
