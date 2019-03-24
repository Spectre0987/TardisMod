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
import net.tardis.mod.util.common.helpers.TardisHelper;

public class MessageSwitchHum implements IMessage {
    private int humID;

    public MessageSwitchHum() {
    }

    public MessageSwitchHum(int soundID) {
        this.humID = soundID;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        PacketBuffer packetBuffer = new PacketBuffer(buf);
        humID = packetBuffer.readInt();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        PacketBuffer packetBuffer = new PacketBuffer(buf);
        packetBuffer.writeInt(humID);
    }

        public static class Handler implements IMessageHandler<MessageSwitchHum,IMessage> {
            @Override
            public IMessage onMessage(MessageSwitchHum message, MessageContext ctx) {
                FMLCommonHandler.instance().getMinecraftServerInstance().addScheduledTask(() -> {
                    BlockPos consolePos = TardisHelper.getTardis(ctx.getServerHandler().player.getUniqueID());
                    TileEntityTardis tardis = (TileEntityTardis) FMLCommonHandler.instance().getMinecraftServerInstance().getWorld(TDimensions.TARDIS_ID).getTileEntity(consolePos);
                    tardis.toggleHum(InteriorHum.hums.get(message.humID));
                });

                return null;
            }
        }
}
