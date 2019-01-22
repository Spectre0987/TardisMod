package net.tardis.mod.handlers;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;
import net.tardis.mod.client.guis.GuiComponentRepair;
import net.tardis.mod.client.guis.GuiWorkbenchSonic;
import net.tardis.mod.common.containers.ContainerCR;
import net.tardis.mod.common.containers.ContainerWorkbenchSonic;
import net.tardis.mod.common.tileentity.TileEntitySonicWorkbench;

public class GuiHandlerTardis implements IGuiHandler {

	public static final int REPAIR_GUI = 0;
	public static final int SONIC_WORKBENCH_GUI	= 1;

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		if(ID == SONIC_WORKBENCH_GUI)
			return new ContainerWorkbenchSonic(player.inventory, (TileEntitySonicWorkbench)world.getTileEntity(new BlockPos(x, y, z)));
		return new ContainerCR(player.inventory, (IInventory) world.getTileEntity(new BlockPos(x, y, z)));
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		if(ID == SONIC_WORKBENCH_GUI)
			return new GuiWorkbenchSonic(new ContainerWorkbenchSonic(player.inventory, (TileEntitySonicWorkbench)world.getTileEntity(new BlockPos(x, y, z))), new BlockPos(x, y, z));
		return new GuiComponentRepair(new ContainerCR(player.inventory, (IInventory) world.getTileEntity(new BlockPos(x, y, z))));
	}

}
