package net.tardis.mod.common.systems;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.tardis.mod.common.entities.controls.ControlDoor;
import net.tardis.mod.common.enums.EnumTardisState;
import net.tardis.mod.common.items.TItems;
import net.tardis.mod.common.systems.TardisSystems.BaseSystem;
import net.tardis.mod.common.tileentity.TileEntityTardis;
import net.tardis.mod.util.common.helpers.Helper;
import net.tardis.mod.util.common.helpers.PlayerHelper;

public class SystemDimension extends BaseSystem{

	boolean shouldFix = false;
	boolean runOnce = false;
	
	public SystemDimension() {
		this.setHealth(1F);
	}
	
	@Override
	public void onUpdate(World world, BlockPos consolePos) {
		TileEntityTardis tardis = (TileEntityTardis)world.getTileEntity(consolePos);
		if(this.getHealth() <= 0.00F) {
			if(runOnce) {
				tardis.setTardisState(EnumTardisState.DISABLED);
				for(EntityPlayer p : world.getEntitiesWithinAABB(EntityPlayer.class, Helper.createBB(consolePos, 8 * 16))) {
					PlayerHelper.sendMessage(p, "door.tardis.relocating", false);
					tardis.transferPlayer(p, false);
					ControlDoor door = tardis.getDoor();
					if(door != null)door.setOpen(false);
				}
				runOnce = false;
			}
			if(!world.isRemote) {
				for(EntityPlayer p : world.getEntitiesWithinAABB(EntityPlayer.class, Helper.createBB(consolePos, 20))) {
					PlayerHelper.sendMessage(p, "door.tardis.relocating", false);
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
		super.readFromNBT(tag);
		this.shouldFix = tag.getBoolean("fix");
	}

	@Override
	public NBTTagCompound writetoNBT(NBTTagCompound tag) {
		super.writetoNBT(tag);
		tag.setBoolean("fix", shouldFix);
		return tag;
	}

	@Override
	public void damage() {
		this.setHealth(this.getHealth() - 0.2F);
		if(this.getHealth() <= 0.00F) runOnce = true;
	}

	@Override
	public Item getRepairItem() {
		return TItems.time_vector_generator;
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
	public String getUsage() {
		return "have";
	}

	@Override
	public void wear() {}

	@Override
	public void setHealth(float health) {
		super.setHealth(health);
		if(this.getHealth() <= 0.0F) this.runOnce = true;
	}

	@Override
	public boolean repair(ItemStack stack) {
		boolean b = super.repair(stack);
		if(b) {
			this.shouldFix = true;
		}
		return b;
	}
}
