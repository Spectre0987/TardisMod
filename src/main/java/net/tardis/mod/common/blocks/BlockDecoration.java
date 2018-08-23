package net.tardis.mod.common.blocks;

import java.util.function.Supplier;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.tardis.mod.Tardis;
import net.tardis.mod.common.blocks.interfaces.IRenderBox;

public class BlockDecoration extends BlockTileBase implements IRenderBox{

	public BlockDecoration(Supplier<TileEntity> te) {
		super(Material.IRON, te);
		this.setCreativeTab(Tardis.tab);
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
	public EnumBlockRenderType getRenderType(IBlockState state) {
		return EnumBlockRenderType.INVISIBLE;
	}

	@Override
	public boolean shouldRenderBox() {
		return false;
	}

}
