package net.tardis.mod.common.protocols;

import net.minecraft.client.Minecraft;
import net.minecraft.world.World;
import net.tardis.mod.client.guis.GuiSystem;
import net.tardis.mod.common.tileentity.TileEntityTardis;

public class ProtocolSystemReadout implements ITardisProtocol {

	@Override
	public void onActivated(World world, TileEntityTardis tardis) {
		if(world.isRemote) {
			Minecraft.getMinecraft().displayGuiScreen(new GuiSystem(tardis));
		}
	}

	@Override
	public String getNameKey() {
		return "protocol.tardis.systemreadout";
	}

}
