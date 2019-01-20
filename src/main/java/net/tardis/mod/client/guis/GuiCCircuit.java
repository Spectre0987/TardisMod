package net.tardis.mod.client.guis;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;
import net.tardis.mod.Tardis;
import net.tardis.mod.client.EnumExterior;
import net.tardis.mod.client.guis.elements.MonitorButton;
import net.tardis.mod.network.NetworkHandler;
import net.tardis.mod.network.packets.MessageExteriorChange;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

import java.awt.*;
import java.io.IOException;

public class GuiCCircuit extends GuiScreen {

	static final int GUI_WIDTH = 256;
	static final int GUI_HEIGHT = 192;
	private static final int BOX_NAME_COLOR = new Color((float) 111 / 255, (float) 111 / 255, (float) 111 / 255).getRGB();
	private static final String title = "Chameleon Circuit";
	public static EnumExterior[] exteriors = new EnumExterior[]{EnumExterior.FIRST, EnumExterior.SECOND, EnumExterior.THIRD, EnumExterior.FIFTH, EnumExterior.FOURTH, EnumExterior.CLOCK, EnumExterior.TT, EnumExterior.WOOD_DOOR, EnumExterior.CC};
	public BlockPos pos = BlockPos.ORIGIN;
	private ResourceLocation TEXTURE = new ResourceLocation(Tardis.MODID, "textures/gui/chameleon_ui.png");
	private GuiButton selectButton;
	private GuiButton nextArrow;
	private GuiButton prevArrow;
	private int index = 0;


	public GuiCCircuit(BlockPos pos) {
		this.pos = pos.toImmutable();
	}

	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		int rotX = 30 + (mouseY - height / 2);
		int rotY = 45 + (mouseX - width / 2);
		drawDefaultBackground();
		mc.getTextureManager().bindTexture(TEXTURE);
		int x = (width - GUI_WIDTH) / 2;
		int y = (height - GUI_HEIGHT) / 2;
		ScaledResolution res = new ScaledResolution(Minecraft.getMinecraft());
		this.drawTexturedModalRect(x, y, 0, 0, GUI_WIDTH, GUI_HEIGHT);
		GlStateManager.pushMatrix();
		mc.fontRenderer.drawStringWithShadow(title, res.getScaledWidth() / 2 - mc.fontRenderer.getStringWidth(title) / 2, (res.getScaledHeight() / 2 - GUI_HEIGHT / 2) + mc.fontRenderer.FONT_HEIGHT + 10, BOX_NAME_COLOR);
		String boxName = new TextComponentTranslation(exteriors[index].name).getFormattedText();
		mc.fontRenderer.drawStringWithShadow(boxName, res.getScaledWidth() / 2 - mc.fontRenderer.getStringWidth(boxName) / 2, (res.getScaledHeight() / 2 - GUI_HEIGHT / 2) + mc.fontRenderer.FONT_HEIGHT * 2.3F + 10, BOX_NAME_COLOR);
		GlStateManager.color(1F, 1F, 1F);
		GlStateManager.popMatrix();
		{

			GlStateManager.pushMatrix();
			GlStateManager.translate(res.getScaledWidth() / 2, res.getScaledHeight() / 2, -20);
			GlStateManager.scale(25, 25, 25);
			if (!isMouseDown()) {
				GlStateManager.rotate(mc.world.getTotalWorldTime() % 360, 0, 1, 0);
			} else {
				GlStateManager.translate(0, 0, -2);
				GlStateManager.rotate(rotX, 1, 0, 0);
				GlStateManager.rotate(rotY, 0, 1, 0);
			}
			GL11.glDepthFunc(GL11.GL_GREATER);
			RenderHelper.disableStandardItemLighting();
			EnumExterior ext = exteriors[index];
			mc.getTextureManager().bindTexture(ext.tex);
			ext.model.renderClosed(0.0625F);
			GL11.glDepthFunc(GL11.GL_LEQUAL);
			GlStateManager.popMatrix();
		}

		super.drawScreen(mouseX, mouseY, partialTicks);
	}

	private boolean isMouseDown() {
		boolean mouse = Mouse.isButtonDown(0);
		boolean bloodyButtons = buttonList.get(0).isMouseOver() || buttonList.get(1).isMouseOver() || buttonList.get(2).isMouseOver();
		return mouse && !bloodyButtons;
	}

	@Override
	public void initGui() {
		this.buttonList.clear();

		int posX = (width - GUI_WIDTH) / 2;
		int posY = (height - GUI_HEIGHT) / 2;

		this.selectButton = this.addButton(new MonitorButton(0, posX + 90, posY + 151, "Select"));
		this.nextArrow = this.addButton(new ChameleonButton(1, posX + 193, posY + 151, ">"));
		this.prevArrow = this.addButton(new ChameleonButton(2, posX + 39, posY + 151, "<"));

	}

	@Override
	protected void actionPerformed(GuiButton button) throws IOException {
		if (button == this.selectButton) {
			NetworkHandler.NETWORK.sendToServer(new MessageExteriorChange(pos, exteriors[index].block.getDefaultState()));
			Minecraft.getMinecraft().displayGuiScreen(null);
		} else if (button == this.nextArrow) {
			int i = index + 1;
			if (i > exteriors.length - 1) {
				index = 0;
			} else index++;
		} else {
			int i = index - 1;
			if (i < 0) {
				index = exteriors.length - 1;
			} else --index;
		}
		super.actionPerformed(button);
	}

	@Override
	public boolean doesGuiPauseGame() {
		return false;
	}

	public class ChameleonButton extends GuiButton {

		int colour = 0x00938F;
		int BUTTON_WIDTH = 23;
		int BUTTON_HEIGHT = 32;
		private ResourceLocation TEXTURE = new ResourceLocation(Tardis.MODID, "textures/gui/chameleon_ui.png");

		public ChameleonButton(int buttonId, int x, int y, String buttonText) {
			super(buttonId, x, y, 23, 32, buttonText);
		}

		@Override
		public void drawButton(Minecraft mc, int mouseX, int mouseY, float partialTicks) {
			if (visible) {
				FontRenderer fontRenderer = mc.fontRenderer;
				mc.getTextureManager().bindTexture(TEXTURE);

				if (mouseX >= this.x && mouseX <= this.x + BUTTON_WIDTH && mouseY >= this.y && mouseY <= this.y + BUTTON_HEIGHT) {
					hovered = true;
					colour = 0xCE8F23;
				} else {
					hovered = false;
					colour = 0x00938F;
				}

				if (hovered) {
					this.drawTexturedModalRect(this.x, this.y, 227, 209, BUTTON_WIDTH, BUTTON_HEIGHT);
				} else {
					this.drawTexturedModalRect(this.x, this.y, 191, 209, BUTTON_WIDTH, BUTTON_HEIGHT);
				}

				GlStateManager.pushMatrix();
				this.drawCenteredString(fontRenderer, this.displayString, this.x + 11, this.y + 12, colour);
				GlStateManager.color(1, 1, 1);
				GlStateManager.popMatrix();
			}
		}

	}

}
