package net.tardis.mod.common.blocks;

import java.util.function.Supplier;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.tardis.mod.common.blocks.BlockTardisTop.ItemBlockTardis;

public class BlockInteriorDoor extends BlockFacingDecoration {
	
	public ItemBlock item = new ItemBlockTardis(this);
	
	public BlockInteriorDoor(Supplier<TileEntity> tileEntity) {
		super(tileEntity);
	}
	
	public ItemBlock getItem() {
		return item;
	}
	
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		if (!worldIn.isRemote) {
			System.out.println(this.getMetaFromState(state));
		}
		return super.onBlockActivated(worldIn, pos, state, playerIn, hand, facing, hitX, hitY, hitZ);
	}
	
	@Override
	public boolean isNormalCube(IBlockState state) {
		return false;
	}
	
	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}
	
	@Override
	public boolean canCollideCheck(IBlockState state, boolean hitIfLiquid) {
		return true;
	}
	
	@Override
	public boolean causesSuffocation(IBlockState state) {
		return false;
	}

}
