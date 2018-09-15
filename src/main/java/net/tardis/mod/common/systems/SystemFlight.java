package net.tardis.mod.common.systems;

import net.minecraft.item.Item;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.tardis.mod.common.items.TItems;
import net.tardis.mod.common.systems.TardisSystems.ISystem;

public class SystemFlight extends ISystem{
	
	public SystemFlight() {}
	
	@Override
	public void onUpdate(World world, BlockPos consolePos) {}

	@Override
	public void damage() {
		this.setHealth(this.getHealth() - 0.15F);
	}

	@Override
	public Item getRepairItem() {
		return TItems.demat_circut;
	}

	@Override
	public String getNameKey() {
		return "systems.tardis.flight";
	}

	@Override
	public void wear() {
		this.setHealth(this.getHealth() - 0.01F);
	}

}
