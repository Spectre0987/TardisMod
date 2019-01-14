package net.tardis.mod.client.guis;

import java.io.IOException;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiYesNo;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;
import net.tardis.mod.Tardis;
import net.tardis.mod.common.protocols.ITardisProtocol;
import net.tardis.mod.common.protocols.ProtocolRegenRoom;
import net.tardis.mod.common.protocols.TardisProtocol;
import net.tardis.mod.common.tileentity.TileEntityTardis;
import net.tardis.mod.network.NetworkHandler;
import net.tardis.mod.network.packets.MessageProtocol;

public class GuiMonitor extends GuiScreen {
	
	public BlockPos pos = BlockPos.ORIGIN;
	
	static final int GUI_WIDTH = 256;
	static final int GUI_HEIGHT = 192;
	
	public static final ResourceLocation TEXTURE = new ResourceLocation(Tardis.MODID, "textures/gui/monitor_ui.png");
	
	public GuiMonitor(BlockPos pos) {
		this.pos = pos;
	}
	
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		mc.getTextureManager().bindTexture(TEXTURE);
		int x = (width - GUI_WIDTH) / 2;
		int y = (height - GUI_HEIGHT) / 2;
		this.drawTexturedModalRect(x, y, 0, 0, GUI_WIDTH, GUI_HEIGHT);
		super.drawScreen(mouseX, mouseY, partialTicks);
	}
	
	@Override
	public void initGui() {
		this.buttonList.clear();
		int x_change = 0;
		int y_change = 0;
		int id = 0;
		for (ITardisProtocol p : TardisProtocol.protocols.values()) {
			this.addButton(new MonitorButton(id, ((width - GUI_WIDTH) / 2) + 11 + x_change, ((height - GUI_HEIGHT) / 2) + 8 + y_change, new TextComponentTranslation(p.getNameKey()).getFormattedText()));
			id++;
			x_change += 80;
			if(id % 3 == 0){
				x_change = 0;
				y_change += 36;
			}
		}
	}
	
	@Override
	protected void actionPerformed(GuiButton button) throws IOException {
		Minecraft.getMinecraft().displayGuiScreen(null);

		if (TardisProtocol.getProtocolFromId(button.id) instanceof ProtocolRegenRoom) {
			regen(button.id);

		} else {
			NetworkHandler.NETWORK.sendToServer(new MessageProtocol(pos, button.id));
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
				NetworkHandler.NETWORK.sendToServer(new MessageProtocol(pos, button));
				Minecraft.getMinecraft().displayGuiScreen(null);
			}
			Minecraft.getMinecraft().displayGuiScreen(null);


		}, I18n.format("Are you sure you want to regenerate your interior?"), "This will completely wipe anything you have placed", I18n.format("Regenerate Interior"), I18n.format("gui.cancel"), 0));
	}
	
	public class MonitorButton extends GuiButton {
		
		ResourceLocation TEXTURE = new ResourceLocation(Tardis.MODID, "textures/gui/monitor_ui.png");
		int BUTTON_WIDTH = 75;
		int BUTTON_HEIGHT = 32;
		int colour = 0x00938F;
		float scale;
		
		public MonitorButton(int buttonId, int x, int y, String buttonText) {
			super(buttonId, x, y, x, 32, buttonText);
		}
		
		@Override
		public void drawButton(Minecraft mc, int mouseX, int mouseY, float partialTicks) {
			if(visible) {
				FontRenderer fontRenderer = mc.fontRenderer;
				if(fontRenderer.getStringWidth(this.displayString) > 70) {
					scale = (float) 70 / fontRenderer.getStringWidth(this.displayString);
				} else {
					scale = 1;
				}
				mc.getTextureManager().bindTexture(TEXTURE);
				if(mouseX >= this.x && mouseX <= this.x + BUTTON_WIDTH && mouseY >= this.y && mouseY <= this.y + BUTTON_HEIGHT) {
					hovered = true;
					colour = 0xCE8F23;
				} else {
					hovered = false;
					colour = 0x00938F;
				}
				
				if(hovered){
					this.drawTexturedModalRect(this.x, this.y, 108, 209, BUTTON_WIDTH, BUTTON_HEIGHT);
				} else {
					this.drawTexturedModalRect(this.x, this.y, 7, 209, BUTTON_WIDTH, BUTTON_HEIGHT);
				}
				GlStateManager.pushMatrix();
				GlStateManager.scale(scale, scale, 1);
				this.drawCenteredString(fontRenderer, this.displayString, (int) ((1 / scale) * (this.x + 37)), (int) ((1 / scale) * (this.y + 16)), colour);
				GlStateManager.color(1, 1, 1);
				GlStateManager.scale(1, 1, 1);
				GlStateManager.popMatrix();
			}
		}
		
	}
}
