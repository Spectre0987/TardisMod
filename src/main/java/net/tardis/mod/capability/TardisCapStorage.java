package net.tardis.mod.capability;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilityProvider;

import javax.annotation.Nullable;

public class TardisCapStorage implements IStorage<ITardisCap> {

	@CapabilityInject(ITardisCap.class)
	public static Capability<ITardisCap> CAP = null;
	
	@Nullable
	@Override
	public NBTBase writeNBT(Capability<ITardisCap> capability, ITardisCap instance, EnumFacing side) {
		return instance.serializeNBT();
	}
	
	@Override
	public void readNBT(Capability<ITardisCap> capability, ITardisCap instance, EnumFacing side, NBTBase nbt) {
		instance.deserializeNBT(nbt instanceof NBTTagCompound ? (NBTTagCompound) nbt : new NBTTagCompound());
	}
	
	
	public static class TardisCapProvider implements ICapabilityProvider{
		
		private CapabilityTardis cap;
		
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
