package net.tardis.mod.common.systems;

import net.minecraft.item.Item;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.tardis.mod.common.blocks.TBlocks;
import net.tardis.mod.common.items.TItems;
import net.tardis.mod.common.systems.TardisSystems.BaseSystem;
import net.tardis.mod.common.tileentity.TileEntityTardis;
import net.tardis.mod.util.common.helpers.Helper;

public class SystemCCircuit extends BaseSystem {

	private boolean checkActive = false;
	private int timeStealth = 0;
	
	@Override
	public void onUpdate(World world, BlockPos consolePos) {
		TileEntityTardis tardis = Helper.getTardis(world.getTileEntity(consolePos));
		if (checkActive) {
			if (tardis != null && tardis.getTopBlock().getBlock() == TBlocks.tardis_top_cc) {
				this.setHealth(this.getHealth() - 0.01F);
			}
			checkActive = false;
		}
		if(tardis.isStealthMode()) {
			++this.timeStealth;
			if(this.getHealth() <= 0.0)
				tardis.setStealthMode(false);
		}
		else this.timeStealth = 0;
		if(this.timeStealth >= 100) {
			this.timeStealth = 0;
			this.setHealth(this.getHealth() - 0.01F);
		}
	}

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
	public String getUsage() {
		return "Without this system, you will not be able to change your TARDIS exterior";
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
