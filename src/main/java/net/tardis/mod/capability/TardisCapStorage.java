package net.tardis.mod.capability;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTBase;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilityProvider;

public class TardisCapStorage implements IStorage {

	@CapabilityInject(ITardisCap.class)
	public static Capability<CapabilityTardis> CAP = null;
	
	@Override
	public NBTBase writeNBT(Capability capability, Object instance, EnumFacing side) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void readNBT(Capability capability, Object instance, EnumFacing side, NBTBase nbt) {
		// TODO Auto-generated method stub

	}
	
	public static class TardisCapProvider implements ICapabilityProvider{

		CapabilityTardis cap;
		
		public TardisCapProvider(EntityPlayer player) {
			this.cap = new CapabilityTardis(player);
		}
		
		@Override
		public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
			return capability == CAP;
		}

		@Override
		public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
			return capability == CAP ? (T)cap : null;
		}}

}
