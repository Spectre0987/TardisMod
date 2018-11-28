package net.tardis.mod.common.blocks;

import net.minecraft.block.material.Material;
import net.minecraft.item.ItemBlock;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.tardis.mod.client.creativetabs.TardisTabs;
import net.tardis.mod.common.blocks.interfaces.INeedItem;
import net.tardis.mod.common.tileentity.TileEntityMultiblockMaster;

public class BlockMultiblockMaster extends BlockMultiblock implements INeedItem{

	public ItemMultiblock item = new ItemMultiblock(this);

	public BlockMultiblockMaster(Material material) {
		super(material);
		this.setCreativeTab(TardisTabs.BLOCKS);
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileEntityMultiblockMaster();
	}

	@Override
	public ItemBlock getItem() {
		return item;
	}

}
