package net.tardis.mod.util;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Teleporter;
import net.minecraft.world.WorldServer;

public class TardisTeleporter extends Teleporter {
	
	public TardisTeleporter(WorldServer worldIn) {
		super(worldIn);
	}
	
	public TardisTeleporter(WorldServer worldIn,EntityPlayerMP player, BlockPos pos) {
		super(worldIn);
		player.setPosition(pos.getX()+0.5, pos.getY(), pos.getZ()+0.5);
	}
	
	@Override
	public void placeInPortal(Entity entityIn, float rotationYaw) {}
	
	@Override
	public boolean placeInExistingPortal(Entity entityIn, float rotationYaw) {
		return true;
	}
	
	@Override
	public boolean makePortal(Entity entityIn) {
		return true;
	}
	
	@Override
	public void removeStalePortalLocations(long worldTime) {}
	
}
