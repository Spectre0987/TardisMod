package net.tardis.mod.client.guis;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiYesNo;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;
import net.tardis.mod.Tardis;
import net.tardis.mod.client.guis.elements.ButtonText;
import net.tardis.mod.common.protocols.ITardisProtocol;
import net.tardis.mod.common.protocols.ProtocolRegenRoom;
import net.tardis.mod.common.protocols.TardisProtocol;
import net.tardis.mod.common.tileentity.TileEntityTardis;
import net.tardis.mod.packets.MessageProtocol;

import java.io.IOException;

public class GuiProtocol extends GuiScreen {
	
	public BlockPos pos = BlockPos.ORIGIN;
	
	static final int GUI_WIDTH = 248;
	static final int GUI_HEIGHT = 167;
	
	public static final ResourceLocation TEXTURE = new ResourceLocation(Tardis.MODID, "textures/gui/monitor.png");
	
	public GuiProtocol() {}
	
	public GuiProtocol(BlockPos pos) {
		this.pos = pos;
	}
	
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		mc.getTextureManager().bindTexture(TEXTURE);
		int x = (width - 248) / 2;
		int y = (height - 167) /2;
		this.drawTexturedModalRect(x, y, 0, 0, GUI_WIDTH, GUI_HEIGHT);
		for (GuiButton b : this.buttonList) {
			b.drawButton(mc, mouseX, mouseY, partialTicks);
		}
	}
	
	@Override
	public void initGui() {
		this.buttonList.clear();
		int cw = width / 2 - GUI_WIDTH / 2;
		int ch = height / 2 - GUI_HEIGHT / 2;
		int id = 0;
		for (ITardisProtocol p : TardisProtocol.protocols.values()) {
			this.addButton(new ButtonText(id++, width / 2, (height / 2 - GUI_HEIGHT / 2) + mc.fontRenderer.FONT_HEIGHT * id, mc.fontRenderer, (new TextComponentTranslation(p.getNameKey()).getFormattedText())));
		}
	}
	
	@Override
	protected void actionPerformed(GuiButton button) throws IOException {
		Minecraft.getMinecraft().displayGuiScreen(null);

		if (TardisProtocol.getProtocolFromId(button.id) instanceof ProtocolRegenRoom) {
			regen(button.id);

		} else {
			Tardis.NETWORK.sendToServer(new MessageProtocol(pos, button.id));
			TardisProtocol.getProtocolFromId(button.id).onActivated(mc.world, (TileEntityTardis) mc.world.getTileEntity(pos));
		}
		super.actionPerformed(button);
	}
	
	@Override
	public boolean doesGuiPauseGame() {
		return false;
	}


	public void regen(int button) {
		Minecraft.getMinecraft().displayGuiScreen(new GuiYesNo((result, id) -> {
			if (result) {
				Tardis.NETWORK.sendToServer(new MessageProtocol(pos, button));
				Minecraft.getMinecraft().displayGuiScreen(null);
			}
			Minecraft.getMinecraft().displayGuiScreen(null);


		}, I18n.format("Are you sure you want to regenerate your interior?"), "This will completely wipe anything you have placed", I18n.format("Regenerate Interior"), I18n.format("gui.cancel"), 0));
	}

}
