package net.tardis.mod.common.blocks;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.tardis.mod.Tardis;
import net.tardis.mod.common.tileentity.TileEntityLight;

public class BlockLight extends BlockTileBase {

	public BlockLight() {
		super(Material.IRON, TileEntityLight::new);
		this.setCreativeTab(Tardis.tab);
	}

	@Override
	public int getLightValue(IBlockState state, IBlockAccess world, BlockPos pos) {
		return 15;
	}

}
