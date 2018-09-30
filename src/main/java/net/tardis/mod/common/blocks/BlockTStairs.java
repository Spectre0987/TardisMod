package net.tardis.mod.common.blocks;

import net.minecraft.block.BlockStairs;
import net.minecraft.block.state.IBlockState;
import net.tardis.mod.client.creativetabs.TardisTabs;

public class BlockTStairs extends BlockStairs {

	public BlockTStairs(IBlockState modelState) {
		super(modelState);
        setCreativeTab(TardisTabs.MAIN);
	}

}
