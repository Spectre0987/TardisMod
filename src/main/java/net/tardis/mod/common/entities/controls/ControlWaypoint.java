package net.tardis.mod.common.entities.controls;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.tardis.mod.common.tileentity.TileEntityTardis;
import net.tardis.mod.util.common.helpers.Helper;

public class ControlWaypoint extends EntityControl{

	public ControlWaypoint(TileEntityTardis tardis) {
		super(tardis);
	}
	
	public ControlWaypoint(World world) {
		super(world);
		this.setSize(0.0625F, 0.0625F);
	}

	@Override
	public Vec3d getOffset(TileEntityTardis tardis) {
		return Helper.convertToPixels(0, 16, 16);
	}

	@Override
	public void preformAction(EntityPlayer player) {
		TileEntityTardis tardis = (TileEntityTardis)world.getTileEntity(this.getConsolePos());
		if(tardis != null) {
			tardis.waypointIndex += player.isSneaking() ? -1 : 1;
			if(tardis.waypointIndex >= tardis.saveCoords.size())
				tardis.waypointIndex = 0;
			if(tardis.waypointIndex < 0)
				tardis.waypointIndex = tardis.saveCoords.size() - 1;
			player.sendStatusMessage(new TextComponentString("Waypoint " + (tardis.waypointIndex + 1) + ": " + tardis.saveCoords.get(tardis.waypointIndex).name), true);
		}
		
	}

}
