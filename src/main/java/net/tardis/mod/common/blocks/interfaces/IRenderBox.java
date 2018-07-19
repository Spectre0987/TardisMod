package net.tardis.mod.common.blocks.interfaces;

public interface IRenderBox {
    /**
     * This interface determines whether
     * the bounding box the player sees when their mouse is over a block
     * should be rendered on the block this is implemented to
     */
    boolean shouldRenderBox();
}