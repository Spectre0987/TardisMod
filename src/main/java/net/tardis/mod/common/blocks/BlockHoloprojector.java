package net.tardis.mod.common.blocks;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.EnumBlockRenderType;
import net.tardis.mod.common.tileentity.TileEntityHoloprojector;

public class BlockHoloprojector extends BlockTileBase {
	
	public ItemBlock item = new ItemBlock(this);
	
	public BlockHoloprojector() {
		super(Material.IRON, TileEntityHoloprojector::new);
		
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
		return EnumBlockRenderType.MODEL;
	}
	
}
