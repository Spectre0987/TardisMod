package net.tardis.mod.common.blocks;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.tardis.mod.Tardis;
import net.tardis.mod.common.tileentity.TileEntityFoodMachine;

public class BlockFoodMachine extends BlockContainer {

	public static final PropertyDirection FACING=PropertyDirection.create("facing", EnumFacing.Plane.HORIZONTAL);
	
	public BlockFoodMachine() {
		super(Material.ANVIL);
		this.setCreativeTab(Tardis.tab);
		this.setLightOpacity(0);
		this.setDefaultState(getDefaultState().withProperty(FACING, EnumFacing.NORTH));
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileEntityFoodMachine();
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
	public boolean isFullCube(IBlockState state) {
		return false;
	}

	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		if(!worldIn.isRemote) {
			EntityItem ei=new EntityItem(worldIn,pos.getX()+0.5,pos.getY()+1,pos.getZ()+0.5,new ItemStack(Items.BREAD));
			worldIn.spawnEntity(ei);
		}
		return true;
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		if(meta<3&&meta>0)
			return this.getDefaultState().withProperty(FACING, EnumFacing.getHorizontal(meta));
		return this.getDefaultState();
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		return state.getValue(FACING).getHorizontalIndex();
	}

	@Override
	public IBlockState getStateForPlacement(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY,float hitZ, int meta, EntityLivingBase placer) {
		if(!facing.equals(EnumFacing.DOWN)&&!facing.equals(EnumFacing.UP))
			return this.getDefaultState().withProperty(FACING, placer.getHorizontalFacing());
		return this.getDefaultState();
	}

	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] {FACING});
	}
	

}
