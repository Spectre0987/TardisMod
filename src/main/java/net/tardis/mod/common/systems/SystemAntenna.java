package net.tardis.mod.common.systems;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.tardis.mod.common.items.TItems;
import net.tardis.mod.common.systems.TardisSystems.BaseSystem;

public class SystemAntenna extends BaseSystem{

	@Override
	public void onUpdate(World world, BlockPos consolePos) {}

	@Override
	public void damage() {}

	@Override
	public Item getRepairItem() {
		return TItems.antenna;
	}

	@Override
	public boolean repair(ItemStack stack) {
		if(this.getHealth() < 1.0F) {
			this.setHealth(MathHelper.clamp(this.getHealth() + (stack.getMaxDamage() - stack.getItemDamage()) / 100F, 0.0F, 1.0F));
			return true;
		}
		return false;
	}

	@Override
	public String getNameKey() {
		return "systems.tardis.antenna";
	}
	
	@Override
	public String getUsage() {
		return "Without this system, your TARDIS will not recieve distress calls";
	}

	@Override
	public void wear() {}

	@Override
	public boolean shouldStopFlight() {
		return false;
	}

}
