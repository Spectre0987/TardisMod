package net.tardis.mod.common.systems;

import net.minecraftforge.fml.common.eventhandler.Event;

public class TardisSubsystemBrokeEvent extends Event {
    private TardisSystems.BaseSystem system;

    public TardisSubsystemBrokeEvent(TardisSystems.BaseSystem baseSystem) {
        system = baseSystem;
    }

    public String getSystemName(){
        return system.getNameKey();
    }

    public TardisSystems.BaseSystem getSubsystem(){
        return system;
    }
}
