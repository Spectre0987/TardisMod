package net.tardis.mod.blocks;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
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
import net.tardis.mod.dimensions.TDimensions;
import net.tardis.mod.tileentity.TileEntityDoor;

public class TardisBlockTop extends BlockContainer implements IUnbreakable{

	public TardisBlockTop() {
		super(Material.WOOD,MapColor.BLUE);
		this.setBlockUnbreakable();
		this.setLightLevel(1F);
		this.setLightOpacity(0);
		this.setResistance(9999);
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileEntityDoor();
	}

	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn,EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		if(!worldIn.isRemote) {
			TileEntityDoor door=(TileEntityDoor)worldIn.getTileEntity(pos);
			if(playerIn.isSneaking()) {
				door.toggleLocked(playerIn);
			}
			else door.transferPlayerToTardis(playerIn);
		}
		return super.onBlockActivated(worldIn, pos, state, playerIn, hand, facing, hitX, hitY, hitZ);
	}

	@Override
	public boolean canCollideCheck(IBlockState state, boolean hitIfLiquid) {
		return true;
	}

	@Override
	public EnumBlockRenderType getRenderType(IBlockState state) {
		return EnumBlockRenderType.MODEL;
	}

	@Override
	public void onEntityCollidedWithBlock(World worldIn, BlockPos pos, IBlockState state, Entity entityIn) {
		System.out.println("Entity hit");
		TileEntityDoor door=(TileEntityDoor)worldIn.getTileEntity(pos);
		if(door!=null&&!door.isLocked) {
			if(!(entityIn instanceof EntityPlayer)) {
				entityIn.removePassengers();
				entityIn.setPosition(door.consolePos.getX(), door.consolePos.getY(), door.consolePos.getZ()-4);
				entityIn.changeDimension(TDimensions.id);
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
		return new AxisAlignedBB(0.1,0,0.1,0.9,0.9,0.1);
	}
	
}
