package net.tardis.mod.common.items;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.tardis.mod.common.dimensions.TDimensions;
import net.tardis.mod.common.sounds.TSounds;
import net.tardis.mod.common.strings.TStrings;
import net.tardis.mod.common.systems.SystemStabilizers;
import net.tardis.mod.common.tileentity.TileEntityTardis;
import net.tardis.mod.util.common.helpers.Helper;

import java.util.List;

public class ItemRemote extends ItemBase {
	
	public ItemRemote() {
		this.setMaxStackSize(1);
	}
	
	public static BlockPos getConsolePos(ItemStack stack) {
		if (stack.hasTagCompound() && stack.getTagCompound().hasKey(NBT.CONSOLE_POS)) {
			return BlockPos.fromLong(stack.getTagCompound().getLong(NBT.CONSOLE_POS));
		}
		return BlockPos.ORIGIN;
	}

	/*@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
		if(!worldIn.isRemote && !Helper.isDimensionBlocked(playerIn.dimension) && !this.getConsolePos(playerIn.getHeldItem(handIn)).equals(BlockPos.ORIGIN)) {
			TileEntityTardis tardis = ((TileEntityTardis)((WorldServer)worldIn).getMinecraftServer().getWorld(TDimensions.TARDIS_ID).getTileEntity(this.getConsolePos(playerIn.getHeldItem(handIn))));
			if(tardis != null && !tardis.isInFlight()) {
				tardis.setDesination(playerIn.getPosition().offset(playerIn.getHorizontalFacing()), playerIn.dimension);
				SystemFlight sys = null;
				for(ISystem s : tardis.systems) {
					if(s.getClass() == SystemFlight.class) {
						sys = (SystemFlight)s;
					}
				}
				if(sys != null && tardis.startFlight()) {
					sys.setHealth(sys.getHealth() - 0.09F);
				}
				
			}
		}
		return ActionResult.newResult(EnumActionResult.PASS, playerIn.getHeldItem(handIn));
	}*/
	
	public static void setConsolePos(ItemStack s, BlockPos pos) {
		if (s.getTagCompound() == null) s.setTagCompound(new NBTTagCompound());
		s.getTagCompound().setLong(NBT.CONSOLE_POS, pos.toLong());
	}
	
	@Override
	public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		TileEntity te = worldIn.getTileEntity(pos);
		if (te != null && te instanceof TileEntityTardis) {
			setConsolePos(player.getHeldItem(hand), pos);
			return EnumActionResult.SUCCESS;
		} else if (!worldIn.isRemote && !getConsolePos(player.getHeldItem(hand)).equals(BlockPos.ORIGIN)) {
			TileEntity tte = worldIn.getMinecraftServer().getWorld(TDimensions.TARDIS_ID).getTileEntity(getConsolePos(player.getHeldItem(hand)));
			if (tte != null && tte instanceof TileEntityTardis) {
				TileEntityTardis tardis = ((TileEntityTardis) tte);
				if (tardis.hasPilot()) return EnumActionResult.FAIL;
				tardis.getSystem(SystemStabilizers.class).setOn(true);
				tardis.setDesination(pos.up(1), player.dimension);
				tardis.setFacing(player.getHorizontalFacing().getOpposite());
				tardis.startFlight();
				worldIn.playSound(null, pos, TSounds.remote_accept, SoundCategory.PLAYERS, 1.0F, 1.0F);
			}
		}
		
		return EnumActionResult.SUCCESS;
		
	}
	
	@Override
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		if (stack.hasTagCompound() && stack.getTagCompound().hasKey(NBT.CONSOLE_POS)) {
			tooltip.add(new TextComponentTranslation(TStrings.ToolTips.REMOTE).getFormattedText() + " " + Helper.formatBlockPos(getConsolePos(stack)));
			String format = (stack.getTagCompound().getFloat(NBT.FUEL) * 100 + "");
			tooltip.add(new TextComponentTranslation(TStrings.ToolTips.REMOTE_FUEL).getFormattedText() + " " + format.substring(0, format.indexOf(".")) + "%");
			tooltip.add(new TextComponentTranslation(TStrings.ToolTips.REMOTE_TIME).getFormattedText() + " " + stack.getTagCompound().getInteger(NBT.TIME) / 20 + " " + new TextComponentTranslation(TStrings.SECONDS).getFormattedText());
			tooltip.add(new TextComponentTranslation(TStrings.ToolTips.REMOTE_EPOS).getFormattedText() + " " + Helper.formatBlockPos(BlockPos.fromLong(stack.getTagCompound().getLong(NBT.POS))));
		} else tooltip.add(new TextComponentTranslation(TStrings.ToolTips.REMOTE_BIND).getFormattedText());
		super.addInformation(stack, worldIn, tooltip, flagIn);
	}
	
	@Override
	public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
		super.onUpdate(stack, worldIn, entityIn, itemSlot, isSelected);
		if (!worldIn.isRemote && !getConsolePos(stack).equals(BlockPos.ORIGIN)) {
			WorldServer ws = worldIn.getMinecraftServer().getWorld(TDimensions.TARDIS_ID);
			TileEntityTardis tardis = (TileEntityTardis) ws.getTileEntity(getConsolePos(stack));
			if (tardis != null && tardis.isInFlight()) {
				stack.getTagCompound().setFloat(NBT.FUEL, tardis.fuel);
				stack.getTagCompound().setInteger(NBT.TIME, tardis.getTimeLeft());
				stack.getTagCompound().setLong(NBT.POS, tardis.getLocation().toLong());
			}
		}
	}
	
	@Override
	public boolean shouldCauseReequipAnimation(ItemStack oldStack, ItemStack newStack, boolean slotChanged) {
		return oldStack.getItem() != newStack.getItem();
	}
	
	public static class NBT {
		public static final String POS = "tardis_position";
		public static final String TIME = "time_left";
		public static final String CONSOLE_POS = "console_pos";
		public static final String FUEL = "fuel";
	}
}
