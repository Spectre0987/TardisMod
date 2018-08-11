package net.tardis.mod.common.entities.controls;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tardis.mod.client.guis.GuiTelepathicCircuts;
import net.tardis.mod.common.tileentity.TileEntityTardis;
import net.tardis.mod.common.tileentity.consoles.TileEntityTardis01;
import net.tardis.mod.common.tileentity.consoles.TileEntityTardis02;
import net.tardis.mod.util.helpers.Helper;

public class ControlTelepathicCircuts extends EntityControl{
	
	public ControlTelepathicCircuts(TileEntityTardis tardis) {
		super(tardis);
		if(tardis.getClass() == TileEntityTardis01.class) {
			this.setSize(12F, 0.125F);
		}
		else setSize(0.0625F, 0.0625F);
	}
	
	public ControlTelepathicCircuts(World world) {
		super(world);
		this.setSize(0.125F, 0.125F);
	}

	@Override
	public Vec3d getOffset(TileEntityTardis tardis) {
		if(tardis.getClass() == TileEntityTardis01.class || tardis.getClass() == TileEntityTardis02.class) {
			return Helper.convertToPixels(0, -2, -10);
		}
		return Helper.convertToPixels(10, -2,-6);
	}

	@Override
	public void preformAction(EntityPlayer player) {
		if(world.isRemote) {
			openGui();
		}
	}
	
	@SideOnly(Side.CLIENT)
	public void openGui() {
		Minecraft.getMinecraft().displayGuiScreen(new GuiTelepathicCircuts(this.getConsolePos()));
	}

}
