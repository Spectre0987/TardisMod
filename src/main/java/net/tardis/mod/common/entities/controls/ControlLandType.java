package net.tardis.mod.common.entities.controls;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import net.tardis.mod.common.sounds.TSounds;
import net.tardis.mod.common.strings.TStrings;
import net.tardis.mod.common.systems.SystemStabilizers;
import net.tardis.mod.common.tileentity.TileEntityTardis;
import net.tardis.mod.common.tileentity.consoles.TileEntityTardis01;
import net.tardis.mod.common.tileentity.consoles.TileEntityTardis02;
import net.tardis.mod.common.tileentity.consoles.TileEntityTardis03;
import net.tardis.mod.util.common.helpers.Helper;

public class ControlLandType extends EntityControl {

	public static SoundEvent controlSound;

	public ControlLandType(TileEntityTardis tardis) {
		super(tardis);
	}

	public ControlLandType(World world) {
		super(world);
		this.setSize(0.0625F, 0.0625F);
	}

	@Override
	public Vec3d getOffset(TileEntityTardis tardis) {
		if (tardis.getClass() == TileEntityTardis01.class || tardis.getClass() == TileEntityTardis02.class) {
			return Helper.convertToPixels(13.5, -2.5, 4);
		}
		if (tardis instanceof TileEntityTardis03)
			return Helper.convertToPixels(-10, 2, 3.5);
		return Helper.convertToPixels(11.5, -2, 4);
	}

	@Override
	public void preformAction(EntityPlayer player) {
		if (!world.isRemote) {
			TileEntityTardis tardis = (TileEntityTardis) world.getTileEntity(this.getConsolePos());
			tardis.setShouldLandOnSurface(!tardis.landOnSurface);
			player.sendStatusMessage(new TextComponentTranslation(TStrings.LAND_ON_GROUND + tardis.landOnSurface), true);
		}
	}

	@Override
	public void init(TileEntityTardis tardis) {
		if (tardis != null) {
			if (tardis instanceof TileEntityTardis03)
				this.setSize(Helper.precentToPixels(3F), Helper.precentToPixels(2F));
		}
	}

	@Override
	public SoundEvent getUseSound() {
		TileEntityTardis tardis = (TileEntityTardis) world.getTileEntity(this.getConsolePos());
		return tardis.isFueling() ? TSounds.control_landing_type_up : TSounds.control_landing_type_down;

	}


}
