package net.tardis.mod.screwdriver;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.tardis.api.screwdriver.IScrewable;
import net.tardis.mod.blocks.TBlocks;

public class RoundelMode implements IScrewable {
	
	@Override
	public String getName() {
		return "screw.lamp";
	}
	
	@Override
	public void screw(World world, BlockPos pos, IBlockState state) {
		if (!world.isRemote) {
			if (state.getBlock() == TBlocks.panel)
				world.setBlockState(pos, TBlocks.light.getDefaultState());
			else if (state.getBlock() == TBlocks.light) world.setBlockState(pos, TBlocks.panel.getDefaultState());
		}
	}
	
}
