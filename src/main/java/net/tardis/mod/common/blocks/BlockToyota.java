package net.tardis.mod.common.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.tardis.mod.Tardis;
import net.tardis.mod.common.blocks.interfaces.INeedItem;

public class BlockToyota extends BlockBase implements INeedItem{
	
	public ItemBlock item = new ItemBlockToyota(this);

	public BlockToyota(boolean light) {
		this.setCreativeTab(Tardis.tab);
		this.item.setCreativeTab(Tardis.tab);
		if(light) {
			this.setLightLevel(1);
		}
	}
	
	public static class BlockToyotaFacing extends BlockToyota{

		public static final PropertyDirection FACING = PropertyDirection.create("facing", EnumFacing.Plane.HORIZONTAL);
		
		public ItemBlock item = new ItemBlockToyota(this);
		
		public BlockToyotaFacing(boolean light) {
			super(light);
			this.setCreativeTab(Tardis.tab);
		}

		@Override
		protected BlockStateContainer createBlockState() {
			return new BlockStateContainer(this, new IProperty[] {FACING});
		}

		@Override
		public IBlockState getStateFromMeta(int meta) {
			return this.getDefaultState().withProperty(FACING, EnumFacing.byHorizontalIndex(meta));
		}

		@Override
		public int getMetaFromState(IBlockState state) {
			return state.getValue(FACING).getHorizontalIndex();
		}

		@Override
		public IBlockState getStateForPlacement(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY,float hitZ, int meta, EntityLivingBase placer, EnumHand hand) {
			return this.getDefaultState().withProperty(FACING, placer.getHorizontalFacing());
		}
		
		
		
	}
	
	public static class BlockToyotaSlab extends BlockToyota{
		
		public ItemBlock item = new ItemBlockToyota(this);
		public static final PropertyBool IS_TOP = PropertyBool.create("top");
		public static final AxisAlignedBB BOTTOM_HALF = new AxisAlignedBB(0,0,0, 1, 0.5, 1);
		public static final AxisAlignedBB TOP_HALF = new AxisAlignedBB(0, 0.5, 0, 1, 1, 1);
		
		public BlockToyotaSlab(boolean b) {
			super(b);
		}

		@Override
		public IBlockState getStateFromMeta(int meta) {
			return this.getDefaultState().withProperty(IS_TOP, meta == 0 ? true : false);
		}

		@Override
		public int getMetaFromState(IBlockState state) {
			return state.getValue(IS_TOP) ? 0 : 1;
		}

		@Override
		protected BlockStateContainer createBlockState() {
			return new BlockStateContainer(this, new IProperty[] {IS_TOP});
		}

		@Override
		public IBlockState getStateForPlacement(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer, EnumHand hand) {
			return this.getDefaultState().withProperty(IS_TOP, placer.getLookVec().y < 0);
		}

		@Override
		public boolean isNormalCube(IBlockState state) {
			return false;
		}

		@Override
		public boolean causesSuffocation(IBlockState state) {
			return false;
		}

		@Override
		public boolean isOpaqueCube(IBlockState state) {
			return false;
		}

		@Override
		public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
			return state.getValue(IS_TOP) ? BOTTOM_HALF : TOP_HALF;
		}

		@Override
		public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos) {
			return blockState.getValue(IS_TOP) ? BOTTOM_HALF : TOP_HALF;
		}
		
	}
	
	public static class ItemBlockToyota extends ItemBlock{

		public ItemBlockToyota(Block block) {
			super(block);
		}

		@Override
		public String getItemStackDisplayName(ItemStack stack) {
			return new TextComponentTranslation(TBlocks.toyota_hexagon_1.getTranslationKey() + ".name").getFormattedText();
		}
		
	}

	@Override
	public ItemBlock getItem() {
		return item;
	}

}
