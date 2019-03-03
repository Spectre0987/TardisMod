package net.tardis.mod.common.entities.controls;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.TextComponentString;
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

public class ControlStabilizers extends EntityControl {

	public static SoundEvent controlSound;


	public ControlStabilizers(TileEntityTardis tardis) {
		super(tardis);
	}

	public ControlStabilizers(World world) {
		super(world);
		this.setSize(0.125F, 0.125F);
	}

	@Override
	public Vec3d getOffset(TileEntityTardis tardis) {
		if (tardis.getClass() == TileEntityTardis01.class || tardis.getClass() == TileEntityTardis02.class) {
			return Helper.convertToPixels(-3, -3, 13);
		}
		if (tardis instanceof TileEntityTardis03)
			return Helper.convertToPixels(0, 1, 15);
		if (tardis.getClass() == TileEntityTardis.class)
			return Helper.convertToPixels(-14, -4, 6);
		return new Vec3d(0, 0, 0);
	}

	@Override
	public void preformAction(EntityPlayer player) {
		if (!world.isRemote) {
			TileEntityTardis tardis = (TileEntityTardis) world.getTileEntity(this.getConsolePos());
			SystemStabilizers stab = tardis.getSystem(SystemStabilizers.class);
			if (stab.getHealth() > 0) {
				stab.setOn(!stab.isOn());
				player.sendStatusMessage(new TextComponentString(new TextComponentTranslation(TStrings.STABILIZERS_ON + stab.isOn()).getFormattedText()), true);

			} else {
				stab.setOn(false);

			}
		}
	}

	@Override
	public void init(TileEntityTardis tardis) {
		if (tardis != null) {
			if (tardis.getClass() == TileEntityTardis.class)
				this.setSize(Helper.precentToPixels(2F), Helper.precentToPixels(2F));
			if (tardis instanceof TileEntityTardis03)
				this.setSize(Helper.precentToPixels(3F), Helper.precentToPixels(2F));
		}
	}

	@Override
	public SoundEvent getUseSound() {
		TileEntityTardis tardis = (TileEntityTardis) world.getTileEntity(this.getConsolePos());
		SystemStabilizers stab = tardis.getSystem(SystemStabilizers.class);
		if(stab.isOn() == true) { controlSound = TSounds.control_stabilizers_on; }
		else controlSound = TSounds.control_stabilizers_off;


		return controlSound;
	}

}
