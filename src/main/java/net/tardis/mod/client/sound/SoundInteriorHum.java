package net.tardis.mod.client.sound;

import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.MovingSound;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.tardis.mod.common.dimensions.WorldProviderTardis;
import net.tardis.mod.common.tileentity.TileEntityTardis;

import java.util.function.Supplier;

/**
 * Created by Sub
 * on 23/09/2018.
 */
public class SoundInteriorHum extends MovingSound {

	private EntityPlayer player = Minecraft.getMinecraft().player;
	private final TileEntityTardis stopCondition;
	
	public SoundInteriorHum(SoundEvent soundIn, TileEntityTardis tardis) {
		super(soundIn, SoundCategory.AMBIENT);
		this.repeat = true;
		this.stopCondition = tardis;
	}

	@Override
	public void update() {

		if (player == null) return;
		if (!(player.world.provider instanceof WorldProviderTardis)) {
			this.donePlaying = true;
		}
		this.xPosF = (float) this.player.posX;
		this.yPosF = (float) this.player.posY;
		this.zPosF = (float) this.player.posZ;
		volume = 1.5F;
	}
	
	@Override
	public boolean isDonePlaying() {
		return stopCondition.soundChanged || !(player.world.provider instanceof WorldProviderTardis);
	}
}
