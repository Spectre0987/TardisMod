package net.tardis.mod.common.items;

import java.util.List;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import net.tardis.mod.cap.ITardisTracker;
import net.tardis.mod.common.tileentity.TileEntityTardis;
import net.tardis.mod.util.common.helpers.Helper;

public class ItemTracker extends Item {
	
	public ItemTracker(){}

	@Override
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		ITardisTracker tracker = stack.getCapability(ITardisTracker.TRACKER, null);
		if(tracker != null && tracker.getConsolePos() != null) {
			tooltip.add(new TextComponentTranslation("msg.tardis.tracker.tooltip", Helper.formatBlockPos(tracker.getConsolePos())).getFormattedText());
		}
		super.addInformation(stack, worldIn, tooltip, flagIn);
	}

	@Override
	public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		TileEntity te = worldIn.getTileEntity(pos);
		if(!worldIn.isRemote && te instanceof TileEntityTardis) {
			ITardisTracker tracker = player.getHeldItem(hand).getCapability(ITardisTracker.TRACKER, EnumFacing.DOWN);
			if(tracker != null) {
				tracker.setConsole(pos);
			}
		}
		return super.onItemUse(player, worldIn, pos, hand, facing, hitX, hitY, hitZ);
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
		ITardisTracker tracker = playerIn.getHeldItem(handIn).getCapability(ITardisTracker.TRACKER, null);
		if(tracker != null) {
			if(!worldIn.isRemote) {
				if(tracker.getConsole() == null)
					tracker.findConsoleInstance();
				tracker.onRightClick(worldIn, playerIn, handIn);
			}
		}
		return super.onItemRightClick(worldIn, playerIn, handIn);
	}
}
