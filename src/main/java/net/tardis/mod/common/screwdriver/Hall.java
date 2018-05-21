package net.tardis.mod.common.screwdriver;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraft.world.gen.structure.template.PlacementSettings;
import net.minecraft.world.gen.structure.template.Template;
import net.tardis.api.screwdriver.IScrewable;
import net.tardis.mod.Tardis;
import net.tardis.mod.common.blocks.TBlocks;

public class Hall implements IScrewable {
	
	public static final ResourceLocation HALL_LOCATION = new ResourceLocation(Tardis.MODID, "corridor");
	
	@Override
	public String getName() {
		return "scredriver.hall";
	}
	
	@Override
	public void screw(World world, BlockPos pos, IBlockState state,EntityPlayer player) {
		if (!world.isRemote) {
			if (world.getBlockState(pos).getBlock() == TBlocks.room_gen) {
				WorldServer ws = (WorldServer) world;
				MinecraftServer server = ws.getMinecraftServer();
				Template temp = ws.getStructureTemplateManager().get(server, HALL_LOCATION);
				temp.addBlocksToWorld(ws, pos, new PlacementSettings());
			}
		}
	}
	
}
