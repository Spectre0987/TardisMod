package net.tardis.mod.common.items.components;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import net.tardis.mod.common.tileentity.TileEntityTardis;
import net.tardis.mod.util.common.helpers.Helper;
import net.tardis.mod.util.common.helpers.RiftHelper;

import java.text.DecimalFormat;
import java.util.List;

public class ItemArtronCapacitor extends Item {
	
	static DecimalFormat FORMAT = new DecimalFormat("#.##");
	
	public ItemArtronCapacitor() {
		this.setMaxStackSize(1);
	}
	
	public static void setPower(ItemStack stack, float power) {
		NBTTagCompound tag = Helper.getStackTag(stack);
		tag.setFloat("artron", power);
		stack.setTagCompound(tag);
	}
	
	public static float getPower(ItemStack stack) {
		NBTTagCompound tag = Helper.getStackTag(stack);
		return tag.hasKey("artron") ? tag.getFloat("artron") : 0F;
	}

	@Override
	public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		if (!worldIn.isRemote) {
			NBTTagCompound tag = player.getHeldItem(hand).getTagCompound();
			if (tag != null && tag.hasKey("artron")) {
				TileEntity te = worldIn.getTileEntity(pos);
				if (te != null && te instanceof TileEntityTardis) {
					TileEntityTardis tardis = (TileEntityTardis) te;
					tardis.setFuel(tardis.fuel + (tag.getFloat("artron") * 0.1F));
					ItemArtronCapacitor.setPower(player.getHeldItem(hand), 0);
				}
			}
		}
		return super.onItemUse(player, worldIn, pos, hand, facing, hitX, hitY, hitZ);
	}

	@Override
	public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
		super.onUpdate(stack, worldIn, entityIn, itemSlot, isSelected);
		if (!worldIn.isRemote && RiftHelper.isRift(worldIn.getChunk(entityIn.getPosition()).getPos(), worldIn)) {
			float oldPower = ItemArtronCapacitor.getPower(stack);
			if (oldPower < 1.0F)
				ItemArtronCapacitor.setPower(stack, (float) MathHelper.clamp(ItemArtronCapacitor.getPower(stack) + 0.005, 0, 1));
		}
	}
	
	@Override
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		if (stack != null) {
			NBTTagCompound tag = Helper.getStackTag(stack);
			if (tag.hasKey("artron")) {
				tooltip.add(new TextComponentTranslation("tooltip.tardis.artron_capacitor").getFormattedText() + ": " + FORMAT.format(tag.getFloat("artron") * 100));
			}
		}
		super.addInformation(stack, worldIn, tooltip, flagIn);
	}
	
	@Override
	public boolean shouldCauseReequipAnimation(ItemStack oldStack, ItemStack newStack, boolean slotChanged) {
		return slotChanged;
	}
}
