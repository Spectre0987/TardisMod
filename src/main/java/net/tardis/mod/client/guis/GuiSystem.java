package net.tardis.mod.client.guis;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiYesNo;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextComponentTranslation;
import net.tardis.mod.Tardis;
import net.tardis.mod.client.guis.elements.MonitorButton;
import net.tardis.mod.common.systems.TardisSystems;
import net.tardis.mod.common.systems.TardisSystems.BaseSystem;
import net.tardis.mod.common.tileentity.TileEntityTardis;
import net.tardis.mod.network.NetworkHandler;
import net.tardis.mod.network.packets.MessageDamageSystem;
import net.tardis.mod.network.packets.MessageProtocol;
import net.tardis.mod.network.packets.MessageSpawnItem;

public class GuiSystem extends GuiScreen {
	
	static final int GUI_WIDTH = 256;
	static final int GUI_HEIGHT = 192;
	public static final ResourceLocation TEXTURE = new ResourceLocation(Tardis.MODID, "textures/gui/monitor_ui.png");
	private Map<Integer, String> sys = new HashMap<>();
	private TileEntityTardis tardis;
	
	public GuiSystem(TileEntityTardis t) {
		tardis = t;
	}
	
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		this.drawDefaultBackground();
		mc.getTextureManager().bindTexture(TEXTURE);
		int x = (width - GUI_WIDTH) / 2;
		int y = (height - GUI_HEIGHT) / 2;
		this.drawTexturedModalRect(x, y, 0, 0, GUI_WIDTH, GUI_HEIGHT);
		super.drawScreen(mouseX, mouseY, partialTicks);
	}
	
	@Override
	public void initGui() {
		this.buttonList.clear();
		int x_change = 0;
		int y_change = 0;
		int id = 0;
		for(BaseSystem s : tardis.systems) {
			GuiButton button = new MonitorButton(id, ((width - GUI_WIDTH) / 2) + 11 + x_change, ((height - GUI_HEIGHT) / 2) + 8 + y_change, new TextComponentTranslation(s.getNameKey()).getFormattedText() + " " + Math.round(s.getHealth() * 100) + "%");
			button.enabled = s.getHealth() > 0.0F;
			this.addButton(button);
			id++;
			x_change += 80;
			if(id % 3 == 0){
				x_change = 0;
				y_change += 36;
			}
		}
	}
	
	@Override
	protected void actionPerformed(GuiButton button) throws IOException {
		if(button.id < TardisSystems.SYSTEMS.size()) {
			BaseSystem sys = tardis.systems[button.id];
			if(sys.getHealth() > 0.0F) {
				Minecraft.getMinecraft().displayGuiScreen(new GUIConfirm((result, id) -> {
					if (result) {
						ItemStack stack = new ItemStack(sys.getRepairItem());
						stack.setItemDamage((int)(100 - (sys.getHealth() * 100)));
						NetworkHandler.NETWORK.sendToServer(new MessageSpawnItem(stack));
						NetworkHandler.NETWORK.sendToServer(new MessageDamageSystem(tardis.getPos(), TardisSystems.getIdBySystem(sys)));
						Minecraft.getMinecraft().displayGuiScreen(null);
					} else {
						Minecraft.getMinecraft().displayGuiScreen(this);
					}

				}, I18n.format("Are you sure you want to remove your " + new TextComponentTranslation(sys.getNameKey()).getFormattedText() + "?"), I18n.format("Your TARDIS will no longer " + sys.getUsage()), I18n.format("Remove System"), I18n.format("gui.cancel"), 0));

			}
		}
		super.actionPerformed(button);
	}

}
