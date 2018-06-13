package net.tardis.mod.common.screwdriver;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.play.server.SPacketChunkData;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraft.world.gen.structure.template.PlacementSettings;
import net.minecraft.world.gen.structure.template.Template;
import net.tardis.mod.common.blocks.BlockPanel;
import net.tardis.mod.common.blocks.TBlocks;
import net.tardis.mod.common.world.Structures;

public class HallwayMode implements IScrewable {
	
	@Override
	public String getName() {
		return "screwdriver.hallway";
	}
	
	@Override
	public void screw(World world, BlockPos pos, IBlockState state, EntityPlayer player) {
		if (!world.isRemote) {
			if (state.getBlock() == TBlocks.panel && state.getValue(BlockPanel.TYPE) == 1) {
				if (state.getBlock() == TBlocks.panel) {
					WorldServer ws = (WorldServer) world;
					MinecraftServer server = ws.getMinecraftServer();
					
					Template temp = ws.getStructureTemplateManager().get(server, Structures.HALLWAY);
					EnumFacing facing = player.getHorizontalFacing();
					Rotation rot = this.getRotationFromFacing(facing);
					PlacementSettings sett = new PlacementSettings().setRotation(this.getRotationFromFacing(facing));
					temp.addBlocksToWorld(ws, pos.add(this.getOffsetFromFacing(facing)), sett, 2);
					ChunkPos cPos = world.getChunkFromBlockCoords(pos).getPos();
					for (int x = -1; x < 3; ++x) {
						for (int z = -1; z < 3; ++z) {
							((EntityPlayerMP) player).connection.sendPacket(new SPacketChunkData(world.getChunkFromChunkCoords(cPos.x + x, cPos.z + z), 65535));
						}
					}
				}
			}
		}
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
