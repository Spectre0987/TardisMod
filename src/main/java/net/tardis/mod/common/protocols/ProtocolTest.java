package net.tardis.mod.common.protocols;

import net.minecraft.client.Minecraft;
import net.minecraft.world.World;
import net.tardis.mod.client.guis.GuiWorldJump;
import net.tardis.mod.common.tileentity.TileEntityTardis;

public class ProtocolTest implements ITardisProtocol {

	@Override
	public void onActivated(World world, TileEntityTardis tardis) {
		if(world.isRemote)
			Minecraft.getMinecraft().displayGuiScreen(new GuiWorldJump());
	}

	@Override
	public String getNameKey() {
		return "Test";
	}

}
