package net.tardis.mod.common.items;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraft.world.gen.structure.template.PlacementSettings;
import net.minecraft.world.gen.structure.template.Template;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tardis.mod.Tardis;
import net.tardis.mod.common.blocks.TBlocks;
import net.tardis.mod.common.dimensions.TDimensions;
import net.tardis.mod.common.strings.TStrings;
import net.tardis.mod.common.tileentity.TileEntityTardis;
import net.tardis.mod.util.helpers.Helper;
import net.tardis.mod.util.helpers.TardisHelper;

import java.util.List;

public class ItemKey extends Item {
	
	public static final ResourceLocation CONSOLE_ROOM = new ResourceLocation(Tardis.MODID, "console_room");
	
	public ItemKey() {
		this.setCreativeTab(Tardis.tab);
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
		if (playerIn.dimension != TDimensions.id) {
			if (!worldIn.isRemote) {
				WorldServer ws = (WorldServer) worldIn;
				ItemStack stack = playerIn.getHeldItem(handIn);
				BlockPos cPos = TardisHelper.getTardis(playerIn.getGameProfile().getId());
				WorldServer tw = ws.getMinecraftServer().getWorld(TDimensions.id);
				MinecraftServer server = tw.getMinecraftServer();
				setPos(stack, cPos);
				if (tw.getTileEntity(cPos) == null) {
					Template tem = tw.getStructureTemplateManager().get(server, CONSOLE_ROOM);
					tem.addBlocksToWorld(tw, cPos.add(-(tem.getSize().getX() / 2), -1, -(tem.getSize().getZ() / 2)), new PlacementSettings());
					tw.setBlockState(cPos, TBlocks.console.getDefaultState());
					EntityItem entitySonic = new EntityItem(tw, cPos.getX(), cPos.getY(), cPos.getZ(), new ItemStack(TItems.sonic_screwdriver));
					tw.spawnEntity(entitySonic);
					TileEntityTardis te = (TileEntityTardis) tw.getTileEntity(cPos);
					te.setDesination(playerIn.getPosition().down().offset(playerIn.getHorizontalFacing().getOpposite(), 1), playerIn.dimension);
					te.setShouldLandOnSurface(true);
					te.setFacing(playerIn.getHorizontalFacing());
					te.startFlight();
					te.travel();
				}
			}
			
		}
		return super.onItemRightClick(worldIn, playerIn, handIn);
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		if (stack.hasTagCompound() && stack.getTagCompound().hasKey("pos")) {
			tooltip.add(new TextComponentTranslation(TStrings.KEY_CONSOLE_LOCATION).getFormattedText() + Helper.formatBlockPos(getPos(stack)));
		}
		super.addInformation(stack, worldIn, tooltip, flagIn);
	}
	
	@Override
	public boolean doesSneakBypassUse(ItemStack stack, IBlockAccess world, BlockPos pos, EntityPlayer player) {
		return true;
	}
}
