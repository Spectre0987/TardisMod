package net.tardis.mod.common.sounds;

import net.minecraft.util.SoundEvent;

import java.util.Arrays;
import java.util.List;

public class InteriorHum {

	public static InteriorHum DEFAULT = new InteriorHum(TSounds.interior_hum_80,20);
	public static InteriorHum INTERIOR_HUM_1963 = new InteriorHum(TSounds.INTERIOR_HUM_1963,50);
	public static InteriorHum INTERIOR_HUM_70 = new InteriorHum(TSounds.interior_hum_70, 620);
	public static InteriorHum COPPER = new InteriorHum(TSounds.interior_hum_copper,620);
	public static InteriorHum CORAL = new InteriorHum(TSounds.interior_hum_coral,1060);
	public static InteriorHum TOYOTA = new InteriorHum(TSounds.interior_hum_toyota,580);

	public static List<InteriorHum> hums = Arrays.asList(DEFAULT, INTERIOR_HUM_1963, INTERIOR_HUM_70,COPPER, CORAL, TOYOTA);

	private SoundEvent event;
	private int ticks;

	public InteriorHum(SoundEvent event, int ticks) {
		this.event = event;
		this.ticks = ticks;
	}

	public SoundEvent getSoundEvent() {
		return this.event;
	}

	public int getTicks() {
		return ticks;
	}
}
