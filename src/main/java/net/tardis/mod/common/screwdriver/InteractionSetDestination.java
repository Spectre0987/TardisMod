package net.tardis.mod.common.screwdriver;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.DimensionManager;
import net.tardis.mod.common.dimensions.TDimensions;
import net.tardis.mod.common.tileentity.TileEntityTardis;
import net.tardis.mod.util.helpers.Helper;
import net.tardis.mod.util.helpers.PlayerHelper;
import net.tardis.mod.util.helpers.TardisHelper;

public class InteractionSetDestination implements IScrew {

	@Override
	public EnumActionResult performAction(World world, EntityPlayer player, EnumHand hand) {
		if(!world.isRemote) {
			if(TardisHelper.hasTardis(player.getGameProfile().getId())) {
				TileEntityTardis tardis = Helper.getTardis(world.getMinecraftServer().getWorld(TDimensions.TARDIS_ID).getTileEntity(TardisHelper.getTardis(player.getGameProfile().getId())));
				tardis.setDesination(player.getPosition(), player.dimension);
				BlockPos pos = player.getPosition();
				String dim = Helper.formatDimensionName(DimensionManager.createProviderFor(player.dimension).getDimensionType().getName());
				PlayerHelper.sendMessage(player, String.format("Tardis destination set to: %s, %s, %s on Dimension " + dim, pos.getX(), pos.getY(), pos.getZ(), player.dimension), false);
				return EnumActionResult.SUCCESS;
			}
		}
		return EnumActionResult.FAIL;
	}

	@Override
	public EnumActionResult blockInteraction(World world, BlockPos pos, IBlockState state, EntityPlayer player) {
		return EnumActionResult.FAIL;
	}

	@Override
	public boolean entityInteraction(ItemStack stack, EntityPlayer playerIn, EntityLivingBase target, EnumHand hand) {
		return false;
	}

	@Override
	public String getName() {
		return "screw.tardis.signal";
	}

	@Override
	public int getCoolDownAmount() {
		return 20;
	}

	@Override
	public boolean causesCoolDown() {
		return true;
	}

	@Override
	public int energyRequired() {
		return 5;
	}

}
