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
		int id = 0;
		NETWORK.registerMessage(MessageHandlerProtocol.class, MessageProtocol.class, id++, Side.SERVER);
		NETWORK.registerMessage(MessageHandlerTeleport.class, MessageTeleport.class, id++, Side.SERVER);
		NETWORK.registerMessage(MessageDoorOpen.Handler.class, MessageDoorOpen.class, id++, Side.CLIENT);
		NETWORK.registerMessage(MessageTelepathicCircuit.Handler.class, MessageTelepathicCircuit.class, id++, Side.SERVER);
		NETWORK.registerMessage(MessageSyncWorldShell.Handler.class, MessageSyncWorldShell.class, id++, Side.CLIENT);
		NETWORK.registerMessage(MessageExteriorChange.Handler.class, MessageExteriorChange.class, id++, Side.SERVER);
		NETWORK.registerMessage(MessageDemat.Handler.class, MessageDemat.class, id++, Side.CLIENT);
		NETWORK.registerMessage(MessageSpawnItem.Handler.class, MessageSpawnItem.class, id++, Side.SERVER);
		NETWORK.registerMessage(MessageDamageSystem.Helper.class, MessageDamageSystem.class, id++, Side.SERVER);
		NETWORK.registerMessage(MessageUpdateBessie.Handler.class, MessageUpdateBessie.class, id++, Side.SERVER);
		NETWORK.registerMessage(MessageCompanion.Handler.class, MessageCompanion.class, id++, Side.SERVER);
		NETWORK.registerMessage(MessageMissControl.Handler.class, MessageMissControl.class, id++, Side.CLIENT);
		NETWORK.registerMessage(MessageSyncTardises.Handler.class, MessageSyncTardises.class, id++, Side.CLIENT);
		NETWORK.registerMessage(MessageWaypointSave.Handler.class, MessageWaypointSave.class, id++, Side.SERVER);
		NETWORK.registerMessage(MessageWaypointLoad.Handler.class, MessageWaypointLoad.class, id++, Side.SERVER);
		NETWORK.registerMessage(MessageSonicWorkbench.Handler.class, MessageSonicWorkbench.class, id++, Side.SERVER);
		NETWORK.registerMessage(MessageStopHum.Handler.class, MessageStopHum.class, id++, Side.CLIENT);
		NETWORK.registerMessage(MessageChangeInterior.Handler.class, MessageChangeInterior.class, id++, Side.SERVER);
		NETWORK.registerMessage(MessageSwitchHum.Handler.class, MessageSwitchHum.class, id++, Side.SERVER);
		NETWORK.registerMessage(MessageRequestBOTI.Handler.class, MessageRequestBOTI.class, id++, Side.SERVER);
		NETWORK.registerMessage(MessageSyncCap.Handler.class, MessageSyncCap.class, id++, Side.CLIENT);
		NETWORK.registerMessage(MessageCapabilityDoorOpen.Handler.class, MessageCapabilityDoorOpen.class, id++, Side.SERVER);
		NETWORK.registerMessage(MessageSetupFlight.Handler.class, MessageSetupFlight.class, id++, Side.CLIENT);
		NETWORK.registerMessage(MessageConsoleChange.Handler.class, MessageConsoleChange.class, id++, Side.SERVER);
		NETWORK.registerMessage(MessageDematAnim.Handler.class, MessageDematAnim.class, id++, Side.SERVER);
	}
	
	
}
