package net.tardis.mod.common.blocks;

import java.util.function.Supplier;

import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.tardis.mod.client.creativetabs.TardisTabs;
import net.tardis.mod.common.tileentity.TileEntityTractorBeam;

public class BlockTractorBeam extends BlockTileBase {

	public static final PropertyBool UP = PropertyBool.create("up");
	
	public BlockTractorBeam(Supplier<TileEntity> tileSup) {
		super(Material.IRON, tileSup);
		this.setCreativeTab(TardisTabs.BLOCKS);
		this.setDefaultState(this.blockState.getBaseState().withProperty(UP, true));
	}

	@Override
	public boolean isNormalCube(IBlockState state) {
		return true;
	}

	@Override
	public EnumBlockRenderType getRenderType(IBlockState state) {
		return EnumBlockRenderType.MODEL;
	}

	@Override
	public BlockRenderLayer getRenderLayer() {
		return BlockRenderLayer.TRANSLUCENT;
	}

	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return super.isOpaqueCube(state);
	}

	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, UP);
	}

	@Override
	public IBlockState getStateForPlacement(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer, EnumHand hand) {
		return this.getDefaultState();
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		return this.getDefaultState().withProperty(UP, meta == 0 ? true : false);
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		return state.getValue(UP) ? 0 : 1;
	}

	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		if(!worldIn.isRemote) {
			worldIn.setBlockState(pos, state.withProperty(UP, !state.getValue(UP)));
			TileEntity te = worldIn.getTileEntity(pos);
			if(te instanceof TileEntityTractorBeam)
				((TileEntityTractorBeam)te).pushTicks = 40;
		}
		return true;
	}

	

}
