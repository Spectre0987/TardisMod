package net.tardis.mod.common.sounds;

import net.minecraft.client.audio.ISound;
import net.minecraft.client.audio.Sound;
import net.minecraft.client.audio.SoundEventAccessor;
import net.minecraft.client.audio.SoundHandler;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;

import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.List;

public class InteriorHum implements ISound {

	public static InteriorHum DEFAULT = new InteriorHum(TSounds.interior_hum_80,20);
	public static InteriorHum INTERIOR_HUM_1963 = new InteriorHum(TSounds.INTERIOR_HUM_1963,50);
	public static InteriorHum COPPER = new InteriorHum(TSounds.interior_hum_copper,620);
	public static InteriorHum CORAL = new InteriorHum(TSounds.interior_hum_coral,1060);
	public static InteriorHum TOYOTA = new InteriorHum(TSounds.interior_hum_toyota,580);

	public static List<InteriorHum> hums = Arrays.asList(DEFAULT, INTERIOR_HUM_1963, COPPER, CORAL, TOYOTA);

	private SoundEvent event;
	private int ticks;

	public InteriorHum(SoundEvent event, int ticks) {
		this.event = event;
		this.ticks = ticks;
	}

	@Override
	public Sound getSound() {
		return new Sound(event.getSoundName().toString(),getVolume(),getPitch(),0,Sound.Type.SOUND_EVENT,false);
	}

	public SoundEvent getSoundEvent() {
		return this.event;
	}

	@Override
	public ResourceLocation getSoundLocation() {
		return event.getSoundName();
	}

	@Nullable
	@Override
	public SoundEventAccessor createAccessor(SoundHandler handler) {
		return null;
	}

	@Override
	public SoundCategory getCategory() {
		return SoundCategory.AMBIENT;
	}

	@Override
	public boolean canRepeat() {
		return false;
	}

	@Override
	public int getRepeatDelay() {
		return 0;
	}

	@Override
	public float getVolume() {
		return 1.5F;
	}

	@Override
	public float getPitch() {
		return 1;
	}

	@Override
	public float getXPosF() {
		return 0;
	}

	@Override
	public float getYPosF() {
		return 0;
	}

	@Override
	public float getZPosF() {
		return 0;
	}

	@Override
	public AttenuationType getAttenuationType() {
		return null;
	}

	public int getTicks() {
		return ticks;
	}
}
