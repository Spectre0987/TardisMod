package net.tardis.mod.common.systems;

import net.minecraft.item.Item;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.tardis.mod.common.items.TItems;
import net.tardis.mod.common.systems.TardisSystems.ISystem;

public class SystemTemporalGrace extends ISystem{

	public SystemTemporalGrace() {}
	
	@Override
	public void onUpdate(World world, BlockPos consolePos) {}

	@Override
	public void damage() {}

	@Override
	public Item getRepairItem() {
		return TItems.temporal_grace_circuits;
	}

	@Override
	public String getNameKey() {
		return "system.tardis.temporal_grace";
	}

	@Override
	public void wear() {}

	@Override
	public boolean shouldStopFlight() {
		return false;
	}

}
