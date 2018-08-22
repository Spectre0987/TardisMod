package net.tardis.mod.common.protocols;

import java.util.HashMap;
import java.util.Map;

public class TardisProtocol {
	
	private static int id = 0;
	public static Map<Integer, ITardisProtocol> PROTOCOLS = new HashMap<Integer, ITardisProtocol>();
	
	public static void register(ITardisProtocol pr) {
		if (!PROTOCOLS.containsValue(pr)) {
			PROTOCOLS.put(id++, pr);
		}
	}
	
	public static int getIdForProtocol(ITardisProtocol pr) {
		int id = 0;
		for (ITardisProtocol protocol : PROTOCOLS.values()) {
			if (protocol.equals(pr)) return id;
			++id;
		}
		return -1;
	}

	public static ITardisProtocol getProtocolFromId(int id) {
		return PROTOCOLS.get(id);
	}

	public static void init() {
		TardisProtocol.register(new ProtocolCCircuit());
		TardisProtocol.register(new ProtocolEnabledHADS());
		TardisProtocol.register(new ProtocolSystemReadout());
		TardisProtocol.register(new ProtocolFindRift());
		TardisProtocol.register(new ProtocolConsole());
		TardisProtocol.register(new ProtocolARS());
		TardisProtocol.register(new ProtocolRegenRoom());
	}

	@Override
	public boolean equals(Object arg0) {
		return arg0 instanceof ITardisProtocol && arg0.getClass() == this.getClass();
	}

}
