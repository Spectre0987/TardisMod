package net.tardis.mod.integrations;

import java.util.Random;

import me.sub.regeneration.common.events.RegenerationEvent;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.SoundCategory;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.tardis.mod.common.dimensions.TDimensions;
import net.tardis.mod.common.sounds.TSounds;

public class Regeneration {

    public static void preInit() {
        MinecraftForge.EVENT_BUS.register(new Regeneration.EventHandler());
    }

    public static class EventHandler {

        @SubscribeEvent
        public void regen(RegenerationEvent event) {
            EntityPlayer player = event.getEntityPlayer();

            if (player.dimension != TDimensions.TARDIS_ID) return;

            if (event.getHandler().getRegenTicks() == 3) {
                player.world.playSound(null, player.getPosition(), TSounds.cloister_bell, SoundCategory.PLAYERS, 1F, 1F);
            }

            if (event.getHandler().getRegenTicks() != 0) {
                Random rand = player.world.rand;
                player.rotationPitch += (rand.nextInt(10) - 5) * 0.1;
                player.rotationYaw += (rand.nextInt(10) - 5) * 0.1;
            }


        }
    }

}
