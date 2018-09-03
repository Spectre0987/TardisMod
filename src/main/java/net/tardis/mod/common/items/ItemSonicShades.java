package net.tardis.mod.common.items;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.tardis.mod.client.EnumClothes;
import net.tardis.mod.util.helpers.Helper;
import net.tardis.mod.util.helpers.RiftHelper;

public class ItemSonicShades extends ItemHat {

	public ItemSonicShades(EnumClothes clothType) {
		super(clothType);
	}

	@Override
	public void onArmorTick(World world, EntityPlayer player, ItemStack stack) {
		super.onArmorTick(world, player, stack);
		if(!world.isRemote && world.getWorldTime() % 20 == 0) {
			NBTTagCompound tag = Helper.getStackTag(player.getItemStackFromSlot(EntityEquipmentSlot.HEAD));
			boolean inRift = RiftHelper.isRift(world.getChunkFromBlockCoords(player.getPosition()).getPos(), world);
			if(!tag.hasKey("rift")) {
				tag.setBoolean("rift", RiftHelper.isRift(world.getChunkFromBlockCoords(player.getPosition()).getPos(), world));
			}
			else {
				boolean rift = tag.getBoolean("rift");
				if(rift != inRift) {
					tag.setBoolean("rift", inRift);
				}
			}
		}
	}

}
