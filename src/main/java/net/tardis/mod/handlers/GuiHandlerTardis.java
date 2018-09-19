package net.tardis.mod.handlers;

import net.minecraft.client.gui.inventory.GuiChest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ContainerChest;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;
import net.tardis.mod.client.guis.GuiComponentRepair;
import net.tardis.mod.common.containers.ContainerCR;

public class GuiHandlerTardis implements IGuiHandler {

	public static final int REPAIR_GUI = 0;

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		return new ContainerCR(player.inventory, (IInventory)world.getTileEntity(new BlockPos(x, y, z)));
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		return new GuiComponentRepair(new ContainerCR(player.inventory, (IInventory)world.getTileEntity(new BlockPos(x, y, z))));
	}

}
