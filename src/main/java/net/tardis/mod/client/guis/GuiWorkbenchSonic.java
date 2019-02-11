package net.tardis.mod.client.guis;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.gui.inventory.GuiCrafting;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.tardis.mod.Tardis;
import net.tardis.mod.client.guis.elements.ButtonRecipe;
import net.tardis.mod.common.items.ItemSonic;
import net.tardis.mod.network.NetworkHandler;
import net.tardis.mod.network.packets.MessageSonicWorkbench;

import java.io.IOException;

public class GuiWorkbenchSonic extends GuiContainer {
	
	public static final ResourceLocation TEXTURE = new ResourceLocation(Tardis.MODID, "textures/gui/sonictable.png");
	
	BlockPos pos = BlockPos.ORIGIN;
	
	public GuiWorkbenchSonic(Container inventorySlotsIn, BlockPos pos) {
		super(inventorySlotsIn);
		this.pos = pos;
	}
	
	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		this.drawDefaultBackground();
		Minecraft.getMinecraft().getTextureManager().bindTexture(TEXTURE);
		this.drawTexturedModalRect(width / 2 - 352 / 4, (height / 2 - 374 / 4) - 11, 0, 0, 352 / 2, 374 / 2);
		renderHoveredToolTip(mouseX, mouseY);
	}
	
	@Override
	public void initGui() {
		super.initGui();
		for (int i = 0; i < ItemSonic.SONICS.size(); ++i) {
			this.addButton(new ButtonRecipe(i, (width / 2 - 80) + 18 * (i % 4), (height / 2 - 92) + 18 * (int) (i / 4), ItemSonic.SONICS.get(i)));
		}
	}
	
	@Override
	protected void renderHoveredToolTip(int p_191948_1_, int p_191948_2_) {
		super.renderHoveredToolTip(p_191948_1_, p_191948_2_);
	}
	
	@Override
	protected void actionPerformed(GuiButton button) throws IOException {
		super.actionPerformed(button);
		NetworkHandler.NETWORK.sendToServer(new MessageSonicWorkbench(pos, button.id));
	}
	
}
