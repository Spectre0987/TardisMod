package net.tardis.mod.common.screwdriver;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.tardis.mod.common.blocks.TBlocks;

public class ElectricPanelMode implements IScrewable {

	@Override
	public String getName() {
		return "screw.electric";
	}

	@Override
	public void screw(World world, BlockPos pos, IBlockState state, EntityPlayer player) {
		if(!world.isRemote) {
			Block block = world.getBlockState(pos).getBlock();
			if(block == TBlocks.panel || block == TBlocks.megalos) {
				world.setBlockState(pos, TBlocks.electric_panel.getDefaultState());
			}
			else if(world.getBlockState(pos).getBlock() == TBlocks.electric_panel) {
				world.setBlockState(pos, TBlocks.panel.getDefaultState());
			}
		}
	}

}
