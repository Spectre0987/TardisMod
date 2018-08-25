package net.tardis.mod.common.screwdriver;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.network.play.server.SPacketChunkData;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraft.world.gen.structure.template.PlacementSettings;
import net.minecraft.world.gen.structure.template.Template;
import net.tardis.mod.common.blocks.BlockPanel;
import net.tardis.mod.common.blocks.TBlocks;
import net.tardis.mod.common.world.Structures;

public class InteractionGRoom implements IScrew {

	@Override
	public EnumActionResult performAction(World world, EntityPlayer player, EnumHand hand) {
		return EnumActionResult.FAIL;
	}

	@Override
	public EnumActionResult blockInteraction(World world, BlockPos pos, IBlockState state, EntityPlayer player) {
		if (!world.isRemote) {
			if (state.getBlock() == TBlocks.panel && state.getValue(BlockPanel.TYPE) == 1) {
				WorldServer ws = (WorldServer) world;
				MinecraftServer server = ws.getMinecraftServer();
				EnumFacing facing = player.getHorizontalFacing();

				Template temp = ws.getStructureTemplateManager().get(server, Structures.GENERIC_ROOM);
				temp.addBlocksToWorld(ws, pos.add(getOffset(facing)), new PlacementSettings().setRotation(getRotation(facing)));
				ChunkPos cPos = world.getChunkFromBlockCoords(pos).getPos();
				for (int x = -1; x < 3; ++x) {
					for (int z = -1; z < 3; ++z) {
						((EntityPlayerMP) player).connection.sendPacket(new SPacketChunkData(world.getChunkFromChunkCoords(cPos.x + x, cPos.z + z), 65535));
					}
				}
			}
			return EnumActionResult.SUCCESS;
		}
		return EnumActionResult.FAIL;
	}

	@Override
	public boolean entityInteraction(ItemStack stack, EntityPlayer playerIn, EntityLivingBase target, EnumHand hand) {
		return false;
	}

	@Override
	public String getName() {
		return "screw.generic_room";
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
		return 0;
	}

	private Rotation getRotation(EnumFacing facing) {
		switch (facing) {
			case NORTH:
				return Rotation.NONE;
			case EAST:
				return Rotation.CLOCKWISE_90;
			case SOUTH:
				return Rotation.CLOCKWISE_180;
			case WEST:
				return Rotation.COUNTERCLOCKWISE_90;
			default:
				return Rotation.NONE;
		}
	}
	
	private Vec3i getOffset(EnumFacing facing) {
		switch (facing) {
			case NORTH:
				return new BlockPos(-4, -2, -8);
			case EAST:
				return new BlockPos(8, -2, -4);
			case SOUTH:
				return new BlockPos(4, -2, 8);
			default:
				return new BlockPos(-8, -2, 4);
		}
	}
	
}
