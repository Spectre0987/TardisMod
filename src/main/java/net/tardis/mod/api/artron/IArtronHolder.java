package net.tardis.mod.api.artron;

import net.minecraft.item.ItemStack;

public interface IArtronHolder {
	
	int getHeldArtron(ItemStack stack);
	int getMaxArtron(ItemStack stack);
	void setArtron(ItemStack stack);
	
	boolean charge(int charge);
	boolean discharge(int charge);

}
