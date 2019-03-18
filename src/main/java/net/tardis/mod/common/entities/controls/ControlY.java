package net.tardis.mod.common.entities.controls;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.tardis.mod.common.tileentity.TileEntityTardis;
import net.tardis.mod.common.tileentity.consoles.*;
import net.tardis.mod.util.common.helpers.Helper;

public class ControlY extends EntityControl {

	public ControlY(TileEntityTardis tardis) {
		super(tardis);
	}

	public ControlY(World world) {
		super(world);
		this.setSize(0.0625F, 0.0625F);
	}

	@Override
	public Vec3d getOffset(TileEntityTardis tardis) {
		if (tardis.getClass() == TileEntityTardis01.class || tardis.getClass() == TileEntityTardis02.class) {
			return Helper.convertToPixels(-0.5, -2.5, -13.5);
		}
		if (tardis instanceof TileEntityTardis03)
			return Helper.convertToPixels(0, 3, 9);
		if(tardis instanceof TileEntityTardis04)
			return Helper.convertToPixels(-11, -1.5, -5);
		if (tardis instanceof TileEntityTardis05) {
			return Helper.convertToPixels(12.5, -2.5, -5);
		}
		return Helper.convertToPixels(-7.5, -0.5, 4.5);
	}

	@Override
	public void preformAction(EntityPlayer player) {
		if (!world.isRemote) {
			TileEntityTardis tardis = ((TileEntityTardis) world.getTileEntity(this.getConsolePos()));
			tardis.setDesination(tardis.getDestination().add(0, player.isSneaking() ? -tardis.magnitude : tardis.magnitude + 1, 0), tardis.getTargetDim());
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
