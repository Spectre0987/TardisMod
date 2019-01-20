package net.tardis.mod.common.protocols;

import java.util.HashMap;
import java.util.Map;

public class TardisProtocol {

	public static Map<Integer, ITardisProtocol> protocols = new HashMap<Integer, ITardisProtocol>();
	private static int id = 0;

	public static void register(ITardisProtocol pr) {
		protocols.put(id++, pr);
	}

	public static int getIdForProtocol(ITardisProtocol pr) {
		int id = 0;
		for (ITardisProtocol p : protocols.values()) {
			if (p.equals(pr)) return id;
			++id;
		}
		return -1;
	}

	public static ITardisProtocol getProtocolFromId(int id) {
		return protocols.get(id);
	}

	@Override
	public boolean equals(Object arg0) {
		return arg0 instanceof ITardisProtocol && arg0.getClass() == this.getClass();
	}

}
