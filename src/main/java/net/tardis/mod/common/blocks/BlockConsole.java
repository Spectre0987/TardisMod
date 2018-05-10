package net.tardis.mod.common.blocks;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.World;
import net.tardis.api.controls.IUnbreakable;
import net.tardis.mod.common.tileentity.TileEntityTardis;

public class BlockConsole extends BlockContainer implements IUnbreakable {
	
	public static final AxisAlignedBB BB = new AxisAlignedBB(-1, 0, -1, 2, 0.7, 2);
	
	public BlockConsole() {
		super(Material.ANVIL);
		this.setBlockUnbreakable();
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
}
