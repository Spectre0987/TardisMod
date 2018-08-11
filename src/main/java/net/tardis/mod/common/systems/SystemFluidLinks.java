package net.tardis.mod.common.systems;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.tardis.mod.Tardis;
import net.tardis.mod.common.items.TItems;
import net.tardis.mod.common.systems.TardisSystems.ISystem;
import net.tardis.mod.util.helpers.Helper;

public class SystemFluidLinks extends ISystem{

	float health = 1F;
	int ticksToHurt = 0;
	
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
		if(health <= 0.00F && ticksToHurt > 0) {
			--ticksToHurt;
			if(!world.isRemote && world.getWorldTime() % 20 == 0) {
				for(EntityLivingBase base : world.getEntitiesWithinAABB(EntityLivingBase.class, Helper.createBB(consolePos, 60))) {
					base.attackEntityFrom(Tardis.SUFFICATION, 2F);
				}
			}
		}
	}

	@Override
	public void readFromNBT(NBTTagCompound tag) {
		this.health = tag.getFloat("health");
		this.ticksToHurt = tag.getInteger("time");
	}

	@Override
	public NBTTagCompound writetoNBT(NBTTagCompound tag) {
		tag.setFloat("health", health);
		tag.setInteger("time", ticksToHurt);
		return tag;
	}

	@Override
	public void damage() {
		health -= 0.25F;
		if(health <= 0.0F) {
			this.ticksToHurt = 600;
		}
	}

	@Override
	public Item getRepairItem() {
		return TItems.fluid_link;
	}

	@Override
	public boolean repair() {
		if(health < 1.0F) {
			health = 1.0F;
			return true;
		}
		return false;
	}

	@Override
	public String getNameKey() {
		return "system.tardis.fluidlinks";
	}

	@Override
	public void wear() {
		health -= 0.015F;
		if(health <= 0.0F) {
			this.ticksToHurt = 600;
		}
	}

}
