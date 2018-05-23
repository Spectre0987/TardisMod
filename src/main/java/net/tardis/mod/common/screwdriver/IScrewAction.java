package net.tardis.mod.common.screwdriver;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

public interface IScrewAction extends IScrew {
	
	void preform(World world, EntityPlayer player, EnumHand hand);
}
