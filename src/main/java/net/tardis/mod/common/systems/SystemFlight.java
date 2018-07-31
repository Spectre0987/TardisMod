package net.tardis.mod.common.systems;

import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.tardis.mod.common.items.TItems;
import net.tardis.mod.common.systems.TardisSystems.ISystem;

public class SystemFlight implements ISystem{

	private float health = 1F;
	
	public SystemFlight() {}
	
	@Override
	public float getHealth() {
		return this.health;
	}

	@Override
	public void setHealth(float health) {
		this.health = health;
	}

	@Override
	public void onUpdate(World world, BlockPos consolePos) {}

	@Override
	public void readFromNBT(NBTTagCompound tag) {
		this.health = tag.getFloat("health");
	}

	@Override
	public NBTTagCompound writetoNBT(NBTTagCompound tag) {
		tag.setFloat("health", health);
		return tag;
	}

	@Override
	public void damage() {
		this.health -= 0.15F;
	}

	@Override
	public Item getRepairItem() {
		return TItems.demat_circut;
	}

	@Override
	public void repair() {
		this.health += 0.5F;
	}

}
