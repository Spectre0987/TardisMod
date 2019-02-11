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
import net.tardis.mod.common.tileentity.consoles.TileEntityTardis03;
import net.tardis.mod.util.common.helpers.Helper;

public class ControlTelepathicCircuts extends EntityControl {

	public ControlTelepathicCircuts(TileEntityTardis tardis) {
		super(tardis);
		this.setSize(0.125F, 0.125F);
	}

	public ControlTelepathicCircuts(World world) {
		super(world);
		this.setSize(0.125F, 0.125F);
	}

	@Override
	public Vec3d getOffset(TileEntityTardis tardis) {
		if (tardis.getClass() == TileEntityTardis01.class || tardis.getClass() == TileEntityTardis02.class) {
			return Helper.convertToPixels(0, -2, -10);
		}
		if (tardis instanceof TileEntityTardis03)
			return Helper.convertToPixels(8.5, 2, 5.5);

		return Helper.convertToPixels(10, -2, -6);
	}

	@Override
	public void preformAction(EntityPlayer player) {
		if (world.isRemote) {
			openGui();
		}
	}

	@SideOnly(Side.CLIENT)
	public void openGui() {
		Minecraft.getMinecraft().displayGuiScreen(new GuiTelepathicCircuts(this.getConsolePos()));
	}

	@Override
	public void init(TileEntityTardis tardis) {
		if (tardis != null) {
			if (tardis instanceof TileEntityTardis03)
				setSize(Helper.precentToPixels(8F), Helper.precentToPixels(4F));
			if (tardis.getClass() == TileEntityTardis.class)
				this.setSize(Helper.precentToPixels(4F), Helper.precentToPixels(2F));
		}
	}

}
