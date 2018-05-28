package net.tardis.mod.common.items;

import java.util.List;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import net.tardis.mod.common.entities.EntityDalekRay;
import net.tardis.mod.common.sounds.TSounds;

public class ItemRayGun extends ItemBase {
	
	public static final String AMMO_KEY = "ammo";
	
	public ItemRayGun() {
		this.setMaxStackSize(1);
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
		ItemStack gun = playerIn.getHeldItem(handIn);
		if (!playerIn.isSneaking()) {
			if (getAmmo(gun) > 0) {
				EntityDalekRay ball = new EntityDalekRay(worldIn, playerIn);
				if (!worldIn.isRemote) worldIn.spawnEntity(ball);
				setAmmo(gun, getAmmo(gun) - 1);
				worldIn.playSound(null, playerIn.getPosition(), TSounds.dalek_ray, SoundCategory.HOSTILE, 1F, 1F);
				return ActionResult.newResult(EnumActionResult.SUCCESS, gun);
			}
		} else {
			ItemStack ammo = getAmmoInInventory(playerIn.inventory.mainInventory, TItems.power_cell);
			if (!ammo.isEmpty()) {
				setAmmo(gun, getAmmo(gun) + (ammo.getCount() * 5));
				ammo.shrink(ammo.getCount());
			}
		}
		return super.onItemRightClick(worldIn, playerIn, handIn);
	}
	
	@Override
	public boolean shouldCauseReequipAnimation(ItemStack oldStack, ItemStack newStack, boolean slotChanged) {
		return false;
	}
	
	public static void setAmmo(ItemStack stack, int ammo) {
		if (!stack.hasTagCompound()) stack.setTagCompound(new NBTTagCompound());
		stack.getTagCompound().setInteger(AMMO_KEY, ammo);
	}
	
	public static int getAmmo(ItemStack stack) {
		if (stack.hasTagCompound() && stack.getTagCompound().hasKey(AMMO_KEY)) return stack.getTagCompound().getInteger(AMMO_KEY);
		return 0;
	}
	
	public static ItemStack getAmmoInInventory(NonNullList<ItemStack> stacks, Item item) {
		for (ItemStack stack : stacks) {
			if (stack.getItem() == item) return stack;
		}
		return ItemStack.EMPTY;
	}
	
	@Override
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		if (getAmmo(stack) > 0)
			tooltip.add(new TextComponentTranslation("raygun.ammo").getFormattedText() + ": " + getAmmo(stack));
		else
			tooltip.add(new TextComponentTranslation("raygun.ammo.none").getFormattedText());
		super.addInformation(stack, worldIn, tooltip, flagIn);
	}
	
}
