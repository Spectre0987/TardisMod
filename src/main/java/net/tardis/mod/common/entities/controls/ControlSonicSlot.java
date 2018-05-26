package net.tardis.mod.common.entities.controls;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.tardis.mod.common.items.TItems;
import net.tardis.mod.common.tileentity.TileEntityTardis;
import net.tardis.mod.util.helpers.Helper;

public class ControlSonicSlot extends EntityControl{

	private ItemStack slot = new ItemStack(TItems.sonic_screwdriver);
	
	public ControlSonicSlot(TileEntityTardis tardis) {
		super(tardis);
	}
	
	public ControlSonicSlot(World world) {
		super(world);
		this.setSize(0.0625F, 0.0625F);
	}

	@Override
	public Vec3d getOffset() {
		return Helper.convertToPixels(0,0,0);
	}

	@Override
	public void preformAction(EntityPlayer player) {
		
	}

}
