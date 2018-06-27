package net.tardis.mod.common.blocks;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.tardis.mod.common.entities.controls.EntityControl;
import net.tardis.mod.common.tileentity.TileEntityTardis;
import net.tardis.mod.util.IUnbreakable;

public class BlockConsole extends BlockContainer implements IUnbreakable {
	
	public static final AxisAlignedBB BB = new AxisAlignedBB(-1, 0, -1, 2, 0.7, 2);
	public ItemBlock item = new ItemBlock(this);
	
	public BlockConsole() {
		super(Material.ANVIL);
		this.setBlockUnbreakable();
		item.setCreativeTab(null);
	}
	
	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileEntityTardis();
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
	public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state) {
		super.onBlockAdded(worldIn, pos, state);
		try {
			TileEntityTardis tardis = (TileEntityTardis) worldIn.getTileEntity(pos);
			tardis.createControls();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn,EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		if(!worldIn.isRemote) {
			if(playerIn.isSneaking()) {
				TileEntityTardis tardis = (TileEntityTardis)worldIn.getTileEntity(pos);
				if(tardis.controls != null) {
					for(EntityControl c : tardis.controls) {
						c.setDead();
					}
					tardis.controls = null;
				}
				tardis.createControls();
			}
		}
		return super.onBlockActivated(worldIn, pos, state, playerIn, hand, facing, hitX, hitY, hitZ);
	}
}
