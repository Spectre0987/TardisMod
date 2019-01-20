package net.tardis.mod.common.protocols;

import net.minecraft.block.state.IBlockState;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraft.world.gen.structure.template.PlacementSettings;
import net.minecraft.world.gen.structure.template.Template;
import net.tardis.mod.Tardis;
import net.tardis.mod.common.tileentity.TileEntityTardis;

public class ProtocolRegenRoom implements ITardisProtocol {

	@Override
	public void onActivated(World world, TileEntityTardis tardis) {
		if (!world.isRemote) {
			for (BlockPos pos : BlockPos.getAllInBox(tardis.getPos().subtract(new Vec3i(20, 20, 20)), tardis.getPos().add(20, 20, 20))) {
				if (world.getTileEntity(pos) == null) {
					world.setBlockToAir(pos);
				}
			}
			NBTTagCompound tag = tardis.writeToNBT(new NBTTagCompound());
			IBlockState state = world.getBlockState(tardis.getPos());
			MinecraftServer server = world.getMinecraftServer();
			Template temp = ((WorldServer) world).getStructureTemplateManager().get(server, new ResourceLocation(Tardis.MODID, "console_room"));
			temp.addBlocksToWorld(world, tardis.getPos().add(-temp.getSize().getX() / 2, -2, -(temp.getSize().getZ() / 2) + 1), new PlacementSettings());
			world.setBlockState(tardis.getPos(), state);
			(world.getTileEntity(tardis.getPos())).readFromNBT(tag);

		}
	}

	@Override
	public String getNameKey() {
		return "protocol.tardis.regenroom";
	}

}
