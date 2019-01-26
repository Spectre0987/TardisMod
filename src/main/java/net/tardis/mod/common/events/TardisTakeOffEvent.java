package net.tardis.mod.common.events;

import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.eventhandler.Cancelable;
import net.minecraftforge.fml.common.eventhandler.Event;
import net.tardis.mod.common.tileentity.TileEntityTardis;

/**
 * Event triggered when a TARDIS take off
 */

@Cancelable
public class TardisTakeOffEvent extends Event {
    private TileEntityTardis tardis;
    private BlockPos destination;
    private int dimension;
    private float fuel;

    public TardisTakeOffEvent(TileEntityTardis tardis) {
        this.tardis = tardis;
        destination = tardis.getDestination();
        dimension = tardis.getTargetDim();
        fuel = tardis.fuel;
    }

    /**
     * @return TileEntityTardis
     */
    public TileEntityTardis getTardis() {
        return tardis;
    }

    /**
     * @return BlockPos of the TARDIS target destination
     */
    public BlockPos getDestination() {
        return destination;
    }

    /**
     * @return Dimension id (int value) of the target dimension
     */
    public int getDimension() {
        return dimension;
    }

    /**
     * @return Fuel level at the TARDIS take-off
     */
    public float getFuel() {
        return fuel;
    }
}
