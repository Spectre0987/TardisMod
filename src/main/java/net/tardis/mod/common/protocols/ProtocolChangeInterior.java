package net.tardis.mod.common.protocols;

import net.minecraft.client.Minecraft;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tardis.mod.client.guis.GuiChangeInterior;
import net.tardis.mod.common.tileentity.TileEntityTardis;

public class ProtocolChangeInterior implements ITardisProtocol {

	@Override
	public void onActivated(World world, TileEntityTardis tardis) {
		if(world.isRemote) {
			openGUI(tardis);
		}
	}
	
	@SideOnly(Side.CLIENT)
	public void openGUI(TileEntityTardis tardis) {
		Minecraft.getMinecraft().displayGuiScreen(new GuiChangeInterior(tardis));
	}

	@Override
	public String getNameKey() {
		return "protocol.tardis.interior";
	}

}
