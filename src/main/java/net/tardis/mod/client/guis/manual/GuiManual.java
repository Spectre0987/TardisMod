package net.tardis.mod.client.guis.manual;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tardis.mod.Tardis;

public class GuiManual extends GuiScreen {
	public static final ResourceLocation TEXTURE = new ResourceLocation(Tardis.MODID, "textures/gui/manual.png");
	private static final ResourceLocation BOOK_GUI_TEXTURES = new ResourceLocation("textures/gui/book.png");
	private static ScaledResolution res;
	public int gui_width = 281, gui_height = 208;
	Minecraft mc;

	public GuiManual() {
		mc = Minecraft.getMinecraft();
		res = new ScaledResolution(mc);
	}

	//TODO: Nobody touch anything, I have an idea - Jake

	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		GlStateManager.pushMatrix();
		mc.getTextureManager().bindTexture(TEXTURE);
		drawModalRectWithCustomSizedTexture(res.getScaledWidth() / 2 - gui_width / 2, res.getScaledHeight() / 2 - gui_height / 2, 0, 0, gui_width, gui_height, 512, 512);
		GlStateManager.popMatrix();
		super.drawScreen(mouseX, mouseY, partialTicks);
	}

	@Override
	public void initGui() {

		buttonList.add(new NextPageButton(1, (this.width - gui_width) / 2 + 235, res.getScaledHeight() / 2 + gui_height / 4, true));
		buttonList.add(new NextPageButton(2, (this.width - gui_width) / 2 + 25, res.getScaledHeight() / 2 + gui_height / 4, false));
		super.initGui();
	}

	@SideOnly(Side.CLIENT)
	static class NextPageButton extends GuiButton {
		private final boolean isForward;

		public NextPageButton(int buttonId, int x, int y, boolean isForwardIn) {
			super(buttonId, x, y, 23, 13, "");
			this.isForward = isForwardIn;
		}

		/**
		 * Draws this button to the screen.
		 */
		public void drawButton(Minecraft mc, int mouseX, int mouseY, float partialTicks) {
			if (this.visible) {
				boolean flag = mouseX >= this.x && mouseY >= this.y && mouseX < this.x + this.width && mouseY < this.y + this.height;
				GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
				mc.getTextureManager().bindTexture(BOOK_GUI_TEXTURES);
				int i = 0;
				int j = 192;

				if (flag) {
					i += 23;
				}

				if (!this.isForward) {
					j += 13;
				}

				this.drawTexturedModalRect(this.x, this.y, i, j, 23, 13);
			}
		}
	}

}
