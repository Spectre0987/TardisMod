package net.tardis.mod.network;

import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;
import net.tardis.mod.Tardis;
import net.tardis.mod.client.worldshell.MessageSyncWorldShell;
import net.tardis.mod.network.packets.*;

public class NetworkHandler {

    public static SimpleNetworkWrapper NETWORK = NetworkRegistry.INSTANCE.newSimpleChannel(Tardis.MODID);

    public static void init() {
        NETWORK.registerMessage(MessageHandlerProtocol.class, MessageProtocol.class, 1, Side.SERVER);
        NETWORK.registerMessage(MessageHandlerTeleport.class, MessageTeleport.class, 2, Side.SERVER);
        NETWORK.registerMessage(MessageDoorOpen.Handler.class, MessageDoorOpen.class, 3, Side.CLIENT);
        NETWORK.registerMessage(MessageTelepathicCircut.Handler.class, MessageTelepathicCircut.class, 4, Side.SERVER);
        NETWORK.registerMessage(MessageSyncWorldShell.Handler.class, MessageSyncWorldShell.class, 5, Side.CLIENT);
        NETWORK.registerMessage(MessageExteriorChange.Handler.class, MessageExteriorChange.class, 6, Side.SERVER);
        NETWORK.registerMessage(MessageDemat.Handler.class, MessageDemat.class, 7, Side.CLIENT);
        NETWORK.registerMessage(MessageSpawnItem.Handler.class, MessageSpawnItem.class, 8, Side.SERVER);
        NETWORK.registerMessage(MessageDamageSystem.Helper.class, MessageDamageSystem.class, 9, Side.SERVER);
        NETWORK.registerMessage(MessageUpdateBessie.Handler.class, MessageUpdateBessie.class, 10, Side.SERVER);
        NETWORK.registerMessage(MessageCompanion.Handler.class, MessageCompanion.class, 11, Side.SERVER);
        NETWORK.registerMessage(MessageMissControl.Handler.class, MessageMissControl.class, 12, Side.CLIENT);
    }


}
