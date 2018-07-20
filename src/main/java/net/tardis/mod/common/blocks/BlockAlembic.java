package net.tardis.mod.common.blocks;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumBlockRenderType;
import net.tardis.mod.Tardis;
import net.tardis.mod.common.tileentity.TileEntityAlembic;

public class BlockAlembic extends BlockTileBase {
	
	public ItemBlock item = new ItemBlock(this);
	
	public BlockAlembic() {
        super(Material.GLASS, TileEntityAlembic::new);
		item.setCreativeTab(Tardis.tab);
	}

    @Override
	public EnumBlockRenderType getRenderType(IBlockState state) {
		return EnumBlockRenderType.MODEL;
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
	public BlockRenderLayer getBlockLayer() {
		return BlockRenderLayer.TRANSLUCENT;
	}
}
