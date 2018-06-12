package net.tardis.mod.common.entities.controls;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.tardis.mod.common.tileentity.TileEntityTardis;
import net.tardis.mod.util.helpers.Helper;
import net.tardis.mod.util.helpers.PlayerHelper;

public class ControlFuel extends EntityControl {
	
	public ControlFuel(TileEntityTardis tardis) {
		super(tardis);
	}
	
	public ControlFuel(World world) {
		super(world);
		this.setSize(0.2F, 0.1F);
	}
	
	@Override
	public Vec3d getOffset() {
		return Helper.convertToPixels(-12, -4, 10);
	}
	
	@Override
	public void preformAction(EntityPlayer player) {
		if (!world.isRemote) {
			TileEntityTardis t = (TileEntityTardis) world.getTileEntity(getConsolePos());
			t.setFueling(!t.isFueling());
			PlayerHelper.sendMessage(player, t.isFueling() ? "Tardis no longer fueling" : "Tardis now fueling", false);
		} else
			ticks = 20;
	}
	
}
