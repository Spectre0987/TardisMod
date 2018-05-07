package net.tardis.mod.common.items;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.tardis.mod.Tardis;
import net.tardis.mod.packets.MessageSB;

public class ItemSonicBlaster extends Item {
	
	public ItemSonicBlaster() {
		this.setCreativeTab(Tardis.tab);
		this.setMaxStackSize(1);
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
		if (worldIn.isRemote) {
			RayTraceResult ray = playerIn.rayTrace(64D, 0.5F);
			if (ray != null && ray.typeOfHit == RayTraceResult.Type.BLOCK) {
				Tardis.packet_instance.sendToServer(new MessageSB(ray.getBlockPos()));
			}
		}
		return super.onItemRightClick(worldIn, playerIn, handIn);
	}
}
