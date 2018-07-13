package net.tardis.mod.util;

import net.minecraft.entity.Entity;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.util.ITeleporter;

public class TardisTeleporter implements ITeleporter {

	public TardisTeleporter() {
		
	}
	
	public TardisTeleporter(WorldServer es) {}
	
	@Override
	public void placeEntity(World world, Entity entity, float yaw) {
		if(!world.isRemote) {
			((WorldServer)world).addScheduledTask(() -> {
                entity.setEntityInvulnerable(false);
                entity.extinguish();
            });
		}
	}
}
