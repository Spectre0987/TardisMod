package net.tardis.mod.network;

import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;
import net.tardis.mod.Tardis;
import net.tardis.mod.client.worldshell.MessageSyncWorldShell;
import net.tardis.mod.network.packets.MessageCapabilityDoorOpen;
import net.tardis.mod.network.packets.MessageChangeInterior;
import net.tardis.mod.network.packets.MessageCompanion;
import net.tardis.mod.network.packets.MessageConsoleChange;
import net.tardis.mod.network.packets.MessageDamageSystem;
import net.tardis.mod.network.packets.MessageDemat;
import net.tardis.mod.network.packets.MessageDematAnim;
import net.tardis.mod.network.packets.MessageDoorOpen;
import net.tardis.mod.network.packets.MessageExteriorChange;
import net.tardis.mod.network.packets.MessageHandlerProtocol;
import net.tardis.mod.network.packets.MessageHandlerTeleport;
import net.tardis.mod.network.packets.MessageMissControl;
import net.tardis.mod.network.packets.MessageProtocol;
import net.tardis.mod.network.packets.MessageRequestBOTI;
import net.tardis.mod.network.packets.MessageSetupFlight;
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
