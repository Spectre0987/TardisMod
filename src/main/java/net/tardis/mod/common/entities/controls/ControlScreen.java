package net.tardis.mod.common.entities.controls;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tardis.mod.client.guis.GuiProtocol;
import net.tardis.mod.common.tileentity.TileEntityTardis;
import net.tardis.mod.util.helpers.Helper;

public class ControlScreen extends EntityControl {
	
	public ControlScreen(TileEntityTardis tardis) {
		super(tardis);
	}
	
	public ControlScreen(World world) {
		super(world);
	}
	
	@Override
	protected void entityInit() {
		super.entityInit();
	}
	
	@Override
	public Vec3d getOffset(TileEntityTardis tardis) {
		return Helper.convertToPixels(101, 8, 0);
	}
	
	@Override
	public void preformAction(EntityPlayer player) {
		if (world.isRemote) {
			openGui();
		}
	}
	
	@SideOnly(Side.CLIENT)
	public void openGui() {
		Minecraft.getMinecraft().displayGuiScreen(new GuiProtocol(this.getConsolePos()));
	}
}
