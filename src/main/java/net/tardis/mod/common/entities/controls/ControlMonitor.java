package net.tardis.mod.common.entities.controls;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tardis.mod.client.guis.GuiProtocol;
import net.tardis.mod.common.tileentity.TileEntityTardis;
import net.tardis.mod.util.common.helpers.Helper;

public class ControlMonitor extends EntityControl{

	public ControlMonitor(TileEntityTardis tardis) {
		super(tardis);
	}
	
	public ControlMonitor(World world) {
		super(world);
		this.setSize(0.125F, 0.125F);
	}

	@Override
	public Vec3d getOffset(TileEntityTardis tardis) {
		return Helper.convertToPixels(11, 2, -7);
	}

	@Override
	public void preformAction(EntityPlayer player) {
		if(world.isRemote)
			opem();
	}
	
	@SideOnly(Side.CLIENT)
	public void opem() {
		Minecraft.getMinecraft().displayGuiScreen(new GuiProtocol(this.getConsolePos()));
	}
	
	@Override
	public void init(TileEntityTardis tardis) {
		if(tardis != null) {
			this.setSize(Helper.precentToPixels(4F), Helper.precentToPixels(2F));
		}
	}

}
