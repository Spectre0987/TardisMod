package net.tardis.mod.client.guis;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;
import net.tardis.mod.Tardis;
import net.tardis.mod.client.guis.elements.ButtonText;
import net.tardis.mod.common.protocols.ITardisProtocol;
import net.tardis.mod.common.protocols.TardisProtocol;
import net.tardis.mod.common.tileentity.TileEntityTardis;
import net.tardis.mod.packets.MessageProtocol;
import net.tardis.mod.packets.NetworkHandler;

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
        for (ITardisProtocol p : TardisProtocol.PROTOCOLS.values()) {
			this.addButton(new ButtonText(id++, width / 2, (height / 2 - GUI_HEIGHT / 2) + mc.fontRenderer.FONT_HEIGHT * id, mc.fontRenderer, (new TextComponentTranslation(p.getNameKey()).getFormattedText())));
		}
	}
	
	@Override
	protected void actionPerformed(GuiButton button) throws IOException {
		Minecraft.getMinecraft().displayGuiScreen(null);
        NetworkHandler.NETWORK.sendToServer(new MessageProtocol(pos, button.id));
		TardisProtocol.getProtocolFromId(button.id).onActivated(mc.world, (TileEntityTardis)mc.world.getTileEntity(pos));
		super.actionPerformed(button);
	}
	
	@Override
	public boolean doesGuiPauseGame() {
		return false;
	}
	
}
