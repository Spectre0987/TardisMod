package net.tardis.mod.client.guis;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

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
		for(String s : TardisSystems.SYSTEMS.keySet()) {
			this.addButton(new GuiButton(id, width, height + (Minecraft.getMinecraft().fontRenderer.FONT_HEIGHT * 2) * id, new TextComponentTranslation(TardisSystems.createFromName(s).getNameKey()).getFormattedText()));
			sys.put(id, s);
			++id;
		}
	}

	@Override
	protected void actionPerformed(GuiButton button) throws IOException {
		super.actionPerformed(button);
		ISystem s;
		for(ISystem sy : tardis.systems) {
			if(TardisSystems.getIdBySystem(sy).equals(sys.get(button.id))) {
				
			}
		}
		ItemStack stack = new ItemStack(TardisSystems.createFromName(sys.get(button.id)).getRepairItem());
		stack.setItemDamage(0);
	}

}
