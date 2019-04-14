package net.tardis.mod.client.colorhandlers;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.color.IBlockColor;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.tardis.mod.common.dimensions.TDimensions;

import java.awt.*;

public class BlockColorTelos implements IBlockColor {

	int color = new Color(254 / 255F, 165 / 255F, 95F / 255F).getRGB();
	int normal = new Color(1F, 1F, 1F).getRGB();

	@Override
	public int colorMultiplier(IBlockState state, IBlockAccess worldIn, BlockPos pos, int tintIndex) {
		return pos == null || worldIn == null ? normal : worldIn.getBiome(pos) == TDimensions.BIOME_TELOS_ORANGE ? color : normal;
	}

}
