package net.tardis.mod.common.blocks;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.EnumBlockRenderType;
import net.tardis.mod.Tardis;
import net.tardis.mod.common.tileentity.TileEntityEPanel;

public class BlockEPanel extends BlockTileBase {
	
	public ItemBlock item = new ItemBlock(this);
	
	public BlockEPanel() {
        super(Material.IRON, TileEntityEPanel::new);
		this.setHardness(1F);
		this.setCreativeTab(Tardis.tab);
		item.setCreativeTab(Tardis.tab);
	}

    @Override
	public EnumBlockRenderType getRenderType(IBlockState state) {
		return EnumBlockRenderType.MODEL;
	}
	
}
