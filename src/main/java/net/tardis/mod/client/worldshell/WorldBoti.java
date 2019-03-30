package net.tardis.mod.client.worldshell;

import java.util.Objects;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.WorldSettings;
import net.minecraftforge.common.DimensionManager;

public class WorldBoti extends WorldClient {

	public int dimension;
	public WorldProvider provider;
	private WorldShell shell;

	public WorldBoti(int dimension, WorldClient wc, WorldShell ws) {
		super(Objects.requireNonNull(Minecraft.getMinecraft().getConnection()), new WorldSettings(wc.getWorldInfo()), dimension, wc.getDifficulty(), wc.profiler);
		shell = ws;
		this.dimension = dimension;
		this.provider = DimensionManager.createProviderFor(dimension);
	}

	@Override
	public Entity getEntityByID(int id) {
		return super.getEntityByID(id);
	}

	@Override
	public int getLight(BlockPos pos) {
		BlockStorage stor = this.shell.blockMap.get(pos);
		if(stor != null)
			return stor.light;
		return 15;
	}

	@Override
	public int getLightFromNeighbors(BlockPos pos) {
		return this.getLight(pos);
	}

	@Override
	public int getLight(BlockPos pos, boolean checkNeighbors) {
		return this.getLight(pos);
	}

	@Override
	public int getLightFor(EnumSkyBlock type, BlockPos pos) {
		return this.getLight(pos);
	}

	@Override
	public IBlockState getBlockState(BlockPos pos) {
		return shell.getBlockState(pos);
	}

	@Override
	public TileEntity getTileEntity(BlockPos pos) {
		return shell.getTileEntity(pos);
	}

	@Override
	public int getCombinedLight(BlockPos pos, int lightValue) {
		return 15;
	}

	public void setShell(WorldShell worldShell) {
		this.shell = worldShell;
	}

}
