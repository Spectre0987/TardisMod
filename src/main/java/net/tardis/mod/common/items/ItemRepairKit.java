package net.tardis.mod.common.items;

import java.util.List;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import net.tardis.mod.common.strings.TStrings;
import net.tardis.mod.common.systems.TardisSystems.BaseSystem;
import net.tardis.mod.common.tileentity.TileEntityTardis;

public class ItemRepairKit extends ItemBase {

	public ItemRepairKit() {
		this.setMaxDamage(5);
	}
	
	@Override
	public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		TileEntity te = worldIn.getTileEntity(pos);
		if (te != null && te instanceof TileEntityTardis) {
			TileEntityTardis tardis = (TileEntityTardis) te;
			for (BaseSystem sys : tardis.systems) {
				if (sys.getHealth() > 0F) {
					sys.setHealth(sys.getHealth() + 0.15F);
				}
			}
			player.getHeldItem(hand).damageItem(1, player);
		}
		return super.onItemUse(player, worldIn, pos, hand, facing, hitX, hitY, hitZ);
	}

	@Override
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		tooltip.add(new TextComponentTranslation(TStrings.ToolTips.TOOLKIT).getFormattedText() + "%");
		super.addInformation(stack, worldIn, tooltip, flagIn);
	}

}
