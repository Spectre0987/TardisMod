package net.tardis.mod.client.guis;

import java.io.IOException;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.ClickType;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.tardis.mod.Tardis;
import net.tardis.mod.client.guis.elements.ButtonRecipe;
import net.tardis.mod.common.tileentity.TileEntitySonicWorkbench;
import net.tardis.mod.network.NetworkHandler;
import net.tardis.mod.network.packets.MessageSonicWorkbench;

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
	}
	
	@Override
	public void initGui() {
		super.initGui();
		this.buttonList.clear();
		this.addButtons();
	}
	
	public void addButtons() {
		int id = 0;
		Item[] items = TileEntitySonicWorkbench.RECIPES.get(this.inventorySlots.getSlot(0).getStack().getItem());
		if(items != null) {
			for(Item item : items) {
				this.addButton(new ButtonRecipe(id, width / 2 - 81 + 18 * (id % 4), height / 2 - 92 + 18 * (int)(id / 4), new ItemStack(item)));
				++id;
			}
		}
	}
	
	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		super.drawGuiContainerForegroundLayer(mouseX, mouseY);
		renderHoveredToolTip(mouseX, mouseY);
		
	}

	@Override
	protected void handleMouseClick(Slot slotIn, int slotId, int mouseButton, ClickType type) {
		super.handleMouseClick(slotIn, slotId, mouseButton, type);
		this.buttonList.clear();
		this.addButtons();
	}

	@Override
	protected void renderHoveredToolTip(int p_191948_1_, int p_191948_2_) {
		super.renderHoveredToolTip(p_191948_1_, p_191948_2_);
	}
	
	@Override
	protected void actionPerformed(GuiButton button) throws IOException {
		super.actionPerformed(button);
		NetworkHandler.NETWORK.sendToServer(new MessageSonicWorkbench(pos, ((ButtonRecipe)button).getStack()));
	}
	
}
