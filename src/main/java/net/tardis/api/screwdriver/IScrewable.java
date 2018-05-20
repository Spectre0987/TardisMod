package net.tardis.api.screwdriver;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public interface IScrewable extends IScrew {
	
	void screw(World world, BlockPos pos, IBlockState state, EntityPlayer player);
}
