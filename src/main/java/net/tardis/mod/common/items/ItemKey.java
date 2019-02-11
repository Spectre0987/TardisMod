package net.tardis.mod.common.items;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tardis.mod.Tardis;
import net.tardis.mod.common.strings.TStrings;
import net.tardis.mod.util.common.helpers.Helper;
import net.tardis.mod.util.common.helpers.TardisHelper;

import java.util.List;
import java.util.UUID;

public class ItemKey extends Item {

	public static final ResourceLocation CONSOLE_ROOM = new ResourceLocation(Tardis.MODID, "console_room");

	public ItemKey() {

		this.setMaxStackSize(1);
	}

	public static void setPos(ItemStack stack, BlockPos pos) {
		if (!stack.hasTagCompound()) {
			stack.setTagCompound(new NBTTagCompound());
		}
		NBTTagCompound tag = stack.getTagCompound();
		tag.setLong("pos", pos.toLong());
	}

	public static BlockPos getPos(ItemStack stack) {
		if (stack.hasTagCompound() && stack.getTagCompound().hasKey("pos")) {
			return BlockPos.fromLong(stack.getTagCompound().getLong("pos"));
		}
		return null;
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
		UUID id = playerIn.getGameProfile().getId();
		if (getPos(playerIn.getHeldItem(handIn)) == null && TardisHelper.hasTardis(id)) {
			setPos(playerIn.getHeldItem(handIn), TardisHelper.getTardis(id));
		}
		return super.onItemRightClick(worldIn, playerIn, handIn);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		if (stack.hasTagCompound() && stack.getTagCompound().hasKey("pos")) {
			tooltip.add(new TextComponentTranslation(TStrings.KEY_CONSOLE_LOCATION).getFormattedText() + " " + Helper.formatBlockPos(getPos(stack)));
		}
		super.addInformation(stack, worldIn, tooltip, flagIn);
	}

	@Override
	public boolean doesSneakBypassUse(ItemStack stack, IBlockAccess world, BlockPos pos, EntityPlayer player) {
		return true;
	}

}
