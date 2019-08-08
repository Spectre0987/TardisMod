package net.tardis.mod.network.packets;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.SoundCategory;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.tardis.mod.common.sounds.InteriorHum;

public class MessageStopHum implements IMessage {
    private int soundID;

    public MessageStopHum() {
    }

    public MessageStopHum(int soundID) {
        this.soundID = soundID;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        PacketBuffer packetBuffer = new PacketBuffer(buf);
        soundID = packetBuffer.readInt();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        PacketBuffer packetBuffer = new PacketBuffer(buf);
        packetBuffer.writeInt(soundID);
    }

    public static class Handler implements IMessageHandler<MessageStopHum,IMessage>{
        @Override
        public IMessage onMessage(MessageStopHum message, MessageContext ctx) {
            Minecraft.getMinecraft().addScheduledTask(()->{
                InteriorHum humToStop = InteriorHum.hums.get(message.soundID);
                Minecraft.getMinecraft().getSoundHandler().stop(humToStop.getSoundEvent().getSoundName().toString(), SoundCategory.AMBIENT);
            });

            return null;
        }
    }
}
