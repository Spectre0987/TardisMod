package net.tardis.mod.common.data;

import net.minecraft.entity.player.EntityPlayer;
import net.tardis.mod.Tardis;

public class TimeLord {
	
	
	public static void setTimeLord(EntityPlayer player) {
		player.getEntityData().setBoolean(Tardis.MODID + ":timelord", true);
		player.getEntityData().setInteger(Tardis.MODID + ":regens", 12);
	}
	
	public static boolean isTimeLord(EntityPlayer player) {
		return player.getEntityData().getBoolean(Tardis.MODID + ":timelord");
	}

	public static boolean useRegen(EntityPlayer player) {
		if(TimeLord.getRegens(player) > 0) {
			player.getEntityData().setInteger(Tardis.MODID + ":regens", TimeLord.getRegens(player) - 1);
			return true;
		}
		return false;
	}
	
	public static int getRegens(EntityPlayer player) {
		if(TimeLord.isTimeLord(player)) {
			return player.getEntityData().getInteger(Tardis.MODID + ":regens");
		}
		return 0;
	}

}
