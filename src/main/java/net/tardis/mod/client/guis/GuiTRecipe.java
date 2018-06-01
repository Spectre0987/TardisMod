package net.tardis.mod.client.guis;

import java.io.IOException;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.tardis.mod.Tardis;
import net.tardis.mod.client.guis.elements.ButtonRecipe;
import net.tardis.mod.common.recipes.TemporalRecipe;
import net.tardis.mod.packets.MessageTR;

public class GuiTRecipe extends GuiScreen {
	
	public static final ResourceLocation TEXTURE = new ResourceLocation(Tardis.MODID, "textures/gui/tardis_coords.png");
	
	BlockPos pos;
	int tW = 248;
	int tH = 166;
	
	public GuiTRecipe() {}
	
	public GuiTRecipe(BlockPos pos) {
		this.pos = pos;
	}
	
	@Override
	public void initGui() {
		this.buttonList.clear();
		int id = 0;
		int x = 0;
		int y = 0;
		for (TemporalRecipe rec : TemporalRecipe.recipes.values()) {
			this.addButton(new ButtonRecipe(++id, ((width / 2) - 45) + (x * 18), ((height / 2) - 45) + (y * 18), rec.getResult(), rec.getTime()));
			++x;
			if (x > 4) {
				x = 0;
				y++;
			}
		}
	}
	
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		mc.getTextureManager().bindTexture(TEXTURE);
		this.drawTexturedModalRect(width / 2 - tW / 2, height / 2 - tH / 2, 0, 0, tW, tH);
		for (GuiButton b : this.buttonList) {
			b.drawButton(Minecraft.getMinecraft(), mouseX, mouseY, partialTicks);
		}
	}
	
	@Override
	protected void actionPerformed(GuiButton button) throws IOException {
		super.actionPerformed(button);
		Tardis.NETWORK.sendToServer(new MessageTR(button.id, pos));
	}
	
}
