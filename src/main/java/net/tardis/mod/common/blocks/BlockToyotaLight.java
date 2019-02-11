package net.tardis.mod.common.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Random;

public class BlockToyotaLight extends Block {
    private final boolean isOn;

    public BlockToyotaLight(boolean isOn) {
        super(Material.REDSTONE_LIGHT);
        this.isOn = isOn;

        if (isOn) {
            this.setLightLevel(1.0F);
        }
    }

    /**
     * Called after the block is set in the Chunk data, but before the Tile Entity is set
     */
    @Override
    public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state) {
        if (!worldIn.isRemote) {
            if (this.isOn && !worldIn.isBlockPowered(pos)) {
                worldIn.setBlockState(pos, TBlocks.toyota_light_off.getDefaultState(), 2);
            } else if (!this.isOn && worldIn.isBlockPowered(pos)) {
                worldIn.setBlockState(pos, TBlocks.toyota_light_on.getDefaultState(), 2);
            }
        }
    }

    /**
     * Called when a neighboring block was changed and marks that this state should perform any checks during a neighbor
     * change. Cases may include when redstone power is updated, cactus blocks popping off due to a neighboring solid
     * block, etc.
     */
    @Override
    public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos) {
        if (!worldIn.isRemote) {
            if (this.isOn && !worldIn.isBlockPowered(pos)) {
                worldIn.scheduleUpdate(pos, this, 4);
            } else if (!this.isOn && worldIn.isBlockPowered(pos)) {
                worldIn.setBlockState(pos, TBlocks.toyota_light_on.getDefaultState(), 2);
            }
        }
    }
    
    @Override
    public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
        if (!worldIn.isRemote) {
            if (this.isOn && !worldIn.isBlockPowered(pos)) {
                worldIn.setBlockState(pos, TBlocks.toyota_light_off.getDefaultState(), 2);
            }
        }
    }

    /**
     * Get the Item that this Block should drop when harvested.
     */
    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        return Item.getItemFromBlock(TBlocks.toyota_light_off);
    }
    
    @Override
    public ItemStack getItem(World worldIn, BlockPos pos, IBlockState state) {
        return new ItemStack(TBlocks.toyota_light_off);
    }
    
    @Override
    protected ItemStack getSilkTouchDrop(IBlockState state) {
        return new ItemStack(TBlocks.toyota_light_off);
    }

}

