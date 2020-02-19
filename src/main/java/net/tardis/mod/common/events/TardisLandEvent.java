package net.tardis.mod.common.events;


import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.eventhandler.Event;
import net.tardis.mod.common.tileentity.TileEntityTardis;

/**
 * Event triggered when a TARDIS land somewhere
 */
public class TardisLandEvent extends Event {
    private TileEntityTardis tardis;
    private BlockPos location;
    private int dimension;
    private float fuel;

    public TardisLandEvent(TileEntityTardis tardis) {
        this.tardis = tardis;
        location = tardis.getDestination();
        dimension = tardis.getTargetDim();
        fuel = tardis.getArtron();
    }

    /**
     * @return TileEntityTardis
     */
    public TileEntityTardis getTardis() {
        return tardis;
    }

    /**
     * @return BlockPos of the TARDIS landed destination
     */
    public BlockPos getLocation() {
        return location;
    }

    /**
     * @return Dimension id (int value) of the target dimension
     */
    public int getDimension() {
        return dimension;
    }

    /**
     * @return Fuel level at the TARDIS land
     */
    public float getFuel() {
        return fuel;
    }
}
