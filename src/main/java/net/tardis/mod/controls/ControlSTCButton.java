package net.tardis.mod.controls;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.tardis.api.controls.SpaceTimeCoord;
import net.tardis.mod.helpers.Helper;
import net.tardis.mod.tileentity.TileEntityTardis;

public class ControlSTCButton extends EntityControl{
	
	int index=0;
	Vec3d vec;
	public ControlSTCButton(TileEntityTardis tardis,int i,Vec3d vec) {
		super(tardis);
		this.index=i;
		this.vec=vec;
	}

	public ControlSTCButton(World world) {
		super(world);
		this.setSize(0.0625F, 0.0625F);
	}

	
	@Override
	public Vec3d getOffset() {
		return Helper.convertToPixels(-8,-2,-8).add(vec);
	}

	@Override
	public void preformAction(EntityPlayer player) {
		if(!world.isRemote) {
			TileEntityTardis tardis=(TileEntityTardis)world.getTileEntity(this.getConsolePos());
			if(index<tardis.saveCoords.size()) {
				if(tardis.getLoading()) {
					tardis.saveCoords.set(index, new SpaceTimeCoord(tardis.getLocation(),tardis.dimension));
				}
				else {
					tardis.setSpaceTimeCoordnate(tardis.saveCoords.get(index));
				}
			}
		}
		else ticks=20;
	}

}
