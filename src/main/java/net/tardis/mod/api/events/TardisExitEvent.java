package net.tardis.mod.api.events;

import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.eventhandler.Event;

public class TardisExitEvent extends Event{

	/**Position of the Console*/
	BlockPos pos;
	Entity entity;
	
	public TardisExitEvent(Entity e, BlockPos p) {
		pos = p;
		entity = e;
	}
	
	public Entity getEntity() {
		return entity;
	}
	
	public BlockPos getPos() {
		return pos;
	}
	
	@Override
	public boolean isCancelable() {
		return false;
	}

}
