package net.tardis.mod.client.guis.manual;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.ResourceLocation;
import net.tardis.mod.Tardis;
import net.tardis.mod.capability.ITardisCap.Vec2d;

public class GuiManual extends GuiScreen {
	public static final ResourceLocation TEXTURE = new ResourceLocation(Tardis.MODID, "textures/gui/manual.png");
	private static final ResourceLocation BOOK_GUI_TEXTURES = new ResourceLocation("textures/gui/book.png");
	private static List<Page> PAGES = new ArrayList<>();
	public int gui_width = 281, gui_height = 208;
	private int index = 0;

	public GuiManual() {
		mc = Minecraft.getMinecraft();
		this.PAGES.clear();
	}

	public void loadPages() {
		
	}
	
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		mc.getTextureManager().bindTexture(TEXTURE);
		super.drawScreen(mouseX, mouseY, partialTicks);
		this.drawModalRectWithCustomSizedTexture(width / 2 - this.gui_width / 2, height / 2 - this.gui_height / 2, 0, 0, this.gui_width, this.gui_height, 512, 512);
		if(this.index < this.PAGES.size()) {
			this.PAGES.get(index).draw(width / 2, height / 2);
		}
		if(this.index + 1 < this.PAGES.size()) {
			this.PAGES.get(index + 1).draw(width / 2, height / 2);
		}
	}

	@Override
	public void initGui() {
		super.initGui();
	}

	public static class Page{
		
		private List<String> lines = new ArrayList<>();
		
		public static Page read(ResourceLocation loc) {
			Page page = new Page();
			try {
				BufferedReader read = new BufferedReader(new InputStreamReader(Minecraft.getMinecraft().getResourceManager().getResource(loc).getInputStream()));
				Iterator<String> iter = read.lines().iterator();
				while(iter.hasNext()) {
					String[] lines = iter.next().split("<br />");
					for(String line : lines) {
						page.addLine(line);
					}
				}
				read.close();
			}
			catch(Exception e) {}
			return page;
		}
		
		public void addLine(String line) {
			this.lines.add(line);
		}
		
		public void addImage(Vec2d vec, ResourceLocation loc) {}
		
		public void draw(double x, double y) {
			FontRenderer fr = Minecraft.getMinecraft().fontRenderer;
			int index = 0;
			for(String line : lines) {
				fr.drawString(line, (int)x, (int)y + (index * fr.FONT_HEIGHT), 0x000000);
				++index;
			}
		}
	}

}
