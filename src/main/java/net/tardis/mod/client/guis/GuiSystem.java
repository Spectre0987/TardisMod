package net.tardis.mod.client.guis;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextComponentTranslation;
import net.tardis.mod.Tardis;
import net.tardis.mod.common.systems.TardisSystems;
import net.tardis.mod.common.systems.TardisSystems.ISystem;
import net.tardis.mod.common.tileentity.TileEntityTardis;
import net.tardis.mod.packets.MessageDamageSystem;
import net.tardis.mod.packets.MessageSpawnItem;
import net.tardis.mod.packets.NetworkHandler;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class GuiSystem extends GuiScreen{
	
	private static ResourceLocation TEXTURE = new ResourceLocation(Tardis.MODID, "textures/gui/tardis_coords.png");
	private static int GUI_WIDTH = 248, GUI_HEIGHT = 166;
	private Map<Integer, String> sys = new HashMap<>();
	private TileEntityTardis tardis;
	
	public GuiSystem() {}
	
	public GuiSystem(TileEntityTardis t) {
		tardis = t;
	}

	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		Minecraft.getMinecraft().getTextureManager().bindTexture(TEXTURE);
		ScaledResolution res = new ScaledResolution(Minecraft.getMinecraft());
		int width = res.getScaledWidth() / 2 - GUI_WIDTH / 2, height = res.getScaledHeight() / 2 - GUI_HEIGHT / 2;
		this.drawTexturedModalRect(width, height, 0, 0, GUI_WIDTH, GUI_HEIGHT);
		super.drawScreen(mouseX, mouseY, partialTicks);
	}

	@Override
	public void initGui() {
		super.initGui();
		ScaledResolution res = new ScaledResolution(Minecraft.getMinecraft());
		int width = GUI_WIDTH / 2, height = GUI_HEIGHT / 2, id = 0;
		for(ISystem s : tardis.systems) {
			this.addButton(new GuiButton(id, width, height + (Minecraft.getMinecraft().fontRenderer.FONT_HEIGHT * 2) * id, new TextComponentTranslation(s.getNameKey()).getFormattedText() + " " + Math.round(s.getHealth() * 100) + "%"));
			++id;
		}
	}

	@Override
	protected void actionPerformed(GuiButton button) throws IOException {
		if(button.id < TardisSystems.SYSTEMS.size()) {
			ISystem sys = tardis.systems[button.id];
			if(sys.getHealth() > 0.0F) {
				ItemStack stack = new ItemStack(sys.getRepairItem());
				stack.setItemDamage((int)(100 - (sys.getHealth() * 100)));
				NetworkHandler.NETWORK.sendToServer(new MessageSpawnItem(stack));
				NetworkHandler.NETWORK.sendToServer(new MessageDamageSystem(tardis.getPos(), TardisSystems.getIdBySystem(sys)));
			}
			Minecraft.getMinecraft().displayGuiScreen(null);
		}
		super.actionPerformed(button);
	}

}
