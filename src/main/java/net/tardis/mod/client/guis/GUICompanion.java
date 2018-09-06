package net.tardis.mod.client.guis;

import java.io.IOException;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.util.text.TextComponentTranslation;
import net.tardis.mod.Tardis;
import net.tardis.mod.client.guis.elements.ButtonText;
import net.tardis.mod.common.entities.EntityCompanion;
import net.tardis.mod.common.strings.TStrings;
import net.tardis.mod.packets.MessageCompanion;

public class GUICompanion extends GuiScreen {

	int entityID = 0;
	
	public GUICompanion(int id) {
		this.entityID = id;
	}
	
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		this.drawDefaultBackground();
		super.drawScreen(mouseX, mouseY, partialTicks);
	}

	@Override
	protected void actionPerformed(GuiButton button) throws IOException {
		int id = button.id;
		EnumAction action = null;
		switch(id) {
		case 0 : {
			action = EnumAction.FOLLOW;
			break;
		}
		case 1: {
			action = EnumAction.GO_TO_TARDIS;
			break;
		}
		default:;
		}
		System.out.println(action);
		if(action != null) {
			Tardis.NETWORK.sendToServer(new MessageCompanion(entityID, action));
			Minecraft.getMinecraft().displayGuiScreen(null);
		}
		super.actionPerformed(button);
	}

	@Override
	public void initGui() {
		super.initGui();
		this.buttonList.clear();
		this.addText(new TextComponentTranslation(TStrings.Companions.FOLLOW + ((EntityCompanion)Minecraft.getMinecraft().world.getEntityByID(entityID)).getSit()).getFormattedText());
		this.addText(new TextComponentTranslation(TStrings.Companions.GO_TO_TARDIS).getFormattedText());
	}

	int id = 0;
	FontRenderer fr = Minecraft.getMinecraft().fontRenderer;
	ScaledResolution res = new ScaledResolution(Minecraft.getMinecraft());
	
	private void addText(String text) {
		this.addButton(new ButtonText(0, res.getScaledWidth() / 2, res.getScaledHeight() - ((Minecraft.getMinecraft().fontRenderer.FONT_HEIGHT * 2) * ++id), Minecraft.getMinecraft().fontRenderer, text));
	}
	
	@Override
	public boolean doesGuiPauseGame() {
		return false;
	}

	public static enum EnumAction{
		FOLLOW,
		GO_TO_TARDIS;
	}
}
