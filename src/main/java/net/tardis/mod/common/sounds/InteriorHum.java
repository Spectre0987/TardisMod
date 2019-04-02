package net.tardis.mod.common.sounds;

import java.util.Arrays;
import java.util.List;

import net.minecraft.util.SoundEvent;
import net.minecraft.util.text.TextComponentTranslation;
import net.tardis.mod.Tardis;

public class InteriorHum {

	public static InteriorHum DISABLED = new InteriorHum(null, 20, "disabled");
	public static InteriorHum DEFAULT = new InteriorHum(TSounds.interior_hum_80, 600, "default");
	public static InteriorHum INTERIOR_HUM_1963 = new InteriorHum(TSounds.INTERIOR_HUM_1963, 50, "1963");
	public static InteriorHum INTERIOR_HUM_70 = new InteriorHum(TSounds.interior_hum_70, 620, "70");
	public static InteriorHum COPPER = new InteriorHum(TSounds.interior_hum_copper,620, "copper");
	public static InteriorHum CORAL = new InteriorHum(TSounds.interior_hum_coral,1060, "coral");
	public static InteriorHum TOYOTA = new InteriorHum(TSounds.interior_hum_toyota,580, "toyota");

	public static List<InteriorHum> hums = Arrays.asList(DISABLED, DEFAULT, INTERIOR_HUM_1963, INTERIOR_HUM_70,COPPER, CORAL, TOYOTA);

	private SoundEvent event;
	private int ticks;
	private String name;

	public InteriorHum(SoundEvent event, int ticks, String name) {
		this.event = event;
		this.ticks = ticks;
		this.name = name;
	}

	public SoundEvent getSoundEvent() {
		return this.event;
	}

	public int getTicks() {
		return ticks;
	}
	
	public String getTranslatedName() {
		return new TextComponentTranslation("hum." + Tardis.MODID + "." + name).getFormattedText();
	}
}
