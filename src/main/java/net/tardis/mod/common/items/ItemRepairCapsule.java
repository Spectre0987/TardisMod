package net.tardis.mod.common.items;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.tardis.mod.client.creativetabs.TardisTabs;
import net.tardis.mod.common.dimensions.TDimensions;
import net.tardis.mod.common.items.components.ItemComponent;
import net.tardis.mod.common.sounds.TSounds;
import net.tardis.mod.common.systems.TardisSystems.BaseSystem;
import net.tardis.mod.common.tileentity.TileEntityTardis;
import net.tardis.mod.util.common.helpers.Helper;
import net.tardis.mod.util.common.helpers.PlayerHelper;
import net.tardis.mod.util.common.helpers.TardisHelper;

public class ItemRepairCapsule extends Item {

	public ItemRepairCapsule() {
		this.setCreativeTab(TardisTabs.ITEMS);
		this.setMaxStackSize(1);
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
		if(!worldIn.isRemote) {
			
			ItemStack stack = playerIn.getHeldItem(handIn);
			
			if(getComponent(stack) == ItemStack.EMPTY) {
				int i = 0;
				for(ItemStack inv : playerIn.inventory.mainInventory) {
					if(inv.getItem() instanceof ItemComponent) {
						setComponent(stack, inv);
						playerIn.inventory.removeStackFromSlot(i);
						break;
					}
					++i;
				}
			}
			else {
				ItemStack component = getComponent(stack);
				if(TardisHelper.hasTardis(playerIn.getUniqueID())) {
					playerIn.setHeldItem(handIn, ItemStack.EMPTY);
					WorldServer server = ((WorldServer)worldIn).getMinecraftServer().getWorld(TDimensions.TARDIS_ID);
					TileEntity te = server.getTileEntity(TardisHelper.getTardis(playerIn.getUniqueID()));
					if(te instanceof TileEntityTardis) {
						TileEntityTardis tardis = (TileEntityTardis)te;
						for(BaseSystem system : tardis.systems) {
							if(system.getRepairItem() == component.getItem()) {
								worldIn.playSound(null, playerIn.getPosition(), TSounds.takeoff, SoundCategory.MASTER, 1f, 1f);
								system.setHealth((100 - component.getItemDamage()) * 0.01F);
								break;
							}
						}
					}
				}
				else PlayerHelper.sendMessage(playerIn, "No TARDIS Found!", true);
			}
		}
		return super.onItemRightClick(worldIn, playerIn, handIn);
	}
	
	public static void setComponent(ItemStack stack, ItemStack component) {
		NBTTagCompound tag = Helper.getStackTag(stack);
		tag.setTag("inv", component.serializeNBT());
	}
	
	public static ItemStack getComponent(ItemStack stack) {
		NBTTagCompound tag = stack.getTagCompound();
		if(tag != null && tag.hasKey("inv")) {
			return new ItemStack(tag.getCompoundTag("inv"));
		}
		return ItemStack.EMPTY;
	}
}
