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

public class ControlX extends EntityControl {
	
	public ControlX(TileEntityTardis tardis) {
		super(tardis);
	}
	
	public ControlX(World world) {
		super(world);
		this.setSize(0.0625F, 0.0625F);
	}
	
	@Override
	public Vec3d getOffset() {
		return Helper.convertToPixels(-8, -0.5, 3.5);
	}
	
	@Override
	public void preformAction(EntityPlayer player) {
		if (!world.isRemote) {
			if(player.getHeldItem(EnumHand.MAIN_HAND).getItem()instanceof ItemManual){
				PlayerHelper.sendMessage(player, new TextComponentTranslation(TStrings.Manual.CONTROL_COORD).getFormattedText(), true);
			}
			else{
				TileEntityTardis tardis = ((TileEntityTardis) world.getTileEntity(this.getConsolePos()));
				tardis.setDesination(tardis.getDestination().add(player.isSneaking() ? -tardis.magnitude : tardis.magnitude, -1, 0), tardis.getTargetDim());
			}
		} else if (this.ticks <= 0) {
			this.ticks = 20;
			this.direction = player.isSneaking() ? -1 : 1;
		}
	}
	
}
