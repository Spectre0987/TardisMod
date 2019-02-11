package net.tardis.mod.common.dimensions.moon;

import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class GenCrater {


	public void genCrater(World world, BlockPos pos, int radius) {
		for (int x = pos.getX() - radius; x < pos.getX() + radius; ++x) {
			for (int y = pos.getY() - radius; y < pos.getY() + radius; ++y) {
				for (int z = pos.getZ() - radius; z < pos.getZ() + radius; ++z) {
					double squareDistance = Math.pow(x - pos.getX(), 2) + Math.pow(y - pos.getY(), 2) + Math.pow(z - pos.getZ(), 2);
					if (squareDistance <= Math.pow(radius, 2)) {
						world.setBlockState(new BlockPos(x, y, z), Blocks.AIR.getDefaultState());
					}
				}
			}
		}
	}

}
