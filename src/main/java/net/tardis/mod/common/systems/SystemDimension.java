package net.tardis.mod.common.systems;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.tardis.mod.common.enums.EnumTardisState;
import net.tardis.mod.common.items.TItems;
import net.tardis.mod.common.systems.TardisSystems.ISystem;
import net.tardis.mod.common.tileentity.TileEntityTardis;
import net.tardis.mod.util.helpers.Helper;

public class SystemDimension implements ISystem{

	float health = 1F;
	boolean shouldFix = false;
	boolean runOnce = false;
	
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
		TileEntityTardis tardis = (TileEntityTardis)world.getTileEntity(consolePos);
		if(this.health <= 0.00F) {
			if(runOnce) {
				tardis.setTardisState(EnumTardisState.DISABLED);
				for(EntityPlayer p : world.getEntitiesWithinAABB(EntityPlayer.class, Helper.createBB(consolePos, 8 * 16))) {
					tardis.transferPlayer(p, false);
				}
				runOnce = false;
			}
			if(!world.isRemote) {
				for(EntityPlayer p : world.getEntitiesWithinAABB(EntityPlayer.class, Helper.createBB(consolePos, 20))) {
					tardis.transferPlayer(p, false);
				}
			}
		}
		if(shouldFix) {
			tardis.setTardisState(EnumTardisState.NORMAL);
			this.shouldFix = false;
		}
	}

	@Override
	public void readFromNBT(NBTTagCompound tag) {
		health = tag.getFloat("health");
		this.shouldFix = tag.getBoolean("fix");
	}

	@Override
	public NBTTagCompound writetoNBT(NBTTagCompound tag) {
		tag.setFloat("health", health);
		tag.setBoolean("fix", shouldFix);
		return tag;
	}

	@Override
	public void damage() {
		health -= 0.005;
		if(health <= 0.00F)runOnce = true;
	}

	@Override
	public Item getRepairItem() {
		return TItems.time_vector_generator;
	}

	@Override
	public boolean repair() {
		if(health < 1.0F) {
			this.health = 1F;
			this.shouldFix = true;
			return true;
		}
		return false;
	}

	@Override
	public String getNameKey() {
		return "systems.protocol.dimensions";
	}

	@Override
	public boolean shouldStopFlight() {
		return false;
	}

	@Override
	public void wear() {
		
	}
}
