package net.tardis.mod.common.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.tardis.mod.common.tileentity.TileEntityEPanel;

public class BlockEPanel extends BlockTileBase {
	
	public BlockEPanel() {
        super(Material.IRON, TileEntityEPanel::new);
		this.setHardness(1F);
        setCreativeTab(Tardis.tab);
	}

    @Override
	public EnumBlockRenderType getRenderType(IBlockState state) {
		return EnumBlockRenderType.INVISIBLE;
	}

	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}

	@Override
	public int getLightValue(IBlockState state, IBlockAccess world, BlockPos pos) {
		TileEntityEPanel panel = (TileEntityEPanel)world.getTileEntity(pos);
		if(panel != null && Block.getStateById(panel.getID()).getBlock() != state.getBlock()) {
			return Block.getStateById(panel.getID()).getLightValue(world, pos);
		}
		return super.getLightValue(state, world, pos);
	}

	@Override
	public boolean isNormalCube(IBlockState state, IBlockAccess world, BlockPos pos) {
		return false;
	}

	@Override
	public int getLightOpacity(IBlockState state, IBlockAccess world, BlockPos pos) {
		TileEntityEPanel panel = (TileEntityEPanel)world.getTileEntity(pos);
		if(panel != null && Block.getStateById(panel.getID()).getBlock() != state.getBlock()) {
			return 0;
		}
		return super.getLightOpacity(state, world, pos);
	}

	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		ItemStack held = playerIn.getHeldItem(hand);
		if(held.getItem() instanceof ItemBlock) {
			((TileEntityEPanel)worldIn.getTileEntity(pos)).setID(((ItemBlock)held.getItem()).getBlock().getDefaultState());
			held.shrink(1);
			return true;
		}
		return false;
	}
}
