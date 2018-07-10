package net.tardis.mod.common.screwdriver;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.DimensionManager;
import net.tardis.mod.common.dimensions.TDimensions;
import net.tardis.mod.common.strings.TStrings;
import net.tardis.mod.common.tileentity.TileEntityTardis;
import net.tardis.mod.util.helpers.TardisHelper;

public class HADSMode implements IScrewAction {

	@Override
	public String getName() {
		return "screwdriver.hads";
	}

	@Override
	public void preform(World world, EntityPlayer player, EnumHand hand) {
		if(!world.isRemote) {
			try {
				if(TardisHelper.hasTardis(player.getGameProfile().getId())) {
					BlockPos pos = TardisHelper.getTardis(player.getGameProfile().getId());
					WorldServer ws = DimensionManager.getWorld(TDimensions.id);
					((TileEntityTardis)ws.getTileEntity(pos)).returnFromHADS();
				}
				else player.sendStatusMessage(new TextComponentTranslation(TStrings.TARDIS_MISSING), true);
			}
			catch(Exception e) {}
		}
	}

}
