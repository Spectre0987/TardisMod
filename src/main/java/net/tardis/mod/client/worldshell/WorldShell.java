package net.tardis.mod.client.worldshell;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.Biome;

public class WorldShell implements IBlockAccess {

	// Keeps stores a collection of BlockAcess's mapped to blockpos coords
	public Map<BlockPos, BlockStorage> blockMap;
	// Contains every TESR to speed up rendering
	private List<TileEntity> tesrs;
	//Entities to render
	private List<NBTTagCompound> entities;
	// The distance between the root of the worldShell and (0,0,0). Subtracting this
	// from the coords in BlockMap should give you positions in relative terms for
	// rendering
	private BlockPos offset;

	public BufferBuilder.State bufferstate;
	public boolean updateRequired = false;

	public WorldShell(BlockPos bp) {
		blockMap = new HashMap<BlockPos, BlockStorage>();
		offset = bp;
		tesrs = new ArrayList<TileEntity>();
	}

	public BlockPos getOffset() {
		return offset;
	}

	@Override
	public TileEntity getTileEntity(BlockPos pos) {
		return blockMap.get(pos).tileentity;
	}

	@Override
	public int getCombinedLight(BlockPos pos, int lightValue) {
		return 15;
	}

	public int getLightSet(EnumSkyBlock type, BlockPos pos) {
		return 15;
	}

	@Override
	public IBlockState getBlockState(BlockPos pos) {
		if (blockMap.get(pos) != null) {
			return blockMap.get(pos).blockstate;
		} else {
			return Blocks.AIR.getDefaultState();
		}
	}

	@Override
	public boolean isAirBlock(BlockPos pos) {
		return blockMap.get(pos).blockstate.getMaterial() == Material.AIR;
	}

	@Override
	public Biome getBiome(BlockPos pos) {
		return Biome.getBiome(0);
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
		if (!blockMap.containsKey(pos))
			return _default;
		return blockMap.get(pos).blockstate.isSideSolid(this, pos, side);
	}

	public List<TileEntity> getTESRs() {
		return tesrs;
	}

	public void setTESRs() {
		for (BlockStorage bs : blockMap.values()) {
			if (bs.tileentity != null) {
				TileEntitySpecialRenderer renderer = TileEntityRendererDispatcher.instance.renderers
						.get(bs.tileentity.getClass());
				if (renderer != null)
					tesrs.add(bs.tileentity);
			}
		}
	}
	
	public List<NBTTagCompound> getEntities(){
		return this.entities;
	}
	
	public void setEntities(List<NBTTagCompound> entity) {
		this.entities = entity;
	}
}
