package net.tardis.mod.common.systems;

import java.util.HashMap;

import net.minecraft.item.Item;
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
	
	public static interface ISystem{
		
		default boolean shouldStopFlight() {
			return this.getHealth() <= 0.0F;
		}
		float getHealth();
		void setHealth(float health);
		void onUpdate(World world, BlockPos consolePos);
		void readFromNBT(NBTTagCompound tag);
		NBTTagCompound writetoNBT(NBTTagCompound tag);
		void damage();
		Item getRepairItem();
		void repair();
		String getNameKey();
	}

	

}
