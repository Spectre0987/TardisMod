package net.tardis.mod.common.items;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBucket;
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

import static net.tardis.mod.util.common.helpers.Helper.getStackTag;

public class ItemSonic extends Item {

	public static final String MODE_KEY = "mode";
	public static final String CONSOLE_POS = "console_pos";
	public static List<ItemStack> SONICS = new ArrayList<ItemStack>();
	private SoundEvent sonicSound;

	public ItemSonic(SoundEvent sonicSound){
		this(sonicSound, false);
	}
	
	public ItemSonic(SoundEvent sonicSound, boolean hasSpecial) {
		this.setMaxStackSize(1);
		this.sonicSound = sonicSound;
		SONICS.add(new ItemStack(this));
		
		if(hasSpecial){
			addPropertyOverride(new ResourceLocation("special"), (stack, worldIn, entityIn) -> {
				if (getStackTag(stack) == null || !getStackTag(stack).hasKey("special")) {
					return 0F; //Closed
				}
				return getStackTag(stack).getFloat("special");
			});
		}
		
	}
	
	public static int getOpen(ItemStack stack) {
		return getStackTag(stack).getInteger("open");
	}
	
	public static void setOpen(ItemStack stack, int amount) {
		getStackTag(stack).setInteger("open", amount);
	}
	

	public static int getCharge(ItemStack stack) {
		//return 100;
		return getStackTag(stack).getInteger("charge");
	}

	public static void setCharge(ItemStack stack, int charge) {
		getStackTag(stack).setInteger("charge", MathHelper.clamp(charge, 0, 100));
	}

	// NBT Start
	private static void setMode(ItemStack stack, int i) {
		if (!stack.hasTagCompound()) stack.setTagCompound(new NBTTagCompound());
		NBTTagCompound tag = stack.getTagCompound();
		int in = getMode(stack) + 1;
		tag.setInteger(MODE_KEY, in >= ScrewdriverHandler.MODES.size() ? 0 : in);
	}

	public static int getMode(ItemStack stack) {
		if (stack.hasTagCompound()) {
			return stack.getTagCompound().getInteger(MODE_KEY);
		}
		setMode(stack, 0);
		return getMode(stack);
	}

	public static BlockPos getConsolePos(ItemStack stack) {
		if (stack.hasTagCompound()) {
			if (getStackTag(stack).hasKey(CONSOLE_POS)) {
				if (stack.getTagCompound() != null) {
					return BlockPos.fromLong(stack.getTagCompound().getLong(CONSOLE_POS));
				}
			}
		}
		return BlockPos.ORIGIN;
	}

	public static void setConsolePos(ItemStack stack, BlockPos pos) {
		if (!stack.hasTagCompound()) {
			stack.setTagCompound(new NBTTagCompound());
		}
		NBTTagCompound tag = Helper.getStackTag(stack);
		if (tag != null) {
			tag.setLong(CONSOLE_POS, pos.toLong());
		}
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
			if (sc.causesCoolDown() && result.equals(EnumActionResult.SUCCESS)) {
				cooldown(held.getItem(), player, sc.getCoolDownAmount());
				worldIn.playSound(null, player.getPosition(), sonicSound, SoundCategory.PLAYERS, 0.25F, 1F);
				setCharge(held, getCharge(held) - sc.energyRequired());
			}
		}
		return super.onItemRightClick(worldIn, player, handIn);
	}

	@Override
	public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		ItemStack held = player.getHeldItem(hand);
		EnumActionResult result = EnumActionResult.FAIL;
		setOpen(held, 1);
		getStackTag(held).setInteger("charge", getCharge(held));

		if (getMode(held) >= 0) {
			IScrew sc = ScrewdriverHandler.MODES.get(getMode(held));
			if (getCharge(held) >= sc.energyRequired()) {
				result = sc.blockInteraction(worldIn, pos, worldIn.getBlockState(pos), player);
				if (sc.causesCoolDown() && EnumActionResult.SUCCESS.equals(result)) {
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

		ItemStack held = player.getHeldItem(hand);
		boolean flag = false;
		setOpen(stack, 1);
		if (getMode(held) >= 0) {

			IScrew sc = ScrewdriverHandler.MODES.get(getMode(held));

			if (getCharge(held) >= sc.energyRequired()) {
				flag = sc.entityInteraction(stack, player, target, hand);
				if (sc.causesCoolDown() && flag) {
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
}
