package net.tardis.mod.common.entities.controls;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import net.tardis.mod.capability.CapabilityTardis;
import net.tardis.mod.common.tileentity.TileEntityTardis;
import net.tardis.mod.common.tileentity.consoles.*;
import net.tardis.mod.util.common.helpers.Helper;
import net.tardis.mod.util.common.helpers.PlayerHelper;

public class ControlLaunch extends EntityControl {

	public ControlLaunch(TileEntityTardis tardis) {
		super(tardis);
	}

	public ControlLaunch(World world) {
		super(world);
		this.setSize(0.125F, 0.125F);
	}

	@Override
	public Vec3d getOffset(TileEntityTardis tardis) {
		if (tardis.getClass() == TileEntityTardis01.class || tardis.getClass() == TileEntityTardis02.class) {
			return Helper.convertToPixels(-5, -2.5, 12.5);
		}
		if (tardis instanceof TileEntityTardis03)
			return Helper.convertToPixels(5.5, 2.5, 14);
		if(tardis instanceof TileEntityTardis04)
			return Helper.convertToPixels(0, -2, 12);
		if(tardis instanceof TileEntityTardis05)
			return Helper.convertToPixels(-11, -2, -6.5);

		return Helper.convertToPixels(-7, 0, 7.5);
	}

	@Override
	public void preformAction(EntityPlayer player) {
		if (!world.isRemote) {
			TileEntity te = world.getTileEntity(this.getConsolePos());
			if (te != null) {
				TileEntityTardis tardis = (TileEntityTardis) te;
				if (!tardis.hasPilot()) {
					if (!tardis.isInFlight()) {
						tardis.startFlight();
					} else {
						tardis.crash(false);
					}
				} else {
					//PlayerHelper.sendMessage(player, new TextComponentTranslation("tardis.message.has_pilot"), true);
					EntityPlayer pilot = tardis.getFlightPilot();
					CapabilityTardis.get(pilot).setFlightState(CapabilityTardis.TardisFlightState.DEMAT);
				}
			}
		} else
			ticks = 30;
	}

	public void setSize(float x, float y, float z) {
		float sx = x / 2;
		float sy = y / 2;
		float sz = z / 2;
		this.setEntityBoundingBox(new AxisAlignedBB(this.posX - sx, this.posY - sy, this.posZ - sz, this.posX + sx, this.posY + sy, this.posZ + sz));
	}
}
