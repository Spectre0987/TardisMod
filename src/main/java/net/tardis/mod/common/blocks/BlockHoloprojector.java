package net.tardis.mod.common.blocks;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemBlock;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.tardis.mod.Tardis;

public class BlockHoloprojector extends BlockContainer {

	public ItemBlock item = new ItemBlock(this);
	
	public BlockHoloprojector() {
		super(Material.IRON);
		this.setCreativeTab(Tardis.tab);
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileEntityHoloprojector();
	}

}
