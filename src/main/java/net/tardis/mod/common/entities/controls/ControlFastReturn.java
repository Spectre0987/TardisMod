package net.tardis.mod.common.entities.controls;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.tardis.mod.common.tileentity.TileEntityTardis;
import net.tardis.mod.util.helpers.Helper;

public class ControlFastReturn extends EntityControl{

	public ControlFastReturn(TileEntityTardis tardis) {
		super(tardis);
	}
	
	public ControlFastReturn(World world) {
		super(world);
		this.setSize(0.0625F, 0.0625F);
	}

	@Override
	public Vec3d getOffset() {
		return Helper.convertToPixels(-2.5, 0,7.3);
	}

	@Override
	public void preformAction(EntityPlayer player) {
		TileEntityTardis tardis = (TileEntityTardis) world.getTileEntity(getConsolePos());
		if(!world.isRemote) {
			tardis.setSpaceTimeCoordnate(tardis.saveCoords.get( tardis.saveCoords.size() - 1));
		}
	}

}
