package net.tardis.mod.common.entities.controls;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.tardis.mod.common.tileentity.TileEntityTardis;
import net.tardis.mod.common.tileentity.consoles.TileEntityTardis01;
import net.tardis.mod.util.helpers.Helper;
import net.tardis.mod.util.helpers.PlayerHelper;

public class ControlFuel extends EntityControl {
	
	public ControlFuel(TileEntityTardis tardis) {
		super(tardis);
	}
	
	public ControlFuel(World world) {
		super(world);
		this.setSize(0.125F, 0.125F);
	}
	
	@Override
	public Vec3d getOffset(TileEntityTardis tardis) {
		if(tardis.getClass() == TileEntityTardis01.class) {
			return Helper.convertToPixels(13.5, -3, -2);
		}
		return Helper.convertToPixels(-12, -4, 10);
	}
	
	@Override
	public void preformAction(EntityPlayer player) {
		if (!world.isRemote) {
			TileEntityTardis t = (TileEntityTardis) world.getTileEntity(getConsolePos());
			t.setFueling(!t.isFueling());
			PlayerHelper.sendMessage(player, t.isFueling() ? "tardis.message.refuelling" : "tardis.message.no_longer_refuelling", true);
		}
		
	}
	
}
