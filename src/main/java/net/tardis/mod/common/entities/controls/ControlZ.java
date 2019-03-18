package net.tardis.mod.common.entities.controls;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.tardis.mod.common.tileentity.TileEntityTardis;
import net.tardis.mod.common.tileentity.consoles.*;
import net.tardis.mod.util.common.helpers.Helper;

public class ControlZ extends EntityControl {

	public ControlZ(TileEntityTardis tardis) {
		super(tardis);
	}

	public ControlZ(World world) {
		super(world);
		this.setSize(0.0625F, 0.0625F);
	}

	@Override
	public Vec3d getOffset(TileEntityTardis tardis) {
		if (tardis.getClass() == TileEntityTardis01.class || tardis.getClass() == TileEntityTardis02.class) {
			return Helper.convertToPixels(-2, -2.5, -13.5);
		}
		if (tardis instanceof TileEntityTardis03)
			return Helper.convertToPixels(1.5, 3, 9);
		if(tardis instanceof TileEntityTardis04)
			return Helper.convertToPixels(-12, -1.5, -3.5);
		if (tardis instanceof TileEntityTardis05) {
			return Helper.convertToPixels(12, -2.5, -6);
		}
		return Helper.convertToPixels(-7, -0.5, 5.5);
	}

	@Override
	public void preformAction(EntityPlayer player) {
		if (!world.isRemote) {
			TileEntityTardis tardis = ((TileEntityTardis) world.getTileEntity(this.getConsolePos()));
			tardis.setDesination(tardis.getDestination().add(0, -1, player.isSneaking() ? -tardis.magnitude : tardis.magnitude), tardis.getTargetDim());
		} else if (this.ticks <= 0) {
			ticks = 20;
			direction = player.isSneaking() ? -1 : 1;
		}
	}

	@Override
	public void init(TileEntityTardis tardis) {
		if (tardis != null) {
			if (tardis instanceof TileEntityTardis03)
				this.setSize(Helper.precentToPixels(1F), Helper.precentToPixels(1F));
		}
	}


}
