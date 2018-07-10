package net.tardis.mod.api.events;


import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.eventhandler.Event;

public class TardisEnterEvent extends Event {

    private final BlockPos destinationPos;
    private final Entity entity;

    public TardisEnterEvent(Entity entity, BlockPos destinationPos) {
        this.entity = entity;
        this.destinationPos = destinationPos;
    }

    public Entity getEntity() {
        return entity;
    }

    public BlockPos getDestinationPos() {
        return destinationPos;
    }

}
