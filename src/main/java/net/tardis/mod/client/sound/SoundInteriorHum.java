package net.tardis.mod.client.sound;

import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.MovingSound;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.tardis.mod.common.dimensions.WorldProviderTardis;

/**
 * Created by Sub
 * on 23/09/2018.
 */
public class SoundInteriorHum extends MovingSound {

    private EntityPlayer player = Minecraft.getMinecraft().player;

    public SoundInteriorHum(SoundEvent soundIn) {
        super(soundIn, SoundCategory.MASTER);
        this.repeat = true;
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
        volume = 0.3F;
    }
}
