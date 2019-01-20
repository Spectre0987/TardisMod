package net.tardis.mod.client.guis;

import com.google.common.collect.Lists;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiYesNoCallback;
import net.minecraft.util.ResourceLocation;
import net.tardis.mod.Tardis;
import net.tardis.mod.client.guis.elements.MonitorButton;

import java.io.IOException;
import java.util.List;

public class GUIConfirm extends GuiScreen {

	public static final ResourceLocation TEXTURE = new ResourceLocation(Tardis.MODID, "textures/gui/monitor_ui.png");
	static final int GUI_WIDTH = 256;
	static final int GUI_HEIGHT = 192;
	private final String messageLine2;
	private final List<String> listLines = Lists.newArrayList();
	protected GuiYesNoCallback parentScreen;
	protected String messageLine1;
	protected String confirmButtonText;
	protected String cancelButtonText;
	protected int parentButtonClickedId;

	public GUIConfirm(GuiYesNoCallback parentScreenIn, String messageLine1In, String messageLine2In, String confirmButtonTextIn, String cancelButtonTextIn, int parentButtonClickedIdIn) {
		this.parentScreen = parentScreenIn;
		this.messageLine1 = messageLine1In;
		this.messageLine2 = messageLine2In;
		this.confirmButtonText = confirmButtonTextIn;
		this.cancelButtonText = cancelButtonTextIn;
		this.parentButtonClickedId = parentButtonClickedIdIn;
	}

	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		this.drawDefaultBackground();
		mc.getTextureManager().bindTexture(TEXTURE);
		int x = (width - GUI_WIDTH) / 2;
		int y = (height - GUI_HEIGHT) / 2;
		this.drawTexturedModalRect(x, y, 0, 0, GUI_WIDTH, GUI_HEIGHT);
		int i = 20;
		for (String s : this.listLines) {
			this.drawCenteredString(this.fontRenderer, s, this.width / 2, y + i, 16777215);
			i += this.fontRenderer.FONT_HEIGHT;
		}
		super.drawScreen(mouseX, mouseY, partialTicks);
	}

	@Override
	public void initGui() {

		int posX = (width - GUI_WIDTH) / 2;
		int posY = (height - GUI_HEIGHT) / 2;

		this.buttonList.clear();
		this.buttonList.add(new MonitorButton(0, posX + 16, posY + 149, this.confirmButtonText));
		this.buttonList.add(new MonitorButton(1, posX + 165, posY + 149, this.cancelButtonText));
		this.listLines.clear();
		this.listLines.addAll(this.fontRenderer.listFormattedStringToWidth(this.messageLine1, GUI_WIDTH - 20));
		this.listLines.addAll(this.fontRenderer.listFormattedStringToWidth(this.messageLine2, GUI_WIDTH - 20));

	}

	protected void actionPerformed(GuiButton button) throws IOException {
		this.parentScreen.confirmClicked(button.id == 0, this.parentButtonClickedId);
	}

}
