package net.tardis.mod.guis;

import java.io.IOException;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.util.math.BlockPos;
import net.tardis.mod.Tardis;
import net.tardis.mod.packets.MessageTeleport;

public class GuiVortexM extends GuiScreen{

	public GuiTextField xCoord;
	public GuiTextField yCoord;
	public GuiTextField zCoord;
	private GuiButton teleport;
	
	private Minecraft mc;
	private FontRenderer fr;
	
	public GuiVortexM(){
		mc=Minecraft.getMinecraft();
		fr=mc.fontRenderer;
	}
	
	@Override
	protected void actionPerformed(GuiButton button) throws IOException {
		if(button==this.teleport) {
			Tardis.packet_instance.sendToServer(new MessageTeleport(new BlockPos(Integer.parseInt(xCoord.getText()),Integer.parseInt(yCoord.getText()),Integer.parseInt(zCoord.getText())),mc.player.getEntityId()));
		}
		super.actionPerformed(button);
	}
	@Override
	public void initGui() {
		xCoord=new GuiTextField(0,fr,width/2,height/2,30,fr.FONT_HEIGHT);
		yCoord=new GuiTextField(1,fr,width/2,(height/2)+fr.FONT_HEIGHT+1,30,fr.FONT_HEIGHT);
		zCoord=new GuiTextField(2,fr,width/2,(height/2)+fr.FONT_HEIGHT*2+1,30,fr.FONT_HEIGHT);
		teleport=new GuiButton(3,width/2,(height/2)+fr.FONT_HEIGHT*3+5,"Teleport");
		xCoord.setFocused(true);
		
		this.buttonList.clear();
		this.addButton(teleport);
	}

	@Override
	protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
		xCoord.mouseClicked(mouseX, mouseY, mouseButton);
		yCoord.mouseClicked(mouseX, mouseY, mouseButton);
		zCoord.mouseClicked(mouseX, mouseY, mouseButton);
		super.mouseClicked(mouseX, mouseY, mouseButton);
	}

	@Override
	public boolean doesGuiPauseGame() {
		return false;
	}
	
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		xCoord.drawTextBox();
		yCoord.drawTextBox();
		zCoord.drawTextBox();
		super.drawScreen(mouseX, mouseY, partialTicks);
	}
	
	@Override
	protected void keyTyped(char typedChar, int keyCode) throws IOException {
		xCoord.textboxKeyTyped(typedChar, keyCode);
		yCoord.textboxKeyTyped(typedChar, keyCode);
		zCoord.textboxKeyTyped(typedChar, keyCode);
		super.keyTyped(typedChar, keyCode);
	}

	@Override
	public void updateScreen() {
		xCoord.updateCursorCounter();
		yCoord.updateCursorCounter();
		zCoord.updateCursorCounter();
		super.updateScreen();
	}
}
