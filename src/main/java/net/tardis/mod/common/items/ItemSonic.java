package net.tardis.mod.common.items;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import net.tardis.mod.Tardis;
import net.tardis.mod.common.screwdriver.IScrew;
import net.tardis.mod.common.screwdriver.ScrewdriverHandler;
import net.tardis.mod.util.common.helpers.Helper;
import net.tardis.mod.util.common.helpers.PlayerHelper;

import java.util.ArrayList;
import java.util.List;

import static net.tardis.mod.util.common.helpers.Helper.getStackTag;

public class ItemSonic extends Item {
	public static List<ItemStack> SONICS = new ArrayList<ItemStack>();
	private SoundEvent sonicSound;
	
	public ItemSonic(SoundEvent sonicSound) {
		this(sonicSound, false);
	}
	
	public ItemSonic(SoundEvent sonicSound, boolean hasSpecial) {
		this.setMaxStackSize(1);
		this.sonicSound = sonicSound;
		SONICS.add(new ItemStack(this));
		
		if (hasSpecial) {
			addPropertyOverride(new ResourceLocation(NBT.SPECIAL), (stack, worldIn, entityIn) -> {
				NBTTagCompound tag = getStackTag(stack);
				if (tag == null || !tag.hasKey(NBT.SPECIAL)) {
					return 0F; //Closed
				}
				return getStackTag(stack).getFloat(NBT.SPECIAL);
			});
		}
		
	}
	
	public static int getOpen(ItemStack stack) {
		return getStackTag(stack).getInteger(NBT.OPEN);
	}
	
	public static void setOpen(ItemStack stack, int amount) {
		getStackTag(stack).setInteger(NBT.OPEN, amount);
	}
	
	
	public static int getCharge(ItemStack stack) {
		if (stack.hasTagCompound() && stack.getTagCompound().hasKey(NBT.CHARGE_KEY))
			return getStackTag(stack).getInteger(NBT.CHARGE_KEY);
		setCharge(stack, 0);
		return getStackTag(stack).getInteger(NBT.CHARGE_KEY);
	}
	
	public static void setCharge(ItemStack stack, int charge) {
		getStackTag(stack).setInteger(NBT.CHARGE_KEY, MathHelper.clamp(charge, 0, 100));
	}
	
	// NBT Start
	private static void setMode(ItemStack stack, int i) {
		NBTTagCompound tag = getStackTag(stack);
		if (tag.hasKey(NBT.MODE_KEY)) {
			int newModeId = getMode(stack) + 1;
			if (newModeId >= ScrewdriverHandler.MODES.size())
				newModeId = 0;
			tag.setInteger(NBT.MODE_KEY, newModeId);
		} else {
			tag.setInteger(NBT.MODE_KEY, 0);
		}
	}
	
	public static int getMode(ItemStack stack) {
		if (stack.hasTagCompound() && stack.getTagCompound().hasKey(NBT.MODE_KEY)) {
			return stack.getTagCompound().getInteger(NBT.MODE_KEY);
		}
		setMode(stack, 0);
		return getMode(stack);
	}
	
	public static BlockPos getConsolePos(ItemStack stack) {
		if (stack.hasTagCompound() && stack.getTagCompound().hasKey(NBT.CONSOLE_POS))
			return BlockPos.fromLong(stack.getTagCompound().getLong(NBT.CONSOLE_POS));
		return BlockPos.ORIGIN;
	}
	
	public static void setConsolePos(ItemStack stack, BlockPos pos) {
		NBTTagCompound tag = Helper.getStackTag(stack);
		tag.setLong(NBT.CONSOLE_POS, pos.toLong());
	}
	
	@Override
	public void onCreated(ItemStack stack, World worldIn, EntityPlayer playerIn) {
		super.onCreated(stack, worldIn, playerIn);
		setCharge(stack, 100);
		setOpen(stack, 0);
	}
	
	/**
	 * returns the action that specifies what animation to play when the items is being used
	 */
	@Override
	public EnumAction getItemUseAction(ItemStack stack) {
		return EnumAction.BOW;
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer player, EnumHand handIn) {
		
		ItemStack held = player.getHeldItem(handIn);
		
		if (player.getCooldownTracker().hasCooldown(this)) return super.onItemRightClick(worldIn, player, handIn);
		
		IScrew sc = (ScrewdriverHandler.MODES.get(getMode(held)));
		RayTraceResult lookPos = this.rayTrace(worldIn, player, true);
		setOpen(held, 1);
		if (lookPos != null && lookPos.getBlockPos() != null && player.isSneaking() && worldIn.getBlockState(lookPos.getBlockPos()).getBlock() != Blocks.DISPENSER) {
			setMode(held, getMode(held) + 1);
			if (!worldIn.isRemote) {
				PlayerHelper.sendMessage(player, new TextComponentTranslation(ScrewdriverHandler.MODES.get(getMode(held)).getName()).getFormattedText(), true);
			}
		}
		
		if (getCharge(held) >= sc.energyRequired()) {
			EnumActionResult result = sc.performAction(worldIn, player, handIn);
			if (sc.causesCoolDown() && result.equals(EnumActionResult.SUCCESS) && !player.isSneaking()) {
				cooldown(held.getItem(), player, sc.getCoolDownAmount());
				worldIn.playSound(null, player.getPosition(), sonicSound, SoundCategory.PLAYERS, 0.25F, 1F);
				setCharge(held, getCharge(held) - sc.energyRequired());
			}
		}
		return super.onItemRightClick(worldIn, player, handIn);
	}
	
	@Override
	public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		if (player.getCooldownTracker().hasCooldown(this)) return EnumActionResult.FAIL;
		ItemStack held = player.getHeldItem(hand);
		EnumActionResult result = EnumActionResult.FAIL;
		setOpen(held, 1);
		getStackTag(held).setInteger("charge", getCharge(held));
		
		if (getMode(held) >= 0) {
			IScrew sc = ScrewdriverHandler.MODES.get(getMode(held));
			if (getCharge(held) >= sc.energyRequired()) {
				result = sc.blockInteraction(worldIn, pos, worldIn.getBlockState(pos), player);
				if (sc.causesCoolDown() && EnumActionResult.SUCCESS.equals(result) && !player.isSneaking()) {
					cooldown(held.getItem(), player, sc.getCoolDownAmount());
					worldIn.playSound(null, player.getPosition(), sonicSound, SoundCategory.PLAYERS, 0.5F, 1F);
					setCharge(held, getCharge(held) - sc.energyRequired());
				}
			}
		}
		
		return result;
	}
	
	@Override
	public boolean itemInteractionForEntity(ItemStack stack, EntityPlayer player, EntityLivingBase target, EnumHand hand) {
		if (player.getCooldownTracker().hasCooldown(this)) return false;
		ItemStack held = player.getHeldItem(hand);
		boolean flag = false;
		setOpen(stack, 1);
		if (getMode(held) >= 0) {
			
			IScrew sc = ScrewdriverHandler.MODES.get(getMode(held));
			
			if (getCharge(held) >= sc.energyRequired()) {
				flag = sc.entityInteraction(stack, player, target, hand);
				if (sc.causesCoolDown() && flag && !player.isSneaking()) {
					cooldown(stack.getItem(), player, sc.getCoolDownAmount());
					player.world.playSound(null, player.getPosition(), sonicSound, SoundCategory.PLAYERS, 0.5F, 1F);
					setCharge(held, getCharge(held) - sc.energyRequired());
				}
			}
		}
		return flag;
	}
	
	@Override
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		tooltip.add("Mode: " + new TextComponentTranslation(ScrewdriverHandler.MODES.get(getMode(stack)).getName()).getFormattedText());
		tooltip.add("Charge: " + getCharge(stack));
		super.addInformation(stack, worldIn, tooltip, flagIn);
	}
	
	private void cooldown(Item stack, EntityPlayer player, int ticks) {
		player.getCooldownTracker().setCooldown(stack, ticks);
	}
	
	@Override
	public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
		super.onUpdate(stack, worldIn, entityIn, itemSlot, isSelected);
		if (getOpen(stack) == 1) {
			if (entityIn.ticksExisted % 200 == 0) {
				setOpen(stack, 0);
			}
		}
	}
	
	@Override
	public String getTranslationKey(ItemStack stack) {
		return "item." + Tardis.MODID + ".sonic_screwdriver";
	}
	
	public static final class NBT {
		public static final String MODE_KEY = "mode";
		public static final String CHARGE_KEY = "charge";
		public static final String CONSOLE_POS = "console_pos";
		public static final String SPECIAL = "special";
		public static final String OPEN = "open";
		
	}
}
