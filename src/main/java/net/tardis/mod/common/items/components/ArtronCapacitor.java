package net.tardis.mod.common.items.components;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.tardis.mod.common.tileentity.TileEntityTardis;

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
	
}
