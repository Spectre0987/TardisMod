package net.tardis.mod.common.systems;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.tardis.mod.common.items.TItems;
import net.tardis.mod.common.systems.TardisSystems.ISystem;

public class SystemFlight extends ISystem{

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
	public boolean repair(ItemStack stack) {
		if(health < 1.0F) {
			this.health = (100 - stack.getItemDamage()) / 100F;
			return true;
		}
		return false;
	}

	@Override
	public String getNameKey() {
		return "systems.tardis.flight";
	}

	@Override
	public void wear() {
		health -= 0.01F;
	}

}
