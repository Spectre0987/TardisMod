package net.tardis.mod.client.guis;

import java.io.IOException;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.config.GuiButtonExt;
import net.tardis.mod.Tardis;

public class GuiItemMaterializer extends GuiScreen {

	public static final ResourceLocation TEXTURE = new ResourceLocation(Tardis.MODID, "textures/gui/tardis_coords.png");
	private GuiTextField x;
	private GuiTextField y;
	private GuiTextField z;
	private GuiTextField dimension;
	private GuiButtonExt submit;
	
	@Override
	public void initGui() {
		super.initGui();
		FontRenderer fr = Minecraft.getMinecraft().fontRenderer;
		int text_width = fr.FONT_HEIGHT * 4;
		int text_height = fr.FONT_HEIGHT * 2;
		int sx = (width / 3 - text_width / 1) + 10, sy = (height / 2 - text_height / 2) - 40;
		x = new GuiTextField(0, fr, sx, sy, text_width, text_height);
		y = new GuiTextField(1, fr, (int)(sx + (text_width * 1.5)), sy, text_width, text_height);
		z = new GuiTextField(2, fr, (int)(sx + text_width * 3), sy, text_width, text_height);
		this.dimension = new GuiTextField(2, fr, (int)(sx + text_width * 4.5), sy, text_width, text_height);
		x.setFocused(true);
		String text = "Materialize!";
		this.addButton(submit = new GuiButtonExt(4, 0, height / 2 + 40, text));
		submit.x = width / 2 - submit.width / 2;
		
	}

	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		this.drawDefaultBackground();
		Minecraft.getMinecraft().getTextureManager().bindTexture(TEXTURE);
		this.drawTexturedModalRect(width / 2 - 248 / 2, height / 2 - 166 / 2, 0, 0, 248, 166);
		x.drawTextBox();
		y.drawTextBox();
		z.drawTextBox();
		dimension.drawTextBox();
		super.drawScreen(mouseX, mouseY, partialTicks);
	}

	@Override
	protected void actionPerformed(GuiButton button) throws IOException {
		super.actionPerformed(button);
		if(button == submit) {
			int x = 0, y = 0, z = 0, dim = 0;
			try{
				x = Integer.parseInt(this.x.getText().trim());
				y = Integer.parseInt(this.y.getText().trim());
				z = Integer.parseInt(this.z.getText().trim());
				dim = Integer.parseInt(this.dimension.getText().trim());
			}
			catch(Exception e) {}
			System.out.println("X: " + x + " Y: " + y + " Z: " + z);
		}
	}

	@Override
	protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
		super.mouseClicked(mouseX, mouseY, mouseButton);
		x.mouseClicked(mouseX, mouseY, mouseButton);
		y.mouseClicked(mouseX, mouseY, mouseButton);
		z.mouseClicked(mouseX, mouseY, mouseButton);
		this.dimension.mouseClicked(mouseX, mouseY, mouseButton);
	}

	@Override
	public void updateScreen() {
		super.updateScreen();
		x.updateCursorCounter();
		y.updateCursorCounter();
		z.updateCursorCounter();
		this.dimension.updateCursorCounter();
	}

	@Override
	protected void keyTyped(char typedChar, int keyCode) throws IOException {
		super.keyTyped(typedChar, keyCode);
		x.textboxKeyTyped(typedChar, keyCode);
		y.textboxKeyTyped(typedChar, keyCode);
		z.textboxKeyTyped(typedChar, keyCode);
		this.dimension.textboxKeyTyped(typedChar, keyCode);
	}

}
