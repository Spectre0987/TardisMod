package net.tardis.mod.common.entities.controls;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tardis.mod.client.guis.GuiTelepathicCircuts;
import net.tardis.mod.common.items.ItemManual;
import net.tardis.mod.common.strings.TStrings;
import net.tardis.mod.common.tileentity.TileEntityTardis;
import net.tardis.mod.util.helpers.Helper;
import net.tardis.mod.util.helpers.PlayerHelper;

public class ControlTelepathicCircuts extends EntityControl{
	
	public ControlTelepathicCircuts(TileEntityTardis tardis) {
		super(tardis);
	}
	
	public ControlTelepathicCircuts(World world) {
		super(world);
	}

	@Override
	public Vec3d getOffset() {
		return Helper.convertToPixels(10,-6,-6);
	}

	@Override
	public void preformAction(EntityPlayer player) {
		if(world.isRemote) {
			openGui();
		}
		else if (player.getHeldItem(EnumHand.MAIN_HAND).getItem()instanceof ItemManual){
			PlayerHelper.sendMessage(player, new TextComponentTranslation(TStrings.Manual.TELEPATHIC).getFormattedText(), true);
		}
	}
	
	@SideOnly(Side.CLIENT)
	public void openGui() {
		Minecraft.getMinecraft().displayGuiScreen(new GuiTelepathicCircuts(this.getConsolePos()));
	}

}
