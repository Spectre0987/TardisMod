package net.tardis.mod.common.entities;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.tardis.mod.common.dimensions.TDimensions;
import net.tardis.mod.util.TardisTeleporter;

public class EntityCam extends Entity {
	
	public BlockPos consolePos = BlockPos.ORIGIN;
	int ticks = 0;
	
	public EntityCam(World worldIn) {
		super(worldIn);
		this.setSize(0.1F, 0.1F);
	}
	
	public EntityCam(World worldIn, BlockPos pos) {
		this(worldIn);
		this.setPositionAndUpdate(pos.getX() + 0.5, pos.getY() + 2, pos.getZ() + 0.5);
	}
	
	public EntityCam(World worldIn, BlockPos pos, Entity e) {
		this(worldIn, pos);
		e.startRiding(this);
	}
	
	@Override
	protected void entityInit() {}
	
	@Override
	protected void readEntityFromNBT(NBTTagCompound tag) {
		consolePos = BlockPos.fromLong(tag.getLong("tPos"));
	}
	
	@Override
	protected void writeEntityToNBT(NBTTagCompound tag) {
		tag.setLong("tPos", consolePos.toLong());
	}
	
	@Override
	public void onEntityUpdate() {
		super.onEntityUpdate();
		/*
		 * if(!world.isRemote) { if(!this.isBeingRidden()) { ++ticks; if(ticks>20)this.setDead(); } }
		 */
	}
	
	@Override
	protected void removePassenger(Entity pas) {
		if (!world.isRemote && pas instanceof EntityPlayer) {
			BlockPos nConsolePos = consolePos.east(3);
			pas.setPosition(nConsolePos.getX(), nConsolePos.getY(), nConsolePos.getZ());
			pas.setInvisible(false);
			world.getMinecraftServer().getPlayerList().transferPlayerToDimension((EntityPlayerMP) pas, TDimensions.id, new TardisTeleporter((WorldServer) world));
		}
		super.removePassenger(pas);
	}
	
	public void setConsolePos(BlockPos tardisPos) {
		this.consolePos = tardisPos;
		this.consolePos.toImmutable();
	}
	
}
