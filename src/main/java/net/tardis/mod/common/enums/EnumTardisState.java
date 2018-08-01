package net.tardis.mod.common.enums;

public enum EnumTardisState {
	
	NORMAL("tardis.state.normal"),
	DISABLED("tardis.state.disabled");
	
	String name = "";
	
	EnumTardisState(String name) {
		this.name = name;
	}
	
	String getNameKey(){
		return name;
	}

}
