package net.tardis.mod.client.guis;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.BossInfo.Color;
import net.tardis.mod.client.guis.elements.ButtonText;
import net.tardis.mod.common.entities.EntityCompanion;
import net.tardis.mod.common.strings.TStrings;
import net.tardis.mod.network.NetworkHandler;
import net.tardis.mod.network.packets.MessageCompanion;

import java.io.IOException;

public class GUICompanion extends GuiScreen {

	int entityID;
	EntityCompanion comp;
	
	public GUICompanion(EntityCompanion comp) {
		this.entityID = comp.getEntityId();
		this.comp = comp;
	}
	
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		this.drawDefaultBackground();
		super.drawScreen(mouseX, mouseY, partialTicks);
		GlStateManager.color(1, 1, 1);
		this.drawString(Minecraft.getMinecraft().fontRenderer, "XP: " + comp.getXP(), 0, 0, Color.RED.ordinal());
	}

	@Override
	protected void actionPerformed(GuiButton button) throws IOException {
		int id = button.id;
		EnumAction action = null;
		if(id == 0) action = EnumAction.FOLLOW;
		else if(id == 1) action = EnumAction.GO_TO_TARDIS;
		else if(id == 2) action = EnumAction.BRING_TARDIS;
		if(action != null) {
			NetworkHandler.NETWORK.sendToServer(new MessageCompanion(entityID, action));
			Minecraft.getMinecraft().displayGuiScreen(null);
		}
		super.actionPerformed(button);
	}
	
	int id = 0;
	
	@Override
	public void initGui() {
		super.initGui();
		this.buttonList.clear();
		id = 0;
		this.addText(new TextComponentTranslation(TStrings.Companions.FOLLOW + ((EntityCompanion)Minecraft.getMinecraft().world.getEntityByID(entityID)).getSit()).getFormattedText());
		this.addText(new TextComponentTranslation(TStrings.Companions.GO_TO_TARDIS).getFormattedText());
		if(((EntityCompanion)Minecraft.getMinecraft().world.getEntityByID(entityID)).getXP() > 0)this.addText(new TextComponentTranslation(TStrings.Companions.BRING_TARDIS).getFormattedText());
		id = 0;
	}

	FontRenderer fr = Minecraft.getMinecraft().fontRenderer;
	ScaledResolution res = new ScaledResolution(Minecraft.getMinecraft());
	
	private void addText(String text) {
		int nid = id;
		this.addButton(new ButtonText(nid, res.getScaledWidth() / 2, res.getScaledHeight() / 2 + ((Minecraft.getMinecraft().fontRenderer.FONT_HEIGHT * 2) * nid), Minecraft.getMinecraft().fontRenderer, text));
		id++;
	}
	
	@Override
	public boolean doesGuiPauseGame() {
		return false;
	}

	public enum EnumAction {
		FOLLOW,
		GO_TO_TARDIS,
		BRING_TARDIS
	}
}
