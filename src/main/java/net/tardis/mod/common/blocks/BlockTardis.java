package net.tardis.mod.common.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.tardis.api.controls.IUnbreakable;

public class BlockTardis extends Block implements IUnbreakable {
	
	public BlockTardis() {
		super(Material.WOOD, MapColor.BLUE);
		this.setBlockUnbreakable();
		this.setResistance(999);
	}
	
	@Override
	public EnumBlockRenderType getRenderType(IBlockState state) {
		return EnumBlockRenderType.INVISIBLE;
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
	public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos) {
		return new AxisAlignedBB(0.1,0,0.1,0.9,1,0.9); 
	}

	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		worldIn.getBlockState(pos.up()).getBlock().onBlockActivated(worldIn, pos, state, playerIn, hand, facing, hitX, hitY, hitZ);
		return super.onBlockActivated(worldIn, pos, state, playerIn, hand, facing, hitX, hitY, hitZ);
	}

	@Override
	public void onEntityCollidedWithBlock(World worldIn, BlockPos pos, IBlockState state, Entity entityIn) {
		worldIn.getBlockState(pos.up()).getBlock().onEntityCollidedWithBlock(worldIn, pos, state, entityIn);
		super.onEntityCollidedWithBlock(worldIn, pos, state, entityIn);
	}
	
}
