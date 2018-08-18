package net.tardis.mod.common.systems;

import java.util.HashMap;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class TardisSystems {
	
	public static HashMap<String, Class<? extends ISystem>> SYSTEMS = new HashMap<>();
	
	public static void register(String name, Class<? extends ISystem> sys){
		if(SYSTEMS.containsKey(name)) {
			return;
		}
		SYSTEMS.put(name, sys);
	}
	
	public static ISystem createFromName(String s) {
		if(!SYSTEMS.containsKey(s))return null;
		try {
			return SYSTEMS.get(s).newInstance();
		}
		catch(Exception e) {}
		return null;
	}
	
	public static String getIdBySystem(ISystem sys) {
		for(String key : SYSTEMS.keySet()) {
			Class<? extends ISystem> clazz = SYSTEMS.get(key);
			if(clazz == sys.getClass()) {
				return key;
			}
		}
		return null;
	}
	
	public static abstract class ISystem{
		
		public abstract float getHealth();
		public abstract void setHealth(float health);
		public abstract void onUpdate(World world, BlockPos consolePos);
		public abstract void readFromNBT(NBTTagCompound tag);
		public abstract NBTTagCompound writetoNBT(NBTTagCompound tag);
		/**Take Damage on crash**/
		public abstract void damage();
		public abstract Item getRepairItem();
		public abstract boolean repair(ItemStack stack);
		public abstract String getNameKey();
		/**Take damage at the end of each flight**/
		public abstract void wear();
		
		public boolean shouldStopFlight() {
			return this.getHealth() <= 0.0F;
		}
		@Override
		public boolean equals(Object obj) {
			if(obj.getClass() == this.getClass()) return true;
			return super.equals(obj);
		}
		
	}

	

}
