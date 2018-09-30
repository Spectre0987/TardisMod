package net.tardis.mod.common.items;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.tardis.mod.common.entities.hellbent.EntityHellbentDoor;
import net.tardis.mod.util.common.helpers.Helper;

public class ItemHellbentDoor extends ItemBase {
	
	public ItemHellbentDoor() {
		
	}

	@Override
	public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		if(!worldIn.isRemote) {
			EntityHellbentDoor door = new EntityHellbentDoor(worldIn);
			door.setPositionAndRotation(pos.getX(), pos.getY() + 1, pos.getZ(), Helper.get360FromFacing(player.getHorizontalFacing()), 0);
			worldIn.spawnEntity(door);
			player.getHeldItem(hand).shrink(1);
		}
		return EnumActionResult.SUCCESS;
	}

}
