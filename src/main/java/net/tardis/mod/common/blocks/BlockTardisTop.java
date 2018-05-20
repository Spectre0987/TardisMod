package net.tardis.mod.common.blocks;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.tardis.api.controls.IUnbreakable;
import net.tardis.mod.common.tileentity.TileEntityDoor;

public class BlockTardisTop extends BlockContainer implements IUnbreakable {
	
	public static final PropertyDirection FACING=PropertyDirection.create("facing",EnumFacing.Plane.HORIZONTAL);
	
	public BlockTardisTop() {
		super(Material.WOOD, MapColor.BLUE);
		this.setBlockUnbreakable();
		this.setResistance(9999);
		this.setDefaultState(this.getDefaultState().withProperty(FACING, EnumFacing.NORTH));
	}
	
	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileEntityDoor();
	}
	
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		if (!worldIn.isRemote) {
			TileEntityDoor door = (TileEntityDoor) worldIn.getTileEntity(pos);
			door.toggleLocked(playerIn);
		}
		return super.onBlockActivated(worldIn, pos, state, playerIn, hand, facing, hitX, hitY, hitZ);
	}
	
	@Override
	public boolean canCollideCheck(IBlockState state, boolean hitIfLiquid) {
		return true;
	}
	
	@Override
	public EnumBlockRenderType getRenderType(IBlockState state) {
		return EnumBlockRenderType.INVISIBLE;
	}
	
	@Override
	public void onEntityCollidedWithBlock(World worldIn, BlockPos pos, IBlockState state, Entity entityIn) {
		TileEntityDoor door = (TileEntityDoor) worldIn.getTileEntity(pos);
		if (door != null && !door.getLocked()) {
			if(entityIn.getHorizontalFacing().equals(state.getValue(FACING).getOpposite())) {
				if (entityIn instanceof EntityPlayer) {
					door.transferPlayerToTardis((EntityPlayer)entityIn);
				}
			}
		}
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
		return new AxisAlignedBB(0.1, 0, 0.1, 0.9, 0.9, 0.1);
	}

	@Override
	public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state) {
		super.onBlockAdded(worldIn, pos, state);
		try {
			((TileEntityDoor)worldIn.getTileEntity(pos)).fadeIn();
		}
		catch(Exception e) {}
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		return this.getDefaultState().withProperty(FACING, EnumFacing.getHorizontal(meta));
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		return state.getValue(FACING).getHorizontalIndex();
	}

	@Override
	public IBlockState getStateForPlacement(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY,float hitZ, int meta, EntityLivingBase placer) {
		return this.getDefaultState().withProperty(FACING, placer.getHorizontalFacing().getOpposite());
	}

	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this,new IProperty[] {FACING});
	}
	
}
