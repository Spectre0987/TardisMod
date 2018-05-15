package net.tardis.mod.common.items.components;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.tardis.mod.common.items.ItemBase;
import net.tardis.mod.info.CrashType;
import net.tardis.mod.systems.Systems;

/**
 * All TARDIS Components must extend this class
 * @author Spectre
 *
 */
public abstract class ItemComponent extends ItemBase{
	
	/**
	 * TARDIS Systems this Component is required for.**/
	public Systems[] systems;

	/**
	 * The Base of all Components
	 * **/
	public ItemComponent() {
		this.setMaxStackSize(1);
		this.setMaxDamage(100);
	}
	
	
	public abstract ItemStack damageItem(CrashType type,ItemStack stack);


	@Override
	public double getDurabilityForDisplay(ItemStack stack) {
		return getDamage(stack)/this.getMaxDamage();
	}
	
	public class NBT{
		public static final String DAMAGE="damage";
	}
	
	public int getDamage(ItemStack stack) {
		if(stack.hasTagCompound() && stack.getTagCompound().hasKey(NBT.DAMAGE)) {
			return stack.getTagCompound().getInteger(NBT.DAMAGE);
		}
		else{
			this.setDamage(stack,this.getMaxDamage());
			return getDamage(stack);
		}
	}
	
	public void setDamage(ItemStack stack, int dam) {
		if(!stack.hasTagCompound()) {
			stack.setTagCompound(new NBTTagCompound());
		}
		NBTTagCompound tag=stack.getTagCompound();
		tag.setInteger(NBT.DAMAGE, dam);
	}
	
}
