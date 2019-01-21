package net.tardis.mod.network.packets;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.SoundCategory;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.tardis.mod.Tardis;
import net.tardis.mod.common.dimensions.TDimensions;
import net.tardis.mod.common.sounds.TSounds;
import net.tardis.mod.common.tileentity.TileEntityDoor;
import net.tardis.mod.common.tileentity.TileEntityTardis;
import net.tardis.mod.util.common.helpers.TardisHelper;

import java.util.UUID;

public class MessageSnap implements IMessage {

    private UUID playerID;

    public MessageSnap() {
    }

    public MessageSnap(UUID playerID) {
        this.playerID = playerID;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        PacketBuffer pBuf = new PacketBuffer(buf);
        playerID = pBuf.readUniqueId();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        PacketBuffer pBuf = new PacketBuffer(buf);
        pBuf.writeUniqueId(playerID);
    }

    public static class Handler implements IMessageHandler<MessageSnap,IMessage>{
        @Override
        public IMessage onMessage(MessageSnap message, MessageContext ctx) {
            Tardis.LOG.info("trigger");
            ctx.getServerHandler().player.getServerWorld().addScheduledTask(() -> {
                TileEntityTardis tardis = TardisHelper.getConsole(TardisHelper.getTardis(message.playerID));
                if (tardis != null){
                    EntityPlayerMP player = ctx.getServerHandler().player;
                    //If the exterior distance < 30
                    if (ctx.getServerHandler().player.dimension != TDimensions.TARDIS_ID && tardis.getLocation().getDistance((int)player.posX,(int)player.posY, (int)player.posZ) > 30) return;

                    //And if the interior distance is below 10
                    if (ctx.getServerHandler().player.dimension == TDimensions.TARDIS_ID && tardis.getDoor().getDistance(player) > 10) return;

                    //Then, we can open the doors
                    ctx.getServerHandler().player.world.playSound(null, player.getPosition(), TSounds.snap, SoundCategory.AMBIENT, 1F, 1F);
                    TileEntity door = player.server.worlds[tardis.dimension].getTileEntity(tardis.getLocation().up());
                    ((TileEntityDoor)door).toggleLockedNoKey(player);
                    tardis.getDoor().setOpen(!((TileEntityDoor)door).isLocked);

                    if (((TileEntityDoor)door).isLocked)
                        ctx.getServerHandler().player.world.playSound(null, player.getPosition(), TSounds.door_closed, SoundCategory.BLOCKS, 0.2F, 1F);
                    else
                        ctx.getServerHandler().player.world.playSound(null, player.getPosition(), TSounds.door_open, SoundCategory.BLOCKS, 0.2F, 1F);

                }
            });
            return null;
        }
    }
}
