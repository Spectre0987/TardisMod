package net.tardis.mod.common.sounds;

import net.minecraft.util.SoundEvent;

import java.util.Arrays;
import java.util.List;

public class InteriorHum {

	public static InteriorHum DEFAULT = new InteriorHum(TSounds.interior_hum_80);
	public static InteriorHum INTERIOR_HUM_1963 = new InteriorHum(TSounds.INTERIOR_HUM_1963);
	public static InteriorHum COPPER = new InteriorHum(TSounds.interior_hum_copper);
	public static InteriorHum CORAL = new InteriorHum(TSounds.interior_hum_coral);
	public static InteriorHum TOYOTA = new InteriorHum(TSounds.interior_hum_toyota);

	public static List<InteriorHum> hums = Arrays.asList(DEFAULT, INTERIOR_HUM_1963, COPPER, CORAL, TOYOTA);

	private SoundEvent event;

	public InteriorHum(SoundEvent event) {
		this.event = event;
	}

	public SoundEvent getSound() {
		return this.event;
	}
}
