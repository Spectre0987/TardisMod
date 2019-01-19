package net.tardis.mod.common.items;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.tardis.mod.common.entities.hellbent.EntityHellbentCorridor;

public class ItemHellbentCorridor extends ItemBase {

	public ItemHellbentCorridor() {
	}

	@Override
	public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		if (!worldIn.isRemote) {
			EntityHellbentCorridor e = new EntityHellbentCorridor(worldIn);
			Vec3d look = player.getLookVec().rotateYaw(0.25F);
			e.setPosition(pos.getX(), pos.getY() + 1, pos.getZ() + 0.5);
			worldIn.spawnEntity(e);
			player.getHeldItem(hand).shrink(1);
		}
		return EnumActionResult.SUCCESS;
	}

}
