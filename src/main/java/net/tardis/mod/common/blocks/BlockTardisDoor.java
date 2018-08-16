package net.tardis.mod.common.blocks;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.tardis.mod.common.enums.EnumInteriorDoor;
import net.tardis.mod.common.tileentity.TileEntityInteriorDoor;

import javax.annotation.Nullable;
import java.util.function.Supplier;

public class BlockTardisDoor extends BlockTileBase {

    private EnumInteriorDoor door;

    public BlockTardisDoor(Material materialIn, Supplier<TileEntity> tileEntity, EnumInteriorDoor interiorDoor) {
        super(materialIn, tileEntity);
        door = interiorDoor;
    }

    /**
     * Called when the block is right clicked by a player.
     */
    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        TileEntityInteriorDoor door = (TileEntityInteriorDoor) world.getTileEntity(pos);
        door.setOpen(!door.getOpen());
        return true;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(World world, IBlockState state) {
        TileEntityInteriorDoor door = (TileEntityInteriorDoor) super.createTileEntity(world, state);
        door.setDoorType(this.door);
        door.setOpen(false);
        return door;
    }
}
