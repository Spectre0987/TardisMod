package net.tardis.mod.common.items;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.tardis.mod.client.creativetabs.TardisTabs;

public class ItemRepairCapsule extends Item {

	public ItemRepairCapsule() {
		this.setCreativeTab(TardisTabs.ITEMS);
		this.setMaxStackSize(1);
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
		
		return super.onItemRightClick(worldIn, playerIn, handIn);
	}
}
