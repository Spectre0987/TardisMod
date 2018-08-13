package net.tardis.mod.common.items;

import java.util.List;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.tardis.mod.common.dimensions.TDimensions;
import net.tardis.mod.common.strings.TStrings;
import net.tardis.mod.common.tileentity.TileEntityTardis;
import net.tardis.mod.util.helpers.Helper;

public class ItemRemote extends ItemBase {

	public ItemRemote(String name) {
		super(name);
		this.setMaxStackSize(1);
	}

	@Override
	public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		TileEntity te = worldIn.getTileEntity(pos);
		if(te != null && te instanceof TileEntityTardis) {
			this.setConsolePos(player.getHeldItem(hand), pos);
			return EnumActionResult.SUCCESS;
		}
		else if(!worldIn.isRemote && !this.getConsolePos(player.getHeldItem(hand)).equals(BlockPos.ORIGIN)) {
			TileEntity tte = ((WorldServer)worldIn).getMinecraftServer().getWorld(TDimensions.TARDIS_ID).getTileEntity(this.getConsolePos(player.getHeldItem(hand)));
			if(tte != null && tte instanceof TileEntityTardis) {
				TileEntityTardis tardis = ((TileEntityTardis)tte);
				tardis.setDesination(pos.up(3), player.dimension);
				tardis.setFacing(player.getHorizontalFacing().getOpposite());
				tardis.startFlight();
			}
		}
		return EnumActionResult.PASS;
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

	@Override
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		if(stack.hasTagCompound() && stack.getTagCompound().hasKey(NBT.CONSOLE_POS)) {
			tooltip.add(new TextComponentTranslation(TStrings.ToolTips.REMOTE).getFormattedText() + " " + Helper.formatBlockPos(this.getConsolePos(stack)));
			String format = (stack.getTagCompound().getFloat(NBT.FUEL) * 100 + "");
			tooltip.add(new TextComponentTranslation(TStrings.ToolTips.REMOTE_FUEL).getFormattedText() + " " + format.substring(0, format.indexOf(".")) + "%");
			tooltip.add(new TextComponentTranslation(TStrings.ToolTips.REMOTE_TIME).getFormattedText() + " " +stack.getTagCompound().getInteger(NBT.TIME) / 20 + " " + new TextComponentTranslation(TStrings.SECONDS).getFormattedText());
			tooltip.add(new TextComponentTranslation(TStrings.ToolTips.REMOTE_EPOS).getFormattedText() + " " + Helper.formatBlockPos(BlockPos.fromLong(stack.getTagCompound().getLong(NBT.POS))));
		}
		super.addInformation(stack, worldIn, tooltip, flagIn);
	}
	
	public static BlockPos getConsolePos(ItemStack stack) {
		if(stack.hasTagCompound() && stack.getTagCompound().hasKey(NBT.CONSOLE_POS)) {
			return BlockPos.fromLong(stack.getTagCompound().getLong(NBT.CONSOLE_POS));
		}
		return BlockPos.ORIGIN;
	}
	
	@Override
	public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
		super.onUpdate(stack, worldIn, entityIn, itemSlot, isSelected);
		if(!worldIn.isRemote && !this.getConsolePos(stack).equals(BlockPos.ORIGIN)) {
			WorldServer ws = ((WorldServer)worldIn).getMinecraftServer().getWorld(TDimensions.TARDIS_ID);
			TileEntityTardis tardis = (TileEntityTardis)ws.getTileEntity(this.getConsolePos(stack));
			if(tardis != null && tardis.isInFlight()) {
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

	public static void setConsolePos(ItemStack s, BlockPos pos) {
		if(s.getTagCompound() == null) s.setTagCompound(new NBTTagCompound());
		s.getTagCompound().setLong(NBT.CONSOLE_POS, pos.toLong());
	}

	public static class NBT{
		public static final String POS = "tardis_position";
		public static final String TIME = "time_left";
		public static final String CONSOLE_POS = "console_pos";
		public static final String FUEL = "fuel";
	}
}
