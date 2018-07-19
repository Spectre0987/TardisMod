package net.tardis.mod.common.blocks;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemBlock;
import net.tardis.mod.Tardis;
import net.tardis.mod.common.tileentity.TileEntityHoloprojector;

public class BlockHoloprojector extends BlockTileBase {

	public ItemBlock item = new ItemBlock(this);
	
	public BlockHoloprojector() {
		super(Material.GLASS, TileEntityHoloprojector::new);
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

}
