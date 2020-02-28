package net.tardis.mod.common.blocks;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.tardis.mod.common.dimensions.TDimensions;
import net.tardis.mod.common.tileentity.TileEntityArtronBank;
import net.tardis.mod.common.tileentity.TileEntityTardis;
import net.tardis.mod.util.common.helpers.TardisHelper;

public class BlockArtronBank extends BlockTileBase {

	public BlockArtronBank() {
		super(Material.CIRCUITS, TileEntityArtronBank::new);
	}

	@Override
	public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
		super.onBlockPlacedBy(worldIn, pos, state, placer, stack);
		if(worldIn.provider.getDimension() == TDimensions.TARDIS_ID) {
			TileEntity te = worldIn.getTileEntity(TardisHelper.getTardisForPosition(pos));
			if(te instanceof TileEntityTardis) {
				((TileEntityTardis)te).addArtronBank((TileEntityArtronBank)worldIn.getTileEntity(pos));
			}
		}
	}

	@Override
	public boolean isNormalCube(IBlockState state) {
		return false;
	}

	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}

}
