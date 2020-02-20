package net.tardis.mod.common.tileentity;

import net.minecraft.tileentity.TileEntity;
import net.tardis.mod.common.dimensions.TDimensions;
import net.tardis.mod.util.common.helpers.TardisHelper;

public class TileEntityArtronBank extends TileEntity{

	public float getMaxArtron() {
		return 100F;
	}

	@Override
	public void invalidate() {
		if(!world.isRemote && world.provider.getDimension() == TDimensions.TARDIS_ID) {
			TileEntity te = world.getTileEntity(TardisHelper.getTardisForPosition(getPos()));	
			if(te instanceof TileEntityTardis) {
				((TileEntityTardis)te).removeArtronBank(this);
			}
		}
		super.invalidate();
	}

}
