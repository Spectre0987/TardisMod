package net.tardis.mod.common.protocols;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.tardis.mod.Tardis;
import net.tardis.mod.common.tileentity.TileEntityDoor;
import net.tardis.mod.common.tileentity.TileEntityTardis;
import net.tardis.mod.util.common.helpers.PlayerHelper;

public class ProtocolForcefield implements ITardisProtocol {

	@Override
	public void onActivated(World world, TileEntityTardis tardis) {
		if(!world.isRemote) {
			WorldServer ser = world.getMinecraftServer().getWorld(tardis.dimension);
			
			if(ser != null) {
				TileEntity te = ser.getTileEntity(tardis.getLocation().up());
				if(te instanceof TileEntityDoor) {
					((TileEntityDoor)te).setForcefield(!((TileEntityDoor)te).getForcefield());
					boolean activated = ((TileEntityDoor)te).getForcefield();
					tardis.setForceFieldEnabled(activated);
					for(EntityPlayer player : world.getEntitiesWithinAABB(EntityPlayer.class, Block.FULL_BLOCK_AABB.expand(16, 16, 16).offset(tardis.getPos()))) {
						PlayerHelper.sendMessage(player, activated ? "Forcefield Activated!" : "Forcefield Deactivated!", true);
					}
				}
			}
		}
	}

	@Override
	public String getNameKey() {
		return "protocol." + Tardis.MODID + ".forcefield";
	}

}
