package net.tardis.mod.common.systems;

import net.minecraft.item.Item;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.tardis.mod.common.items.TItems;
import net.tardis.mod.common.systems.TardisSystems.ISystem;

public class SystemCCircuit extends ISystem{

	@Override
	public void onUpdate(World world, BlockPos consolePos) {}

	@Override
	public void damage() {}

	@Override
	public Item getRepairItem() {
		return TItems.chameleon_circuit;
	}

	@Override
	public String getNameKey() {
		return "system.tardis.ccircuit";
	}

	@Override
	public void wear() {
		this.setHealth(this.getHealth() - 0.005F);
	}

	@Override
	public boolean shouldStopFlight() {
		return false;
	}

}
