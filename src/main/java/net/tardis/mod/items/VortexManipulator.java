package net.tardis.mod.items;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tardis.mod.Tardis;
import net.tardis.mod.guis.GuiVortexM;

public class VortexManipulator extends Item {
	
	public VortexManipulator() {
		this.setMaxStackSize(1);
		this.setCreativeTab(Tardis.tab);
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
		if(worldIn.isRemote) {
			openVM();
		}
		return super.onItemRightClick(worldIn, playerIn, handIn);
	}

	@SideOnly(Side.CLIENT)
	public void openVM() {
		Minecraft.getMinecraft().displayGuiScreen(new GuiVortexM());
	}
}
