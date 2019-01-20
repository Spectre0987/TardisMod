package net.tardis.mod.common.entities.controls;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.tardis.mod.common.tileentity.TileEntityTardis;
import net.tardis.mod.common.tileentity.consoles.TileEntityTardis01;
import net.tardis.mod.common.tileentity.consoles.TileEntityTardis02;
import net.tardis.mod.common.tileentity.consoles.TileEntityTardis03;
import net.tardis.mod.util.common.helpers.Helper;

public class ControlWaypoint extends EntityControl {

	public ControlWaypoint(TileEntityTardis tardis) {
		super(tardis);
	}

	public ControlWaypoint(World world) {
		super(world);
		this.setSize(0.0625F, 0.0625F);
	}

	@Override
	public Vec3d getOffset(TileEntityTardis tardis) {
		if (tardis instanceof TileEntityTardis03)
			return Helper.convertToPixels(-9, 2, 6.5);
		if (tardis.getClass() == TileEntityTardis01.class || tardis.getClass() == TileEntityTardis02.class)
			return Helper.convertToPixels(-9.75, -2.5, 9);
		return Helper.convertToPixels(1.25, -2.5, -12);
	}

	@Override
	public void preformAction(EntityPlayer player) {
		TileEntityTardis tardis = (TileEntityTardis) world.getTileEntity(this.getConsolePos());
		if (tardis != null) {
			tardis.waypointIndex += player.isSneaking() ? -1 : 1;
			if (tardis.waypointIndex >= tardis.saveCoords.size())
				tardis.waypointIndex = 0;
			if (tardis.waypointIndex < 0)
				tardis.waypointIndex = tardis.saveCoords.size() - 1;
			player.sendStatusMessage(new TextComponentString("Waypoint " + (tardis.waypointIndex + 1) + ": " + tardis.saveCoords.get(tardis.waypointIndex).name), true);
		}

	}

	@Override
	public void init(TileEntityTardis tardis) {
		if (tardis != null) {
			if (tardis instanceof TileEntityTardis03) {
				this.setSize(Helper.precentToPixels(2F), Helper.precentToPixels(2F));
			}
			this.setSize(Helper.precentToPixels(2F), Helper.precentToPixels(2F));
		}
	}

}
