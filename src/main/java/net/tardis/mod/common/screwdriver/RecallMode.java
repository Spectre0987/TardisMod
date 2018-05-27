package net.tardis.mod.common.screwdriver;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.tardis.mod.common.dimensions.TDimensions;
import net.tardis.mod.common.strings.TStrings;
import net.tardis.mod.common.tileentity.TileEntityTardis;
import net.tardis.mod.util.helpers.TardisHelper;

public class RecallMode implements IScrewAction {
	
	@Override
	public String getName() {
		return "screw.recall";
	}
	
	@Override
	public void preform(World world, EntityPlayer player, EnumHand hand) {
		if (!world.isRemote && player.dimension != TDimensions.id) {
			if (TardisHelper.hasTardis(player.getUniqueID())) {
				BlockPos pos = TardisHelper.getTardis(player.getUniqueID());
				WorldServer ws = ((WorldServer) world).getMinecraftServer().getWorld(TDimensions.id);
				TileEntityTardis tardis = (TileEntityTardis) ws.getTileEntity(pos);
				tardis.setDesination(player.getPosition().down().offset(player.getHorizontalFacing(),2), player.dimension);
				tardis.setFacing(player.getHorizontalFacing().getOpposite());
				if (tardis.startFlight())
					tardis.travel();
			} else
				player.sendStatusMessage(new TextComponentTranslation(TStrings.TARDIS_MISSING),true);
		}
	}
	
}
