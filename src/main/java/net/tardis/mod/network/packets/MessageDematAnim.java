package net.tardis.mod.network.packets;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.tardis.mod.capability.CapabilityTardis;

public class MessageDematAnim implements IMessage {
    public MessageDematAnim() {

    }

    @Override
    public void fromBytes(ByteBuf buf) {

    }

    @Override
    public void toBytes(ByteBuf buf) {

    }

    public static class Handler implements IMessageHandler<MessageDematAnim, IMessage>{
        @Override
        public IMessage onMessage(MessageDematAnim message, MessageContext ctx) {
            FMLCommonHandler.instance().getMinecraftServerInstance().addScheduledTask(() ->{
                EntityPlayer player = ctx.getServerHandler().player;
                if (CapabilityTardis.get(player).isInFlight()) {
                    CapabilityTardis.get(player).setFlightState(CapabilityTardis.get(player).getFlightState().name().toLowerCase().contains("demat") ? CapabilityTardis.TardisFlightState.REMAT : CapabilityTardis.TardisFlightState.DEMAT);
                }
            });
            return null;
        }
    }
}
