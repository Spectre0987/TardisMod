package net.tardis.mod.common.items;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.tardis.mod.common.entities.controls.ControlDoor;
import net.tardis.mod.util.common.helpers.Helper;

public class ItemInteriorDoor extends ItemBase {

	public ItemInteriorDoor() {
		this.setMaxStackSize(1);
	}

	@Override
	public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		if (!worldIn.isRemote) {
			if (facing == EnumFacing.UP) {
				ControlDoor door = new ControlDoor(worldIn);
				door.setPositionAndRotation(pos.getX() + 0.5, pos.getY() + 1, pos.getZ() + (player.isSneaking() ? 0 : 0.5), Helper.getAngleFromFacing(player.getHorizontalFacing()), 0);
				worldIn.spawnEntity(door);
				player.getHeldItem(hand).shrink(1);
			}
		}
		return EnumActionResult.SUCCESS;
	}

}
