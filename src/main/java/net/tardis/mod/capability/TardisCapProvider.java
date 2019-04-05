package net.tardis.mod.capability;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class TardisCapProvider implements ICapabilitySerializable<NBTTagCompound> {
	
	private ITardisCap capability;
	
	public TardisCapProvider(ITardisCap capability) {
		this.capability = capability;
	}
	
	@Override
	public boolean hasCapability(@Nonnull Capability<?> capability, @Nullable EnumFacing facing) {
		return CapabilityTardis.CAPABILITY != null && capability == CapabilityTardis.CAPABILITY;
	}
	
	@Nullable
	@Override
	public <T> T getCapability(@Nonnull Capability<T> capability, @Nullable EnumFacing facing) {
		return capability == CapabilityTardis.CAPABILITY ? CapabilityTardis.CAPABILITY.cast(this.capability) : null;
	}
	
	@Override
	public NBTTagCompound serializeNBT() {
		return (NBTTagCompound) CapabilityTardis.CAPABILITY.getStorage().writeNBT(CapabilityTardis.CAPABILITY, capability, null);
	}
	
	@Override
	public void deserializeNBT(NBTTagCompound nbt) {
		CapabilityTardis.CAPABILITY.getStorage().readNBT(CapabilityTardis.CAPABILITY, capability, null, nbt);
	}
	
}