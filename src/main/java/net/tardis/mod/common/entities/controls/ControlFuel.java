package net.tardis.mod.common.entities.controls;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import net.tardis.mod.common.items.ItemManual;
import net.tardis.mod.common.strings.TStrings;
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
			if (player.getHeldItem(EnumHand.MAIN_HAND).getItem()instanceof ItemManual){
				PlayerHelper.sendMessage(player, new TextComponentTranslation(TStrings.Manual.REFUELER).getFormattedText(), true);
			}
			else{
				TileEntityTardis t = (TileEntityTardis) world.getTileEntity(getConsolePos());
				t.setFueling(!t.isFueling());
				PlayerHelper.sendMessage(player, t.isFueling() ? "tardis.message.refuelling" : "tardis.message.no_longer_refuelling", true);
			}
		} else {
			TileEntity te = world.getTileEntity(getConsolePos());
			if(te != null && te instanceof TileEntityTardis) {
				((TileEntityTardis)te).aniObj.animate(this);
			}
		}
	}
	
}
