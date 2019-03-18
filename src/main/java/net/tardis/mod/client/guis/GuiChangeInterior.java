package net.tardis.mod.client.guis;

import java.io.IOException;

import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextComponentTranslation;
import net.tardis.mod.common.systems.TardisSystems;
import net.tardis.mod.network.packets.MessageDamageSystem;
import net.tardis.mod.network.packets.MessageSpawnItem;
import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.ResourceLocation;
import net.tardis.mod.Tardis;
import net.tardis.mod.client.guis.GuiCCircuit.ChameleonButton;
import net.tardis.mod.client.guis.elements.MonitorButton;
import net.tardis.mod.common.ars.ConsoleRoom;
import net.tardis.mod.common.tileentity.TileEntityTardis;
import net.tardis.mod.network.NetworkHandler;
import net.tardis.mod.network.packets.MessageChangeInterior;

public class GuiChangeInterior extends GuiScreen {

	public static final ResourceLocation TEXTURE = new ResourceLocation(Tardis.MODID, "textures/gui/monitor_ui.png");
	TileEntityTardis tardis;
	FontRenderer fr;
	GuiButton select;
	GuiButton next;
	GuiButton prev;
	int index = 0;
	
	public GuiChangeInterior(TileEntityTardis tardis) {
		this.tardis = tardis;
		fr = Minecraft.getMinecraft().fontRenderer;
	}

	@Override
	public void initGui() {
		super.initGui();
		this.addButton(select = new MonitorButton(0, 0, height / 2 + 50, "Select"));
		select.x = width / 2 - select.width / 2;
		this.addButton(next = new ChameleonButton(1, width / 2 + 50, height / 2 + 50, ">"));
		this.addButton(prev = new ChameleonButton(1, width / 2 - 75, height / 2 + 50, "<"));
	}
	
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		if(fr != null) {
			this.drawDefaultBackground();
			Minecraft.getMinecraft().getTextureManager().bindTexture(TEXTURE);
			this.drawTexturedModalRect(width / 2 - 255 / 2, height / 2 - 192 / 2, 0, 0, 255, 192);
			fr.drawStringWithShadow("Interiors", width / 2 - fr.getStringWidth("Interiors") / 2, height / 2 - 85, GuiCCircuit.BOX_NAME_COLOR);
			GlStateManager.color(1, 1, 1);
			super.drawScreen(mouseX, mouseY, partialTicks);
			GlStateManager.color(1, 1, 1);
			if(!ConsoleRoom.CONSOLE_ROOMS.isEmpty() && index < ConsoleRoom.CONSOLE_ROOMS.size()) {
				Minecraft.getMinecraft().getTextureManager().bindTexture(ConsoleRoom.CONSOLE_ROOMS.get(index).getPreview());
				BufferBuilder bb = Tessellator.getInstance().getBuffer();
				bb.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_TEX);
				double minX = width / 2 - 100, maxX = minX + 200, minY = height / 2 - 70, maxY = minY + 117;
				bb.pos(minX, minY, 0).tex(0, 0).endVertex();
				bb.pos(minX, maxY, 0).tex(0, 1).endVertex();
				bb.pos(maxX, maxY, 0).tex(1, 1).endVertex();
				bb.pos(maxX, minY, 0).tex(1, 0).endVertex();
				Tessellator.getInstance().draw();
			}
		}
	}

	@Override
	protected void actionPerformed(GuiButton button) throws IOException {
		super.actionPerformed(button);
		if(button == next) {
			if(index < ConsoleRoom.CONSOLE_ROOMS.size() - 1) {
				++index;
			}
			else index = 0;
		}
		
		if(button == this.select) {
			ask();
		}
		if(button == this.prev) {
			if(index > 0)
				--index;
			else index = ConsoleRoom.CONSOLE_ROOMS.size() - 1;
		}
	}
	
	public void ask(){
		Minecraft.getMinecraft().displayGuiScreen(new GUIConfirm((result, id) -> {
			if (result) {
				NetworkHandler.NETWORK.sendToServer(new MessageChangeInterior(this.index, tardis.getPos()));
				Minecraft.getMinecraft().displayGuiScreen(null);
			} else {
				Minecraft.getMinecraft().displayGuiScreen(this);
			}
			
		}, I18n.format("Are you sure you want to Generate this interior??"), "This can be very damaging if you are not prepared!", I18n.format("Yes!"), I18n.format("gui.cancel"), 0));
	}
}
