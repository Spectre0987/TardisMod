package net.tardis.mod.api.events.tardis;


import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.eventhandler.Event;

public class TardisEnterEvent extends Event {

    private final BlockPos interiorPos;
    private final Entity entity;

    public TardisEnterEvent(Entity entity, BlockPos interiorPos) {
        this.entity = entity;
        this.interiorPos = interiorPos;
    }

    public Entity getEntity() {
        return entity;
    }

    public BlockPos getInteriorPos() {
        return interiorPos;
    }

}
