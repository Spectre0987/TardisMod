package net.tardis.mod.common.entities;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.tardis.mod.Tardis;
import net.tardis.mod.client.worldshell.BlockStorage;
import net.tardis.mod.client.worldshell.IContainsWorldShell;
import net.tardis.mod.client.worldshell.MessageSyncWorldShell;
import net.tardis.mod.client.worldshell.WorldShell;
import net.tardis.mod.common.dimensions.TDimensions;

public class EntityCam extends Entity implements IContainsWorldShell{

	public WorldShell shell;
	public static int radius = 10;
	
	public EntityCam(World worldIn) {
		super(worldIn);
		this.setWorldShell(new WorldShell(BlockPos.ORIGIN));
	}

	@Override
	public WorldShell getWorldShell() {
		return shell;
	}

	@Override
	public void setWorldShell(WorldShell ws) {
		shell = ws;
	}

	@Override
	protected void entityInit() {}

	@Override
	protected void readEntityFromNBT(NBTTagCompound compound) {}

	@Override
	protected void writeEntityToNBT(NBTTagCompound compound) {}

	@Override
	public void onUpdate() {
		super.onUpdate();
		if(!world.isRemote ) {
			shell = new WorldShell(new BlockPos(264, 6, 264));
			WorldServer ws = ((WorldServer)world).getMinecraftServer().getWorld(TDimensions.id);
			for (BlockPos pos : BlockPos.getAllInBox(shell.getOffset().subtract(new Vec3i(radius,radius,radius)), shell.getOffset().add(new Vec3i(radius,radius,radius)))) {
				IBlockState state = ws.getBlockState(pos);
				if(state.getBlock() != Blocks.AIR) {
					shell.blockMap.put(pos, new BlockStorage(state, ws.getTileEntity(pos), ws.getLight(pos)));
				}
			}
			for(EntityPlayerMP mp : world.getEntitiesWithinAABB(EntityPlayerMP.class, this.getEntityBoundingBox().grow(16D))) {
				Tardis.NETWORK.sendTo(new MessageSyncWorldShell(shell, this.getEntityId()), mp);
			}
		}
	}
	
}