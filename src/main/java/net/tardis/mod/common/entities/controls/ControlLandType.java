package net.tardis.mod.common.entities.controls;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import net.tardis.mod.common.strings.TStrings;
import net.tardis.mod.common.tileentity.TileEntityTardis;
import net.tardis.mod.common.tileentity.consoles.TileEntityTardis01;
import net.tardis.mod.common.tileentity.consoles.TileEntityTardis02;
import net.tardis.mod.util.common.helpers.Helper;

public class ControlLandType extends EntityControl {
	
	public ControlLandType(TileEntityTardis tardis) {
		super(tardis);
	}
	
	public ControlLandType(World world) {
		super(world);
		this.setSize(0.0625F, 0.0625F);
	}
	
	@Override
	public Vec3d getOffset(TileEntityTardis tardis) {
		if(tardis.getClass() == TileEntityTardis01.class || tardis.getClass() == TileEntityTardis02.class) {
			return Helper.convertToPixels(13.5, -2.5, 4);
		}
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
	
}
