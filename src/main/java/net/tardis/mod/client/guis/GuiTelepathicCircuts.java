package net.tardis.mod.client.guis;

import java.io.IOException;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.util.math.BlockPos;
import net.tardis.mod.Tardis;
import net.tardis.mod.packets.MessageTelepathicCircut;

public class GuiTelepathicCircuts extends GuiScreen {
	
	public GuiTextField name;
	public BlockPos pos = BlockPos.ORIGIN;
	Minecraft mc;
	
	public GuiTelepathicCircuts() {
		mc = Minecraft.getMinecraft();
	}
	
	public GuiTelepathicCircuts(BlockPos pos) {
		this();
		this.pos = pos;
	}

	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		super.drawScreen(mouseX, mouseY, partialTicks);
		this.drawDefaultBackground();
		name.drawTextBox();
	}

	@Override
	public void initGui() {
		super.initGui();
		int textWidth = width / 3;
		name = new GuiTextField(0, mc.fontRenderer, (width - textWidth) / 2, (height - mc.fontRenderer.FONT_HEIGHT) / 2, textWidth, mc.fontRenderer.FONT_HEIGHT*2);
		name.setFocused(true);
	}

	@Override
	public void updateScreen() {
		super.updateScreen();
		name.updateCursorCounter();
	}

	@Override
	protected void keyTyped(char typedChar, int keyCode) throws IOException {
		super.keyTyped(typedChar, keyCode);
		if(keyCode != 28) {
			name.textboxKeyTyped(typedChar, keyCode);
		}
		else {
			Tardis.NETWORK.sendToServer(new MessageTelepathicCircut(this.pos, name.getText()));
			Minecraft.getMinecraft().displayGuiScreen(null);
		}
	}

}
