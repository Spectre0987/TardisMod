package net.tardis.mod.client.sound;

import net.minecraft.client.audio.MovingSound;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.tardis.mod.capability.CapabilityTardis;
import net.tardis.mod.common.sounds.TSounds;

import java.util.function.Supplier;

/**
 * Created by Sub
 * on 20/09/2018.
 */
public class MovingSoundFlightMode extends MovingSound {
	
	private final Object entity;
	private final Supplier<Boolean> stopCondition;
	private boolean donePlaying = false;
	
	public MovingSoundFlightMode(Object object, SoundEvent soundIn, SoundCategory categoryIn, boolean repeat, Supplier<Boolean> stopCondition, float volumeSfx) {
		super(soundIn, categoryIn);
		this.entity = object;
		this.stopCondition = stopCondition;
		this.repeat = repeat;
		volume = volumeSfx;
	}
	
	@Override
	public void update() {
		
		if (entity == null) {
			setDonePlaying();
		}
		
		if (entity instanceof Entity) {
			Entity entityObject = (Entity) entity;
			if (stopCondition.get() || entityObject.isDead) {
				setDonePlaying();
			}
			
			if (entityObject instanceof EntityPlayer) {
				EntityPlayer player = (EntityPlayer) entityObject;
				
				//Remat
				if(sound.getSoundLocation().equals(TSounds.tardis_land.getRegistryName())) {
					if (CapabilityTardis.get(player).getFlightState() == CapabilityTardis.TardisFlightState.REMAT) {
						volume = 1;
					} else {
						volume = 0;
					}
				}
				
				
				//Remat
				if(sound.getSoundLocation().equals(TSounds.takeoff.getRegistryName())) {
					if (CapabilityTardis.get(player).getFlightState() == CapabilityTardis.TardisFlightState.DEMAT) {
						volume = 1;
					} else {
						volume = 0;
					}
				}
				
				
				//Fly loop
				if(sound.getSoundLocation().equals(TSounds.flyLoop.getRegistryName())) {
					if (player.onGround || !CapabilityTardis.get(player).hasFuel()) {
						volume = 0;
					} else {
						volume = 1;
					}
				}
				
				//Cloister bell
				if(sound.getSoundLocation().equals(TSounds.cloister_bell.getRegistryName())) {
					if (player.hurtTime > 0 || !CapabilityTardis.get(player).hasFuel() || player.collidedHorizontally) {
						volume = 1;
					} else {
						volume = 0;
					}
				}
				
				//Spoopy noise
				if(sound.getSoundLocation().equals(TSounds.tardis_no_fuel.getRegistryName())) {
					if (!CapabilityTardis.get(player).hasFuel() && !player.onGround) {
						volume = 1;
					} else {
						volume = 0;
					}
				}
			}
			
			super.xPosF = (float) entityObject.posX;
			super.yPosF = (float) entityObject.posY;
			super.zPosF = (float) entityObject.posZ;
		}
		
		if (entity instanceof TileEntity) {
			TileEntity tileObject = (TileEntity) entity;
			BlockPos pos = tileObject.getPos();
			super.xPosF = (float) pos.getX();
			super.yPosF = (float) pos.getY();
			super.zPosF = (float) pos.getZ();
		}
		
	}
	
	
	public void setDonePlaying() {
		this.repeat = false;
		this.donePlaying = true;
		this.repeatDelay = 0;
	}
	
	@Override
	public boolean canRepeat() {
		return this.repeat;
	}
	
	@Override
	public float getVolume() {
		return this.volume;
	}
	
	@Override
	public float getPitch() {
		return this.pitch;
	}
	
	@Override
	public boolean isDonePlaying() {
		return donePlaying;
	}
	
	
	@Override
	public int getRepeatDelay() {
		return this.repeatDelay;
	}
	
	@Override
	public AttenuationType getAttenuationType() {
		return AttenuationType.LINEAR;
	}
}