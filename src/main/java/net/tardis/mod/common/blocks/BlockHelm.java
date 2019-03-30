package net.tardis.mod.common.blocks;

import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.tardis.mod.client.creativetabs.TardisTabs;
import net.tardis.mod.client.worldshell.BlockStorage;
import net.tardis.mod.common.entities.EntityShip;

public class BlockHelm extends BlockHorizontal {
	
	AxisAlignedBB SHIP_BOX = new AxisAlignedBB(-10, -10, -10, 10, 10, 10);
	
	public BlockHelm() {
		super(Material.IRON);
		this.setCreativeTab(TardisTabs.BLOCKS);
	}

	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		if(!worldIn.isRemote) {
			EntityShip ship = new EntityShip(worldIn);
			for(int x = (int)SHIP_BOX.minX; x < SHIP_BOX.maxX; ++x) {
				for(int y = (int)SHIP_BOX.minY; y < SHIP_BOX.maxY; ++y) {
					for(int z = (int)SHIP_BOX.minZ; z < SHIP_BOX.maxZ; ++z) {
						BlockPos aPos = new BlockPos(x, y, z).add(pos);
						ship.addBlock(aPos.subtract(pos), new BlockStorage(worldIn.getBlockState(aPos), worldIn.getTileEntity(aPos), 15));
						worldIn.setBlockToAir(aPos);
					}
				}
			}
			ship.setPosition(pos.getX(), pos.getY(), pos.getZ());
			worldIn.spawnEntity(ship);
		}
		return true;
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		return this.getDefaultState().withProperty(FACING, EnumFacing.byHorizontalIndex(meta));
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		return state.getValue(FACING).getHorizontalIndex();
	}

	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, FACING);
	}

	@Override
	public IBlockState getStateForPlacement(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY,float hitZ, int meta, EntityLivingBase placer, EnumHand hand) {
		return this.getDefaultState().withProperty(FACING, placer.getHorizontalFacing());
	}

}
