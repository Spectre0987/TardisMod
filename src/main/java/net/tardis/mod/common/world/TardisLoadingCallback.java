package net.tardis.mod.common.world;

import net.minecraft.world.World;
import net.minecraftforge.common.ForgeChunkManager;
import net.minecraftforge.common.ForgeChunkManager.LoadingCallback;
import net.minecraftforge.common.ForgeChunkManager.Ticket;

import java.util.List;

public class TardisLoadingCallback implements LoadingCallback {

	@Override
	public void ticketsLoaded(List<Ticket> tickets, World world) {
		for (Ticket t : tickets) {
			ForgeChunkManager.releaseTicket(t);
		}
	}

}
