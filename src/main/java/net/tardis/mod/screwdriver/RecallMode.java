package net.tardis.mod.screwdriver;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.tardis.api.screwdriver.IScrewAction;
import net.tardis.mod.dimensions.TDimensions;
import net.tardis.mod.helpers.TardisHelper;
import net.tardis.mod.tileentity.TileEntityTardis;

public class RecallMode implements IScrewAction{

	@Override
	public String getName() {
		return "screw.recall";
	}

	@Override
	public void preform(World world, EntityPlayer player, EnumHand hand) {
		if(!world.isRemote&&player.dimension!=TDimensions.id) {
			if(TardisHelper.hasTardis(player.getUniqueID())) {
				BlockPos pos=TardisHelper.getTardis(player.getUniqueID());
				WorldServer ws=((WorldServer)world).getMinecraftServer().getWorld(TDimensions.id);
				TileEntityTardis tardis=(TileEntityTardis)ws.getTileEntity(pos);
				tardis.setDesination(player.getPosition().down(), player.dimension);
				if(tardis.startFlight())
					tardis.travel();
			}
			else player.sendMessage(new TextComponentTranslation("tardis.none"));
		}
	}
	
}
