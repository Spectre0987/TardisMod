package net.tardis.mod.common.items;


import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.tardis.mod.common.interfaces.IEntityFactory;
import net.tardis.mod.util.common.helpers.Helper;

public class ItemESpawn extends ItemBase {

	IEntityFactory<?> entity;

	public ItemESpawn(IEntityFactory<?> e) {
		this.setMaxStackSize(1);
		this.entity = e;
	}

	@Override
	public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		if (!worldIn.isRemote) {
			Entity e = entity.createEntity(worldIn);
			double height = worldIn.getBlockState(pos).getCollisionBoundingBox(worldIn, pos).maxY;
			e.setWorld(worldIn);
			e.setPosition(pos.getX() + 0.5, pos.getY() + height, pos.getZ() + 0.5);
			e.rotationYaw = Helper.get180Rot(player.getHorizontalFacing());
			worldIn.spawnEntity(e);
			player.getHeldItem(hand).shrink(1);
		}
		return super.onItemUse(player, worldIn, pos, hand, facing, hitX, hitY, hitZ);
	}
}
