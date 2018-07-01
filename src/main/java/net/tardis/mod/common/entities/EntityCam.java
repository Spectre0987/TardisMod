package net.tardis.mod.common.entities;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
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
		if(!world.isRemote && this.ticksExisted % 5 == 0) {
			shell = new WorldShell(new BlockPos(264, 7, 264));
			WorldServer ws = ((WorldServer)world).getMinecraftServer().getWorld(TDimensions.id);
			for (BlockPos pos : BlockPos.getAllInBox(shell.getOffset().subtract(new Vec3i(radius,radius,radius)), shell.getOffset().add(new Vec3i(radius,radius,radius)))) {
				shell.blockMap.put(pos, new BlockStorage(ws.getBlockState(pos), ws.getTileEntity(pos) != null ? ws.getTileEntity(pos) : null, ws.getLight(pos)));
			}
			for(EntityPlayerMP mp : world.getEntitiesWithinAABB(EntityPlayerMP.class, this.getEntityBoundingBox().grow(16D))) {
				Tardis.NETWORK.sendTo(new MessageSyncWorldShell(shell, this.getEntityId()), mp);
			}
		}
	}
	
}