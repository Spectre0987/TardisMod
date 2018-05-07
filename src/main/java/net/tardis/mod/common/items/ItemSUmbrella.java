package net.tardis.mod.common.items;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ItemSUmbrella extends ItemSScrewdriver {
	
	public ItemSUmbrella() {
		
	}
	
	@Override
	public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		if (!player.isSneaking()) return super.onItemUse(player, worldIn, pos, hand, facing, hitX, hitY, hitZ);
		ItemStack held = player.getHeldItem(hand);
		Item item = held.getItem() == TItems.umbrella_closed ? TItems.umbrella_open : TItems.umbrella_closed;
		ItemStack newStack = new ItemStack(item);
		newStack.setTagCompound(held.getTagCompound());
		player.setHeldItem(hand, newStack);
		return EnumActionResult.SUCCESS;
	}
	
}
