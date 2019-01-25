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
	private GuiButtonExt submit;
	
	@Override
	public void initGui() {
		super.initGui();
		FontRenderer fr = Minecraft.getMinecraft().fontRenderer;
		x = new GuiTextField(0, fr, width / 2 - width / 6, ((height / 2 - 166 / 2) + 15) + fr.FONT_HEIGHT * 3, width / 3, fr.FONT_HEIGHT * 2);
		y = new GuiTextField(1, fr, width / 2 - width / 6, ((height / 2 - 166 / 2) + 15) + fr.FONT_HEIGHT * 6, width / 3, fr.FONT_HEIGHT * 2);
		z = new GuiTextField(2, fr, width / 2 - width / 6, ((height / 2 - 166 / 2) + 15) + fr.FONT_HEIGHT * 9, width / 3, fr.FONT_HEIGHT * 2);
		x.setFocused(true);
		String text = "Materialize!";
		this.addButton(submit = new GuiButtonExt(3, 0, height / 2 + 40, text));
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
		super.drawScreen(mouseX, mouseY, partialTicks);
	}

	@Override
	protected void actionPerformed(GuiButton button) throws IOException {
		super.actionPerformed(button);
		if(button == submit) {
			int x = 0, y = 0, z = 0;
			try{
				x = Integer.parseInt(this.x.getText().trim());
				y = Integer.parseInt(this.y.getText().trim());
				z = Integer.parseInt(this.z.getText().trim());
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
	}

	@Override
	public void updateScreen() {
		super.updateScreen();
		x.updateCursorCounter();
		y.updateCursorCounter();
		z.updateCursorCounter();
	}

	@Override
	protected void keyTyped(char typedChar, int keyCode) throws IOException {
		super.keyTyped(typedChar, keyCode);
		x.textboxKeyTyped(typedChar, keyCode);
		y.textboxKeyTyped(typedChar, keyCode);
		z.textboxKeyTyped(typedChar, keyCode);
	}

}
