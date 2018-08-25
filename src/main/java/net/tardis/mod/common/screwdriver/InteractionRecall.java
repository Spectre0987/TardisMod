package net.tardis.mod.common.screwdriver;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.tardis.mod.common.dimensions.TDimensions;
import net.tardis.mod.common.strings.TStrings;
import net.tardis.mod.common.tileentity.TileEntityTardis;
import net.tardis.mod.util.helpers.TardisHelper;

public class InteractionRecall implements IScrew {

	@Override
	public void performAction(World world, EntityPlayer player, EnumHand hand) {
		if (!world.isRemote && player.dimension != TDimensions.TARDIS_ID) {
			if (TardisHelper.hasTardis(player.getUniqueID())) {
				BlockPos pos = TardisHelper.getTardis(player.getUniqueID());
				WorldServer ws = world.getMinecraftServer().getWorld(TDimensions.TARDIS_ID);
				TileEntityTardis tardis = (TileEntityTardis) ws.getTileEntity(pos);
				tardis.setDesination(player.getPosition().down().offset(player.getHorizontalFacing(), 2), player.dimension);
				tardis.setFacing(player.getHorizontalFacing().getOpposite());
				if (tardis.startFlight()) tardis.travel();
			} else
				player.sendStatusMessage(new TextComponentTranslation(TStrings.TARDIS_MISSING), true);
		}
	}

	@Override
	public void blockInteraction(World world, BlockPos pos, IBlockState state, EntityPlayer player) {

	}

	@Override
	public void entityInteraction(ItemStack stack, EntityPlayer playerIn, EntityLivingBase target, EnumHand hand) {

	}

	@Override
	public String getName() {
		return "screw.recall";
	}

	@Override
	public int getCoolDownAmount() {
		return 0;
	}

	@Override
	public boolean causesCoolDown() {
		return false;
	}

}
