package net.tardis.mod.common.blocks;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemMap;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraft.world.storage.MapData;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tardis.mod.client.guis.GuiMonitor;
import net.tardis.mod.common.blocks.interfaces.IRenderBox;
import net.tardis.mod.common.tileentity.TileEntityTardis;
import net.tardis.mod.util.common.helpers.TardisHelper;

import java.util.function.Supplier;

public class BlockMonitor extends BlockFacingDecoration implements IRenderBox {

	public BlockMonitor(Supplier<TileEntity> tileEntity) {
		super(tileEntity);
	}

	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		ItemStack held = playerIn.getHeldItem(hand);
		TileEntityTardis tardis = (TileEntityTardis) worldIn.getTileEntity(TardisHelper.getTardisForPosition(pos));
		if (tardis == null) return true;
		if (held.getItem() instanceof ItemMap) {
			if (!worldIn.isRemote) {
				MapData data = ItemMap.loadMapData(held.getItemDamage(), worldIn);
				WorldServer ws = worldIn.getMinecraftServer().getWorld(data.dimension);
				tardis.setDesination(ws.getTopSolidOrLiquidBlock(new BlockPos(data.xCenter, 0, data.zCenter)), data.dimension);
			}
		} else {
			if (worldIn.isRemote) {
				this.openGui(tardis);
			}
		}
		return true;
	}

	@SideOnly(Side.CLIENT)
	public void openGui(TileEntityTardis tardis) {
		Minecraft.getMinecraft().displayGuiScreen(new GuiMonitor(tardis.getPos()));
	}

	@Override
	public void getDrops(NonNullList<ItemStack> drops, IBlockAccess world, BlockPos pos, IBlockState state, int fortune) {
		drops.clear();
		drops.add(new ItemStack(this));
		super.getDrops(drops, world, pos, state, fortune);
	}
	
	@Override
	public boolean shouldRenderBox() {
		return false;
	}
}
