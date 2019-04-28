package net.tardis.mod.common.blocks;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.tardis.mod.common.blocks.interfaces.IARSBlock;

public class BlockLight extends BlockBase {
	
	public BlockLight() {
	
	}
	
	@Override
	public int getLightValue(IBlockState state, IBlockAccess world, BlockPos pos) {
		return 15;
	}
}
