package net.tardis.mod.common.sounds;

import net.minecraft.util.SoundEvent;

public class InteriorHum {

	public static InteriorHum DEFAULT = new InteriorHum(TSounds.interior_hum_80, 20);
	
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
