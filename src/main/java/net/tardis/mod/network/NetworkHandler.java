package net.tardis.mod.network;

import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;
import net.tardis.mod.Tardis;
import net.tardis.mod.client.worldshell.MessageSyncWorldShell;
import net.tardis.mod.network.packets.MessageChangeInterior;
import net.tardis.mod.network.packets.MessageCompanion;
import net.tardis.mod.network.packets.MessageDamageSystem;
import net.tardis.mod.network.packets.MessageDemat;
import net.tardis.mod.network.packets.MessageDoorOpen;
import net.tardis.mod.network.packets.MessageExteriorChange;
import net.tardis.mod.network.packets.MessageHandlerProtocol;
import net.tardis.mod.network.packets.MessageHandlerTeleport;
import net.tardis.mod.network.packets.MessageMissControl;
import net.tardis.mod.network.packets.MessageProtocol;
import net.tardis.mod.network.packets.MessageRequestBOTI;
import net.tardis.mod.network.packets.MessageSonicWorkbench;
import net.tardis.mod.network.packets.MessageSpawnItem;
import net.tardis.mod.network.packets.MessageStopHum;
import net.tardis.mod.network.packets.MessageSwitchHum;
import net.tardis.mod.network.packets.MessageSyncCap;
import net.tardis.mod.network.packets.MessageSyncTardises;
import net.tardis.mod.network.packets.MessageTelepathicCircuit;
import net.tardis.mod.network.packets.MessageTeleport;
import net.tardis.mod.network.packets.MessageUpdateBessie;
import net.tardis.mod.network.packets.MessageWaypointLoad;
import net.tardis.mod.network.packets.MessageWaypointSave;

public class NetworkHandler {
	
	public static SimpleNetworkWrapper NETWORK = NetworkRegistry.INSTANCE.newSimpleChannel(Tardis.MODID);
	
	public static void init() {
		NETWORK.registerMessage(MessageHandlerProtocol.class, MessageProtocol.class, 1, Side.SERVER);
		NETWORK.registerMessage(MessageHandlerTeleport.class, MessageTeleport.class, 2, Side.SERVER);
		NETWORK.registerMessage(MessageDoorOpen.Handler.class, MessageDoorOpen.class, 3, Side.CLIENT);
		NETWORK.registerMessage(MessageTelepathicCircuit.Handler.class, MessageTelepathicCircuit.class, 4, Side.SERVER);
		NETWORK.registerMessage(MessageSyncWorldShell.Handler.class, MessageSyncWorldShell.class, 5, Side.CLIENT);
		NETWORK.registerMessage(MessageExteriorChange.Handler.class, MessageExteriorChange.class, 6, Side.SERVER);
		NETWORK.registerMessage(MessageDemat.Handler.class, MessageDemat.class, 7, Side.CLIENT);
		NETWORK.registerMessage(MessageSpawnItem.Handler.class, MessageSpawnItem.class, 8, Side.SERVER);
		NETWORK.registerMessage(MessageDamageSystem.Helper.class, MessageDamageSystem.class, 9, Side.SERVER);
		NETWORK.registerMessage(MessageUpdateBessie.Handler.class, MessageUpdateBessie.class, 10, Side.SERVER);
		NETWORK.registerMessage(MessageCompanion.Handler.class, MessageCompanion.class, 11, Side.SERVER);
		NETWORK.registerMessage(MessageMissControl.Handler.class, MessageMissControl.class, 12, Side.CLIENT);
		NETWORK.registerMessage(MessageSyncTardises.Handler.class, MessageSyncTardises.class, 13, Side.CLIENT);
		NETWORK.registerMessage(MessageWaypointSave.Handler.class, MessageWaypointSave.class, 14, Side.SERVER);
		NETWORK.registerMessage(MessageWaypointLoad.Handler.class, MessageWaypointLoad.class, 15, Side.SERVER);
		NETWORK.registerMessage(MessageSonicWorkbench.Handler.class, MessageSonicWorkbench.class, 16, Side.SERVER);
		NETWORK.registerMessage(MessageStopHum.Handler.class, MessageStopHum.class, 17, Side.CLIENT);
		NETWORK.registerMessage(MessageChangeInterior.Handler.class, MessageChangeInterior.class, 18, Side.SERVER);
		NETWORK.registerMessage(MessageSwitchHum.Handler.class, MessageSwitchHum.class, 19, Side.SERVER);
		NETWORK.registerMessage(MessageRequestBOTI.Handler.class, MessageRequestBOTI.class, 20, Side.SERVER);
		NETWORK.registerMessage(MessageSyncCap.Handler.class, MessageSyncCap.class, 21, Side.CLIENT);
	}
	
	
}
