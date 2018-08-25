package net.tardis.mod.common.screwdriver;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.tardis.mod.common.blocks.TBlocks;
import net.tardis.mod.common.tileentity.TileEntityEPanel;
import net.tardis.mod.util.helpers.TardisHelper;

public class InteractonElectricPanel implements IScrew {

	@Override
	public EnumActionResult performAction(World world, EntityPlayer player, EnumHand hand) {
		return EnumActionResult.FAIL;
	}

	@Override
	public EnumActionResult blockInteraction(World world, BlockPos pos, IBlockState state, EntityPlayer player) {

		if (!world.isRemote) {
			Block block = world.getBlockState(pos).getBlock();
			if (block == TBlocks.panel || block == TBlocks.megalos) {
				world.setBlockState(pos, TBlocks.electric_panel.getDefaultState());
			} else if (world.getBlockState(pos).getBlock() == TBlocks.electric_panel) {
				world.setBlockState(pos, TBlocks.panel.getDefaultState());
			}
			TileEntity te = world.getTileEntity(pos);
			if(te != null && te instanceof TileEntityEPanel) {
				TileEntityEPanel panel = (TileEntityEPanel)te;
				if(TardisHelper.hasTardis(player.getGameProfile().getId())) {
					BlockPos tardisPos = TardisHelper.getTardis(player.getGameProfile().getId());
					if(player.getPosition().getDistance(tardisPos.getX(), tardisPos.getY(), tardisPos.getZ()) < 8 * 16) {
						panel.setOwner(player.getGameProfile().getId());
					}
					return EnumActionResult.SUCCESS;
				}
			}
		}
		return EnumActionResult.FAIL;
	}

	@Override
	public boolean entityInteraction(ItemStack stack, EntityPlayer playerIn, EntityLivingBase target, EnumHand hand) {
		return false;
	}

	@Override
	public String getName() {
		return "screw.electric";
	}

	@Override
	public int getCoolDownAmount() {
		return 50;
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
