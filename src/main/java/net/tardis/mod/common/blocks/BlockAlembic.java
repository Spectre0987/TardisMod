package net.tardis.mod.common.blocks;

import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.tardis.mod.common.tileentity.TileEntityAlembic;
import net.tardis.mod.common.tileentity.TileEntityAlembic.AlembicRecipe;

public class BlockAlembic extends BlockTileBase {

	public static final PropertyDirection FACING = PropertyDirection.create("facing", EnumFacing.Plane.HORIZONTAL);
	public ItemBlock item = new ItemBlock(this);

	public BlockAlembic() {
		super(Material.GLASS, TileEntityAlembic::new);

	}

	@Override
	public EnumBlockRenderType getRenderType(IBlockState state) {
		return EnumBlockRenderType.INVISIBLE;
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
	public BlockRenderLayer getRenderLayer() {
		return BlockRenderLayer.TRANSLUCENT;
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
	public IBlockState getStateForPlacement(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) {
		return this.getDefaultState().withProperty(FACING, placer.getHorizontalFacing().getOpposite());
	}

	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, FACING);
	}

	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		if (!worldIn.isRemote) {
			ItemStack held = playerIn.getHeldItem(hand);
			TileEntityAlembic te = (TileEntityAlembic) worldIn.getTileEntity(pos);
			if (!held.isEmpty() && AlembicRecipe.getItemResult(held.getItem()) != null) {
				if (te.getStack().isEmpty()) {
					te.setStack(held.copy());
					playerIn.setHeldItem(hand, ItemStack.EMPTY);
				} else if (te.getStack().isItemEqual(held) && te.getStack().getCount() + held.getCount() <= te.getStack().getMaxStackSize()) {
					te.getStack().setCount(te.getStack().getCount() + held.getCount());
				}
			} else {
				if (!te.getResult().isEmpty()) {
					playerIn.inventory.addItemStackToInventory(te.getResult());
					te.setResult(ItemStack.EMPTY);
				} else if (!te.getStack().isEmpty() && playerIn.isSneaking()) {
					playerIn.inventory.addItemStackToInventory(te.getStack());
					te.setStack(ItemStack.EMPTY);
				}
			}
		}
		return true;
	}
}
