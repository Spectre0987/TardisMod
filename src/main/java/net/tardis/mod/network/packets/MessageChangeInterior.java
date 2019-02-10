package net.tardis.mod.network.packets;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.tardis.mod.common.ars.ConsoleRoom;
import net.tardis.mod.common.tileentity.TileEntityTardis;
import net.tardis.mod.util.common.helpers.TardisHelper;

import java.util.UUID;

public class MessageChangeInterior implements IMessage {

    int index = -1;
    BlockPos pos = BlockPos.ORIGIN;

    public MessageChangeInterior() {
    }

    public MessageChangeInterior(int id, BlockPos pos) {
        this.index = id;
        this.pos = pos;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        this.index = buf.readInt();
        this.pos = BlockPos.fromLong(buf.readLong());
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(index);
        buf.writeLong(this.pos.toLong());
    }

    public static class Handler implements IMessageHandler<MessageChangeInterior, IMessage> {

        @Override
        public IMessage onMessage(MessageChangeInterior message, MessageContext ctx) {
            ctx.getServerHandler().player.getServerWorld().addScheduledTask(new Runnable() {
                @Override
                public void run() {
                    if (!ConsoleRoom.CONSOLE_ROOMS.isEmpty() && ConsoleRoom.CONSOLE_ROOMS.size() > message.index) {
                            ConsoleRoom.CONSOLE_ROOMS.get(message.index).generate(ctx.getServerHandler().player.getServerWorld(), message.pos);
                        }
                    }
            });
            return null;
        }

    }

}
