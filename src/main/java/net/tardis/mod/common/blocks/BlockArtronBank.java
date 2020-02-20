package net.tardis.mod.common.blocks;

import net.minecraft.block.material.Material;
import net.tardis.mod.common.tileentity.TileEntityArtronBank;

public class BlockArtronBank extends BlockTileBase {

	public BlockArtronBank() {
		super(Material.CIRCUITS, TileEntityArtronBank::new);
	}

}
