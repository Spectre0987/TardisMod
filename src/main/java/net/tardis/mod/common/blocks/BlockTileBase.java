package net.tardis.mod.common.blocks;

import java.util.function.Supplier;

import javax.annotation.Nullable;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockTileBase extends Block {

    private Supplier<TileEntity> tileEntity;

    public BlockTileBase(Material materialIn, Supplier<TileEntity> tileEntity) {
        super(materialIn);
        this.tileEntity = tileEntity;
    }

    @Override
    public boolean hasTileEntity(IBlockState state) {
        return true;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(World world, IBlockState state) {
        return tileEntity.get();
    }
}
