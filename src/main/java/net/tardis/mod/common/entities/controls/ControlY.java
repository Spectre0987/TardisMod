package net.tardis.mod.common.entities.controls;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import net.tardis.mod.common.items.ItemManual;
import net.tardis.mod.common.strings.TStrings;
import net.tardis.mod.common.tileentity.TileEntityTardis;
import net.tardis.mod.util.helpers.Helper;
import net.tardis.mod.util.helpers.PlayerHelper;

public class ControlY extends EntityControl {
	
	public ControlY(TileEntityTardis tardis) {
		super(tardis);
	}
	
	public ControlY(World world) {
		super(world);
		this.setSize(0.0625F, 0.0625F);
	}
	
	@Override
	public Vec3d getOffset() {
		return Helper.convertToPixels(-7.5, -0.5, 4.5);
	}
	
	@Override
	public void preformAction(EntityPlayer player) {
		if (!world.isRemote) {
			if(player.getHeldItem(EnumHand.MAIN_HAND).getItem()instanceof ItemManual){
				PlayerHelper.sendMessage(player, new TextComponentTranslation(TStrings.Manual.CONTROL_COORD).getFormattedText(), true);
			}
			else{
				TileEntityTardis tardis = ((TileEntityTardis) world.getTileEntity(this.getConsolePos()));
				tardis.setDesination(tardis.getDestination().add(0, player.isSneaking() ? -tardis.magnitude : tardis.magnitude, 0), tardis.getTargetDim());
			}
		} else if (this.ticks <= 0) {
			ticks = 20;
			direction = player.isSneaking() ? -1 : 1;
		}
	}
	
}
