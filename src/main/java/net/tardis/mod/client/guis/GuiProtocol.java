package net.tardis.mod.client.guis;

import java.io.IOException;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;
import net.tardis.api.protocols.ITardisProtocol;
import net.tardis.api.protocols.TardisProtocol;
import net.tardis.mod.Tardis;
import net.tardis.mod.client.guis.elements.ButtonText;
import net.tardis.mod.packets.MessageProtocol;

public class GuiProtocol extends GuiScreen {
	
	public BlockPos pos = BlockPos.ORIGIN;
	
	static final int GUI_WIDTH = 228;
	static final int GUI_HEIGHT = 136;
	
	public static final ResourceLocation TEXTURE = new ResourceLocation(Tardis.MODID, "textures/gui/screen.png");
	
	public GuiProtocol() {}
	
	public GuiProtocol(BlockPos pos) {
		this.pos = pos;
	}
	
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		mc.getTextureManager().bindTexture(TEXTURE);
		this.drawTexturedModalRect(width / 2 - GUI_WIDTH / 2, height / 2 - GUI_HEIGHT / 2, 0, 0, GUI_WIDTH, GUI_HEIGHT);
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
			this.addButton(new ButtonText(id++, width / 2, height / 2 - (GUI_HEIGHT / 2) + mc.fontRenderer.FONT_HEIGHT, mc.fontRenderer, (new TextComponentTranslation(p.getNameKey()).getFormattedText())));
		}
	}
	
	@Override
	protected void actionPerformed(GuiButton button) throws IOException {
		Tardis.NETWORK.sendToServer(new MessageProtocol(pos, button.id));
		super.actionPerformed(button);
	}
	
	@Override
	public boolean doesGuiPauseGame() {
		return false;
	}
	
}
