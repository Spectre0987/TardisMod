package net.tardis.mod.common.tileentity;


import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
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
import net.tardis.mod.util.helpers.Helper;

public class TileEntityHoloprojector extends TileEntity implements ITickable, IContainsWorldShell{
	
	public WorldShell worldShell = new WorldShell(BlockPos.ORIGIN);
	
	public TileEntityHoloprojector() {}

	@Override
	public void update() {
		if(!world.isRemote /*&& world.getTotalWorldTime() % 5 == 0*/) {
			Chunk c = world.getChunkFromBlockCoords(getPos());
			for(TileEntity te : c.getTileEntityMap().values()) {
				if(te instanceof TileEntityTardis) {
					TileEntityTardis tardis = (TileEntityTardis)te;
					worldShell = new WorldShell(tardis.getLocation());
					Vec3i vec = new Vec3i(10, 5, 10);
					WorldServer ws = DimensionManager.getWorld(tardis.dimension);
					if(ws != null) {
						for(BlockPos pos : BlockPos.getAllInBox(worldShell.getOffset().subtract(vec), worldShell.getOffset().add(vec))) {
							IBlockState state = ws.getBlockState(pos);
							if(state.getMaterial() != Material.AIR) {
								worldShell.blockMap.put(pos, new BlockStorage(state, ws.getTileEntity(pos), ws.getLight(pos)));
							}
						}
						List<NBTTagCompound> lists = new ArrayList<>();
						for(Entity e : ws.getEntitiesWithinAABB(Entity.class, Helper.createBB(tardis.getLocation(), 16))) {
							if(EntityList.getKey(e) != null) {
								NBTTagCompound tag = new NBTTagCompound();
								e.writeToNBT(tag);
								tag.setString("id", EntityList.getKey(e).toString());
								lists.add(tag);
							}
						}
						List<PlayerStorage> players = new ArrayList<PlayerStorage>();
						for(EntityPlayer player : ws.getEntitiesWithinAABB(EntityPlayerMP.class, Helper.createBB(tardis.getLocation(), 16))) {
							players.add(new PlayerStorage(player));
						}
						worldShell.setPlayers(players);
						worldShell.setEntities(lists);
						Tardis.NETWORK.sendToAllAround(new MessageSyncWorldShell(worldShell, this.getPos()), new TargetPoint(world.provider.getDimension(), this.getPos().getX(), this.getPos().getY(), this.getPos().getZ(), 16D));
					}
					return;
				}
			}
		}
	}

	@Override
	public WorldShell getWorldShell() {
		return this.worldShell;
	}

	@Override
	public void setWorldShell(WorldShell worldShell) {
		this.worldShell = worldShell;
	}

}
