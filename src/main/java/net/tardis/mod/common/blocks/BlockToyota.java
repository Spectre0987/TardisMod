package net.tardis.mod.common.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemBlock;
import net.tardis.mod.Tardis;

public class BlockToyota extends BlockBase {
	
	public ItemBlock item = new ItemBlock(this);

	public BlockToyota(boolean light) {
		this.setCreativeTab(Tardis.tab);
		this.item.setCreativeTab(Tardis.tab);
		if(light) {
			this.setLightLevel(1);
		}
	}

}
