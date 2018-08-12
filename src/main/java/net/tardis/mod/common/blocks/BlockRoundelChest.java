package net.tardis.mod.common.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.tardis.mod.common.tileentity.decoration.TileEntityRoundelChest;

public class BlockRoundelChest extends BlockDecoration {

	public BlockRoundelChest() {
		super(TileEntityRoundelChest::new);
	}

	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		ItemStack stack = playerIn.getHeldItem(hand);
		if(Block.getBlockFromItem(stack.getItem()) != Blocks.AIR)((TileEntityRoundelChest)worldIn.getTileEntity(pos)).setBlockId(Block.getStateId(Block.getBlockFromItem(stack.getItem()).getDefaultState()));
		playerIn.displayGUIChest((IInventory)worldIn.getTileEntity(pos));
		return true;
	}

}
