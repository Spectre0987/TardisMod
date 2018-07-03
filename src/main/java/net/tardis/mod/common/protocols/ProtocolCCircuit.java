package net.tardis.mod.common.protocols;

import net.minecraft.client.Minecraft;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tardis.mod.client.guis.GuiCCircuit;
import net.tardis.mod.common.tileentity.TileEntityTardis;

public class ProtocolCCircuit implements ITardisProtocol {

	@Override
	public void onActivated(World world, TileEntityTardis tardis) {
		if(world.isRemote) {
			openGui(tardis.getPos());
		}
	}
	
	@SideOnly(Side.CLIENT)
	public void openGui(BlockPos pos) {
		Minecraft.getMinecraft().displayGuiScreen(null);
		Minecraft.getMinecraft().displayGuiScreen(new GuiCCircuit(pos));
	}

	@Override
	public String getNameKey() {
		return "protocol.consolechange";
	}

}
