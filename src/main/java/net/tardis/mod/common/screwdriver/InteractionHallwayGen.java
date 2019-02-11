package net.tardis.mod.common.screwdriver;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraft.world.gen.structure.template.PlacementSettings;
import net.minecraft.world.gen.structure.template.Template;
import net.tardis.mod.common.blocks.BlockPanel;
import net.tardis.mod.common.blocks.TBlocks;
import net.tardis.mod.common.world.Structures;

public class InteractionHallwayGen implements IScrew {

	@Override
	public EnumActionResult performAction(World world, EntityPlayer player, EnumHand hand) {
		return EnumActionResult.FAIL;
	}

	@Override
	public EnumActionResult blockInteraction(World world, BlockPos pos, IBlockState state, EntityPlayer player) {

		if (world.isRemote) return EnumActionResult.FAIL;

		if (state.getBlock() == TBlocks.panel && state.getValue(BlockPanel.TYPE) == 1) {
			if (state.getBlock() == TBlocks.panel) {
				WorldServer ws = (WorldServer) world;
				MinecraftServer server = ws.getMinecraftServer();

				Template temp = ws.getStructureTemplateManager().get(server, Structures.HALLWAY);
				EnumFacing facing = player.getHorizontalFacing();
				Rotation rot = this.getRotationFromFacing(facing);
				PlacementSettings sett = new PlacementSettings().setRotation(this.getRotationFromFacing(facing));
				temp.addBlocksToWorld(ws, pos.add(this.getOffsetFromFacing(facing)), sett);
				return EnumActionResult.SUCCESS;
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
		return "screwdriver.hallway";
	}

	@Override
	public int getCoolDownAmount() {
		return 0;
	}

	@Override
	public boolean causesCoolDown() {
		return false;
	}

	@Override
	public int energyRequired() {
		return 10;
	}


	public BlockPos getOffsetFromFacing(EnumFacing facing) {
		switch (facing) {
			case NORTH:
				return new BlockPos(-3, -2, 1);
			case EAST:
				return new BlockPos(-1, -2, -3);
			case SOUTH:
				return new BlockPos(3, -2, -1);
			case WEST:
				return new BlockPos(1, -2, 3);
			default:
				return new BlockPos(0, 0, 0);
		}
	}

	public Rotation getRotationFromFacing(EnumFacing facing) {
		switch (facing) {
			case EAST:
				return Rotation.NONE;
			case SOUTH:
				return Rotation.CLOCKWISE_90;
			case WEST:
				return Rotation.CLOCKWISE_180;
			// AKA North
			default:
				return Rotation.COUNTERCLOCKWISE_90;
		}
	}

}
