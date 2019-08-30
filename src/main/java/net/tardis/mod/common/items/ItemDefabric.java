package net.tardis.mod.common.items;

import java.util.List;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.tardis.mod.client.creativetabs.TardisTabs;
import net.tardis.mod.common.entities.EntityDefabric;

/*
 * Just to be clear, this is an item.
 * I hold no ill-will to the alternate mod loader
 */
public class ItemDefabric extends Item {
	
	public ItemDefabric() {
		this.setMaxStackSize(1);
		this.setCreativeTab(TardisTabs.ITEMS);
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
		playerIn.setActiveHand(handIn);
		return new ActionResult(EnumActionResult.SUCCESS, playerIn.getHeldItem(handIn));
	}

	
	@Override
	public void onPlayerStoppedUsing(ItemStack stack, World worldIn, EntityLivingBase player, int timeLeft) {
		if(!worldIn.isRemote) {
			EntityDefabric entity = new EntityDefabric(worldIn, player);
			entity.setPosition(player.posX, player.posY + player.getEyeHeight(), player.posZ);
			worldIn.spawnEntity(entity);
		}
		super.onPlayerStoppedUsing(stack, worldIn, player, timeLeft);
	}

	@Override
	public EnumAction getItemUseAction(ItemStack stack) {
		return EnumAction.BOW;
	}

	@Override
	public int getMaxItemUseDuration(ItemStack stack) {
		return 72000;
	}

	@Override
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		super.addInformation(stack, worldIn, tooltip, flagIn);
	}

	@Override
	public double getDurabilityForDisplay(ItemStack stack) {
		// TODO Auto-generated method stub
		return super.getDurabilityForDisplay(stack);
	}

}
