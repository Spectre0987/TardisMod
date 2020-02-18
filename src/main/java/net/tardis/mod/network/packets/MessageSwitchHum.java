package net.tardis.mod.network.packets;

import io.netty.buffer.ByteBuf;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.tardis.mod.common.dimensions.TDimensions;
import net.tardis.mod.common.sounds.InteriorHum;
import net.tardis.mod.common.tileentity.TileEntityTardis;

public class MessageSwitchHum implements IMessage {
    private int humID;
    private long blockpos;

    public MessageSwitchHum() {}

    public MessageSwitchHum(int soundID, long blockpos) {
        this.humID = soundID;
        this.blockpos = blockpos;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        PacketBuffer packetBuffer = new PacketBuffer(buf);
        humID = packetBuffer.readInt();
        blockpos = packetBuffer.readLong();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        PacketBuffer packetBuffer = new PacketBuffer(buf);
        packetBuffer.writeInt(humID);
        packetBuffer.writeLong(blockpos);
    }

        public static class Handler implements IMessageHandler<MessageSwitchHum,IMessage> {
            @Override
            public IMessage onMessage(MessageSwitchHum message, MessageContext ctx) {
                FMLCommonHandler.instance().getMinecraftServerInstance().addScheduledTask(() -> {
                    BlockPos consolePos = BlockPos.fromLong(message.blockpos);
                    TileEntityTardis tardis = (TileEntityTardis) FMLCommonHandler.instance().getMinecraftServerInstance().getWorld(TDimensions.TARDIS_ID).getTileEntity(consolePos);
                    if (tardis != null) {
                        tardis.toggleHum(InteriorHum.hums.get(message.humID));
                    }
                });

                return null;
            }
        }
}
