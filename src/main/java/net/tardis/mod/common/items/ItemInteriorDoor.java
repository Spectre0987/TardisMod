package net.tardis.mod.common.items;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.tardis.mod.common.entities.controls.ControlDoor;

public class ItemInteriorDoor extends ItemBase{
	
	public ItemInteriorDoor() {
		this.setMaxStackSize(1);
	}

	@Override
	public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		if(facing == EnumFacing.UP) {
			ControlDoor door = new ControlDoor(worldIn);
			door.setPosition(pos.getX() + 0.5, pos.getY() + 1, pos.getZ() + 0.5);
			worldIn.spawnEntity(door);
		}
		return EnumActionResult.SUCCESS;
	}

}
