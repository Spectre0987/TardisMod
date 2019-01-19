package net.tardis.mod.common.items.components;

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
import net.minecraft.world.World;
import net.tardis.mod.common.tileentity.TileEntityTardis;

public class ItemArtronCapacitor extends Item {

	public ItemArtronCapacitor() {
		this.setMaxStackSize(1);
	}

	@Override
	public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		if (!worldIn.isRemote) {
			NBTTagCompound tag = player.getHeldItem(hand).getTagCompound();
			if (tag != null && tag.hasKey("artron")) {
				TileEntity te = worldIn.getTileEntity(pos);
				if (te != null && te instanceof TileEntityTardis) {
					TileEntityTardis tardis = (TileEntityTardis) te;
					//tardis.setFuel(tardis.fuel + (tag.getFloat("artron") * 0.1));
				}
			}
		}
		return super.onItemUse(player, worldIn, pos, hand, facing, hitX, hitY, hitZ);
	}

	@Override
	public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
		super.onUpdate(stack, worldIn, entityIn, itemSlot, isSelected);
		if (isSelected && !worldIn.isRemote && entityIn instanceof EntityPlayer) {

		}
	}

}
