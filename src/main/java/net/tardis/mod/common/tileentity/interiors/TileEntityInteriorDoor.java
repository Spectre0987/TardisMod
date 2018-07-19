package net.tardis.mod.common.tileentity.interiors;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.WorldServer;
import net.minecraft.world.chunk.Chunk;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.fml.common.network.NetworkRegistry.TargetPoint;
import net.tardis.mod.Tardis;
import net.tardis.mod.client.worldshell.BlockStorage;
import net.tardis.mod.client.worldshell.IContainsWorldShell;
import net.tardis.mod.client.worldshell.MessageSyncWorldShell;
import net.tardis.mod.client.worldshell.PlayerStorage;
import net.tardis.mod.client.worldshell.WorldShell;
import net.tardis.mod.common.blocks.BlockTardisTop;
import net.tardis.mod.common.tileentity.TileEntityTardis;
import net.tardis.mod.util.helpers.Helper;

public class TileEntityInteriorDoor extends TileEntity implements ITickable, IContainsWorldShell{

	public WorldShell shell = new WorldShell(BlockPos.ORIGIN);
	private BlockPos consolePos = BlockPos.ORIGIN;
	private Vec3i radius = new Vec3i(10, 10, 10);
	
	public TileEntityInteriorDoor() {}
	
	@Override
	public void readFromNBT(NBTTagCompound compound) {
		super.readFromNBT(compound);
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		return super.writeToNBT(compound);
	}

	@Override
	public WorldShell getWorldShell() {
		return shell;
	}

	@Override
	public void setWorldShell(WorldShell worldShell) {
		this.shell = worldShell;
	}

	@Override
	public void update() {
		if(!world.isRemote) {
			BlockPos console = this.getConsolePos();
			TileEntityTardis tardis = (TileEntityTardis)world.getTileEntity(console);
			WorldServer tardisWorld = DimensionManager.getWorld(tardis.dimension);
			EnumFacing facing = EnumFacing.NORTH;
			IBlockState tardisState = tardisWorld.getBlockState(tardis.getLocation().up());
			if(tardisState.getBlock() instanceof BlockTardisTop) {
				facing = tardisState.getValue(BlockTardisTop.FACING);
			}
			shell = new WorldShell(tardis.getLocation().offset(facing, 10));
			for(BlockPos pos : BlockPos.getAllInBox(shell.getOffset().subtract(radius), shell.getOffset().add(radius))) {
				IBlockState state = tardisWorld.getBlockState(pos);
				if(state.getMaterial() != Material.AIR) {
					shell.blockMap.put(pos, new BlockStorage(state, tardisWorld.getTileEntity(pos), tardisWorld.getLight(pos)));
				}
			}
			List<NBTTagCompound> entities = new ArrayList<NBTTagCompound>();
			List<PlayerStorage> players = new ArrayList<PlayerStorage>();
			for(Entity entity : tardisWorld.getEntitiesWithinAABB(Entity.class, Helper.createBB(shell.getOffset(), 10))) {
				if(entity instanceof EntityPlayer) {
					players.add(new PlayerStorage((EntityPlayer)entity));
				}
				else {
					NBTTagCompound tag = new NBTTagCompound();
					entity.writeToNBT(tag);
					entities.add(tag);
				}
			}
			shell.setEntities(entities);
			shell.setPlayers(players);
			Tardis.NETWORK.sendToAllAround(new MessageSyncWorldShell(shell, this.getPos()), new TargetPoint(world.provider.getDimension(), this.getPos().getX(), this.getPos().getY(), this.getPos().getZ(), 20D));
		}
	}
	
	public BlockPos getConsolePos() {
		Chunk c = world.getChunkFromBlockCoords(this.getPos());
		BlockPos tardis = BlockPos.ORIGIN;
		for(TileEntity te : c.getTileEntityMap().values()) {
			if(te instanceof TileEntityTardis) {
				tardis = te.getPos();
			}
		}
		return tardis;
	}

}
