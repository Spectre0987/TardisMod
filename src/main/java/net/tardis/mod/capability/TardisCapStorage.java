package net.tardis.mod.capability;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;

import javax.annotation.Nullable;

public class TardisCapStorage implements Capability.IStorage<ITardisCap> {
	
	@Nullable
	@Override
	public NBTBase writeNBT(Capability<ITardisCap> capability, ITardisCap instance, EnumFacing side) {
		return instance.serializeNBT();
	}
	
	@Override
	public void readNBT(Capability<ITardisCap> capability, ITardisCap instance, EnumFacing side, NBTBase nbt) {
		instance.deserializeNBT(nbt instanceof NBTTagCompound ? (NBTTagCompound) nbt : new NBTTagCompound());
	}
	
}

