package net.tardis.mod.network.packets;

import io.netty.buffer.ByteBuf;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.SoundCategory;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.tardis.mod.Tardis;
import net.tardis.mod.common.sounds.TSounds;
import net.tardis.mod.common.tileentity.TileEntityTardis;
import net.tardis.mod.util.common.helpers.TardisHelper;

import java.util.UUID;

public class MessageSnap implements IMessage {

    UUID playerID;

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
                    tardis.getDoor().setOpen(!tardis.getDoor().isOpen());
                    ctx.getServerHandler().player.world.playSound(null, ctx.getServerHandler().player.getPosition(), TSounds.snap, SoundCategory.AMBIENT, 1F, 1F);
                }
            });
            return null;
        }
    }
}
