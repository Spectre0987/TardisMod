package net.tardis.api.protocols;

import java.util.HashMap;
import java.util.Map;

public class TardisProtocol {
	
	private static int id=0;
	public static Map<Integer,ITardisProtocol> protocols=new HashMap<Integer,ITardisProtocol>();
	
	public static void register(ITardisProtocol pr) {
		protocols.put(id++, pr);
	}
	
	public static int getIdForProtocol(ITardisProtocol pr) {
		int id=0;
		for(ITardisProtocol p:protocols.values()) {
			if(p.equals(pr))
				return id;
			++id;
		}
		return -1;
	}
	
	@Override
	public boolean equals(Object arg0) {
		if(arg0 instanceof ITardisProtocol&&arg0.getClass()==this.getClass())
			return true;
		return false;
	}

	public static ITardisProtocol getProtocolFromId(int id) {
		return protocols.get(id);
	}

}
