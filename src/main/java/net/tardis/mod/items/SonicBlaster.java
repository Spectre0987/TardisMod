package net.tardis.mod.items;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.tardis.mod.Tardis;
import net.tardis.mod.helpers.Helper;

public class SonicBlaster extends Item {
	
	public SonicBlaster() {
		this.setCreativeTab(Tardis.tab);
		this.setMaxStackSize(1);
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
		
		return super.onItemRightClick(worldIn, playerIn, handIn);
	}
}
