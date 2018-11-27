package net.tardis.mod.common.items;

import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import net.tardis.mod.client.guis.manual.GuiManual;
import net.tardis.mod.common.strings.TStrings;

public class ItemManual extends ItemBase {
	
	public ItemManual() {
		this.setMaxStackSize(1);
	}
	
	@Override
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		tooltip.add(new TextComponentTranslation("manual.help").getFormattedText());
		tooltip.add(new TextComponentTranslation(TStrings.ToolTips.MANUAL_SUPERNOVA).getFormattedText());
		super.addInformation(stack, worldIn, tooltip, flagIn);
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
		if (worldIn.isRemote) {
			Minecraft.getMinecraft().displayGuiScreen(new GuiManual());
		}
		return super.onItemRightClick(worldIn, playerIn, handIn);
	}
	
}
