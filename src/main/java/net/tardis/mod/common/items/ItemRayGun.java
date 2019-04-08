package net.tardis.mod.common.items;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.*;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import net.tardis.mod.common.TDamage;
import net.tardis.mod.common.entities.EntityLaserRay;
import net.tardis.mod.common.sounds.TSounds;

import java.util.List;

public class ItemRayGun extends ItemBase {

	public static final String AMMO_KEY = "ammo";

	public ItemRayGun() {
		this.setMaxStackSize(1);
	}

	public static void setAmmo(ItemStack stack, int ammo) {
		if (!stack.hasTagCompound()) stack.setTagCompound(new NBTTagCompound());
		stack.getTagCompound().setInteger(AMMO_KEY, ammo);
	}

	public static int getAmmo(ItemStack stack) {
		if (stack.hasTagCompound() && stack.getTagCompound().hasKey(AMMO_KEY))
			return stack.getTagCompound().getInteger(AMMO_KEY);
		return 0;
	}

	public static ItemStack getAmmoInInventory(NonNullList<ItemStack> stacks, Item item) {
		for (ItemStack stack : stacks) {
			if (stack.getItem() == item) return stack;
		}
		return ItemStack.EMPTY;
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
		ItemStack gun = playerIn.getHeldItem(handIn);

		if (!playerIn.isSneaking()) {
			if (getAmmo(gun) > 0) {
				Vec3d v3 = playerIn.getLook(1);
				EntityLaserRay ball = new EntityLaserRay(worldIn, playerIn, 2, TDamage.LASER, new Vec3d(0, 1, 0));
				if (!worldIn.isRemote) {
					ball.shoot(v3.x, v3.y, v3.z, 1.6F, (float) (14 - worldIn.getDifficulty().getId() * 4));
					worldIn.spawnEntity(ball);
					setAmmo(gun, getAmmo(gun) - 1);
				}
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

	@Override
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		if (getAmmo(stack) > 0)
			tooltip.add(new TextComponentTranslation("raygun.ammo").getFormattedText() + ": " + getAmmo(stack));
		else
			tooltip.add(new TextComponentTranslation("raygun.ammo.none").getFormattedText());
		super.addInformation(stack, worldIn, tooltip, flagIn);
	}

}
