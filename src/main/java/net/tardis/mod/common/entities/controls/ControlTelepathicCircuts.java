package net.tardis.mod.common.entities.controls;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.tardis.mod.Tardis;
import net.tardis.mod.common.tileentity.TileEntityTardis;
import net.tardis.mod.packets.MessageGetPlayers;
import net.tardis.mod.util.helpers.Helper;

public class ControlTelepathicCircuts extends EntityControl{
	
	public ControlTelepathicCircuts(TileEntityTardis tardis) {
		super(tardis);
	}
	
	public ControlTelepathicCircuts(World world) {
		super(world);
	}

	@Override
	public Vec3d getOffset() {
		return Helper.convertToPixels(0,16,0);
	}

	@Override
	public void preformAction(EntityPlayer player) {
		if(!world.isRemote) {
			Tardis.NETWORK.sendTo(new MessageGetPlayers(((WorldServer)world).getMinecraftServer().getPlayerList().getPlayers()), (EntityPlayerMP)player);
		}
	}

}
