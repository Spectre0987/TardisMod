package net.tardis.mod.common.blocks;


import net.minecraft.block.BlockAir;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.tardis.mod.common.tileentity.TileEntitySonicGun;

import javax.annotation.Nullable;

public class BlockSonicBlaster extends BlockAir {

	public BlockSonicBlaster() {
		super();
	}

	@Override
	public boolean hasTileEntity(IBlockState state) {
		return true;
	}

	@Nullable
	@Override
	public TileEntity createTileEntity(World world, IBlockState state) {
		return new TileEntitySonicGun();
	}
}
