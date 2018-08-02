package net.tardis.mod.common.systems;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.tardis.mod.common.items.TItems;
import net.tardis.mod.common.systems.TardisSystems.ISystem;
import net.tardis.mod.util.helpers.Helper;

public class SystemFluidLinks implements ISystem{

	float health = 1F;
	
	@Override
	public float getHealth() {
		return health;
	}

	@Override
	public void setHealth(float health) {
		this.health = health;
	}

	@Override
	public void onUpdate(World world, BlockPos consolePos) {
		if(health <= 0.00F) {
			if(!world.isRemote && world.getWorldTime() % 20 == 0) {
				for(EntityLivingBase base : world.getEntitiesWithinAABB(EntityLivingBase.class, Helper.createBB(consolePos, 60))) {
					base.attackEntityFrom(DamageSource.GENERIC, 2F);
				}
			}
		}
	}

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
		health -= 0.25F;
	}

	@Override
	public Item getRepairItem() {
		return TItems.fluid_link;
	}

	@Override
	public boolean repair() {
		return false;
	}

	@Override
	public String getNameKey() {
		return "system.tardis.fluidlinks";
	}

	@Override
	public void wear() {
		health -= 0.05F;
	}

}
