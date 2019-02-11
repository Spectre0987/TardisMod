package net.tardis.mod.common.blocks;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.tardis.mod.common.tileentity.TileEntityTardisCoral;

import javax.annotation.Nullable;

public class BlockTardisCoral extends BlockTileBase {

	public ItemBlock item = new ItemBlock(this);
	public static final PropertyInteger GROW_STAGE = PropertyInteger.create("grow", 0, 3);

	protected static final AxisAlignedBB CORAL_AABB = new AxisAlignedBB(0.25D, 0D, 0.25D, 0.75D, 0.75D, 0.75D);
	
	public BlockTardisCoral() {
		super(Material.SPONGE, TileEntityTardisCoral::new);
		this.setDefaultState(this.getDefaultState().withProperty(GROW_STAGE, 0));
		this.setSoundType(SoundType.PLANT);

	}

	@Override
	public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
		if (!worldIn.isRemote && placer instanceof EntityPlayer) {
			TileEntityTardisCoral coral = (TileEntityTardisCoral) worldIn.getTileEntity(pos);
			coral.setOwner(((EntityPlayer) placer).getGameProfile().getId());
		}
		super.onBlockPlacedBy(worldIn, pos, state, placer, stack);
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
	public boolean isFullCube(IBlockState state)
	{
		return false;
	}


	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, GROW_STAGE);
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		return this.getDefaultState().withProperty(GROW_STAGE, meta);
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		return state.getValue(GROW_STAGE);
	}

	@Override
	public BlockRenderLayer getRenderLayer() {
		return BlockRenderLayer.CUTOUT_MIPPED;
	}

	@Nullable
	public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos)
	{
		return NULL_AABB;
	}
	
	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
	{
		return CORAL_AABB;
	}




}
