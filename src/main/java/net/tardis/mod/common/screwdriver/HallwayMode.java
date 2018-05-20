package net.tardis.mod.common.screwdriver;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraft.world.gen.structure.template.PlacementSettings;
import net.minecraft.world.gen.structure.template.Template;
import net.tardis.api.screwdriver.IScrewable;
import net.tardis.mod.common.world.Structures;

public class HallwayMode implements IScrewable {

	@Override
	public String getName() {
		return "screwdriver.hallway";
	}

	@Override
	public void screw(World world, BlockPos pos, IBlockState state,EntityPlayer player) {
		if(!world.isRemote) {
			WorldServer ws=(WorldServer)world;
			MinecraftServer server=ws.getMinecraftServer();
			
			Template temp=ws.getStructureTemplateManager().get(server, Structures.HALLWAY);
			temp.addBlocksToWorld(ws, pos, new PlacementSettings().setRotation(Rotation.CLOCKWISE_90));
		}
	}

}
