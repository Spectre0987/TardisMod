package net.tardis.mod.common.screwdriver;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.tardis.api.screwdriver.IScrewable;
import net.tardis.mod.common.blocks.TBlocks;

public class RoundelMode implements IScrewable {
	
	@Override
	public String getName() {
		return "screw.lamp";
	}
	
	@Override
	public void screw(World world, BlockPos pos, IBlockState state,EntityPlayer player) {
		if (!world.isRemote) {
			if (state.getBlock() == TBlocks.panel)
				world.setBlockState(pos, TBlocks.light.getDefaultState());
			else if (state.getBlock() == TBlocks.light) world.setBlockState(pos, TBlocks.panel.getDefaultState());
		}
	}
	
}
