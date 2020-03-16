package net.tardis.mod.cap;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.tardis.mod.common.tileentity.TileEntityTardis;

public interface ITardisTracker {

	@CapabilityInject(ITardisTracker.class)
	public static final Capability<TardisTrackerCapability> TRACKER = null;
	
	void setConsole(BlockPos pos);
	BlockPos getConsolePos();
	
	TileEntityTardis getConsole();
	BlockPos getExteriorPos();
	
	void deserialize(NBTTagCompound tag);
	NBTTagCompound write(NBTTagCompound tag);
	
	void findConsoleInstance();
	
	void onRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn);
	
	public static class TardisTrackerStorage implements IStorage<ITardisTracker>{

		@Override
		public NBTBase writeNBT(Capability<ITardisTracker> capability, ITardisTracker instance, EnumFacing side) {
			System.out.println("Tryed to write, cap no get");
			return instance.write(new NBTTagCompound());
		}

		@Override
		public void readNBT(Capability<ITardisTracker> capability, ITardisTracker instance, EnumFacing side, NBTBase nbt) {
			instance.deserialize((NBTTagCompound)nbt);
		}
		
	}
	
	public static class TrackerProvider implements ICapabilityProvider{

		private ITardisTracker tracker;
		
		public TrackerProvider() {
			tracker = new TardisTrackerCapability();
		}
		
		@Override
		public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
			return capability == tracker;
		}

		@Override
		public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
			return capability == TRACKER ? (T)tracker : null;
		}
		
	}
}
