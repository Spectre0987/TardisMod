package net.tardis.mod.common.items;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tardis.mod.client.guis.GuiVortexM;
import net.tardis.mod.common.dimensions.TDimensions;
import net.tardis.mod.config.TardisConfig;

public class ItemVortexManipulator extends Item {
	
	public ItemVortexManipulator() {
		this.setMaxStackSize(1);
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
		if (worldIn.isRemote && playerIn.dimension != TDimensions.TARDIS_ID) {
			openVM();
		}
		return super.onItemRightClick(worldIn, playerIn, handIn);
	}
	
	@SideOnly(Side.CLIENT)
	public void openVM() {
		Minecraft.getMinecraft().displayGuiScreen(new GuiVortexM());
	}
}
