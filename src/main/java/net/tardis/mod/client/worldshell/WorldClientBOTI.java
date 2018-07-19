package net.tardis.mod.client.worldshell;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.init.Blocks;
import net.minecraft.profiler.Profiler;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldSettings;

/***
 * 
 * @author Spectre
 *
 *	Should fix sign orientation
 */
public class WorldClientBOTI extends WorldClient{
	
	public WorldShell shell;

	public WorldClientBOTI(WorldClient wc, WorldShell ws) {
		super(Minecraft.getMinecraft().getConnection(), new WorldSettings(wc.getWorldInfo()), wc.provider.getDimension(), wc.getDifficulty(), new Profiler());
		shell = ws;
	}

	@Override
	public IBlockState getBlockState(BlockPos pos) {
		shell.getBlockState(pos);
		return Blocks.AIR.getDefaultState();
	}

	@Override
	public TileEntity getTileEntity(BlockPos pos) {
		return shell.getTileEntity(pos);
	}

}
