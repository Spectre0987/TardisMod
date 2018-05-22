package net.tardis.mod.common.screwdriver;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.tardis.api.screwdriver.IScrewAction;
import net.tardis.mod.common.dimensions.TDimensions;
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
				BlockPos pos = TardisHelper.getTardis(player.getUniqueID());
				WorldServer ws = ((WorldServer) world).getMinecraftServer().getWorld(TDimensions.id);
				((EntityPlayerMP)player).connection.setPlayerLocation(pos.getX(), pos.getY(), pos.getZ(), 0, 0);
				ws.getMinecraftServer().getPlayerList().transferPlayerToDimension((EntityPlayerMP) player, TDimensions.id, new TardisTeleporter((WorldServer) world));
			} else
				player.sendMessage(new TextComponentTranslation("tardis.none"));
		}
	}
	
}
