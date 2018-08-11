package net.tardis.mod.common.protocols;

import net.minecraft.client.Minecraft;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tardis.mod.client.guis.GuiARS;
import net.tardis.mod.common.tileentity.TileEntityTardis;

public class ProtocolARS implements ITardisProtocol {

	@Override
	public void onActivated(World world, TileEntityTardis tardis) {
		if(world.isRemote) {
			this.openGui();
		}
	}

	@Override
	public String getNameKey() {
		return "protocol.tardis.ars";
	}
	
	@SideOnly(Side.CLIENT)
	public void openGui() {
		Minecraft.getMinecraft().displayGuiScreen(new GuiARS());
	}

}
