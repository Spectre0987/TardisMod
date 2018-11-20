package net.tardis.mod.common.protocols;

import net.minecraft.client.Minecraft;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tardis.mod.client.guis.GuiWaypoints;
import net.tardis.mod.common.tileentity.TileEntityTardis;

public class ProtocolWaypoints implements ITardisProtocol {

	@Override
	public void onActivated(World world, TileEntityTardis tardis) {
		if(world.isRemote)
			open(tardis.getPos());
	}
	
	@SideOnly(Side.CLIENT)
	public void open(BlockPos pos) {
		Minecraft.getMinecraft().displayGuiScreen(new GuiWaypoints(pos));
	}

	@Override
	public String getNameKey() {
		return "protocol.tardis.waypoint.load";
	}

}
