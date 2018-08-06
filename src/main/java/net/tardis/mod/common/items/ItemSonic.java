package net.tardis.mod.common.items;

import java.util.List;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import net.tardis.mod.Tardis;
import net.tardis.mod.common.screwdriver.IScrew;
import net.tardis.mod.common.screwdriver.IScrewAction;
import net.tardis.mod.common.screwdriver.IScrewable;
import net.tardis.mod.common.screwdriver.ScrewdriverMode;
import net.tardis.mod.common.sounds.TSounds;
import net.tardis.mod.util.helpers.Helper;

public class ItemSonic extends Item {
	
	public static final String MODE_KEY = "mode";
	public static final String CONSOLE_POS = "console_pos";
	
	public ItemSonic() {
		this.setCreativeTab(Tardis.tab);
		this.setMaxStackSize(1);
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer player, EnumHand handIn) {
		ItemStack held = player.getHeldItem(handIn);
		if (player.isSneaking()) {
			setMode(held, getMode(held) + 1);
			if (!worldIn.isRemote) Helper.tell(player, new TextComponentTranslation(ScrewdriverMode.modes.get(getMode(held)).getName()).getFormattedText());
		} else {
			IScrew sc = (ScrewdriverMode.modes.get(getMode(held)));
			if (sc instanceof IScrewAction) ((IScrewAction) sc).preform(worldIn, player, handIn);
			worldIn.playSound(null, player.getPosition(), TSounds.sonic, SoundCategory.PLAYERS, 0.25F, 1F);
		}
		return super.onItemRightClick(worldIn, player, handIn);
	}
	
	@Override
	public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		ItemStack held = player.getHeldItem(hand);
		if (getMode(held) >= 0) {
			IScrew sc = ScrewdriverMode.modes.get(getMode(held));
			if (sc instanceof IScrewable) {
				((IScrewable) sc).screw(worldIn, pos, worldIn.getBlockState(pos), player);
				worldIn.playSound(null, player.getPosition(), TSounds.sonic, SoundCategory.PLAYERS, 0.5F, 1F);
			}
		}
		return super.onItemUse(player, worldIn, pos, hand, facing, hitX, hitY, hitZ);
	}
	
	// NBT Start
	private static void setMode(ItemStack stack, int i) {
		if (!stack.hasTagCompound()) stack.setTagCompound(new NBTTagCompound());
		NBTTagCompound tag = stack.getTagCompound();
		int in = getMode(stack) + 1;
		tag.setInteger(MODE_KEY, in >= ScrewdriverMode.modes.size() ? 0 : in);
	}
	
	public static int getMode(ItemStack stack) {
		if (stack.hasTagCompound()) {
			return stack.getTagCompound().getInteger(MODE_KEY);
		}
		setMode(stack, 0);
		return getMode(stack);
	}
	
	public static BlockPos getConsolePos(ItemStack stack) {
		if(stack.hasTagCompound()) {
			if(stack.getTagCompound().hasKey(CONSOLE_POS)) {
				return BlockPos.fromLong(stack.getTagCompound().getLong(CONSOLE_POS));
			}
		}
		return BlockPos.ORIGIN;
	}
	
	public static void setConsolePos(ItemStack stack, BlockPos pos) {
		if(!stack.hasTagCompound()) {
			stack.setTagCompound(new NBTTagCompound());
		}
		NBTTagCompound tag = stack.getTagCompound();
		tag.setLong(CONSOLE_POS, pos.toLong());
	}
	
	@Override
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		try {
			tooltip.add("Mode: " + new TextComponentTranslation(ScrewdriverMode.modes.get(getMode(stack)).getName()).getFormattedText());
		}
		catch(Exception e) {}
		super.addInformation(stack, worldIn, tooltip, flagIn);
	}
}
