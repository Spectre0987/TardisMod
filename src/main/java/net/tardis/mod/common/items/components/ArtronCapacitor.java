package net.tardis.mod.common.items.components;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.tardis.mod.common.tileentity.TileEntityTardis;
import net.tardis.mod.util.helpers.RiftHelper;

public class ArtronCapacitor extends ItemComponent {
	
	public ArtronCapacitor() {
		this.setMaxDamage(5);
		this.setMaxStackSize(1);
	}

	@Override
	public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand,EnumFacing facing, float hitX, float hitY, float hitZ) {
		if(!worldIn.isRemote) {
			TileEntity te = worldIn.getTileEntity(pos);
			if(te != null && te instanceof TileEntityTardis) {
				TileEntityTardis tardis = (TileEntityTardis)te;
				if(tardis.fuel < 1) {
					tardis.fuel += 0.1;
					tardis.markDirty();
					player.getHeldItem(hand).damageItem(1, player);
				}
				
			}
		}
		return super.onItemUse(player, worldIn, pos, hand, facing, hitX, hitY, hitZ);
	}

	@Override
	public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
		super.onUpdate(stack, worldIn, entityIn, itemSlot, isSelected);
		if(isSelected && !worldIn.isRemote && entityIn instanceof EntityPlayer && worldIn.getTotalWorldTime() % 20 == 0) {
			EntityPlayer player = (EntityPlayer)entityIn;
			player.sendStatusMessage(new TextComponentString("Rift: " + RiftHelper.isRift(worldIn.getChunk(entityIn.getPosition()).getPos(), worldIn)), true);
		}
	}
	
}
