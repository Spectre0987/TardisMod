package net.tardis.mod.common.sounds;

import net.minecraft.util.SoundEvent;

import java.util.Arrays;
import java.util.List;

public class InteriorHum {

	public static InteriorHum DEFAULT = new InteriorHum(TSounds.interior_hum_80, 20);
	public static InteriorHum INTERIOR_HUM_1963 = new InteriorHum(TSounds.INTERIOR_HUM_1963,50);

	public static List<InteriorHum> hums = Arrays.asList(DEFAULT, INTERIOR_HUM_1963);
	
	private SoundEvent event = null;
	private int timeToPlay = 0;
	
	public InteriorHum(SoundEvent event, int time) {
		this.event = event;
		this.timeToPlay = time;
	}
	
	public SoundEvent getSound() {
		return this.event;
	}
	
	public int getTicks() {
		return this.timeToPlay;
	}
}
