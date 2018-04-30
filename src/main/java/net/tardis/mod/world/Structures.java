package net.tardis.mod.world;

import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class Structures {
	
	public class Hallway implements IGeneratable{
		@Override
		public void gen(World world, BlockPos pos, EnumFacing facing) {
			BlockPos pos1=new BlockPos(0,2,0);
			
		}
	}

}
