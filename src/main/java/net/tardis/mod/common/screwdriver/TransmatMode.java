package net.tardis.mod.common.screwdriver;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.tardis.mod.common.dimensions.TDimensions;
import net.tardis.mod.common.strings.TStrings;
import net.tardis.mod.util.TardisTeleporter;
import net.tardis.mod.util.helpers.TardisHelper;

public class TransmatMode implements IScrewAction {
	
	@Override
	public String getName() {
		return "screw.transmat";
	}
	
	@Override
	public void preform(World world, EntityPlayer player, EnumHand hand) {
		if (!world.isRemote) {
			if (TardisHelper.hasTardis(player.getUniqueID())) {
				BlockPos pos = TardisHelper.getTardis(player.getGameProfile().getId());
				if(player.dimension != TDimensions.id) {
					((WorldServer)world).getMinecraftServer().getPlayerList().transferPlayerToDimension((EntityPlayerMP) player, TDimensions.id, new TardisTeleporter());
				}
				((EntityPlayerMP) player).connection.setPlayerLocation(pos.getX(), pos.getY(), pos.getZ(), 0, 0);
			} else
				player.sendStatusMessage(new TextComponentTranslation(TStrings.TARDIS_MISSING), true);
		}
	}
	
}
