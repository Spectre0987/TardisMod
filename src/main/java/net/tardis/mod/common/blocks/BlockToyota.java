package net.tardis.mod.common.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import net.tardis.mod.Tardis;

public class BlockToyota extends BlockBase {
	
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
			return this.getDefaultState().withProperty(FACING, EnumFacing.getHorizontal(meta));
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
	
	public static class ItemBlockToyota extends ItemBlock{

		public ItemBlockToyota(Block block) {
			super(block);
		}

		@Override
		public String getItemStackDisplayName(ItemStack stack) {
			return new TextComponentTranslation(TBlocks.toyota_hexagon_1.getUnlocalizedName() + ".name").getFormattedText();
		}
		
	}

}
