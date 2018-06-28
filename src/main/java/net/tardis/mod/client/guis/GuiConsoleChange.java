package net.tardis.mod.client.guis;

import java.io.IOException;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.tardis.mod.client.guis.elements.ButtonRecipe;

public class GuiConsoleChange extends GuiScreen {
	
	Minecraft mc;
	public BlockPos pos = BlockPos.ORIGIN;
	public ButtonRecipe console1;
	
	public GuiConsoleChange() {
		mc = Minecraft.getMinecraft();
	}
	
	public GuiConsoleChange(BlockPos pos) {
		this();
		this.pos = pos.toImmutable();
	}

	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		this.drawDefaultBackground();
		console1.drawButton(mc, mouseX, mouseY, partialTicks);
		super.drawScreen(mouseX, mouseY, partialTicks);
	}

	@Override
	protected void actionPerformed(GuiButton button) throws IOException {
		super.actionPerformed(button);
	}

	@Override
	public void initGui() {
		super.initGui();
		console1 = new ButtonRecipe(0, 0, 0, new ItemStack(Blocks.ANVIL), 0);
		console1.x = (width / 2) - console1.width / 2;
		console1.y = (height / 2) - console1.height / 2;
	}

	@Override
	public boolean doesGuiPauseGame() {
		return false;
	}

}
