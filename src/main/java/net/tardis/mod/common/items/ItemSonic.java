package net.tardis.mod.common.items;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import net.tardis.mod.Tardis;
import net.tardis.mod.common.screwdriver.IScrew;
import net.tardis.mod.common.screwdriver.ScrewdriverHandler;
import net.tardis.mod.common.sounds.TSounds;
import net.tardis.mod.util.helpers.PlayerHelper;

import java.util.List;

public class ItemSonic extends Item {
	
	public static final String MODE_KEY = "mode";
	public static final String CONSOLE_POS = "console_pos";

	private int charge = 100;

	public ItemSonic() {
		this.setCreativeTab(Tardis.tab);
		this.setMaxStackSize(1);
	}

	public int getCharge() {
		return charge;
	}

	public void setCharge(ItemStack stack, int charge) {
		this.charge = charge;
		if (stack.hasTagCompound()) {
			NBTTagCompound nbt = stack.getTagCompound();
			nbt.setInteger("charge", charge);
		} else {
			stack.setTagCompound(new NBTTagCompound());
			NBTTagCompound nbt = stack.getTagCompound();
			nbt.setInteger("charge", charge);
		}
	}


	@Override
	public void onCreated(ItemStack stack, World worldIn, EntityPlayer playerIn) {
		super.onCreated(stack, worldIn, playerIn);
		setCharge(stack, 100);
	}

	/**
	 * returns the action that specifies what animation to play when the items is being used
	 */
	@Override
	public EnumAction getItemUseAction(ItemStack stack) {
		return EnumAction.BOW;
	}


	// NBT Start
	private static void setMode(ItemStack stack, int i) {
		if (!stack.hasTagCompound()) stack.setTagCompound(new NBTTagCompound());
		NBTTagCompound tag = stack.getTagCompound();
		int in = getMode(stack) + 1;
		tag.setInteger(MODE_KEY, in >= ScrewdriverHandler.MODES.size() ? 0 : in);
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer player, EnumHand handIn) {

		ItemStack held = player.getHeldItem(handIn);
		IScrew sc = (ScrewdriverHandler.MODES.get(getMode(held)));

		BlockPos lookPos = player.rayTrace(100, 1.0F).getBlockPos();

		if (player.isSneaking() && worldIn.getBlockState(lookPos).getBlock() != Blocks.DISPENSER) {
			setMode(held, getMode(held) + 1);
			if (!worldIn.isRemote) {
				PlayerHelper.sendMessage(player, new TextComponentTranslation(ScrewdriverHandler.MODES.get(getMode(held)).getName()).getFormattedText(), true);
			}
		}

		if (getCharge() >= sc.energyRequired()) {
			EnumActionResult result = sc.performAction(worldIn, player, handIn);
			if (sc.causesCoolDown() && result.equals(EnumActionResult.SUCCESS)) {
				cooldown(held.getItem(), player, sc.getCoolDownAmount());
				worldIn.playSound(null, player.getPosition(), TSounds.sonic, SoundCategory.PLAYERS, 0.25F, 1F);
				setCharge(held, getCharge() - sc.energyRequired());
			}
		}
		return super.onItemRightClick(worldIn, player, handIn);
	}

	@Override
	public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		ItemStack held = player.getHeldItem(hand);
		EnumActionResult result = EnumActionResult.FAIL;

		if(!held.hasTagCompound()){
			NBTTagCompound nbt = held.getTagCompound();
			nbt.setInteger("charge", getCharge());
		}
		
		if (getMode(held) >= 0) {
			IScrew sc = ScrewdriverHandler.MODES.get(getMode(held));
			if (getCharge() >= sc.energyRequired()) {
				result = sc.blockInteraction(worldIn, pos, worldIn.getBlockState(pos), player);
				if (sc.causesCoolDown() && result.equals(EnumActionResult.SUCCESS)) {
					cooldown(held.getItem(), player, sc.getCoolDownAmount());
					worldIn.playSound(null, player.getPosition(), TSounds.sonic, SoundCategory.PLAYERS, 0.5F, 1F);
					setCharge(held, getCharge() - sc.energyRequired());
				}
			}
		}

		return result;
	}

	@Override
	public boolean itemInteractionForEntity(ItemStack stack, EntityPlayer player, EntityLivingBase target, EnumHand hand) {

		ItemStack held = player.getHeldItem(hand);
		boolean flag = false;

		if (getMode(held) >= 0) {

			IScrew sc = ScrewdriverHandler.MODES.get(getMode(held));

			if (getCharge() >= sc.energyRequired()) {
				flag = sc.entityInteraction(stack, player, target, hand);
				if (sc.causesCoolDown() && flag) {
					cooldown(stack.getItem(), player, sc.getCoolDownAmount());
					player.world.playSound(null, player.getPosition(), TSounds.sonic, SoundCategory.PLAYERS, 0.5F, 1F);
					setCharge(held, getCharge() - sc.energyRequired());
				}
			}
		}
		return flag;
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
		tooltip.add("Mode: " + new TextComponentTranslation(ScrewdriverHandler.MODES.get(getMode(stack)).getName()).getFormattedText());
		tooltip.add("Charge: " + getCharge());
		super.addInformation(stack, worldIn, tooltip, flagIn);
	}

	private void cooldown(Item stack, EntityPlayer player, int ticks) {
		player.getCooldownTracker().setCooldown(stack, ticks);
	}
}
