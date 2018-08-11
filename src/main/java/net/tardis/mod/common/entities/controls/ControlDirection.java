package net.tardis.mod.common.entities.controls;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import net.tardis.mod.common.strings.TStrings;
import net.tardis.mod.common.tileentity.TileEntityTardis;
import net.tardis.mod.common.tileentity.consoles.TileEntityTardis01;
import net.tardis.mod.common.tileentity.consoles.TileEntityTardis02;
import net.tardis.mod.util.helpers.Helper;

public class ControlDirection extends EntityControl {
	
	public ControlDirection(TileEntityTardis tardis) {
		super(tardis);
		if(tardis.getClass() == TileEntityTardis01.class) {
			this.setSize(0.0625F, 0.0625F);
		}
	}
	
	public ControlDirection(World world) {
		super(world);
		this.setSize(0.125F, 0.125F);
	}
	
	@Override
	public Vec3d getOffset(TileEntityTardis tardis) {
		if(tardis instanceof TileEntityTardis01 || tardis.getClass() == TileEntityTardis02.class) {
			return Helper.convertToPixels(3, -2.5, -13);
		}
		return Helper.convertToPixels(9.5, -3.5, 10);
	}
	
	@Override
	public void preformAction(EntityPlayer player) {
		if (!world.isRemote) {
			TileEntityTardis tardis = (TileEntityTardis) world.getTileEntity(getConsolePos());
			tardis.setFacing(tardis.getFacing().rotateY());
			player.sendStatusMessage(new TextComponentTranslation(TStrings.TARDIS_FACING + tardis.getFacing().getName()), true);
		}
	}
	
}
