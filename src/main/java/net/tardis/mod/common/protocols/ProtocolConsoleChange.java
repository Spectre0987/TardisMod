package net.tardis.mod.common.protocols;

import net.minecraft.client.Minecraft;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tardis.mod.client.guis.GuiConsoleChange;
import net.tardis.mod.common.tileentity.TileEntityTardis;

public class ProtocolConsoleChange implements ITardisProtocol {

	@Override
	public void onActivated(World world, TileEntityTardis tardis) {
		System.out.println(world);
		if(!world.isRemote) {
			((TileEntityTardis)world.getTileEntity(tardis.getPos())).travel();;
		}
	}
	
	@SideOnly(Side.CLIENT)
	public void openGui(BlockPos pos) {
		Minecraft.getMinecraft().displayGuiScreen(null);
		Minecraft.getMinecraft().displayGuiScreen(new GuiConsoleChange(pos));
	}

	@Override
	public String getNameKey() {
		return "protocol.consolechange";
	}

}
