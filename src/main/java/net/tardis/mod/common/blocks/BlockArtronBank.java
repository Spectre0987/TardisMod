package net.tardis.mod.common.blocks;

import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.tardis.mod.common.tileentity.TileEntityTardis;
import net.tardis.mod.util.common.helpers.TardisHelper;

public class BlockArtronBank extends BlockBase {

	public static final int MAX_ARTRON = 100;
	
	public BlockArtronBank() {}

	

	@Override
	public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state) {
		super.onBlockAdded(worldIn, pos, state);
		if(!worldIn.isRemote) {
			((WorldServer)worldIn).addScheduledTask(() -> {
				TileEntity te = worldIn.getTileEntity(TardisHelper.getTardisForPosition(pos));
				if(te instanceof TileEntityTardis) {
					((TileEntityTardis)te).addArtronBank();
				}
			});
		}
	}



	@Override
	public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
		super.breakBlock(worldIn, pos, state);
		if(!worldIn.isRemote) {
			((WorldServer)worldIn).addScheduledTask(() -> {
				TileEntity te = worldIn.getTileEntity(TardisHelper.getTardisForPosition(pos));
				if(te instanceof TileEntityTardis) {
					((TileEntityTardis)te).removeArtronBank();
				}
			});
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
