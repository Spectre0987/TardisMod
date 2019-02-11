package net.tardis.mod.common.protocols;

import net.minecraft.client.Minecraft;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tardis.mod.client.guis.GuiSystem;
import net.tardis.mod.common.tileentity.TileEntityTardis;

public class ProtocolSystemReadout implements ITardisProtocol {

	@Override
	public void onActivated(World world, TileEntityTardis tardis) {
		if (world.isRemote) {
			openGui(tardis);
		}
	}

	@SideOnly(Side.CLIENT)
	public void openGui(TileEntityTardis tardis) {
		Minecraft.getMinecraft().displayGuiScreen(new GuiSystem(tardis));
	}

	@Override
	public String getNameKey() {
		return "protocol.tardis.systemreadout";
	}

}
