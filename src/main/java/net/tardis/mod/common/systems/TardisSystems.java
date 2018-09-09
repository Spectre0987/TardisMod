package net.tardis.mod.common.systems;

import java.util.HashMap;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
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
		
		private float health = 1F;
		
		public float getHealth() {
			return health;
		}
		public void setHealth(float health) {
			this.health = health > 1F ? 1F : (health < 0F ? 0F : health);
		}
		public abstract void onUpdate(World world, BlockPos consolePos);
		public void readFromNBT(NBTTagCompound tag) {
			this.health = tag.getFloat("health");
		}
		public NBTTagCompound writetoNBT(NBTTagCompound tag) {
			tag.setFloat("health", health);
			return tag;
		}
		/**Take Damage on crash**/
		public abstract void damage();
		public abstract Item getRepairItem();
		public boolean repair(ItemStack stack) {
			if(this.getHealth() < 1.0F) {
				this.setHealth(MathHelper.clamp(this.getHealth() + ((stack.getMaxDamage() - stack.getItemDamage()) / 100F), 0.0F, 1.0F));
				return true;
			}
			return false;
		}
		public abstract String getNameKey();
		/**Take damage at the end of each flight**/
		public abstract void wear();
		
		public boolean shouldStopFlight() {
			return this.getHealth() <= 0.0F;
		}
		@Override
		public boolean equals(Object obj) {
			return obj.getClass() == this.getClass() || super.equals(obj);
		}
		
	}

	

}
