package net.tardis.mod.common.events;

import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.eventhandler.Event;
import net.tardis.mod.common.tileentity.TileEntityTardis;

/**
 * Event triggered when a TARDIS Crash
 */
public class TardisCrashEvent extends Event {
    private TileEntityTardis tardis;
    private BlockPos crashPos;
    private int crashDimension;

    /**
     * @param tardis
     */
    public TardisCrashEvent(TileEntityTardis tardis, BlockPos crashPos, int crashDimension) {
        this.tardis = tardis;
        this.crashPos = crashPos;
        this.crashDimension = crashDimension;
    }

    /**
     * @return TileEntityTardis
     */
    public TileEntityTardis getTardis() {
        return tardis;
    }

    /**
     * @return Crash location of the TARDIS
     */
    public BlockPos getCrashPos() {
        return crashPos;
    }

    public int getCrashDimension() {
        return crashDimension;
    }
}
