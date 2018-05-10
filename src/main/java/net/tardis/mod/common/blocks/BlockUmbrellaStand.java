package net.tardis.mod.common.blocks;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.tardis.mod.Tardis;
import net.tardis.mod.common.items.ItemSUmbrella;
import net.tardis.mod.common.tileentity.TileEntityUmbrellaStand;

public class BlockUmbrellaStand extends BlockContainer {
	
	public BlockUmbrellaStand() {
		super(Material.CLAY);
		this.setCreativeTab(Tardis.tab);
	}
	
	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileEntityUmbrellaStand();
	}
	
	@Override
	public EnumBlockRenderType getRenderType(IBlockState state) {
		return EnumBlockRenderType.MODEL;
	}
	
	@Override
	public boolean isNormalCube(IBlockState state) {
		return false;
	}
	
	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}
	
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		if (playerIn.getHeldItem(hand).getItem() instanceof ItemSUmbrella || playerIn.getHeldItem(hand).isEmpty()) {
			TileEntityUmbrellaStand te = (TileEntityUmbrellaStand) worldIn.getTileEntity(pos);
			te.setItem(playerIn.getHeldItem(hand));
			playerIn.setHeldItem(hand, ItemStack.EMPTY);
		}
		return true;
	}
	
}
