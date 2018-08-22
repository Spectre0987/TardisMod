package net.tardis.mod.client.util;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.Biome;

public class RenderAccess implements IBlockAccess{

    IBlockState state;
	
	public RenderAccess(IBlockState state){
		this.state = state;
	}
	
	@Override
	public TileEntity getTileEntity(BlockPos pos) {
		return null;
	}

	@Override
	public int getCombinedLight(BlockPos pos, int c) {
		return 15;
	}

	@Override
	public IBlockState getBlockState(BlockPos pos) {
		return pos.equals(BlockPos.ORIGIN) ? state : Blocks.AIR.getDefaultState();
	}

	@Override
	public boolean isAirBlock(BlockPos pos) {
		return state.getMaterial() == Material.AIR;
	}

	@Override
	public Biome getBiome(BlockPos pos) {
		return Biomes.VOID;
	}

	@Override
	public int getStrongPower(BlockPos pos, EnumFacing direction) {
		return 0;
	}

	@Override
	public WorldType getWorldType() {
		return WorldType.DEFAULT;
	}

	@Override
	public boolean isSideSolid(BlockPos pos, EnumFacing side, boolean _default) {
		return state.isSideSolid(this, pos, side);
	}

}
