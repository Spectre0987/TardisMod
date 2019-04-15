package net.tardis.mod.common.systems;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.tardis.mod.common.TDamage;
import net.tardis.mod.common.items.TItems;
import net.tardis.mod.common.systems.TardisSystems.BaseSystem;
import net.tardis.mod.util.common.helpers.Helper;

public class SystemFluidLinks extends BaseSystem {

	int ticksToHurt = 0;

	public SystemFluidLinks() {
		this.ticksToHurt = 0;
		this.setHealth(1F);
	}

	@Override
	public void onUpdate(World world, BlockPos consolePos) {
		if (this.getHealth() <= 0.00F && ticksToHurt > 0) {
			--ticksToHurt;
			if (!world.isRemote && world.getWorldTime() % 20 == 0) {
				for (EntityLivingBase base : world.getEntitiesWithinAABB(EntityLivingBase.class, Helper.createBB(consolePos, 60))) {
					base.attackEntityFrom(TDamage.SUFFICATION, 2F);
				}
			}
		}
	}

	@Override
	public void readFromNBT(NBTTagCompound tag) {
		super.readFromNBT(tag);
		this.ticksToHurt = tag.getInteger("time");
	}

	@Override
	public NBTTagCompound writetoNBT(NBTTagCompound tag) {
		super.writetoNBT(tag);
		tag.setInteger("time", ticksToHurt);
		return tag;
	}

	@Override
	public void damage() {
		this.setHealth(this.getHealth() - 0.25F);
		if (this.getHealth() <= 0.0F) {
			this.ticksToHurt = 600;
		}
	}

	@Override
	public Item getRepairItem() {
		return TItems.fluid_link;
	}

	@Override
	public String getNameKey() {
		return "system.tardis.fluidlinks";
	}

	@Override
	public String getUsage() {
		return "Without this system, you will not be able to fly the TARDIS";
	}

	@Override
	public void wear() {
		this.setHealth(this.getHealth() - 0.015F);
		if (this.getHealth() <= 0.0F) {
			this.ticksToHurt = 600;
		}
	}

}
