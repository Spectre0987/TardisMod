package net.tardis.mod.common.blocks;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.world.World;
import net.tardis.mod.Tardis;
import net.tardis.mod.common.tileentity.TileEntityEPanel;

public class BlockEPanel extends BlockContainer {

	public BlockEPanel() {
		super(Material.IRON);
		this.setCreativeTab(Tardis.tab);
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileEntityEPanel();
	}

	@Override
	public EnumBlockRenderType getRenderType(IBlockState state) {
		return EnumBlockRenderType.MODEL;
	}

}
