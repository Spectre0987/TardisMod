package net.tardis.mod.common.protocols;

import net.minecraft.client.Minecraft;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tardis.mod.client.guis.GuiConsoleChange;
import net.tardis.mod.common.tileentity.TileEntityTardis;

public class ProtocolConsole implements ITardisProtocol {

	@Override
	public void onActivated(World world, TileEntityTardis t) {
		if(world.isRemote)
			openGUI(t);
	}

	@SideOnly(Side.CLIENT)
	public void openGUI(TileEntityTardis tardis) {
		Minecraft.getMinecraft().displayGuiScreen(new GuiConsoleChange(tardis));
	}
	
	@Override
	public String getNameKey() {
		return "protocol.tardis.change.console";
	}

}
