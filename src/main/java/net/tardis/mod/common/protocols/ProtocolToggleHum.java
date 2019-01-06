package net.tardis.mod.common.protocols;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import net.tardis.mod.common.strings.TStrings;
import net.tardis.mod.common.tileentity.TileEntityTardis;

public class ProtocolToggleHum implements ITardisProtocol {
    @Override
    public void onActivated(World world, TileEntityTardis tardis) {
        if (!world.isRemote){
            tardis.toggleHum();
            EntityPlayer player = world.getClosestPlayer(tardis.getPos().getX(), tardis.getPos().getY(), tardis.getPos().getZ(), 10D, false);
            if(player != null) {
                player.sendStatusMessage(new TextComponentTranslation(TStrings.HUM_TOGGLED + tardis.isHumEnabled()), true);
            }
        }
    }

    @Override
    public String getNameKey() {
        return "protocol.hum";
    }
}
